 <?php  
 
 define('DB_HOST', 'localhost');
 define('DB_USER', 'id4410338_sang');
 define('DB_PASS', 'sang1190');
 define('DB_NAME', 'id4410338_chinese');
 mb_language('uni'); 
 mb_internal_encoding('UTF-8');
 $connect = mysqli_connect(DB_HOST,DB_USER,DB_PASS,DB_NAME);  
 mysqli_set_charset($connect, "utf8");
 $cate=$_POST["category"][0];
 $category=(int)$cate;
 $number = count($_POST["word"]);  
 if($number > 0)  
 {  
      for($i=0; $i<$number; $i++)  
      {  
           if(trim($_POST["word"][$i] != ''))  
           {  
               $link="https://chineseisfun.000webhostapp.com/WEB/Audio/".$_POST["name"][$i];
				mysqli_query($connect,"SET character_set_client=utf8");
				mysqli_query($connect,"SET character_set_connection=utf8");
                $sql = "INSERT INTO content(Cate_id,word,pinyin,meaning,audio) VALUES('".mysqli_real_escape_string($connect,$category)."','".mysqli_real_escape_string($connect, $_POST["word"][$i])."','".mysqli_real_escape_string($connect, $_POST["pinyin"][$i])."','".mysqli_real_escape_string($connect, $_POST["meaning"][$i])."','".mysqli_real_escape_string($connect, $link)."')";  
                mysqli_query($connect, $sql);  
           }  
      }  
      echo "Added successful..";  
 }  
 else  
 {  
      echo "No data";  
 }  
 ?> 