"use strict";

$(function () {
  $("#orderId").hide();
  showOrderItemList();
});

//注文一覧の表示  合計金額の変更も行う
let showOrderItemList = function () {
  let orderId = $("#orderId").text();
  let showUrl = "http://localhost:8080/ec-202204c/show?orderId=" + orderId;

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
      });
      let totalPrice = data.tax + data.calcTotalPrice;

      //消費税の更新
      $("#tax").text(`消費税：${data.tax}`);

      //合計金額の更新
      $("#totalPrice").text(`ご注文金額合計：${totalPrice} (税込)`);
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
  let subTotalCell = buildSubTotalCell(orderItem.subTotal);
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
  let cell = `<td>
                    <span>
                        ${price}円
                    </span>
                    <br />
                    <span>
                        <button type="button" onclick="subOrderItemQuantity(${orderItemId});">
                            -
                        </button>
                        <label id="item${orderItemId}Quantity"> ${quantity} </label>
                        <button type="button" onclick='addOrderItemQuantity(${orderItemId});'">
                            +
                        </button>
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
                ${
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

let buildSubTotalCell = function (subtotal) {
  let cell = `<td>
                    <span>
                        ${subtotal}円
                    </span>
                </td>`;

  return cell;
};

let buildDeleeButtonCell = function (orderItemId) {
  let cell = `<td>
                    <button
                    type="button"
                    onclick="deleteOrderItem(${orderItemId});">
                    <span>削除</span>
                    </button>
                </td>`;

  return cell;
};

/**
 *注文商品情報を削除する.
 */

function deleteOrderItem(id) {
  let hostUrl = `http://localhost:8080/ec-202204c/delete?orderItemId=${id}`;

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
  let hostUrl = "http://localhost:8080/ec-202204c/update";

  $.ajax({
    url: hostUrl,
    type: "post",
    dataType: "json",
    data: {
      orderItemId: id,
      quantity: quantity,
    },
    async: true,
  })
    .done(function (data) {
      // $(`#item${id}Quantity`).text(data.quantity);
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

  updateOrderItem(orderItemId, Number(quantity) + 1);
}

function subOrderItemQuantity(orderItemId) {
  let quantitySpan = $(`#item${orderItemId}Quantity`);
  let quantity = quantitySpan.text();

  updateOrderItem(orderItemId, Number(quantity) - 1);
}