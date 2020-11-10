<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Comic+Neue&display=swap" rel="stylesheet">


<style>
h1 {
	font-family: 'Comic Neue', 'Lobster', serif;
    font-size: 72px;
    color:   #002080;
	}
body {
background-color: white-space;
font-family: 'Comic Neue';
}
.active {
  background-color: white;
}
.split {
  height: 100%;
  width: 50%;
  position: relative;
  z-index: 1010101010;
  float: left;
  overflow-x: hidden;
  
}
.container {
  position: relative;
  top: 5px;
  left : 250px;
 
}
.left {
  left: 0;
  background-color: white;
}
.right {
  right: 0;
  background-color: white;
}
 #hi {
  border-radius: 15px;
  background-color: #f2f2f2;
  padding: 20px;
  font-family: 'Comic Neue';
}
 input[type=submit] {
  width: 80%;
  background-color: #00BFFF;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-family: 'Comic Neue';
}
 .fa-1x {
font-size: 3rem;
}
.navbar-toggler.toggler-example {
cursor: pointer;
}
.block {
  display: block;
  width: 40%;
  border: none;
  background-color: #00BFFF;
  color : white;
  padding: 14px 28px;
  font-size: 20px;
  font-family: 'Comic Neue';
  cursor: pointer;
  text-align: center;
  border: none;
  border-radius: 4px;
}

.text_area{
	font-family: 'Comic Neue';
	font-size : 18px;
	padding-left:10px;
}

.logout {

  width: 100%;
  border: none;
  background-color: #00BFFF;
  color : white;
  padding: 7px 7px;
  font-size: 20px;
  cursor: pointer;
  text-align: center;
  font-family: 'Comic Neue';
  border: none;
  border-radius: 4px;
}

.fa {
    color: white;
}
</style>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col-lg-6">			
      <h1>SmartGCC</h1>
    </div>
    <div class="col-lg-6">
<div class="row">

	<div class="col-xs-6 col-md-2"><br>
                
        <a href="#" class="btn btn-default  btn-sm">
<span class="glyphicon glyphicon-print"></span> Print Output File 
</a>        
        
                
    </div>
  <div class="col-sm-6"></div>
	<form action="${contextPath}/root/editor/logout" method="post">
	<div class="col-xs-6 col-md-2"><br>
				<button class="btn logout" type="submit"   >Log out</button>
	</div>
	</form>
	
</div>
</div>
</div>



<div class="row">
	<div class="col-lg-3">
        <h2><b>Welcome </b>
        <label>${message }</label></h2><br> 
        </div>
     <div class="col-lg-3">
     <form action="${contextPath}/root/editor/readfile" onsubmit="return ipFileneeded();"method="post" enctype = "multipart/form-data">
        <input type="file" name="ip_File" id="ip_File" ><br>
        <button type="submit" class="btn block" id="load_file" >Load File</button>
     </form> 
	</div>
<form action="${contextPath}/root/editor/readcode" method="post" onsubmit="return ipneeded();">
<div class="col-lg-3">
 	<select class="menuformat" name="usertype" id="usertype">
        <option value="Beginner">Beginner</option>
        <option value="Typical">Experienced</option>
        <option value="Expert">Expert</option>
    </select><br><br>
  
    <div class="Beginner box" id="begin">
      <select class="menuformat" name="novice_option">
      <option value="Compile">Compile</option>
      <option value="Execute">Execute</option>
      <option value="ConfigurationInfo">Configuration info</option>
      </select>
    </div>
    <div class="Typical box" id="inter">
      <select class="menuformat" name="inter_option">
      <option value="Execute">Execute</option>
      <option value="Compile">Compile</option>
      <option value="CodeGeneration">Code Generation</option>
      <option value="CodeOptimisation">Code Optimization</option>
      </select>
    </div>
    <div class="Expert box" id="expert">
      <select class="menuformat" name="expert_option">
      <option value="Execute">Execute</option>
      <option value="Compile">Compile</option>
      <option value="ConfigurationInfo">Configuration info</option>
      <option value="CodeGeneration">Code Generation</option>
      <option value="CodeOptimisation">Code Optimization</option>
      <option value="RequestWarnings">Request Warnings</option>
      <option value="SuppressWarnings">Suppress Warnings</option>
      </select>
    </div>
</div>
    
 <div class="col-lg-3">
  <div class="custom-control custom-switch">
    <input type="checkbox" class="custom-control-input" id="checkBoxChecked" name="quick_options" onclick="myFunction()">
     <label class="custom-control-label" for="switch1"> Show quick options</label><br><br>
     <select class="menuformat"  style="display:none" id="frequent" name="quick_value">
     			<option value="${quick_ops.get(0)}">${quick_ops.get(0)}</option>
       			<option value="${quick_ops.get(1)}">${quick_ops.get(1)}</option>
       			<option value="${quick_ops.get(2)}">${quick_ops.get(2)}</option>
       </select>
 </div>
