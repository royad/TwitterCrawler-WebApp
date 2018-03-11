<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="cs242project.*, java.util.*, cs242project.HadoopSearcher.TermInfo" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
HadoopSearcher hadoopQuery = new HadoopSearcher();
Object searchedTerm = request.getAttribute("searchedTerm");
ArrayList<TermInfo> location = hadoopQuery.getUniversityValueByName("\"university of california, san diego\"");
StudentWithFreq students = new StudentWithFreq();
String id = TestHadoopQuery.getResult();
%>

<%=searchedTerm %>

<%=id %>

</body>
</html>