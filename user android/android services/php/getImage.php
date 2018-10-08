<?php 
 
 //Importing dbdetails file 
 //require_once 'dbDetails.php';
	define ('DB_HOST','localhost');
	define ('DB_USER','id4410338_sang');
	define ('DB_PASSWORD','sang1190');
	define ('DB_name','id4410338_chinese');
 
 //connection to database 
 $con = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_name) or die('Unable to Connect...');
 
 //sql query to fetch all images 
 $stmt =$con->prepare("SELECT image FROM User_info where id=4");
 
  //response array 
 $response = array();
 
 if($stmt->execute()){
	$stmt-> bind_result($result);
		  while ( $stmt-> fetch() ) {
               	 	 $response['error'] = false; 
	                $response['images'] = $result; 
			 }

	 
 }else{ 
	 $response['error'] = true; 
	 $response['images'] = array(); 
 }
 //traversing through all the rows 
 //while($row = mysqli_fetch_array($result)){
	 // $temp = array(); 
	 // $temp['id']=$row['id'];
	 // $temp['name']=$row['name'];
	 // $temp['url']=$row['url'];
	 // array_push($response['images'],$temp);
 //}
 //displaying the response 
  mysqli_close($con);
 echo json_encode($response);
 ?>