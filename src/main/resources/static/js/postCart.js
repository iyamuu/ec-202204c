/**
 * カートに追加ボタンを押した際の処理
 */
function postCart(id, size, count, topping) {
  console.log("postcart");
  console.log(id);
  console.log(size);
  console.log(count);
  console.log(topping);
  let hostUrl = "http://localhost:8080/ec-202204c/add";

  $.ajax({
    url: "post",
    dataType: "json",
    data: {},
  });
}
