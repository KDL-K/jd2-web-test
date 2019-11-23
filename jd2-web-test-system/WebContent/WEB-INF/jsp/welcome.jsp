<%@ page language="java" import="by.htp.ts.bean.User" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
<% User user=(User)request.getAttribute("user");
   out.println("WELCOME "+user.getName()+"!!!");

%>
</body>
</html>