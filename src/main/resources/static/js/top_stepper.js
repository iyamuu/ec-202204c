/**
 * トップページのステッパーを操作する
 */


function toCart(destroyFeedback) {
  console.log("toCart");
  $('.fixed_btn').css('display', 'none');
  showOrderItemList();
  setTimeout(function () {
    destroyFeedback(true);
  }, 1500);
}
function toConfirm(destroyFeedback) {
  console.log("toConfirm");
  console.log("orderItemTable: " + $("#orderItemTable").text());
  if($("#orderItemTable").text() !== ``) {
  	console.log("要素があります");
  }
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
  setTimeout(function () {
    destroyFeedback(true);
  }, 10000);
}

function itemPrev() {
	console.log("itemPrev");
	$('.fixed_btn').css('display','');
}

$('.step1').on('click', function() {
	$('.fixed_btn').css('display','');
});

$('.step2').on('click', function() {
	$('.fixed_btn').css('display','none');
	toCart();
});
  
  var stepper = document.querySelector(".stepper");
var stepperInstace = new MStepper(stepper, {
  // options
  firstActive: 0, // this is the default
  });