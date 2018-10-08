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
 $keep=array();
 
        $stmt ="SELECT name,link FROM pinyin where id<43 OR id=44 OR id=45 OR id=50 OR id=56 OR id=58 OR id=59 OR id>67 ";
        $result = mysqli_query($conn ,$stmt);
        while($row = mysqli_fetch_array($result)) {
             $row_array['name'] = $row['name'];
            $row_array['link'] = $row['link'];
            array_push($keep,$row_array);
        }
    
 echo json_encode($keep);
?>
