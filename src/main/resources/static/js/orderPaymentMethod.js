
"use strict";

function orderPaymentByCredit(userId,orderId,calTotalPrice,cardNumber,cardExpYear,cardExpMonth,cardName,cardCvc){
	let hostUrl = "http://:8080/sample-credit-card-web-api/credit-card/payment";
	
	$.ajax({
		url: hostUrl,
		type: "post",
		dataType: "json",
		data: {
			user_id: userId,
			order_number: orderId,
			amount : calTotalPrice,
			card_number : cardNumber,
			card_exp_year: cardExpYear,
			card_exp_month: cardExpMonth,
			card_name: cardName,
			card_cvc : cardCvc,
		},
		async: true,
	})
	.done(function(data) {
		console.log(data);
	})
	.fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
      });
}