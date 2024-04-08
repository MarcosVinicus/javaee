/**
 * Validação do Formulário
 * 
 * @author Marcos Vinicius Vieira da Silva
 */

function validar() {
	/*alert("test")*/

	let nome = frmContato.nome.value
	let fone = frmContato.fone.value


	if (nome === "" && fone === "") {
		alert("Preencha o campo nome e o campo fone!")
		frmContato.nome.focus()
		frmContatos.fone.focus()
		return false
	} else if (nome === "") {
		alert("Preencha o campo nome!")
		frmContato.nome.focus()
		return false
	} else if (fone == "") {
		alert("Preencha o campo fone!")
		frmContato.fone.focus
		return false
	} else {
		document.forms["frmContato"].submit()
	}
}

