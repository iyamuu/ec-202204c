"use strict";


$(function () {
  $("#orderId").hide();
  showOrderItemList();
});

//注文一覧の表示  合計金額の変更も行う
let showOrderItemList = function () {
  let orderId = $("#orderId").val();
  let showUrl = serverURL + "show?orderId=" + orderId;

  $.ajax({
    url: showUrl,
    type: "get",
    dataType: "json",
    async: true,
  })
    .done(function (data) {
      console.log(JSON.stringify(data));
	  
      //テーブルの更新
      let orderItemTable = $("#orderItemTable");
      orderItemTable.empty();
      data.orderItemList.forEach((orderItem) => {
        let row = buildOrderItemRow(orderItem);
        orderItemTable.append(row);
        
        var leftPrice = $(".left-price-" + orderItem.item.id).html();
  		var rightPrice = $(".right-price-"+ orderItem.item.id).html();
  
  		rightPrice= String(rightPrice).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
  		leftPrice = String(leftPrice).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
  		$(".left-price-" + orderItem.item.id).html(leftPrice);
  		$(".right-price-" + orderItem.item.id).html(rightPrice);
      });
      
      if (orderItemTable.html() === ``) {
		$('#noItemMessage').css("display","");
		$('#stepParchase').css("display","none");
	  }else {
		$('#noItemMessage').css("display","none");
		$('#stepParchase').css("display","");
	  }
      let totalPrice = data.tax + data.calcTotalPrice;

      //消費税の更新
      $("#tax").html(`消費税：¥<span class="tax-price">${data.tax}</span>`);
	  
	  var taxPrice = $(".tax-price").html();
	  taxPrice = String(taxPrice).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
	  $(".tax-price").html(taxPrice);
      //合計金額の更新
      $("#totalPrice").html(`ご注文金額合計：¥<span class="sum-price">${totalPrice}</span> (税込)`);
      var sumPrice = $(".sum-price").html();
	  sumPrice = String(sumPrice).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
	  $(".sum-price").html(sumPrice);
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
    });
};

let buildOrderItemRow = function (orderItem) {
  let row = $("<tr>");
  let imgAndNameCell = buildItemImgAndNameCell(
    orderItem.item.imagePath,
    orderItem.item.name
  );

  let size = orderItem.size;
  let priceAndQuantityCell = buildPriceAndQuantityCell(
    size === "M" ? orderItem.item.priceM : orderItem.item.priceL,
    orderItem.quantity,
    orderItem.id
  );
  let toppingCell = buildToppingCell(orderItem.orderToppingList, size);
  let subTotalCell = buildSubTotalCell(orderItem.id, orderItem.subTotal);
  let deleeButtonCell = buildDeleeButtonCell(orderItem.id);

  row.append(imgAndNameCell);
  row.append(priceAndQuantityCell);
  row.append(toppingCell);
  row.append(subTotalCell);
  row.append(deleeButtonCell);
  return row;
};

let buildItemImgAndNameCell = function (imgpath, itemName) {
  let cell = `<td class="cart-item-name">
                    <div class="cart-item-icon">
                        <img src="/ec-202204c/img/${imgpath}"/>
                    </div>
                    <span>${itemName}</span>
                </td>`;

  return cell;
};

let buildPriceAndQuantityCell = function (price, quantity, orderItemId) {
	let status;
  if (quantity === 1) {
	status = "disabled";	
  }
  
  let cell = `<td>
  					<span class="col s12">¥
                    <span class=" left-price-${orderItemId}">
                        ${price}
                    </span>
                    </span>
                    <br />
                    <span >
                        <i class="col s2 material-icons ${status} cart-plus"  id="item${orderItemId}QuantityMinus" onclick="subOrderItemQuantity(${orderItemId})" >remove_circle_outline</i>
                        <label class="col s1" min="1" id="item${orderItemId}Quantity"> ${quantity} </label>
                        <i class="col s2 material-icons cart-minus" onclick='addOrderItemQuantity(${orderItemId})'>add_circle</i>
                    </span>
                </td>`;
                
  
  return cell;
};

let buildToppingCell = function (orderToppingList, size) {
  let cell = $("<td>");
  let toppingUl = $("<ul>");

  orderToppingList.forEach((orderTopping) => {
    toppingUl.append(`
        <li>
            <span>
                ${orderTopping.topping.name}
            </span>
            <span>
               ¥ ${
                  size === "M"
                    ? orderTopping.topping.priceM
                    : orderTopping.topping.priceL
                }
            </span>
        </li>
        `);
  });

  cell.append(toppingUl);

  return cell;
};

let buildSubTotalCell = function (id, subtotal) {
  let cell = `<td>
                    <span>
                       ¥<span class="right-price-${id}"> ${subtotal}</span>
                    </span>
                </td>`;

  return cell;
};

let buildDeleeButtonCell = function (orderItemId) {
  let cell = `<td>
                    <button
                    class="btn deep-orange accent-3"
                    type="button"
                    onclick="deleteOrderItem(${orderItemId});">
                    <span><i class="material-icons left">delete</i>削除</span>
                    </button>
                </td>`;

  return cell;
};

/**
 *注文商品情報を削除する.
 */

function deleteOrderItem(id) {
  let hostUrl = `${serverURL}delete?orderItemId=${id}`;

  console.log(hostUrl);
  $.ajax({
    url: hostUrl,
    type: "get",
    dataType: "json",
    async: true,
  })
    .done(function (data) {
      console.log(data);
      showOrderItemList(); //削除したのでテーブルを更新
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
    });
}

/**
 * カートに注文商品情報を更新する.
 */

function updateOrderItem(id, quantity) {
  let xsrf = $.cookie("XSRF-TOKEN");
  let hostUrl = `${serverURL}update`;

  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: JSON.stringify({
      orderItemId: id,
      quantity: quantity,
    }),
    headers: {
      "Content-Type": "application/json; charset=utf-8",
      "X-XSRF-TOKEN": xsrf,
    },
    async: true,
  })
    .done(function (data) {
      showOrderItemList();
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
    });
}

function addOrderItemQuantity(orderItemId) {
  let quantitySpan = $(`#item${orderItemId}Quantity`);
  let quantity = quantitySpan.text();
  $(`item${orderItemId}QuantityMinus`).removeClass("disabled");
  updateOrderItem(orderItemId, Number(quantity) + 1);
}

function subOrderItemQuantity(orderItemId) {
  let quantitySpan = $(`#item${orderItemId}Quantity`);
  let quantity = quantitySpan.text();
  if (Number(quantity) !== 1) {
  updateOrderItem(orderItemId, Number(quantity) - 1);
  }else {
	$(`item${orderItemId}QuantityMinus`).addClass("disabled");
}
}
