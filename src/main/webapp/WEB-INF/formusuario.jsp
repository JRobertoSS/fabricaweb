<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário</title>
</head>
<body>

<%@include file="menu.jsp" %>

<%
Usuario u = (Usuario) request.getAttribute("usuario");
%>
	<form action="usucontroller.do" method="post">
		ID: <input type="number" name="id" value="<% if(u != null) out.print(u.getId());%>" /> <br>
		Nome: <input type="text" name="nome" value="<% if(u != null) out.print(u.getNome());%>" /><br> 
		Login: <input type="text" name="login" value="<% if(u != null) out.print(u.getLogin());%>" /> <br>
		Senha :<input type="text" name="senha" value="<% if(u != null) out.print(u.getSenha());%>" /><br>
		
		<input type="submit" value="Salvar" />
	</form>
</body>
</html>