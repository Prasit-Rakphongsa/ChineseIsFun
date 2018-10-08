<?php
 $coin_id=$_POST['Paycoin'];
 $email_id=$_POST['Useremail'];
 $coin=(int)$coin_id;
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
$c;
$response =array("se" => 0);
$keepChoice=array();
     $stmt =$conn->prepare("select Coins from User_info WHERE email='$email_id'");
	 //$stmt->bind_param("ss", $score_id,$lesson_id);
	 if ($stmt->execute()) {
           $stmt-> bind_result($question);
			 while ( $stmt-> fetch() ) {
               $c=$question;
			 }
	}
	$remainCoin;
    if($c>=$coin){
        $c=$c-$coin;
        $query=$conn->prepare("UPDATE User_info SET Coins='$c' WHERE email='$email_id'");
        $query->execute();

    }else{
        $response= array("se" => 1);
    }
    echo json_encode($response);
      mysqli_close($conn);
?>