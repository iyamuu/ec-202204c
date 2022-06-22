package com.example.ecommerce_c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.User;
import com.example.ecommerce_c.repository.OrderItemRepository;
import com.example.ecommerce_c.repository.OrderRepository;
import com.example.ecommerce_c.repository.UserRepository;

/**
 * ログイン処理を行うサービス.
 * 
 * @author hvthinh
 *
 */
@Service
@Transactional
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	/**
	 * ログイン処理を行う.
	 * 
	 * @param email    メールアドレス
	 * @param password パスワード
	 * @return ログインユーザ
	 */
	public User login(String email, String password) {

		return userRepository.findByMailAddressAndPassword(email, password);
	}

	/**
	 * ログイン前後のカートの中身を統合する.
	 * ・ゲストユーザで商品をカートに追加したあとにログインする。 -> ログイン後も、ゲストユーザのときに追加した商品がカートに入っている
	 * ・ゲストユーザで商品をカートに追加したあとにログインしたとき、登録済みユーザのカートにも商品が入っている。 -> ログイン後のカートに、もともと入っていた商品とゲストユーザで追加した商品の両方が入っている
	 * 
	 * @param loginUserId　ログイン後のユーザID
	 * @param guestUserId ログイン前のユーザID
	 */

	public void mergeOrder(int loginUserId, Integer guestUserId) {
//		ログイン画面に直リンクしたとき
		if(guestUserId == null) {
			return;
		}
//		登録済みユーザのカートとゲストユーザのカートを取得
		Integer loginUserOrderId = orderRepository.findOrderIdByUserId(loginUserId);
		Integer guestUserOrderId = orderRepository.findOrderIdByUserId(guestUserId);

		if (loginUserOrderId == null && guestUserOrderId == null) {
//			登録済みユーザのカートも、ゲストユーザのカートも無いとき
//			何も変更しない(TopControllerで新規作成してくれる)
		} else if (loginUserOrderId != null && guestUserOrderId == null) {
//			登録済みユーザのカートが有って、ゲストユーザのカートが無いとき
//			何も変更しない
		} else if (loginUserOrderId == null && guestUserOrderId != null) {
//			登録済みユーザのカートが無くて、ゲストユーザのカートが有るとき
//			ゲストユーザの注文情報にあるユーザIDを登録済みユーザのIDに変更
			Order order = orderRepository.findFullOrderById(guestUserOrderId);
			order.setUserId(loginUserId);
			orderRepository.update(order);
		} else if (loginUserOrderId != null && guestUserOrderId != null) {
//			登録済みユーザのカートも、ゲストユーザのカートも有るとき
//			ゲストユーザの注文商品のユーザIDを、登録済みユーザのユーザIDに変更
			Order order = orderRepository.findFullOrderById(guestUserOrderId);
			for (OrderItem orderItem : order.getOrderItemList()) {
				orderItem.setOrderId(loginUserOrderId);
				orderItemRepository.insertOne(orderItem);
				orderItemRepository.deleteOrderItem(orderItem.getId());
			}
		}
	}
}
