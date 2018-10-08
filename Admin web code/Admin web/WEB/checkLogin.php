<?php

session_start(); // Starting Session

    define('DB_HOST', 'localhost');
    define('DB_USER', 'id4410338_sang');
    define('DB_PASS', 'sang1190');
    define('DB_NAME', 'id4410338_chinese');
    $conn = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);  
	// json response array
$response = array("error" => FALSE);

function VerifyUserAuthentication($email, $password) {
 
        $stmt =$GLOBALS['conn']->prepare("SELECT email,encrypted_password, salt FROM User_info WHERE email = ? and type=1");
 
        $stmt->bind_param("s", $email);
 
        if ($stmt->execute()) {
            $stmt-> bind_result($token1,$token2,$token3);
            // verifying user password
            while ( $stmt-> fetch() ) {
                 $user["email"] =$token1;
            }
             $stmt->close();
            $salt = $token3;
            $encrypted_password = $token2;
            $hash =CheckHashFunction($salt, $password);
            //echo ($encrypted_password." ".$hash);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
            } else {
            return false;
        }

}
 
function checkHashFunction($salt, $password) {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
    }

 
if (isset($_POST['username']) && isset($_POST['password'])) {

    // receiving the post params
    $email = $_POST['username'];
    $password = $_POST['password'];
    
     $user =VerifyUserAuthentication($email, $password);   
 
    if ($user!= false) {
        // use is found
         //$response["email"] =$user["email"];
         $_SESSION['login_user']=$email; // Initializing Session
        header('Location: index.php');
	
    } else {
        // user is not found with the credentials
         echo "<script>
		alert('email or password is wrong. Please try again!');
		window.location.href='login.php';
		</script>";
    }
} else {
    // required post params is missing
     echo "<script>
		alert('Required parameters email or password is missing!');
		window.location.href='login.php';
		</script>";
}
?>