<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>CodePen - Login</title>

    <link rel="stylesheet" href="style.css" media="screen" type="text/css" />

</head>

<body>

  <html lang="en-US">
  <head>

    <meta charset="utf-8">

    <title>Login</title>

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700">

    <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->

  </head>

  <body>
      <div  align="middle" class="logo">
          
          <img src="http://chineseisfun.000webhostapp.com/WEB/image/logochines.png" alt="Smiley face" width="300" height="100">
      </div>

    <div class="container">

      <div id="login">

        <form action="checkLogin.php" method="post">

          <fieldset class="clearfix">

            <p><input type=text name="username"  placeholder="Email" required> </p> <!-- JS because of IE support; better: placeholder="Username" -->
            <p><input type="password" name="password"  placeholder="Password" required> </p> <!-- JS because of IE support; better: placeholder="Password" -->
            <p><input type="submit" value="Sign In"></p>

          </fieldset>

        </form>
        
        <p><a href="forgetpass.php">Forget Password ?</a><span class="fontawesome-arrow-right"></span></p>

      </div> <!-- end login -->

    </div>

  </body>
</html>

</body>

</html>