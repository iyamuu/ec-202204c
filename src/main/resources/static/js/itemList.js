/**
 * アイテムリストを作成する
 */
$(function () {
  //ページが読み込まれた際の処理
  getInitialItemList();
  //materializeのモーダルの初期化
  $(".modal").modal();
});

$(".search-btn").click(function () {
  let name = $(".search-name-input").val();
  getItemByName(name);
});

function getItemByName(name) {
  let hostUrl = "http://localhost:8080/ec-202204c/getItemByPage";
  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: {
      from: 0,
      to: 10,
      name: name,
    },
    async: true,
  }).done(function (data) {
    $("#itemList").empty();
    data.forEach((item) => genarateItemCell(item));
  });
}

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
    $("#itemList").empty();
    data.forEach((item) => genarateItemCell(item));
  });
}

function genarateItemCell(item) {
  $("#itemList").append(`
         <div class="mb-2 row d-flex">
            <input type="hidden" value="${item.id}" />
            <div class="item-icon col-6" data-bs-toggle="modal" data-bs-target="#item_${item.id}">
              <img src="/ec-202204c/img/${item.imagePath}"/>
            </div>
            <div class="col-6">
              <div class="left">
                <a href="#item_${item.id}"
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
                onclick="postCart(${item.id}, 'M', 1, [])"
              >
                <i class="material-icons left">add_shopping_cart</i>
                <span>カートに追加</span></a
              >
            </div>
          </div>
          <!--モーダル-->
          <div class="modal fade" id="item_${item.id}">
            <div class="modal-dialog modal-dialog-scrollable modal-lg">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="ModalLabel_${item.id}">${item.name}</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div class="item-icon mb-5">
                  <img src="/ec-202204c/img/${item.imagePath}"/>
                  </div>
                  <div class="mb-2">
                    ${item.description}
                  </div>
                  <div class="modal-option">
                  <h5 class="options">オプション</h5>
                  <form name="options">
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[0].name}M ${item.toppingList[0].priceM}円 </span>
                      </label>
                      <label class="m-1">
                      <input type="checkbox" class="filled-in" />
                      <span>${item.toppingList[0].name}L ${item.toppingList[0].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[1].name}M ${item.toppingList[1].priceM}円 </span>
                      </label>
                      <label class="m-1">
                      <input type="checkbox" class="filled-in" />
                      <span>${item.toppingList[1].name}L ${item.toppingList[1].priceL}円 </span>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[2].name}M ${item.toppingList[2].priceM}円 </span>
                      </label>
                      <label class="m-1">
                      <input type="checkbox" class="filled-in" />
                      <span>${item.toppingList[2].name}L ${item.toppingList[2].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[3].name}M ${item.toppingList[3].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[3].name}L ${item.toppingList[3].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[4].name}M ${item.toppingList[4].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[4].name}L ${item.toppingList[4].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[5].name}M ${item.toppingList[5].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[5].name}L ${item.toppingList[5].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[6].name}M ${item.toppingList[6].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[6].name}L ${item.toppingList[6].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[7].name}M ${item.toppingList[7].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[7].name}L ${item.toppingList[7].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[8].name}M ${item.toppingList[8].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[8].name}L ${item.toppingList[8].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[9].name}M ${item.toppingList[9].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[9].name}L ${item.toppingList[9].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[10].name}M ${item.toppingList[10].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[10].name}L ${item.toppingList[10].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[11].name}M ${item.toppingList[11].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[11].name}L ${item.toppingList[11].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[12].name}M ${item.toppingList[12].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[12].name}L ${item.toppingList[12].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[13].name}M ${item.toppingList[13].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[13].name}L ${item.toppingList[13].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[14].name}M ${item.toppingList[14].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[14].name}L ${item.toppingList[14].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[15].name}M ${item.toppingList[15].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[15].name}L ${item.toppingList[15].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[16].name}M ${item.toppingList[16].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[16].name}L ${item.toppingList[16].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[17].name}M ${item.toppingList[17].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[17].name}L ${item.toppingList[17].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[18].name}M ${item.toppingList[18].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[18].name}L ${item.toppingList[18].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[19].name}M ${item.toppingList[19].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[19].name}L ${item.toppingList[19].priceL}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[20].name}M ${item.toppingList[20].priceM}円 </span>
                      </label>
                      <label class="m-1">
                         <input type="checkbox" class="filled-in" />
                         <span>${item.toppingList[20].name}L ${item.toppingList[20].priceL}円 </span>
                      </label>
                  </form>
                  </div>    
                </div>
           
                <div class="modal-footer">
                    <a
                    class="waves-effect waves-light btn-large orange mr-3 mt-1"
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
