
$ (function(){
  //表單驗證
  $('input[name="loginbtn"]').click(function(event){
    var $name = $('input[name="username"]');
    var $password = $('input[name="password"]');
    var $verify = $('input[name="verify"]');
    var $text = $('#text');
    var _name = $.trim($name.val());
    var _password = $.trim($password.val());
    var _verify = $.trim($verify.val());

    if('' ==_name){
      $text.text('請輸入帳號!');
      $name.focus();
      return;
    }
    if('' ==_password){
      $text.text('請輸入密碼!');
      $password.focus();
      return;
    }
    if('' ==_verify){
      $text.text('請輸入驗證碼!');
      $verify.focus();
      return;
    }
    $text.text('登入成功!');
  });

}); //當DOM讀取完成
