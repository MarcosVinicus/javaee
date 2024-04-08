package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
//Módulo de conexão
//Parametros para conexão

	/** The driver. */
private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "backend@24";

/**
 * Conectar.
 *
 * @return the connection
 */
//Método de conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	// CRUD CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?, ?, ?)";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a consulta(query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametro pelo Conteúdo dos atributos da Classe JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query (Ctrl+Enter)
			pst.executeUpdate();
			// Sempre fechar o banco de dados/ encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	// Crud read

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContatos() {
		// Criando o objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList();

		String read = "select * from contatos order by idcon;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			//
			ResultSet rs = pst.executeQuery();
			// o laço abaixo sera executador enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				int idcon = rs.getInt(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

//Crud update
	/**
 * Selecao contato.
 *
 * @param contato the contato
 */
// selecionar contato
	public void selecaoContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon=?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);

			pst.setInt(1, contato.getIdcon());

			ResultSet rs = pst.executeQuery();

			// enquanto houver dados fazer a seleção
			// laço while para receber dados do BD e enviar para a classe JB
			while (rs.next()) {
				// setar as variasveis JavaBeans
				contato.setIdcon(rs.getInt(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));

			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Edicao contato.
	 *
	 * @param contato the contato
	 */
	public void edicaoContato(JavaBeans contato) {
		String read3 = "update contatos set nome=?,fone=?,email=? where idcon=?;";
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a consulta(query) para execução no Banco de Dados
			PreparedStatement pst = con.prepareStatement(read3);
			// Substituir os parametro pelo Conteúdo dos atributos da Classe JavaBeans

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, Integer.toString(contato.getIdcon()));

			// Executar a query (Ctrl+Enter)
			pst.executeUpdate();
			// Sempre fechar o banco de dados/ encerrar a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	/**
	 * Exclusao contato.
	 *
	 * @param contato the contato
	 */
	public void exclusaoContato(JavaBeans contato) {
	    String delete = "DELETE FROM contatos WHERE idcon = ?;";
	    try {
	        // Abrir a conexão
	        Connection con = conectar();
	        // Preparar a consulta(query) para execução no Banco de Dados
	        PreparedStatement pst = con.prepareStatement(delete);
	        // Substituir os parâmetros pelo ID do contato
	        pst.setString(1, Integer.toString(contato.getIdcon()));
	        // Executar a query para excluir o contato
	        pst.executeUpdate();
	        // Sempre fechar o banco de dados/encerrar a conexão com o banco
	        con.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
//Teste de conexão
	/*
	 * public void testeConexao() { try { Connection con = conectar();
	 * System.out.println(con); con.close(); } catch (Exception e) {
	 * System.out.println(e); } }
	 */
	
}
