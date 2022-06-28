/**
 * カートに追加ボタンを押した際の処理
 */

function postCart(id) {
  //各要素の取得
  let orderId = $("#orderId").val();
  let size = $("#modal" + id + " input:radio[name='size']:checked").val();
  let quantity = $("#modal" + id + " .spinner").val();
  let toppingList = [];
  $("#modal" + id + " input[name=option]:checked").each(function () {
    toppingList.push($(this).val());
  });
  //各要素の初期化
  if (size === "L") {
    $("#modal" + id + " input:radio[name='size']:eq(0)").prop("checked", true);
  }
  $("#modal" + id + " .spinner").val(1);
  $("#modal" + id + " .spinner-sub").addClass("disabled");
  $("#modal" + id + " input[name=option]").prop("checked", false);

  let xsrf = $.cookie("XSRF-TOKEN");
  let hostUrl = `${serverURL}add`;
  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: JSON.stringify({
      orderId: orderId,
      itemId: id,
      size: size,
      quantity: quantity,
      toppingIdList: toppingList,
    }),
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "X-XSRF-TOKEN": xsrf,
    },
    async: true,
  }).done(function (data) {});
}
