<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page session="true" %>
     <%@page import="com.shaofan.spring.pojo.User" %>
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
                <a class="navbar-brand page-scroll" href="#page-top">
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
                   
                    <% 
                    User user=(User)session.getAttribute("user");
            		if(user!=null){
            			if(user.isAdmin()){
            			
            			%>
            				 <li>
                    		<a href="adminManage.htm">Admin</a>
                    	</li>
            		<% 	}}
            				%>
                    
                   
                   
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
                    <c:if test="${user==null}">
                    <li>
                    	<a href="login.htm">login</a>
                    </li>
                    
                    </c:if>
                    <c:if test="${!empty user}">
                    <li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
         <ul class="dropdown-menu">
           <li><a href="#">My Account</a></li>
           <li><a href="#">My Profile</a></li>
           <li><a href="#">My Group</a></li>
           <li role="separator" class="divider"></li>
           <li><a href="logout.htm">Log out</a></li>
           
         </ul>
       </li>
		</c:if>
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
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <h1 class="brand-heading">Group</h1>

                     
                    </div>
                    <div class="row">
                      <div class="col-md-8 col-md-offset-2">
                        <form  action="addGroup.htm" method="post">
                        <input type="text" class="form-control" placeholder="Group Name" name="groupName" >
                        <input type="text" class="form-control" placeholder="Group Description" name="description" >
                        <button type="submit" class="btn btn-success">Add Group</button>
                         </form>
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- About Section -->
    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>About Anime Station</h2>
                <p>Grayscale is a free Bootstrap 3 theme created by Start Bootstrap. It can be yours right now, simply download the template on <a href="http://startbootstrap.com/template-overviews/grayscale/">the preview page</a>. The theme is open source, and you can use it for any purpose, personal or commercial.</p>
                <p>This theme features stock photos by <a href="http://gratisography.com/">Gratisography</a> along with a custom Google Maps skin courtesy of <a href="http://snazzymaps.com/">Snazzy Maps</a>.</p>
                <p>Grayscale includes full HTML, CSS, and custom JavaScript files along with LESS files for easy customization.</p>
            </div>
        </div>
    </section>

    <!-- Download Section -->
    <section id="download" class="content-section text-center ">
        <div class="download-section">
            <div class="container">
                <div class="col-lg-8 ">
                    <br/><br/><br/><br/><br/><br/><br/><br/><br/>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Contact Start Bootstrap</h2>
                <p>Feel free to email us to provide some feedback on our templates, give us suggestions for new templates and themes, or to just say hello!</p>
                <p><a href="mailto:yu.shaof@husky.neu.edu">yu.shaof@husky.neu.edu</a>
                </p>
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://twitter.com/Fankasyyu" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                    <li>
                        <a href="https://github.com/Fankasy" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                    </li>
                    
                </ul>
            </div>
        </div>
        <br>
        <br><br><br>
    </section>

    <!-- Map Section -->

    <div id="map"></div>

    <!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; ShaofanYu 2017</p>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Google Maps API Key - Use your own API key to enable the map feature. More information on the Google Maps API can be found at https://developers.google.com/maps/ -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>

    <!-- Theme JavaScript -->
    <script src="js/grayscale.js"></script>

</body>

</html>
