<?php
class php_connect {
    private $conn;
 
    // Connecting to database
    public function connect() {
        require_once 'connect.php';
 
        // Connecting to mysql database
        $this->conn = new mysqli(DB_HOST,DB_USER,DB_PASSWORD,DB_name);;
        // return database object
        return $this->conn;
    }
}
?>