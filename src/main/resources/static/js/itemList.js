/**
 * アイテムリストを作成する
 */

$(function () {
  //ページが読み込まれた際の処理
  getInitialItemList();
  //materializeのモーダルの初期化
   
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
         <div class="row d-flex">
            <input type="hidden" value="${item.id}" />
            <a class="item-icon col s4 modal-trigger" href="#modal${item.id}" onclick="$('#modal${item.id}').modal()">
              <img src="/ec-202204c/img/${item.imagePath}"/>
            </a>
            <div class="col s8">
              <h3 class="col s12">
                <a href="#modal${item.id}" class="modal-trigger flow-text left left-align" onclick="$('#modal${item.id}').modal()"
                >${item.name}</a
                >
              </h3>
              <div class="col s12" style="margin-bottom:10%">
                <span class="flow-text left">\ ${item.priceM}円（税抜)</span>
              </div>
              <div>
              <a
                class="right waves-effect waves-light btn-large orange cart_button add_cart_${item.id}"
                style="z-index:0"
                onclick="postCart(${item.id}, 'M', 1, [])"
              >
                <i class="material-icons left">add_shopping_cart</i>
                <span>カートに追加</span></a
              >
              </div>
            </div>
          </div>
          <!--モーダル-->
          <div class="modal" id="modal${item.id}">
            <div class="modal-content">
               <h3 class="modal-title">${item.name}</h3>
           	   <div class="item-icon">
                  <img src="/ec-202204c/img/${item.imagePath}"/>
               </div>
               <div>
               <h5 class="description orange lighten-4">商品説明</h5>
               <p class="left-align">${item.description}</p>
               </div>
               <div>
	               <h5 class="options orange lighten-4">オプション</h5>
	               <form>
	               		<div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[0].name}M ${item.toppingList[0].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                      <input type="checkbox" class="filled-in" />
	                      <span>${item.toppingList[0].name}L ${item.toppingList[0].priceL}円 </span>
	                      </label>
	                    </div>
	                    
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[1].name}M ${item.toppingList[1].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                      <input type="checkbox" class="filled-in" />
	                      <span>${item.toppingList[1].name}L ${item.toppingList[1].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[2].name}M ${item.toppingList[2].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                      <input type="checkbox" class="filled-in" />
	                      <span>${item.toppingList[2].name}L ${item.toppingList[2].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[3].name}M ${item.toppingList[3].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[3].name}L ${item.toppingList[3].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[4].name}M ${item.toppingList[4].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[4].name}L ${item.toppingList[4].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[5].name}M ${item.toppingList[5].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[5].name}L ${item.toppingList[5].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[6].name}M ${item.toppingList[6].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[6].name}L ${item.toppingList[6].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[7].name}M ${item.toppingList[7].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[7].name}L ${item.toppingList[7].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[8].name}M ${item.toppingList[8].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[8].name}L ${item.toppingList[8].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[9].name}M ${item.toppingList[9].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[9].name}L ${item.toppingList[9].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[10].name}M ${item.toppingList[10].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[10].name}L ${item.toppingList[10].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[11].name}M ${item.toppingList[11].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[11].name}L ${item.toppingList[11].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[12].name}M ${item.toppingList[12].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[12].name}L ${item.toppingList[12].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[13].name}M ${item.toppingList[13].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[13].name}L ${item.toppingList[13].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[14].name}M ${item.toppingList[14].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[14].name}L ${item.toppingList[14].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[15].name}M ${item.toppingList[15].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[15].name}L ${item.toppingList[15].priceL}円 </span>
	                      </label>
	                    </div>
	                    
	                    <div class="row">  
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[16].name}M ${item.toppingList[16].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[16].name}L ${item.toppingList[16].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[17].name}M ${item.toppingList[17].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[17].name}L ${item.toppingList[17].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[18].name}M ${item.toppingList[18].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[18].name}L ${item.toppingList[18].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[19].name}M ${item.toppingList[19].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[19].name}L ${item.toppingList[19].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                    <div class="row">
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[20].name}M ${item.toppingList[20].priceM}円 </span>
	                      </label>
	                      <label class="col s6">
	                         <input type="checkbox" class="filled-in" />
	                         <span>${item.toppingList[20].name}L ${item.toppingList[20].priceL}円 </span>
	                      </label>
	                    </div>
	                      
	                  </form>
                  </div>
               <div class="modal-footer">
                  <a
                  class="waves-effect waves-light btn-large orange modal-action modal-close right" href="#!"
                  >
                  <i class="material-icons left">add_shopping_cart</i>
                  <span id=class="cart_button add_cart_${item.id}">カートに追加</span></a
                  >
               </div>
            </div>
          </div>
          <hr />
    `);
}
