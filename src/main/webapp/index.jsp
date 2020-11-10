<!DOCTYPE html>
<html lang="en">
<head>
  <title>SmartGCC</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Comic+Neue&display=swap" rel="stylesheet">
  
  <style>
      h1 {
        font-family: 'Comic Neue', 'Lobster', serif;
        font-size: 72px;
        color:   #002080;
        padding-top: 10px;
        
      }
      .form_used
      {
      font-family: 'Comic Neue';
      font-size:18px;
      }
  </style>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container-fluid">
  <div class="col-lg-6">	
      <h1>SmartGCC</h1>	
    </div>
    <div class="row">
    <div class="col-sm-6 form_used" >
    <h2>Sign In</h2>
    <form action="clerk/user_login" method="post" class="was-validated">
    <div class="w-75 form-group">
      <label for="uname" name="uname1">Username:</label>
      <input type="text" class="form-control" id="uname" placeholder="Enter username" name="uname" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="w-75 form-group">
      <label for="pwd" name="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <button type="submit" class="btn btn-primary">Sign In</button>
    <label>${msg}</label>
  </form>
  </div>

  <div class="col-sm-6 form_used" >
  <h2>Sign Up</h2>
  <form action="clerk/user_register" method="post" class="was-validated">
    <div class="w-75 form-group">
      <label for="uname" name="uname1">Username:</label>
      <input type="text" class="form-control" id="uname" placeholder="Enter username" name="uname" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="w-75 form-group">
      <label for="pwd" name="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="w-75 form-group">
      <label for="cnpwd" name="cnpwd">Confirm Password:</label>
      <input type="password" class="form-control" id="cnpwd" placeholder="Enter password" name="cnpswd" required>
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="w-75 form-group">
      <label for="cnpwd">Please select the user type</label>
      <div class="w-75 dropdown form_used">
        <select id="usertype" name="usertype">
            <option value="Beginner">I am a beginner</option>
            <option value="Typical">I am experienced</option>
            <option value="Expert">I am an expert</option>
        </select>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Sign up</button>
    <label class="userError"></label>
  </form>
  </div>
  </div>
</div>
</body>
<script>

window.onload =function userExists()
{
    var user="<%=session.getAttribute("user")%>";
	console.log(user);
	if(user === null)
		{
		console.log(user.length);
		alert(user +" already exists..");
		}
	
}
</script>
</html>
