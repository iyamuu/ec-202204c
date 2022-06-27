"use strict";

$(function () {
  //郵便番号検索欄が変更されたとき　（ユーザの住所）
  $("#zipCode").keyup(function () {
    //入力されたものを検知するにはKeyup
    if ($("#zipCode").val() !== "") {
      let zipCode = $("#zipCode").val().replace("-", "");
      console.log(zipCode);
      $.ajax({
        url: "https://zipcoda.net/api",
        dataType: "jsonp",
        data: {
          zipcode: zipCode,
        },
        async: true,
      })
        .done(function (data) {
          console.dir(JSON.stringify(data));
          $("#zipList").empty();
          data.items.forEach((item) => {
            $("#zipList").append(
              "<option class='zipSuggestion' value=" +
                item.zipcode +
                ":" +
                item.components.join("") +
                ">"
            );
          });
        })
        .fail(function (XMLHttpRequest, textStatus, errorThrown) {
          console.log("XMLHttpRequest" + XMLHttpRequest);
          console.log("textStatus" + textStatus);
          console.log("errorThrown" + errorThrown);
        });
    }
  });

  $("#zipCode").change(function () {
    console.log($("#zipCode").val());
    let addressWithCode = $("#zipCode").val().split(":");
    let zipcode =
      addressWithCode[0].slice(0, 3) + "-" + addressWithCode[0].slice(3, 7);
    let address = addressWithCode[1];

    $("#zipCode").val(zipcode);
    $("#address").val(address);

    console.log($("#zipCode").val());
    console.log($("#address").val());
  });

  //郵便番号検索欄が変更されたとき　宛先の住所
  $("#partnerZipCode").keyup(function () {
    //入力されたものを検知するにはKeyup
    if ($("#partnerZipCode").val() !== "") {
      let zipCode = $("#partnerZipCode").val().replace("-", "");
      console.log(zipCode);
      $.ajax({
        url: "https://zipcoda.net/api",
        dataType: "jsonp",
        data: {
          zipcode: zipCode,
        },
        async: true,
      })
        .done(function (data) {
          console.dir(JSON.stringify(data));
          $("#zipList").empty();
          data.items.forEach((item) => {
            $("#zipList").append(
              "<option class='zipSuggestion' value=" +
                item.zipcode +
                ":" +
                item.components.join("") +
                ">"
            );
          });
        })
        .fail(function (XMLHttpRequest, textStatus, errorThrown) {
          console.log("XMLHttpRequest" + XMLHttpRequest);
          console.log("textStatus" + textStatus);
          console.log("errorThrown" + errorThrown);
        });
    }
  });

  $("#partnerZipCode").change(function () {
    let addressWithCode = $("#partnerZipCode").val().split(":");
    let zipcode =
      addressWithCode[0].slice(0, 3) + "-" + addressWithCode[0].slice(3, 7);
    let address = addressWithCode[1];

    $("#partnerZipCode").val(zipcode);
    $("#partnerAddress").val(address);
  });
});
