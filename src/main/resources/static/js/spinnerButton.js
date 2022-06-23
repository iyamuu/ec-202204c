/**
 * 個数を変更するスピナーボタンを操作する
 */

//個数をプラスする処理
function spinnerPlus(id) {
  var add = $(this);
  var sub = $("#modal" + id + " .spinner-sub");
  var el = $("#modal" + id + " .spinner");

  el.val(function (i, oldval) {
    return ++oldval;
  });
  sub.removeClass("disabled");
}

//個数をマイナスする処理
function spinnerMinus(id) {
  var sub = $("#modal" + id + " .spinner-sub");
  var el = $("#modal" + id + " .spinner");

  if (el.val() > parseInt(el.attr("min"))) {
    el.val(function (i, oldval) {
      return --oldval;
    });
  }

  if (el.val() == parseInt(el.attr("min"))) {
    sub.addClass("disabled");
  }
}
