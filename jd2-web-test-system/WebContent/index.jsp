<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
 
 <% String str=(String)request.getAttribute("registered"); 
   if(str==null){
	   out.println("");
   }else {
  	 out.println(str);
   }
 %>
    <form action="Controller" method="post">
      <input type="hidden" name="command" value="authorization"/>
      Login:
      <input type="text" name="login" value=""/><br/>
      Password:
      <input type="password" name="password" value=""/><br/>
      <input type="submit" value="sign in"/>
      <a href="register">   registration</a>
    </form>

</body>
</html>