<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" id="search-form" action="SearchLucene">
		<input type="text" name="searchfield" placeholder="Search for a University"> 
		<br> <br>
		<button class="btn btn-primary" type="submit" value="searchLucence">Search with Lucene</button>
		<button class="btn btn-primary" type="submit" value="searchHadoop" href="">Search with Hadoop</button>
	</form>

</body>
</html>