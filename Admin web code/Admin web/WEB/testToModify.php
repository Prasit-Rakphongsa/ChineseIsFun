<!DOCTYPE html>
<html>
<title>CHINESE IS FUN</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="http://chineseisfun.000webhostapp.com/WEB/style.css" /> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<style>
body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.w3-third img{margin-bottom: -6px; opacity: 0.8; cursor: pointer}
.w3-third img:hover{opacity: 1}
input[type='text'] { font-size: 20px; text-align: center; color: #000000; }
</style>
<body class="w3-light-grey w3-content" style="max-width:1600px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center" style="z-index:3;width:300px;font-weight:bold" id="mySidebar"><br>
  <h3 class="w3-padding-64 w3-center"><b>CHINESE IS FUN</b></h3>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-hide-large">CLOSE</a>
  <a href="#" onclick="w3_close()" class="w3-bar-item w3-button">UPDATE CONTENT</a> 
  <a href="#about" onclick="w3_close()" class="w3-bar-item w3-button">UPDATE AUDIO</a> 
  <a href="#contact" onclick="w3_close()" class="w3-bar-item w3-button">CONTACT</a>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
  <span class="w3-left w3-padding">CHINESE IS FUN</span>
  <a href="javascript:void(0)" class="w3-right w3-button w3-white" onclick="w3_open()">☰</a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
	<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">

  <!-- Push down content on small screens --> 
  <div class="w3-hide-large" style="margin-top:83px"></div>

  <!-- About section -->
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="about">
    <h4><b>CONTENT</b></h4>
     <!--<img src="/w3images/avatar_hat.jpg" alt="Me" class="w3-image w3-padding-32" width="600" height="650">-->
    <div class="w3-content w3-justify" style="max-width:645px,max-height:600px"">
                    <section class="box">
                        <form method="post" action="saveNewGroup.php">
                        <div class="row uniform 50%">         
                                <div class="12u">
													<table align="center" style="margin-top:20px;" class="table" width="90%">
												
													<tr>
														<td id="j" width="10%" class="c"><b>CATEGORY<b></td>
														<td id="j" width="10%" class="c"><b>LESSON<b></td>
														<td id="j" width="10%" class="c"><b>GROUP<b></td>
														<td width="50%" class="c"><b>CONTENT<b></td>
                                                   
													</tr>
													<?php
	define ('DB_HOST','localhost');
	define ('DB_USER','id4410338_sang');
	define ('DB_PASSWORD','sang1190');
	define ('DB_name','id4410338_chinese');
	$con= mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_name);
	
	$mysql_qry = "select * from QuestionsGroup where Cate_id=2";
$result = mysqli_query($con ,$mysql_qry);
 	while($row = mysqli_fetch_array($result)) {
		echo'<tr>
			<td class="c" >'.$row['Cate_id'].'</td>
			<td class="c">'.$row['LESSON_id'].'</td>
			<td class="c">'.$row['Group_id'].'</td>
			<td class="c"><input type="text" name="'.$row['ID'].'"  size="30" value="'.$row['QuestionContent'].'"></td>
			<td class="c"><button type="submit" name="click" class="btn-success" value="'.$row['ID'].'" >SAVE</button></td>
			</tr>';
	} 
?>
												</table>
											</div>
                                
                        </div>
                       								
                        
                        </form>
						
                    </section>

			<!-- Footer -->
				</div>
			</div>
		</div>

	</body>
</html>
