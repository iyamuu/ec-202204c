package com.example.ecommerce_c.mail;

import java.io.StringWriter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.ecommerce_c.domain.Order;

/**
 * メールの送信を扱うサービス.
 * 
 * @author ryuya.sasagawa
 *
 */
@Service
public class MailService {
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private VelocityEngine velocity;
	
	private String mailBodyTemplate;
	private String subject;

	/**
	 * メールを送信する.
	 * 
	 * @param order 注文
	 */
	@Async
	public void sendMail(Order order) {
//		使用するテンプレートとタイトルを決める
		setTemplateAndSubject(order.getStatus());
		
		MimeMessage message = sender.createMimeMessage();
		try {
			// 送信情報設定
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("xxxxx@xxx.xx");
//			helper.setTo("自分のメールアドレス"); XXX: debug
			helper.setTo(order.getDestinationEmail());
			helper.setSubject(subject);
			
			// パラメーター定義
			VelocityContext context = new VelocityContext();
			context.put("order", order);

			// テンプレートの読み込み
			StringWriter writer = new StringWriter();
			velocity.mergeTemplate(mailBodyTemplate, "UTF-8", context, writer);
			// 本文設定
			helper.setText(writer.toString());
			
			// メール送信
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用するテンプレートとタイトルを決定する.
	 * 
	 * @param status 注文ステータス
	 * @return タイトル
	 */
	private void setTemplateAndSubject(Integer status) {
		switch (status) {
		case 0: //XXX: debug
		case 1:
			subject = "ご注文の確認";
			mailBodyTemplate = "static/mail/purchase.vm";
			break;
		case 2:
			subject = "入金確認のお知らせ";
			mailBodyTemplate = "static/mail/payment.vm";
			break;
		case 3:
			subject = "商品を発送しました";
			mailBodyTemplate = "static/mail/send.vm";
			break;
		case 4:
			subject = "配達が完了しました";
			mailBodyTemplate = "static/mail/finish.vm";
			break;
		case 9:
			subject = "注文がキャンセルされました";
			mailBodyTemplate = "static/mail/cancel.vm";
			break;
		}
	}

}
