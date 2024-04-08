<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<%-- for (int i=0; i<lista.size(); i++) {
	 out.println(lista.get(i).getIdcon());
	 out.println(lista.get(i).getNome());
	 out.println(lista.get(i).getFone());
	 out.println(lista.get(i).getEmail());
	
	}--%>

<!DOCTYPE html>
<html>
<head lang="pt-br">
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="icon" href="Imagens/favicon.png">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body class="container">
	<center>
		<h1 class="agendatitle">Agenda de Contatos</h1>
		<a href="novo.html" class="botao1">Novo Contato</a> <br> <br>
		<a href="report" class="botao3">Relatório</a> <br> <br>
		<table id="tabela">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nome</th>
					<th>Fone</th>
					<th>E-mail</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%= lista.get(i).getIdcon() %>" class="botao3">Editar</a>
					<a href="javascript:confirmar(<%=lista.get(i).getIdcon()  %>)" class="botao2">Excluir</a></td>
				</tr>

				<%
				}
				%>
			</tbody>
		</table>
		<script type="text/javascript" src="scripts/confirmador.js"></script>
</body>
</center>
</html>