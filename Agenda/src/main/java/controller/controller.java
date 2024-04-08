package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class controller.
 */
@WebServlet(urlPatterns = { "/controller", "/main", "/main1", "/insert", "/select", "/update", "/delete", "/report" })
public class controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public controller() {
		super();

	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());

		// Teste de conexão
		// dao.testeConexao();

		String requisicao = request.getServletPath();
		System.out.println(requisicao);
		if (requisicao.equals("/main")) {
			contatos(request, response);
		} else if (requisicao.equals("/insert")) {
			novoContato(request, response);
		}

		else if (requisicao.equals("/main1")) {
			listacontatos(request, response);
		} else if (requisicao.equals("/select")) {
			selecionarContato(request, response);
		} else if (requisicao.equals("/update")) {
			editarContato(request, response);
		}else if(requisicao.equals("/delete")) {
			excluirContato(request, response);
		}else if (requisicao.equals("/report")) {
			gerarRelatorio(request, response);
		}
		else {
			response.sendRedirect("Index.html");
		}

	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");
		// Criando um obejeto que ira receber os dados da classe JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhamento do objeto lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		// teste de recebimento de lista
		/*
		 * for (int i = 0; i < lista.size(); i++) {
		 * System.out.println(lista.get(i).getIdcon());
		 * System.out.println(lista.get(i).getNome());
		 * System.out.println(lista.get(i).getFone());
		 * System.out.println(lista.get(i).getEmail()); }
		 */

	}

	/**
	 * Listacontatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listacontatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("listadecontatos.jsp");
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento de dados do fromulário novo.html
		/*
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("e-mail"));
		 */
		// Setar os atributos da Classe JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("e-mail"));
		// invocar o método inserirContato passando o objeto contato

		dao.inserirContato(contato);
		// redirecionar para a página agenda.jsp
		response.sendRedirect("main");

	}

	/**
	 * Selecionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// editar contato
	protected void selecionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id de contato que será editado
		String idcon = request.getParameter("idcon");
		// System.out.println(idcon);
		contato.setIdcon(Integer.parseInt(idcon));
		// executar o metodo selecionarContato pelo DAO
		dao.selecaoContato(contato);

		// Teste de recebimento
		/*
		 * System.out.println(contato.getIdcon());
		 * System.out.println(contato.getNome()); System.out.println(contato.getFone());
		 * System.out.println(contato.getEmail());
		 *
		 */

		// request para enviar dados, response para receber
		// Setar os atributos ao formulário com o conteúdo da classe JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("e-mail", contato.getEmail());

		// encaminhar ao documento editar.jsp´
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String idcon = request.getParameter("");
		// teste de recebimento para edição

		/*
		 * System.out.println(request.getParameter("idcon"));
		 * System.out.println(request.getParameter("nome"));
		 * System.out.println(request.getParameter("fone"));
		 * System.out.println(request.getParameter("e-mail"));
		 */

		// Setar os atributos da Classe JavaBeans
		contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("e-mail"));
		// invocar o método inserirContato passando o objeto contato

		dao.edicaoContato(contato);

		response.sendRedirect("main");
	}
	
	/**
	 * Excluir contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void excluirContato(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		// Recebimento do id de contato que será excluído
	    String idcon = request.getParameter("idcon");
	   // System.out.println(idcon);
	    // Verifica se o idcon foi fornecido
	    if (idcon != null && !idcon.isEmpty()) {
	        // Setar o idcon ao objeto JavaBeans
	        contato.setIdcon(Integer.parseInt(request.getParameter("idcon")));
	        
	        // Executar o método excluirContato no DAO
	        dao.exclusaoContato(contato);
	        
	        // Redireciona de volta à página principal
	        response.sendRedirect("main");
	    } else {
	        // Se o idcon não foi fornecido, redireciona para uma página de erro ou para a página principal
	        response.sendRedirect("main");
	    }
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		Document documento= new Document();
		try {
			//Tipo de Conteúdo
			response.setContentType("application/pdf");
			//Nome do Documento
			response.addHeader("Content-Disposition", "inline; filename="+"contato.pdf");
			//Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//Abrir Documento --> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de Contatos"));
			//Listagem de contatos
			documento.add(new Paragraph(" "));
			//criar uma tabela no pdf
			PdfPTable tabela = new PdfPTable(3);
			//Cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("e-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			//popular a tabela com os contatos
			ArrayList<JavaBeans> lista= dao.listarContatos();
			for (int i=0; i<lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			documento.close();
		}
	}
	
	
}
