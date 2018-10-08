<?php
$student_email=$_POST["email"];
 $lesson_id=$_POST["lesson"];
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

function CheckExistingUser($getemail,$getlesson_id) {
 
 //connecting to database and getting the connection object
        $s=$GLOBALS['conn']->prepare("SELECT email,LESSON_id from Student_Lesson WHERE email ='$getemail' and LESSON_id='$getlesson_id'");
 
        //$s->bind_param("ss", $email,$lesson_id);
        $s->execute();
 
        $s->store_result();
 
        if ($s->num_rows > 0) {
            // user existed 
            $s->close();
            return true;
        } else {
            // user not existed
            $s->close();
            return false;
        }
    }
    
if(CheckExistingUser($student_email,$lesson_id)){
     $stmt =$conn->prepare("SELECT Score from Student_Lesson WHERE email='$student_email' and LESSON_id='$lesson_id'");
	 //$stmt->bind_param("ss", $score_id,$lesson_id);
	if($stmt->execute()){
		 $stmt-> bind_result($result);
		  while ( $stmt-> fetch() ) {
               	 $response['score']=$result;
			 }
		
	}
}else{
	$response['score']=0;
}
  
    echo json_encode($response);
    
    mysqli_close($conn);
	
	
?>