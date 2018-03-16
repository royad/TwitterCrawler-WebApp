<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter Crawler</title>
<script src="jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
<link rel="stylesheet" href="assets/css/main.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>

</head>
<body>
<!--  
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">

      <li class="nav-item">
        <a class="nav-link" href="#">About Ptoject</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About Us</a>
      </li>

    </ul>
  </div>
</nav>-->

<section>

<div class="header-background">
<!--  <video autoplay muted loop id="myVideo">
  <source src="assets/images/walking.mp4" type="video/mp4">
  Your browser does not support HTML5 video.
</video>-->

<div class="header-content" align="center">
	<!-- <h1 style="padding-bottom:50px;color:#fff">Find Students</h1>-->

	<form method="post" action="Search" style="width:100%;">
			<div class="row">
			<div class="col-md-12">
		<input type="text" name="searchfield" placeholder="Search for a University" style="width:100%;">
		</div>
		</div> 
		<br>
		<div class="row">
		<div class="col-md-6">
		<input class="btn btn-primary" type="submit" value="Search Lucene" name="submitButton" style="width:100%;">
		</div>
		<div class="col-md-6">
				<input class="btn btn-primary" type="submit" value="Search Hadoop" name="submitButton" style="width:100%;">
				</div>
				</div>
		
	</form>
	<!-- 
		<form method="post" action="SearchHadoop">
		<input type="text" name="searchfield" placeholder="Search for a University"> 
		<br> <br>
		<input class="btn btn-primary" type="submit" value="searchHadoop" name="submitButton">Search with Hadoop
	</form>-->
</div>
<a href="#aboutproject">
<div class="header-arrow" id="arrow" align="center">
<i class="down"></i>
</div>
</a>
</div>
</section>
<script>
$('a[href^="#"]').on('click', function(event) {
    var target = $(this.getAttribute('href'));
    if( target.length ) {
        event.preventDefault();
        $('html, body').stop().animate({
            scrollTop: target.offset().top
        }, 1000);
    }
});
</script>
<section id="aboutproject">
<div class="container">
<div align="center">
<div class="title">
<span>Information Retrieval Project</span>
</div>
</div>
<div class="row">
<div class="col">
<div class="projectimg-container">
<img src="assets/images/twitter-ipad.jpg" class="img-fluid">
</div>
</div>
<div class="col float-left">
	<p>This project crawls Twitter to find accounts that follow university Twitter profiles. It then determines what university the user goes to and what their major is
	based on the information they have provided in their bio as well as their 200 most recent tweets.</p>
</div>

</div>
</div>
</section>

<section id="aboutus" style="background-color:#F8F8F8;">
<div class="container" align="center">
<div class="title">
<span style="background-color:#F8F8F8;">About Us</span>
</div>
<div class="row">
<div class="col-md-4">
<div class="card">
<div class="card-front">
<img src="assets/images/RoyaDelparastaran.jpg" class="img-fluid">
</div>
<div class="card-back" align="center">
Roya<br><br> Master's student in Computer Science at UCR <br><br><br>
<a href="https://www.linkedin.com/in/roya-delparastaran" target="_blank"><i class="fab fa-linkedin fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="https://github.com/royad" target="_blank"><i class="fab fa-github fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="mailto:rdelp002@ucr.edu"><i class="fab fa-google fa-3x"></i></a>
</div>

</div>
</div>

<div class="col-md-4">
<div class="card">
<div class="card-front">
<img src="assets/images/brook.jpg" class="img-fluid">
</div>
<div class="card-back" align="center">
Jakapun<br><br> PhD student in Computer Science at UCR <br><br><br>
<a href="https://www.linkedin.com/in/jakapun-tachaiya-8abba9118" target="_blank"><i class="fab fa-linkedin fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="https://github.com/JakapunTachaiya" target="_blank"><i class="fab fa-github fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="mailto:jtach001@ucr.edu"><i class="fab fa-google fa-3x"></i></a>
</div>

</div>
</div>

<div class="col-md-4">
<div class="card">
<div class="card-front">
<img src="assets/images/daniel.jpg" class="img-fluid">
</div>
<div class="card-back" align="center">
Daniel<br><br> Master's student in Computer Science at UCR <br><br><br>
<a href="https://www.linkedin.com/in/daniel-handojo-646963139" target="_blank"><i class="fab fa-linkedin fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="https://github.com/Fire3galaxy" target="_blank"><i class="fab fa-github fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="mailto:dhand002@ucr.edu"><i class="fab fa-google fa-3x"></i></a>
</div>

</div>
</div>
</div>
</div>
</section>

<footer>
<div class="container" style="padding:0">
&copy; copyright 2018
</div>
</footer>



</body>
</html>