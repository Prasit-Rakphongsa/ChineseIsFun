<?php
    $cate=$_POST['cateid'];
    $cateid=(int)$cate;
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
 $keep=array();
 
        $stmt ="SELECT word,pinyin,meaning,audio FROM content where Cate_id='$cateid'";
        $result = mysqli_query($conn ,$stmt);
        while($row = mysqli_fetch_array($result)) {
             $row_array['word'] = $row['word'];
            $row_array['pinyin'] = $row['pinyin'];
			$row_array['meaning'] = $row['meaning'];
			$row_array['audio'] = $row['audio'];
            array_push($keep,$row_array);
        }
    
 echo json_encode($keep);
?>
