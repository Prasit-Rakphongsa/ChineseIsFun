<?php
$data = $_POST["coin"];
$data1 =$_POST["email"];
$data2 =$_POST["cateid"];
$data3 =$_POST["lesson"];
$cate=(int)$data2;
$lesson=(int)$data3;
$coin=(int)$data;

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
	// json response array
$response = array("error" => FALSE);
$keepChoice=array();
     $stmt =$conn->prepare("UPDATE User_info SET Coins=Coins+'$coin', Category='$cate', lesson='$lesson' WHERE email='$data1'");
	//$stmt->bind_param("ss", $score_id,$lesson_id);
	if($stmt->execute()){
	    echo("sucess");
	}
    mysqli_close($conn); 
?>