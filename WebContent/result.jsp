<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cs242project.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lucene Results</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
<link rel="stylesheet" href="assets/css/main.css">
</head>
<body>
<%
Object searchedTerm = request.getAttribute("searchedTerm");
String searchedTermStr = searchedTerm.toString();

LuceneSearcher lucene = new LuceneSearcher();

ArrayList value = lucene.searchLuceneIndex(searchedTermStr);
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- <a class="navbar-brand" href="#">Navbar</a> -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button> 
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">

      <li class="nav-item">
        <a class="nav-link" href="home.jsp"><i class="fas fa-id-badge"></i> Home</a>
      </li>

    </ul>
  </div>
</nav>

<section style="height:50%;">
<div class="header-background">
<div class="header-content">
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
</div>
</div>
</section>

<section>
<div class="container">
<table class="table">
<tbody>

<h4><%=searchedTermStr.toUpperCase() %></h4><br><br>

<% if(value.size()==0){ %>
<h4>No results found</h4>
<%} %>

<% for (int i = 0; i < value.size(); i++ ) { %>
<tr>
<td><img src=<%=value.get(i).toString().replace("_normal","")%> class="img-fluid img-thumbnail" style="max-width:250px;max-height:250px;"><br><br>
<%=value.get(++i) %></td>
<td><%= value.get(++i)%><br><%=value.get(++i)%><br><br><b>Lives in: </b><%=value.get(++i)%></td>
<td><b>Goes to: </b><br><%=value.get(++i)%><br><br>
<b>Studies: </b><br><%=value.get(++i)%></td>
</tr>
<% } %>
</tbody>
</table>
</div>
</section>

</body>
</html>