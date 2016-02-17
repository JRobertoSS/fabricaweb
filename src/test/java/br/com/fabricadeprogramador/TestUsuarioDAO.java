package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testExcluir();
	}
	
	public static void testCadastrar(){

		// Criando o Usuário
		Usuario usu = new Usuario();
		usu.setNome("Jãozão");
		usu.setLogin("jzao");
		usu.setSenha("1234");
		//Criando o DAO do Usuário
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("Cadastrado com sucesso!");
	}
	public static void testAlterar(){

		// Criando o Usuário
		Usuario usu = new Usuario();
		usu.setId(1);
		usu.setNome("Jão da Silva");
		usu.setLogin("jzaosilva");
		usu.setSenha("1234987");
		//Criando o DAO do Usuário
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("Alterado com sucesso!");
	}
	public static void testExcluir(){

		// Criando o Usuário
		Usuario usu = new Usuario();
		usu.setId(3);
		
		//Criando o DAO do Usuário
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluído com sucesso!");
	}
}
