<?php
include('checkStatus.php'); // Includes Login Script
?>

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
</style>
<body class="w3-light-grey w3-content" style="max-width:1600px">

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center" style="z-index:3;width:300px;font-weight:bold" id="mySidebar"><br>
  <h3 class="w3-padding-64 w3-center"><b>CHINESE IS FUN</b></h3>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-hide-large">CLOSE</a>
    <a href="index.php" class="w3-bar-item w3-button c" style="font-size:18px;">UPDATE GROUP</a>
    <a href="uploadAudio.php" onclick="w3_close()" class="w3-bar-item w3-button c">UPDATE AUDIO</a> 
  <a onclick="w3_close()" class="w3-bar-item w3-button c clickcolor">UPDATE CONTENT</a> 
   <a href="logout.php" onclick="w3_close()" class="w3-bar-item w3-button c">LOGOUT</a>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
  <span class="w3-left w3-padding">SOME NAME</span>
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
    <h4><b>Update Content</b></h4>
     <!--<img src="/w3images/avatar_hat.jpg" alt="Me" class="w3-image w3-padding-32" width="600" height="650">-->
    <div class="w3-content w3-justify" style="max-width:600px,max-height:600px">
      <form name="add_name" id="add_name">  
                          <div class="table-responsive">  
                               <table class="table table-bordered" id="dynamic_field">  
                                    <tr>  
                                        <td><select name="category[]" class="select">
                                          <option value="1">Basic</option>
                                          <option value="2">Number</option>
                                          <option value="3">Family</option>
                                          <option value="4">Times</option>
                                            <option value="5">Classroom</option>
                                          <option value="6">Travel</option>
                                          <option value="7">Animal</option>
                                          <option value="8">Season</option>
                                           <option value="9">Career</option>
                                          <option value="10">Conversation1</option>
                                          <option value="11">Conversation2</option>
                                          <option value="12">Conversation3</option>
                                        </select></td>
                                         <td><input type="text" name="word[]" placeholder="Word" class="form-control name_list" /></td>
										<td><input type="text" name="pinyin[]" placeholder="Pinyin" class="form-control name_list" /></td>
										<td><input type="text" name="meaning[]" placeholder="Meaning" class="form-control name_list" /></td>
										<td><input type="text" name="name[]" placeholder="mp3 name" class="form-control name_list" /></td>
                                         <td><button type="button" name="add" id="add" class="btn btn-success">Add More</button></td>  
                                    </tr>  
                               </table>  
                               <input type="button" name="submit" id="submit" class="btn btn-info" value="Submit" />  
                          </div>  
                     </form>  
      </div>
    </div>
  </div>

<script>
// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  captionText.innerHTML = element.alt;
}

$(document).ready(function(){  
      var i=1;  
      $('#add').click(function(){  
           i++;  
           $('#dynamic_field').append('<tr id="row'+i+'"><td><input type="text" name="word[]" placeholder="Word" class="form-control name_list" /></td><td><input type="text" name="pinyin[]" placeholder="Pinyin" class="form-control name_list" /></td><td><input type="text" name="meaning[]" placeholder="Meaning" class="form-control name_list" /></td><td><input type="text" name="name[]" placeholder="mp3 name" class="form-control name_list" /></td><td><button type="button" name="remove" id="'+i+'" class="btn btn-danger btn_remove">X</button></td></tr>');  
      });  
      $(document).on('click', '.btn_remove', function(){  
           var button_id = $(this).attr("id");   
           $('#row'+button_id+'').remove();  
      });  
      $('#submit').click(function(){            
           $.ajax({  
                url:"SQLaddcontent.php",  
                method:"POST",  
                data:$('#add_name').serialize(),  
                success:function(data)  
                {  
                     alert(data);  
                     $('#add_name')[0].reset();  
                }  
           });  
      });  
 });  

</script>


</body>
</html>
