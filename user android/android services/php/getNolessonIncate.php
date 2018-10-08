
<?php
 $cate=$_POST["cate"];
 $cate_id=(int)$cate;
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

        $s=$conn->prepare("SELECT Cate_id from LESSON WHERE Cate_id='$cate_id'");

        //$s->bind_param("ss", $email,$lesson_id);
        $s->execute();
 
        $s->store_result();
		$response['Nolesson']=$s->num_rows;
		echo json_encode($response);

?>