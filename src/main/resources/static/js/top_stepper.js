/**
 * トップページのステッパーを操作する
 */
function toCart(destroyFeedback) {
  setTimeout(function () {
    destroyFeedback(true);
  }, 1500);
}

function toConfirm(destroyFeedback) {
  setTimeout(function () {
    destroyFeedback(true);
  }, 1500);
}

function toComplete(destroyFeedback) {
  setTimeout(function () {
    destroyFeedback(true);
  }, 1500);
}

function noThing(destroyFeedback) {
  setTimeout(function(){ destroyFeedback(true); }, 10000);
}

var stepper = document.querySelector(".stepper");
var stepperInstace = new MStepper(stepper, {
  // options
  firstActive: 0, // this is the default
});
