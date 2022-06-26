/**
 * カートに追加ボタンを押した際の追加を知らせるモーダルを操作する
 */

// ウィンドウを開く
function displayModal() {
          var modal = document.getElementById( 'modal01');
          $( modal ).fadeIn( 500 );
          
          setInterval(function() {
	 		$( '.js-modal' ).fadeOut( 300 );
		  }, 1500);
          
          return false;
};