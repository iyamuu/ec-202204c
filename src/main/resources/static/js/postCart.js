/**
 * カートに追加ボタンを押した際の処理
 */
function postCart(id) {
  let orderId = $("#orderId").val();
  let size = $('input:radio[name="size"]:checked').val();
  let quantity = $(".spinner").val();
  let toppingList = [];
  $("input[name=option]:checked").each(function () {
    toppingList.push($(this).val());
  });

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
    console.log(data);
  });
}
