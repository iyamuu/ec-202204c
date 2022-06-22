/**
 *注文商品情報を削除する.
 */

function deleteOrderItem(id) {
  let hostUrl = "http://localhost:8080/ec-202204c/delete";
  
  $.ajax({
    url: hostUrl,
    type: "delete",
    dataType: "json",
    data: {
      orderItemId: id
    },
    async: true,
  }).done(function (data) {
    console.log(data);
  });
}
