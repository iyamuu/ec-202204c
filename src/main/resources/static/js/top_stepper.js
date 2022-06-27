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
function validationActiveChange() {
	 var stepper = document.querySelector(".stepper");
	 var stepperInstace = new MStepper(stepper, {
  	// options
  	firstActive: 2, // this is the default
  	});
}
　$('.cart_button').css('display', 'none');
 let pageVal = $('#currentPage').val();
 if (pageVal === '') {
 	pageVal = 0;
 	　$('.cart_button').css('display', '');
 }
 	
 
  var stepper = document.querySelector(".stepper");
var stepperInstace = new MStepper(stepper, {
  // options
  firstActive: pageVal, // this is the default
  });