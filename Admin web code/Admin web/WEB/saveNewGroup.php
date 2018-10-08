<?php
	$getid=$_POST['click'];
	$data=$_POST[$getid];
	
	$id=(int)$getid;
	
     define ('DB_HOST','localhost');
	define ('DB_USER','id4410338_sang');
	define ('DB_PASSWORD','sang1190');
	define ('DB_name','id4410338_chinese');
	$con= mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_name);
	 $mysql_qry = "UPDATE QuestionsGroup SET QuestionContent='$data' WHERE ID=$id ;";
	$result = mysqli_query($con ,$mysql_qry);
		if($result) {
			echo "<script>
		alert('save successful');
		window.location.href='http://chineseisfun.000webhostapp.com/WEB/testToModify.php';
		</script>";
			//header('Location: Havemenu.php');
	}else{
			echo "<script>
		 alert('something wrong with server');
		 window.location.href='http://chineseisfun.000webhostapp.com/WEB/testToModify.php';
		 </script>";
		 }
?>