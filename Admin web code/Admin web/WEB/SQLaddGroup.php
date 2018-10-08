 <?php  
 $group=$_POST['numGroup'];
 $content=$_POST['content'];
 $lesson=$_POST['lesson'];
 $numGroup=(int)$group+1;
 $cate_id=(int)substr($lesson,0,-2);
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id4410338_sang');
 define('DB_PASS', 'sang1190');
 define('DB_NAME', 'id4410338_chinese');
 mb_language('uni'); 
 mb_internal_encoding('UTF-8');
 $connect = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);  
 mysqli_set_charset($connect, "utf8");
mysqli_query($connect,"SET character_set_client=utf8");
mysqli_query($connect,"SET character_set_connection=utf8");
$sql = "INSERT INTO QuestionsGroup(Cate_id,Group_id,LESSON_id,QuestionContent) VALUES('$cate_id','$numGroup','$lesson','$content')";  
if(mysqli_query($connect, $sql)){
    
        echo "<script>
		alert('save successful');
	    window.location.href='http://chineseisfun.000webhostapp.com/WEB/index.php';
		</script>";
   }  
     else  
     {  
         echo "<script>
		alert('false to save');
		window.location.href='http://chineseisfun.000webhostapp.com/WEB/index.php';
		</script>";
    }  
 ?> 