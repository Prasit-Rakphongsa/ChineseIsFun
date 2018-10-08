<?php
	
	define('DB_HOST', 'localhost');
	define('DB_USER', 'id4410338_sang');
	define('DB_PASS', 'sang1190');
	define('DB_NAME', 'id4410338_chinese');
	$con= mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	session_start();// Starting Session
	
	$user_check=$_SESSION['login_user'];
	// SQL Query To Fetch Complete Information Of User
	$ses_sql=mysqli_query($con,"select email from User_info where email='$user_check'");
	$row = mysqli_fetch_assoc($ses_sql);
	$login_session =$row['email'];
	if(!isset($login_session)){
		mysqli_close($con); // Closing Connection
		header('Location:login.php'); // Redirecting To Home Page
	}
?>