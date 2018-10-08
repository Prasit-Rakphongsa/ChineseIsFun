<?php
header('Content-type: text/plain; charset=utf-8');

if(isset($_POST['Submit']))
{
	if (isset($_FILES['audio_file'])) {
		 $myFile = $_FILES['audio_file'];
		 $fileCount = count($myFile["name"]);
		 
$dir="Audio/";
 // Where the file is going to be placed
 $target_path =$dir.basename( $myFile["name"][$i]);
 
  for ($i = 0; $i < $fileCount; $i++) {
  //following function will move uploaded file to audios folder. 
if(move_uploaded_file($myFile['tmp_name'][$i],$dir.basename( $myFile["name"][$i]))) {
	echo('uploaded successful');
  //insert query if u want to insert file
}else{
	echo('false to upload');
}
}
	}else{
		echo('No file');
	}
}else{
	echo('No submit');
}

?>