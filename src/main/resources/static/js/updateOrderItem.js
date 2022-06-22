/**
 * カートに注文商品情報を更新する.
 */
 
 function updateOrderItem(id, quantity){
	let hostUrl = "http://localhost:8080/ec-202204c/update";
	console.log(id);
	console.log(quantity);
	
	$.ajax({
		url: hostUrl,
		type: "post",
		dataType: "json",
		data: {
			orderItemId: id,
			quantity: quantity,
		},
		async: true,
	}).done(function(data){
		console.log(data);
	})
}