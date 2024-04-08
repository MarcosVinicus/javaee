/*
 * @author Marcos Vinicius Vieira da Silva
 */

function confirmar(idcon) {
	let resposta = confirm("Deseja realmente excluir este contato?")
	if (resposta === true) {
		//alert(idcon)
		//+idcon estamos pegando idcon do confirmador.js para sair junto com o delete.
		window.location.href="delete?idcon=" + idcon	}
}