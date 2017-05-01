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
  <input type="hidden" name="animeID" value="${animeID}"/>
    <form:form class="form-horizontal" action="manageAnimeDetail.htm" method="POST" commandName="anime" enctype="multipart/form-data">
      <div class="form-group">
    <label for="title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-10">
      <form:input type="text" class="form-control" name="title" placeholder="Title" path="title"/>
    </div>
  </div>

  <div class="form-group">
<label for="number" class="col-sm-2 control-label">Number of Episodes</label>
<div class="col-sm-10">
  <form:input type="text" class="form-control" name="number" placeholder="Number" path="numberOfEpisodes"/>
</div>
</div>

<div class="form-group">
<label for="TV" class="col-sm-2 control-label">The TV for playing</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="TV" placeholder="TV" path="playTV"/>
</div>
</div>

<div class="form-group">
<label for="production" class="col-sm-2 control-label">Production</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="production" placeholder="Production" path="production"/>
</div>
</div>

<div class="form-group">
<label class="col-sm-2 control-label">Descripion</label>
<div class="col-sm-10">
  <textarea class="form-control" rows="3" name="description" placeholder="Description"></textarea>

</div>
</div>



<div class="form-group">
<label for="artsupervising" class="col-sm-2 control-label">Art Supervising</label>
<div class="col-sm-10">
<form:input type="text" class="form-control" name="artsupervising" placeholder="Art Supervising" path="artSupervising"/>
</div>
</div>


<div class="form-group">
<label for="photo" class="col-sm-2 control-label">Photo</label>
<div class="col-sm-10">
  <form:input type="file" value="Upload Photo" class="form-control btn-default" name="upload" path="animePhoto"/>
</div>
</div>

<div class="form-group">
<div class="col-sm-2"></div>
<div class="col-sm-10">
<button class="btn btn-success form-control" type="submit">Save changes</button>
</div>

    </form:form>
</div>

</body>
</html>
