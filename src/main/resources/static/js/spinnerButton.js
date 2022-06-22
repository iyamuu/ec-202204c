/**
 * 個数を変更するスピナーボタンを操作する
 */
//個数をプラスする処理
function spinnerPlus() {
  var add = $(this);
  var sub = $(".spinner-sub");
  var el = $(".spinner");

  el.val(function (i, oldval) {
    return ++oldval;
  });
  sub.removeClass("disabled");
}

//個数をマイナスする処理
function spinnerMinus() {
  var sub = $(".spinner-sub");
  var el = $(".spinner");

  if (el.val() > parseInt(el.attr("min"))) {
    el.val(function (i, oldval) {
      return --oldval;
    });
  }

  if (el.val() == parseInt(el.attr("min"))) {
    console.log("aaa");
    sub.addClass("disabled");
  }
}
