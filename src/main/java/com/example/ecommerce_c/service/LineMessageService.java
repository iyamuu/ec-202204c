package com.example.ecommerce_c.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.common.StateEnum;
import com.example.ecommerce_c.domain.Order;
import com.example.ecommerce_c.domain.OrderItem;
import com.example.ecommerce_c.domain.OrderTopping;
import com.example.ecommerce_c.repository.OrderRepository;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.container.Bubble.BubbleSize;
import com.linecorp.bot.model.message.flex.container.Carousel;
import com.linecorp.bot.model.message.flex.unit.FlexAlignItems;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexJustifyContent;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;

/**
 * 
 * Lineでメッセージ送信処理を行う Service,
 * 
 * @author teranishidaina
 *
 */
@Service
public class LineMessageService {
	
	private final String IMG_URL = "https://ec-202204c-toy.herokuapp.com/ec-202204c/img/";

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 注文完了メッセージを生成する.
	 * 
	 * @param orderId 購入が完了した注文ID
	 * @return
	 */
	public Carousel getCompleteMessage(Integer orderId) {

		Order order = orderRepository.findById(orderId);

		
		
		
		List<Bubble> orderItemMessage = new ArrayList<>();
		for (OrderItem orderitem : order.getOrderItemList()) {
			Bubble orderitemBubble = buildOrderItemBubble(orderitem);
			orderItemMessage.add(orderitemBubble);
		}

		Carousel complateMessage = Carousel.builder().contents(orderItemMessage).build();

		return complateMessage;
	}
	
	
	/**
	 * 完了画面の1ページ目を生成する.
	 * 
	 * @param order 注文
	 * @return 完了画面
	 */
	private Bubble buildOrderCompleteBubble(Order order) {
		
		
		return null;
	}
	
	/**
	 * 1ページ目のHeaderを生成する.
	 * 
	 * @param name ユーザ名
	 * @return header
	 */
	private Box buildOrderCompleteHeader(String name) {
		
		Text nameText = Text.builder().size(FlexFontSize.LG).color("#ffffff").text(name).build();
		Text thanksText = Text.builder().size(FlexFontSize.LG).color("#ffffff").text("ご注文ありがとうございます").build();
		
		Box orderCompleteBox = Box.builder().layout(FlexLayout.VERTICAL).backgroundColor("#ff8c00")
				.justifyContent(FlexJustifyContent.CENTER).alignItems(FlexAlignItems.CENTER).contents(Arrays.asList(nameText, thanksText))
				.build();
		
		return orderCompleteBox;
	}
	
	/**
	 * ロゴのHeroを生成する.
	 * 
	 * @return
	 */
	private Image buildLogoHero() {
		URI imageUri;
		Image itemImage;
		try {
			imageUri = new URI(IMG_URL + "header_logo.png");
			itemImage = Image.builder().size(ImageSize.XXXXL).url(imageUri).build();
			return itemImage;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private Box buildOrderCompleteBox(Order order) {
		
		//注文状態
		StateEnum state = StateEnum.findState(order.getStatus());
		Box stateBox = buildDetailBox("注文状態", state.getDescription());
		
		//請求金額
		String price = "¥" + order.getCalcTotalPrice() + "(税込)";
		Box priceBox = buildDetailBox("ご請求金額", price);
		
		//名前
		String name = order.getUser().getName() + " 様";
		Box nameBox = buildDetailBox("宛先氏名", name);
		
		//住所
		Box addressBox = buildDetailBox("宛先住所", order.getDestinationAddress());
		
		//支払い方法
		String payment;
		if(order.getPaymentMethod() == 0) {
			payment = "代金支払い";
		}else {
			payment = "クレジットカード";
		}
		Box paymentBox = buildDetailBox("支払い方法", payment);
		
		//配送日時　これはメールおやつをpullしてから
	}
	

	/**
	 * 注文商品の説明文を生成する.
	 * 
	 * @param orderitem
	 * @return
	 */
	private Bubble buildOrderItemBubble(OrderItem orderitem) {

		Box headerBox = buildOrderItemHeaderBox();
		Image heroBox = buildOrderItemHeroBox(orderitem.getItem().getImagePath());
		Box bodyBox = buildOrderItemBodyBox(orderitem);
		Box footerBox = buildFooterBox();
		
		Bubble orderItemBubble = Bubble.builder().header(headerBox).hero(heroBox).body(bodyBox).footer(footerBox).size(BubbleSize.MEGA).build();
		
		return orderItemBubble;
	}

	/**
	 * 注文商品のHeaderを生成する.
	 * 
	 * @return
	 */
	private Box buildOrderItemHeaderBox() {

		Text headerText = Text.builder().size(FlexFontSize.LG).color("#ffffff").text("ご注文商品").build();
		Box orderItemBox = Box.builder().layout(FlexLayout.VERTICAL).backgroundColor("#ff8c00")
				.justifyContent(FlexJustifyContent.CENTER).alignItems(FlexAlignItems.CENTER).content(headerText)
				.build();

		return orderItemBox;

	}
	
	/**
	 * 商品詳細のHEROで用いる画像を生成する.
	 * 
	 * @param imgpath
	 * @return
	 */
	private Image buildOrderItemHeroBox(String imgpath) {
		URI imageUri;
		Image itemImage;
		try {
			imageUri = new URI(IMG_URL + imgpath);
			itemImage = Image.builder().size(ImageSize.XXXXL).url(imageUri).build();
			return itemImage;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 注文商品の詳細情報を生成する.
	 * 
	 * @param orderItem
	 * @return
	 */
	private Box buildOrderItemBodyBox(OrderItem orderItem) {
		
		Box nameBox = buildDetailBox("商品名", orderItem.getItem().getName());
		Box quantityBox = buildDetailBox("数量", orderItem.getQuantity() + "個");
		Box syoukeiBox = buildDetailBox("小計", Integer.valueOf(orderItem.getSubTotal()).toString());
		
		String toppingListString = "";
		for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
			toppingListString += orderTopping.getTopping().getName() + ", ";
		}
		Box toppingBox = buildDetailBox("オプション", toppingListString);
		
		Box orderItemBodyBox = Box.builder().layout(FlexLayout.VERTICAL).contents(Arrays.asList(nameBox, quantityBox, toppingBox, syoukeiBox)).build();
		return orderItemBodyBox;
	}

	/**
	 * 詳細の説明文を生成する.
	 * 
	 * @param title       タイトル
	 * @param description 説明
	 * @return
	 */
	private Box buildDetailBox(String title, String description) {

		Text titleText = Text.builder().flex(1).text(title).build();
		Text descriptionText = Text.builder().flex(2).text(description).wrap(true).build();

		Box detailBox = Box.builder().layout(FlexLayout.HORIZONTAL).contents(Arrays.asList(titleText, descriptionText))
				.build();

		return detailBox;
	}
	
	/**
	 * フッターのBoxを生成する.
	 * 
	 * @return
	 */
	private Box buildFooterBox() {
		
		Text footerText = Text.builder().size(FlexFontSize.Md).color("#ffffff").text("またのご利用をお待ちしております").build();
		Box footerBox = Box.builder().layout(FlexLayout.VERTICAL).backgroundColor("#ff8c00")
						.justifyContent(FlexJustifyContent.CENTER).alignItems(FlexAlignItems.CENTER).content(footerText).build();
		
		return footerBox;
	}
}
