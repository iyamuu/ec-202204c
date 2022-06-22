/**
 * カートに追加ボタンを押した際の処理
 */
function postCart(id, size, quantity, topping) {
  let orderId = $("#orderId").val();
  let hostUrl = "http://localhost:8080/ec-202204c/add";

  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: {
      orderId: orderId,
      itemId: id,
      size: size,
      quantity: quantity,
      toppingIdList: [],
    },
    async: true,
  }).done(function (data) {
  });
}