</div>
    
</div>
  
  <script>
$(document).ready(function(){
    $("select").change(function(){
        $(this).find("option:selected").each(function(){
            var optionValue = $(this).attr("value");
            if(optionValue){
              //  $(".box").not("." + optionValue).hide();
                $("." + optionValue).show();
                if(optionValue == "Beginner" ){
                  $(".box").not(".Beginner" ).hide();
                }else if(optionValue == "Typical"){
                  $(".box").not(".Typical" ).hide();
                }else if(optionValue == "Expert"){
                  $(".box").not(".Expert" ).hide();
                }
            } else{
                $(".box").hide();
            }
        });
    }).change();
});
function myFunction() {
  var checkBox = document.getElementById("checkBoxChecked");
  var text = document.getElementById("frequent");
  
  if (checkBox.checked == true){      
    text.style.display = "block";
    $(".box").hide();
    $("#usertype").hide();
  } else {
     text.style.display = "none";
     $("#usertype").show();
     var usertype="<%=session.getAttribute("usertype")%>";
     
     if(usertype==="Beginner")
 	{
 	console.log(usertype)
 	$("#expert").hide();
 	$("#begin").show();
 	$("#inter").hide();
 	}
 else if(usertype==="Typical")
 	{
 	console.log(usertype)
 	$("#expert").hide();
 	$("#begin").hide();
 	$("#inter").show();
 	}
 	
 else if(usertype==="Expert")
 	{
 	console.log(usertype)
 	$("#expert").show();
 	$("#begin").hide();
 	$("#inter").hide();
 	}
     
     //$("#begin").show();
  }
}
window.onload = function() {
	var usertype="<%=session.getAttribute("usertype")%>";
	var option="<%=session.getAttribute("option")%>";
	var check="<%=session.getAttribute("quick_options")%>"
	document.getElementById("usertype").value = usertype;
	//document.getElementById("quick_options").checked = true;
	//$("#expert").show();
	//$("#begin").hide();
	//$("#inter").hide();
	//$(".box").not(".Expert" ).hide();
	//alert(check);
	//if(check===null)
	
    if(usertype==="Beginner")
    	{
    	console.log(usertype)
    	$("#expert").hide();
    	$("#begin").show();
    	$("#inter").hide();
    	}
    else if(usertype==="Typical")
    	{
    	console.log(usertype)
    	$("#expert").hide();
    	$("#begin").hide();
    	$("#inter").show();
    	}
    	
    else if(usertype==="Expert")
    	{
    	console.log(usertype)
    	$("#expert").show();
    	$("#begin").hide();
    	$("#inter").hide();
    	}
	
	/* else
		{
	    document.getElementById("quick_options").checked = true;
	    var user = document.getElementById("usertype");
	    user.style.display="none";
		} */    	
}
</script>

</div></br>

<div class="row">
<div class="col-lg-6">
<div class="form-group text_area" >
	<label><b>Write your code below   </b><i class="glyphicon glyphicon-arrow-down"></i></label>
  <textarea class="form-control rounded-0 " id="code_editor" name="code_editor" rows="24" cols="100" placeholder="code here...">${code }</textarea>
</div>
</div>
<div class="card col-lg-6" style="border-radius: 15px;  padding: 20px">
<div class="card-body">

   
		<button type="submit" class="btn btn-info btn-lg block" >
          <span class="glyphicon glyphicon-cog"></span> Execute
        </button>
</form>
</div>

</div>
<div class="col-lg-6 text_area">
<b>Console</b><br><br>
<textarea class="form-control rounded-0" id="ex1" style="background-color:black ;color:white" rows="16">${result}</textarea>
</div>
</div>
</form>
<script type="text/javascript">
function ipFileneeded() 
{
	  var ip_name = document.getElementById("ip_File").value;
	  console.log(ip_name);
	  if(ip_name == "")
		  {
		  alert("Please select a file!");
		  return false;
		  }
	  else if(!ip_name.endsWith(".cpp"))
		  {
		  alert("Please select a .cpp file!");
		  return false;
		  }
	  else
		  {
		  return true;
		  }	  
}
function ipneeded()
{
 	var text_area = document.getElementById("code_editor").value;
 	var checkBox = document.getElementById("checkBoxChecked");
 	if(text_area=="")
	 	{
	 	alert("Please load a file or type into the editor!");
	  	return false;
	 	}
 	if(checkBox.checked == true)
 		{
 		var option = document.getElementById("frequent").value;
 		if(option==="quick_option")
 			{
 			alert("Please select a valid option!");
 		  	return false;
 			}
 		}
 	else
 	{
	 return true;
 	}
		
}


     </script>
</body>
</html>
