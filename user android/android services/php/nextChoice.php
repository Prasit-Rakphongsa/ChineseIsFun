<?php
 //database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id4410338_sang');
 define('DB_PASS', 'sang1190');
 define('DB_NAME', 'id4410338_chinese');
 
 	
	mb_language('uni'); 
    mb_internal_encoding('UTF-8');
	$dbLink = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	//Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
	mysqli_set_charset($dbLink, "utf8");
	
	 mysqli_query($dbLink,"SET character_set_client=utf8");
    mysqli_query($dbLink,"SET character_set_connection=utf8");
	
$response = array();
	$sql_query = "select word from pinyin where word!=''";
	$dbResult=mysqli_query($dbLink,$sql_query);
        if($dbResult){
			while($row = mysqli_fetch_array($dbResult))
        {
            echo $row['word']. '<br />';
        }
    }else{
		echo('false');
	}
 
 
		
?>