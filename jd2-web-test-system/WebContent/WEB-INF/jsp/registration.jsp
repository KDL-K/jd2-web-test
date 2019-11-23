<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% String str=(String)request.getAttribute("unregistered"); 
   if(str==null){
	   out.println("");
   }else {
  	 out.println(str);
   }
 %>

    <form action="Controller" method="post">
      <input type="hidden" name="command" value="registration"/>
      Name: <input type="text" name="name" value=""/><br/>
      Surname: <input type="text" name="surname" value=""/><br/>
      E-mail: <input type="text" name="email" value=""/><br/>
      Sex: <input type="text" name="sex" value=""/><br/>
      Age: <input type="text" name="age" value=""/><br/>
      Login: <input type="text" name="login" value=""/><br/>
      Password: <input type="password" name="password" value=""/><br/>
      
      <input type="submit" value="register"/>
    </form>

</body>
</html>