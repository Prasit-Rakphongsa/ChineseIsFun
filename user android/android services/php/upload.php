
<?php
	$email=$_POST["email"];
    $image =$_POST["pic"];
	
	 define('DB_HOST', 'localhost');
 define('DB_USER', 'id4410338_sang');
 define('DB_PASS', 'sang1190');
 define('DB_NAME', 'id4410338_chinese');
  $conn = new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME) or die('Unable to connect');
 
	$target="../image";
    $response = array();
	$imageName=rand()."_".time().".jpg";
   // define image name as time
    $target=$target."/".$imageName;
	// $decodedImage = base64_decode($image);
    $return = file_put_contents($target, base64_decode($image));
 
    if($return !== false){
        try{
     $stmt = $conn->prepare("UPDATE User_info set image=? WHERE email='$email'");
     $imageLink="http://chineseisfun.000webhostapp.com/image/".$imageName."";
	 $stmt->bind_param("s",$imageLink);
	 if($stmt->execute()){
		 $response['error'] = false;
		 $response['message'] = 'File uploaded successfully';
	 }else{
		throw new Exception("Could not upload file");
	 }
	 }catch(Exception $e){
		 $response['error'] = true;
		 $response['message'] = 'Could not upload file';
	 }
    }else{
        $response['message'] = "Image Uploaded Failed";
    }
 
    echo json_encode($response);
?>