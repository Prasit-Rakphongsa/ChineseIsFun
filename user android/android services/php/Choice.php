<?php
 $lesson_id=$_POST['lesson'];
 $group=$_POST['group'];
 $cate=$_POST['cateid'];
 $cate_id=(int)$cate;
 $Ngroup=(int)$group;
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
     $stmt =$conn->prepare("SELECT QuestionContent FROM QuestionsGroup WHERE LESSON_id ='$lesson_id' and Group_id='$Ngroup' and Cate_id='$cate_id'");
	 //$stmt->bind_param("ss", $lesson_id,$group_id);
	if ($stmt->execute()) {
           $stmt-> bind_result($question);
			 while ( $stmt-> fetch() ) {
               $keepChoice['choice']=$question;
			 }
	}
	echo json_encode($keepChoice);
?>