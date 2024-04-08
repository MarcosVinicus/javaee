<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Agenda de Contatos</title>
<link rel="icon" href="Imagens/favicon.png">
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body class="container">
	<h1>Editar Contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td><input id="caixa3" type="text" name="idcon" readonly
					value="<%out.print(request.getAttribute("idcon"));%>">
			</tr>
			<tr>
				<td><input class="caixa1" type="text" name="nome"
					value="<%out.print(request.getAttribute("nome"));%>">
			</tr>
			<tr>
				<td><input class="caixa1" type="text" name="fone"
					value="<%out.print(request.getAttribute("fone"));%>">
			</tr>
			<tr>
				<td><input class="caixa2" type="text" name="e-mail"
					value="<%out.print(request.getAttribute("e-mail"));%>">
			</tr>
		</table>
		<br> <input type="submit" value="Salvar" class="botao1"
			onclick="validar()">
	</form>
	<script type="text/javascript" src="scripts/validacao.js"></script>
</body>