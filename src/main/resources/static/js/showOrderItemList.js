"use strict";

$(function () {
  let orderId = $("#orderId").text();
  $("#orderId").hide();
  showOrderItemList(orderId);
});

let showOrderItemList = function (orderId) {
  let showUrl = "http://localhost:8080/ec-202204c/show?orderId=" + orderId;

  $.ajax({
    url: showUrl,
    type: "get",
    dataType: "json",
    async: true,
  })
    .done(function (data) {
      console.log(JSON.stringify(data));
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
    });
};
