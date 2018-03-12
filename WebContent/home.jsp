<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- <a class="navbar-brand" href="#">Navbar</a> -->
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
</nav>
<section>
<div class="header-background">
<div class="header-content">
	<form method="post" action="SearchLucene">
		<input type="text" name="searchfield" placeholder="Search for a University"> 
		<br> <br>
		<button class="btn btn-primary" type="submit" value="searchLucence">Search with Lucene</button>
	</form>
	<!--  -->
		<form method="post" action="SearchHadoop">
		<input type="text" name="searchfield" placeholder="Search for a University"> 
		<br> <br>
		<button class="btn btn-primary" type="submit" value="searchHadoop" href="">Search with Hadoop</button>
	</form>
</div>
</div>
</section>

<section id="aboutproject">
<div class="container">
<div align="center">
<div class="title">
<span>CS242 Project</span>
</div>
</div>
<div class="row">
<div class="col">
<div class="projectimg-container">
<img src="assets/images/twitter-ipad.jpg" class="img-fluid">
</div>
</div>
<div class="col float-left">
	<p>This project crawls Twitter to find accounts that follow university Twitter profiles. It then determines what university the user goes to nd what their major is
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
Roya<br><br> Master's student in Computer Science at UCR <br><br>
<a href="https://www.linkedin.com/in/roya-delparastaran" target="_blank"><i class="fab fa-linkedin fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="https://github.com/royad" target="_blank"><i class="fab fa-github fa-3x"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<a href="mailto:rdelp002@ucr.edu"><i class="fab fa-google fa-3x"></i></a>
</div>

</div>
</div>

<div class="col-md-4">
<div class="card">
<div class="card-front">
<img src="assets/images/student.jpg" class="img-fluid">
</div>
<div class="card-back" align="center">
Jakupan<br><br> PhD student in Computer Science at UCR <br><br>
<i class="fab fa-linkedin fa-3x"></i>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<i class="fab fa-github fa-3x"></i>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<i class="fab fa-google fa-3x"></i>
</div>

</div>
</div>

<div class="col-md-4">
<div class="card">
<div class="card-front">
<img src="assets/images/student.jpg" class="img-fluid">
</div>
<div class="card-back" align="center">
Daniel<br><br> Master's student in Computer Science at UCR <br><br>
<i class="fab fa-linkedin fa-3x"></i>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<i class="fab fa-github fa-3x"></i>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<i class="fab fa-google fa-3x"></i>
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