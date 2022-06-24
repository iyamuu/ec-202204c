/**
 * アイテムリストを作成する
 */

$(function () {
  //ページが読み込まれた際の処理
  getInitialItemList();
});

$(".search-btn").click(function () {
  let name = $("#search-name-input").val();
  getItemByName(name);
});

$("#search-name-input").keypress(function (e) {
  if (e.which == 13) {
    $(".search-btn").click();
    return false;
  }
});

//検索
function getItemByName(name) {
  let xsrf = $.cookie("XSRF-TOKEN");

  let hostUrl = `${serverURL}getItemByPage`;
  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "X-XSRF-TOKEN": xsrf,
    },
    data: JSON.stringify({
      from: 0,
      to: 10,
      name: name,
    }),
    async: true,
  }).done(function (data) {
    $("#itemList").empty();
    data.forEach((item) => genarateItemCell(item));
  });
}

//初期表示
function getInitialItemList() {
  let xsrf = $.cookie("XSRF-TOKEN");

  let hostUrl = `${serverURL}getItemByPage`;

  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "X-XSRF-TOKEN": xsrf,
    },
    data: JSON.stringify({
      from: 0,
      to: 10,
    }),
    async: true,
  })
    .done(function (data) {
      $("#itemList").empty();
      data.forEach((item) => genarateItemCell(item));
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
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
                <span class="flow-text left">¥ ${item.priceM}円（税抜)</span>
              </div>
              <div>
              <a
                class="right waves-effect waves-light btn-large amber accent-3 cart_button add_cart_${item.id}"
                style="z-index:0"
                onclick="postCart(${item.id})"
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
			   	   	<h5 class="size orange lighten-4">サイズ選択</h5>
			   		<form style="margin-bottom:3rem">
						<label class="col s6">
							<input name="size" type="radio" value="M" checked />
							<span>M ¥ ${item.priceM}円（税抜）</span>
						</label>
						<label class="col s6">
							<input name="size" type="radio" value="L" />
							<span>L ¥ ${item.priceL}円（税抜）</span>
						</label>
					</form>
				</div>
				<div>
					<h5 class="size orange lighten-4">個数選択</h5>
					<div class="spinner-container">
						<span class="spinner-sub disabled col s4" onclick="spinnerMinus(${item.id})"><i class="material-icons">remove_circle_outline</i></span>
						<input class="spinner col s4" min="1" value="1" type="text" readonly></input>
						<span class="spinner-add col s4" onclick="spinnerPlus(${item.id})"><i class="material-icons">add_circle</i></span>
					</div>
				</div>
               <div>
	               <h5 class="options orange lighten-4">オプション選択</h5>
	           </div>
	           <div>
	               <form style="height:300px">
	                    <label class="col s6">
	                        <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[0].id}"/>
	                        <span>${item.toppingList[0].name}</span>
	                    </label>
	                    <label class="col s6">
	                        <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[1].id}" />
	                        <span>${item.toppingList[1].name}</span>
	                    </label>
	                	<label class="col s6">
	                        <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[2].id}"/>
	                        <span>${item.toppingList[2].name}</span>
	                    </label>
	                    <label class="col s6">
	                      <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[3].id}" />
	                      <span>${item.toppingList[3].name}</span>
	                    </label>
						<label class="col s6">
						  <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[4].id}"/>
						  <span>${item.toppingList[4].name}</span>
					  	</label>
					   	<label class="col s6">
						  <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[5].id}" />
						  <span>${item.toppingList[5].name}</span>
					   	</label>
					   	<label class="col s6">
						  <input type="checkbox" name="option" class="filled-in" value="${item.toppingList[6].id}"/>
						  <span>${item.toppingList[6].name}</span>
					   	</label>
					   	<label class="col s6">
					   		<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[7].id}" />
					   		<span>${item.toppingList[7].name}</span>
					    </label>
					   	<label class="col s6">
					   		<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[8].id}"/>
					   		<span>${item.toppingList[8].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[9].id}" />
							<span>${item.toppingList[9].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[10].id}"/>
							<span>${item.toppingList[10].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[11].id}" />
							<span>${item.toppingList[11].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[12].id}" />
							<span>${item.toppingList[12].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[13].id}"/>
							<span>${item.toppingList[13].name}</span>
						</label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[14].id}" />
							<span>${item.toppingList[14].name}</span>
					    </label>
						<label class="col s6">
							<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[15].id}"/>
							<span>${item.toppingList[15].name}</span>
					    </label>
					    <label class="col s6">
						  	<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[16].id}" />
						  	<span>${item.toppingList[16].name}</span>
					    </label>
					    <label class="col s6">
						  	<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[17].id}"/>
						  	<span>${item.toppingList[17].name}</span>
					    </label>
					    <label class="col s6">
						  	<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[18].id}" />
						  	<span>${item.toppingList[18].name}</span>
					    </label>  
					    <label class="col s6">
					  		<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[19].id}"/>
					  		<span>${item.toppingList[19].name}</span>
				  		</label>
				  		<label class="col s6">
					  		<input type="checkbox" name="option" class="filled-in" value="${item.toppingList[20].id}" />
					  		<span>${item.toppingList[20].name}</span>
				  		</label>  
	                  </form>
                </div>
                <div style="height:3rem">
	                <a
	                class="right waves-effect waves-light btn-large amber accent-3 cart_button add_cart_${item.id}"
	                style="z-index:0"
	                onclick="postCart(${item.id})"
	              	>
	                <i class="material-icons left">add_shopping_cart</i>
	                <span>カートに追加</span></a
	              	>
	            </div>
            </div>
          </div>
          <hr />
    `);
}
