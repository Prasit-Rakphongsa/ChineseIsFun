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

if (isset($_POST['email'])) {
 
    // receiving the post params
    $email = $_POST['email'];
 
    // check if user is already existed with the same email
    if (CheckExistingUser($email)) {
        // user already existed
	
		$password=$_POST['password'];
		$confirm=$_POST['confirm'];
		if(strcmp($password,$confirm)=="0"){
		
        $hash =hashFunction($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
		
		 $stmt =$conn->prepare("UPDATE User_info SET encrypted_password='$encrypted_password', salt='$salt' where email='$email'");
        $result=$stmt->execute();
		
    		if($result){
    		     $stmt->close();
    		    echo "<script>
		alert('Successful created new password');
	    window.location.href='http://chineseisfun.000webhostapp.com/WEB/login.php';
		</script>";
    		}else{
    		    echo ("wrong query");
    		}
        
		}else{
         echo "<script>
		alert('Password is not match! ');
	    window.location.href='http://chineseisfun.000webhostapp.com/WEB/forgetpass.php';
		</script>";
		
        }
    }else{
         echo "<script>
		alert('Invalid Email');
	    window.location.href='http://chineseisfun.000webhostapp.com/WEB/forgetpass.php';
		</script>";
    }
	}else{
	   echo "<script>
		alert('Incomplete input');
	    window.location.href='http://chineseisfun.000webhostapp.com/WEB/forgetpass.php';
		</script>";
	
	}
	
function CheckExistingUser($mail) {
        $stmt =$GLOBALS['conn']->prepare("SELECT email from User_info WHERE email = ?");
 
        $stmt->bind_param("s", $mail);
 
        $stmt->execute();
 
        $stmt->store_result();
 
        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
 
function hashFunction($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
	
?>