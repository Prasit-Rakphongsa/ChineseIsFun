<?php
$email=$_POST['email'];
$date=date("Y-m-d");

 //database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id4410338_sang');
 define('DB_PASS', 'sang1190');
 define('DB_NAME', 'id4410338_chinese');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
 
 $coin=50;
 
$response=array("response"=>0);
         $stmt =$conn->prepare("UPDATE User_info SET date='$date',Coins=Coins+'$coin' where email='$email' and date<'$date'");
         $stmt->execute();
    mysqli_close($conn);
    echo Json_encode($response);
	
	
?>