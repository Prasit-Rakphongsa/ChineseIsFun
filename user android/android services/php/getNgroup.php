<?php
 $lesson_id=$_POST['lessonID'];
 $c_id=$_POST['cateID'];
 $cate_id=(int)$c_id;
 
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
$response = array("error" => FALSE,"group" =>0);
     $stmt =$conn->prepare("select Ngroup from LESSON WHERE LESSON_id='$lesson_id' and Cate_id='$cate_id'");
	 //$stmt->bind_param("ss", $score_id,$lesson_id);
	 if($stmt->execute()){
		 $stmt-> bind_result($result);
		  while ( $stmt-> fetch() ) {
               	 $response['group']=$result;
			 }
	 }else{
	     $response['error']=true;
	 }
    mysqli_close($conn);
	echo json_encode($response);
?>