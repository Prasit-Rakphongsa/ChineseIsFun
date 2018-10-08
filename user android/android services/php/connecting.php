<?php
	require_once"connect.php";
	$con=new mysqli(DB_HOST,DB_USER,DB_PASSWORD);
	if($con){
	echo"Database connected successfully";
	}else{
		echo"Database connection failed";}
?>