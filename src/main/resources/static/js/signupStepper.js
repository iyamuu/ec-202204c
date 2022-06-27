/**
 *
 */

function userCheck(destroyFeedback) {
  let validationResults = [];
  let next = true;
  validationResults.push(nameValidation());
  validationResults.push(emailValidation());
  validationResults.push(zipCodeValidation());
  validationResults.push(addressValidation());
  validationResults.push(telValidation());
  validationResults.push(passwordValidation());
  validationResults.push(confirmPasswordValidation());

  validationResults.forEach((result) => {
    if (result === false) {
      $("#step1").addClass("wrong");
      $("#step2").removeClass("active");
      $("#step1").addClass("active");
      next = false;
      return destroyFeedback(false);
    }
  });

  if (next === true) {
    console.log("pass");
    setTimeout(function () {
      destroyFeedback(true);
      $("#step1").removeClass("wrong");
    }, 1500);
  }

  //フォームのバリデーションをする
}

//名前フォームのバリデーション
function nameValidation() {
  let name = $("#name").val();
  if (name == "" || !name.match(/[^\s\t]/)) {
    $(".error-name").text(`名前を入力してください`);
    return false;
  }
  $(".error-name").text(``);
  return true;
}

//メールフォームのバリデーション
function emailValidation() {
  let email = $("#email").val();

  if (
    !email.match(
      /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]+.[A-Za-z0-9]+$/
    )
  ) {
    $(".error-mail").text(
      `メールアドレスは xxx@yyy.zzz 形式で入力してください`
    );
    return false;
  }
  $(".error-mail").text(``);
	let isDuplicate = "false";
	duplicateCheckEmail(email).done(function (data) {
      isDuplicate = JSON.stringify(data);
    });
	console.log("duplicate:", isDuplicate);
  if (isDuplicate === "true") {
    console.log('nakatootteimasu')
    $(".duplicate-mail").text(`このメールアドレスはすでに登録されています`);
    return false;
  }
  $(".duplicate-mail").text(``);
  return true;
}

//郵便番号フォームのバリデーション
function zipCodeValidation() {
  let zipCode = $("#zipCode").val();

  if (!zipCode.match(/^[0-9]{3}-[0-9]{4}$/)) {
    $(".error-zipCode").text(`郵便番号はXXX-XXXXの形式で入力してください`);
    return false;
  }
  $(".error-zipCode").text(``);
  return true;
}

//住所フォームのバリデーション
function addressValidation() {
  let address = $("#address").val();

  if (address == "" || !address.match(/[^\s\t]/)) {
    $(".error-address").text(`住所を入力して下さい`);
    return false;
  }
  $(".error-address").text(``);
  return true;
}

//電話番号フォームのバリデーション
function telValidation() {
  let tel = $("#tel").val();

  if (!tel.match(/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/)) {
    $(".error-telephone").text(
      `電話番号はXXXX-XXXX-XXXXの形式で入力してください`
    );
    return false;
  }
  $(".error-telephone").text(``);
  return true;
}

//パスワードフォームのバリデーション
function passwordValidation() {
  let password = $("#password").val();

  if (!password.match(/^[a-z\\d]{8,16}$/)) {
    $(".error-password").text(
      `パスワードは8文字以上１６文字以下で入力してください`
    );
    return false;
  }
  $(".error-password").text(``);
  return true;
}

//確認用パスワードフォームのバリデーション
function confirmPasswordValidation() {
  let password = $("#password").val();
  let confirmPassword = $("#confirmation_password").val();

  if (password !== confirmPassword) {
    $(".error-confirmPassword").text(
      `パスワードと確認用パスワードが不一致です`
    );
    return false;
  }
  $(".error-confirmPassword").text(``);
  return true;
}

//メールアドレスの重複をチェックするAPI
function duplicateCheckEmail(email) {
  let hostUrl = "http://localhost:8080/ec-202204c/duplicateCheckEmail";

  return $.ajax({
    url: hostUrl,
    dataType: "json",
    type: "get",
    data: {
      mail: email,
    },
    async: true,
  })
    .done(function (data) {
      return JSON.stringify(data);
    })
    .fail(function (XMLHttpRequest, textStatus, errorThrown) {
      console.log("XMLHttpRequest : " + XMLDocument);
      console.log("textStatus : " + textStatus);
      console.log("errorThrown : " + errorThrown);
    });
}

function noThing(destroyFeedback) {
  setTimeout(function () {
    destroyFeedback(true);
  }, 10000);
}

$(".step2").on("click", function () {
  console.log("step2が押されたよ");
});

var stepperDiv = document.querySelector(".stepper");
console.log(stepperDiv);
var stepper = new MStepper(stepperDiv);
