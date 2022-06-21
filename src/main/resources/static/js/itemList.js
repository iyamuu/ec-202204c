/**
 * アイテムリストを作成する
 */
$(function () {
  //ページが読み込まれた際の処理
  getInitialItemList();
  //検索をかけられた際の処理
  $("#search").click(function () {
    let name = $(".search-name-input").val();
    getItemByName(name);
  });
});

function getInitialItemList() {
  let hostUrl = "http://localhost:8080/ec-202204c/getItemByPage";

  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: {
      from: 0,
      to: 10,
    },
    async: true,
  }).done(function (data) {
    console.log(data);
    console.dir(JSON.stringify(data));
    $("#itemList").empty();
    data.forEach((item) => genarateItemCell(item));
  });
}

function genarateItemCell(item) {
  $("#itemList").append(`
         <div class="mb-2 row d-flex">
            <input type="hidden" value="${item.id}" />
            <div class="item-icon col-6">
              <img src="/ec-202204c/img/${item.imagePath}"/>
            </div>
            <div class="col-6">
              <div class="left">
                <a data-bs-toggle="modal" data-bs-target="#item_${item.id}"
                >${item.name}</a
                >
              </div>
              <br />
              <div class="left">
                <span>${item.priceM}</span>円（税抜）
              </div>
              <br />
              <a
                class="waves-effect waves-light btn-large orange cart_button add_cart_${item.id}"
                style="z-index:0"
                onclick="postCart(${item.id}, ${item.priceM}, 1, [])"
              >
                <i class="material-icons left">add_shopping_cart</i>
                <span>カートに追加</span></a
              >
            </div>
          </div>
          <!--モーダル-->
          <div class="modal fade" id="item-${item.id}" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="ModalLabel-${item.id}">${item.name}</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ${item.description}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <a
                    class="waves-effect waves-light btn-large orange position-absolute bottom-0 end-0"
                    >
                    <i class="material-icons left">add_shopping_cart</i>
                    <span id=class="cart_button add_cart-${item.id}">カートに追加</span></a
                     >
                </div>
              </div>
            </div>
          </div>
          <hr />
    `);
}
