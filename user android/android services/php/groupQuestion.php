<?php
 $lesson_id=$_POST['lesson'];
 $group_id=$_POST['group'];
 $cate=$_POST['cateid'];
 $group=(int)$group_id;
 $cate_id=(int)$cate;
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
$keepPinyin="";
     $stmt =$conn->prepare("SELECT QuestionContent FROM QuestionsGroup WHERE LESSON_id =? and Group_id=? and Cate_id=?");
	 $stmt->bind_param("sss", $lesson_id,$group,$cate_id);
	if ($stmt->execute()) {
           $stmt-> bind_result($question);
			 while ( $stmt-> fetch() ) {
               $keepPinyin=$question;
			 }
	}
	
$groupQuestion=array();
$groupQuestion=explode(',', $keepPinyin);
$keep=array();
static $i=0;

 foreach($groupQuestion as $value){
	 $name=(string)$i;
    
    if($cate_id==1){
     $stmt =$conn->prepare("SELECT link FROM pinyin WHERE name=?");
      $stmt->bind_param("s", $value);
	 if ($stmt->execute()) {
	     $stmt-> bind_result($question);
			 while ( $stmt-> fetch() ) {
			   $keep['name'.$name]=$question;
			   $i++;
			 }
	 }
     
    }
    else{
        $stmt ="SELECT audio,meaning FROM content WHERE word='$value'";
        $result = mysqli_query($conn ,$stmt);
        while($row = mysqli_fetch_array($result)) {
             $row_array['audio'] = $row['audio'];
            $row_array['meaning'] = $row['meaning'];
            array_push($keep,$row_array);
        }
    }
}
 echo json_encode($keep);
?>
