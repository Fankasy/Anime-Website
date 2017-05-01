<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

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

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="index.htm">
                    <i class="fa fa-play-circle"></i> <span class="light">Anime</span> Station
                </a>
            </div>


            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav drop-down">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#about">About</a>
                    </li>
                    <li>
                        <a class="page-scroll "href="#contact" >Contact</a>
                    </li>
                    <li>
                        <a class="page-scroll"href="allanime.htm" >All Anime</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="allGroup.htm">Groups</a>
                    </li>

                    <li>
                        <a class="page-scroll" >Performers</a>
                    </li>
                    <!-- <li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li><a href="#">My Account</a></li>
           <li><a href="#">My Profile</a></li>
           <li><a href="#">My Group</a></li>
           <li role="separator" class="divider"></li>
           <li><a href="#">Log out</a></li>
           
         </ul>
       </li> -->
       				<li>
       					<a href="login.htm">login</a>
       					</li>

                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
        <!-- Intro Header -->
    <header class="intro">
        <div class="intro-body">
            <div class="container">
              <form:form action="register.htm" method="POST" commandName="user" enctype="multipart/form-data">
            
                <div class="row">
                    <div class="col-md-3 col-md-offset-3">
                        <h1 class="brand-heading">Register</h1>

                    </div>
                    
                </div>
                <div class="row">
                      <div class="col-md-4 col-md-offset-4">
                        UserName: <form:input type="text" class="form-control" placeholder="Username" path="userName"/> <form:errors path="userName"></form:errors>
                            
                      
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="col-md-4 col-md-offset-4">
                        UserName: <form:input type="email" class="form-control" placeholder="Email"  path="email"/><form:errors path="email"> </form:errors>
                            
                      
                      </div>
                    </div>
                <div class="row">
                	<div class="col-md-4 col-md-offset-4">
                	PassWord: <form:input type="password" class="form-control" placeholder="PassWord" path="password"/><form:errors path="password"/>
                	</div>
                </div>
                <br/>
                <div class="row">
                	<div class="col-md-4 col-md-offset-4">
                	<input class="btn btn-info form-control btn-lg"  value="Register" type="submit"/>
                	</div>
                	
                </div>
                
                </form:form>
            </div>
        </div>
    </header>
        <footer>
        <div class="container text-center">
            <p>Copyright &copy; ShaofanYu 2017</p>
        </div>
    </footer>
    </body>
    </html>