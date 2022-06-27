"use strict";

$(function () {
  //郵便番号検索欄が変更されたとき
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

    // console.log(zipCode);
    // console.log(address);

    $("#zipCode").val(zipcode);
    $("#address").val(address);
  });
});
