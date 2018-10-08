<?php

require_once 'update_user_info.php';
$db = new update_user_info();

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
$response = array("error"=>FALSE);

    // receiving the post params
    $email = $_POST['email'];
	$status=(int)$_POST['status'];
 
	if($status==0){
    // check if user is already existed with the same email
    if ($db->CheckExistingUser($email)) {
        // user already existed
        $response["error"] = TRUE;
        echo json_encode($response);
    }else{
        
         echo json_encode($response);
    }
	}else{
		$password=$_POST['password'];
		
        $hash =hashFunction($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
		
		 $stmt =$conn->prepare("UPDATE User_info SET encrypted_password='$encrypted_password', salt='$salt' where email='$email'");
        $result=$stmt->execute();
		
		if($result){
			 echo json_encode($response);
		}
        $stmt->close();
	
	}
 
function hashFunction($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
	
?>