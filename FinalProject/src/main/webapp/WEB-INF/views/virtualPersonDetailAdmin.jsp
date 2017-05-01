<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Anime Station</title>

  <!-- Bootstrap Core CSS -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom Fonts -->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">

  <!-- Theme CSS -->
  <link href="css/grayscale.css" rel="stylesheet">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>
<body >
  <div class="container">
        <input type="hidden" class="form-control" name="virtualpersonId"  value="${virtualpersonId}" placeholder="${virtualpersonId }"/>
  
    <form:form class="form-horizontal" action="manageVirtualPersonDetail.htm" method="POST" commandName="virtualPerson" enctype="multipart/form-data">
    
   
    

  
      <div class="form-group">
    <label for="title" class="col-sm-2 control-label">First Name</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" name="fname" placeholder="First Name" path="fname"/>
    </div>
  </div>

  <div class="form-group">
<label for="number" class="col-sm-2 control-label">Last Name</label>
<div class="col-sm-10">
  <form:input type="text" class="form-control" name="lname" placeholder="Last Name" path="lname"/>
</div>
</div>

<div class="form-group">
<label for="TV" class="control-label col-sm-2">Gender</label>

<div class="col-sm-1">
Male<input type="radio" class="form-check" name="gender" value="Male" />
</div>
<div class="col-sm-2">
Female<input type="radio" class="form-check" name="gender" value="Female" />
</div>
</div>

<div class="form-group">
<label for="bornPlace" class="col-sm-2 control-label">Born Place</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="bornPlace" placeholder="Born Place" path="bornplace"/>
</div>
</div>

<div class="form-group">
<label for="weight" class="col-sm-2 control-label">Weight</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="weight" placeholder="Weight" path="weight"/>
</div>
</div>



<div class="form-group">
<label for="age" class="col-sm-2 control-label">Age</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="age" placeholder="Age" path="age"/>
</div>
</div>

<div class="form-group">
<label for="height" class="col-sm-2 control-label">Height</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="height" placeholder="Height" path="height"/>
</div>
</div>








<div class="form-group">
<label for="photo" class="col-sm-2 control-label">Photo</label>
<div class="col-sm-10">
  <form:input type="file" value="Upload Photo" class="form-control btn-default" name="upload" path="photo"/>
</div>
</div>

<div class="form-group">
<div class="col-sm-2"></div>
<div class="col-sm-10">
<button class="btn btn-success form-control" type="submit">Save the Changes</button>
</div>


</div>
    </form:form>
</div>

</body>
<script src="vendor/jquery/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Plugin JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

<!-- Google Maps API Key - Use your own API key to enable the map feature. More information on the Google Maps API can be found at https://developers.google.com/maps/ -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>

<!-- Theme JavaScript -->
<script src="js/grayscale.js"></script>
<script type="text/javascript src="js/jquery.1.8.3.min.js"></script>


</html>
