<?php
 
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
 
//if (isset($_POST['lesson_id'])) {
 
    // receiving the post params
    //$lesson_id = $_POST['lesson_id'];
 
    // get the question by lesson_id
     $stmt =$conn->prepare("SELECT Question FROM Questions WHERE LESSON_id ='1.1'");
	 //$stmt->bind_param("s", $lesson_id);
	 if ($stmt->execute()) {
            $stmt-> bind_result($question);
			 while ( $stmt-> fetch() ) {
               $keep=array();
			   $keep['question']=$question;
			   echo json_encode($keep);
			 }
	 }
	 
//}
?>