<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Usuários</title>
<script type="text/javascript">
function confirmaExclusao(id){
	if (window.confirm("Tem certeza que deseja excluir?")){
		location.href="usucontroller.do?acao=excluir&id=" + id;
	}
}

	function novo(){
		location.href='usucontroller.do?acao=cadastrar'
	}
</script>
</head>
<body>

<%@include file="menu.jsp" %>

	<table border=1>
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Acao</th>
		</tr>
		<%
		List<Usuario> lista = (List<Usuario>)request.getAttribute("lista");
		
		for (Usuario u: lista){%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getNome()%></td>
			<td><%=u.getLogin()%></td>
			<td><a href="usucontroller.do?acao=alterar&id=<%=u.getId()%>">alterar</a> |
				<a href="javascript:confirmaExclusao(<%=u.getId()%>)">excluir</a></td>	
		</tr>
		<%}%>
	
	</table>
	<input type="button" value="Novo Usuário" onclick="javascript:novo()" />
</body>
</html>