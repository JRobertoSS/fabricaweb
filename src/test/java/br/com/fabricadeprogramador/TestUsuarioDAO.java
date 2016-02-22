package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testAutenticar();
	}
	
	private static void testCadastrar(){

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
	private static void testAlterar(){

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
	private static void testExcluir(){

		// Criando o Usuário
		Usuario usu = new Usuario();
		usu.setId(3);
		
		//Criando o DAO do Usuário
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluído com sucesso!");
	}
	
	private static void testSalvar(){
		Usuario usuario = new Usuario();
		//usuario.setId(2);
		usuario.setNome("Pedro da Silva");
		usuario.setLogin("psilva");
		usuario.setSenha("ps12345");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		System.out.println("Salvo com Sucesso!");
	}
	
	private static void testBuscarPorId(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(2);
		System.out.println(usuario);
	}
	
	private static void testBuscarTodos(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		for (Usuario u: lista)
			System.out.println(u.toString());
	}
	
	private static void testAutenticar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("psilva");
		usuario.setSenha("ps12345");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario retorno = usuarioDAO.autenticar(usuario);
		System.out.println(retorno.toString());
	
	}
}
