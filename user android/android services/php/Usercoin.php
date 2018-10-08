<?php
$student_email=$_POST['email'];
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
$response = array();
	$stmt=$conn->prepare("SELECT Coins from User_info where email='$student_email'");
	if($stmt->execute()){
		 $stmt-> bind_result($result);
		  while ( $stmt-> fetch() ) {
               	 $response['coin']=$result;
			 }
	 }
    mysqli_close($conn);
	echo json_encode($response);
	
?>