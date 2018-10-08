<?php
class update_user_info {
 private $conn;

    // constructor
    function __construct() {
        require_once 'php_connect.php';
        // connecting to database
        $db = new php_connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {

    }

    /**
     * Storing new user
     * returns user details
     */
public function StoreUserInfo($name, $email, $password) {
        $date=date("Y-m-d");
        $hash = $this->hashFunction($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
 
        $stmt = $this->conn->prepare("INSERT INTO User_info(name, email, encrypted_password, salt,Coins,date) VALUES(?, ?, ?, ?,300,'$date')");
        $stmt->bind_param("ssss", $name, $email, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT name, email, encrypted_password, salt FROM User_info WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt-> bind_result($token2,$token3,$token4,$token5);
 
            while ( $stmt-> fetch() ) {
               $user["name"] = $token2;
               $user["email"] = $token3;
            }
            $stmt->close();
            return $user;
        } else {
          return false;
        }
    }
 
    public function hashFunction($password) {
 
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

public function VerifyUserAuthentication($email, $password) {
 
        $stmt = $this->conn->prepare("SELECT name, email, encrypted_password, salt,Coins,image FROM User_info WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
        if ($stmt->execute()) {
            $stmt-> bind_result($token2,$token3,$token4,$token5,$token6,$token7);
 
            while ( $stmt-> fetch() ) {
               $user["name"] = $token2;
               $user["email"] = $token3;
               $user["encrypted_password"] = $token4;
               $user["salt"] = $token5;
               $user["coins"]=$token6;
               $user["image"]=$token7;
            }
 
            $stmt->close();
 
            // verifying user password
            $salt = $token5;
            $encrypted_password = $token4;
            $hash = $this->CheckHashFunction($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
        } else {
            return NULL;
        }
    }
 
public function checkHashFunction($salt, $password) {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
    }

public function CheckExistingUser($email) {
        $stmt = $this->conn->prepare("SELECT email from User_info WHERE email = ?");
 
        $stmt->bind_param("s", $email);
 
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
}
?>