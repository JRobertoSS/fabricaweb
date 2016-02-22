package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome, login, senha) " + "values (?, ?, md5(?));";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// substituir todas ? pelos valores corretos
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			// executar o comando
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=?, senha=md5(?) " + "where id=? ";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// substituir todas ? pelos valores corretos
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			// executar o comando
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=? ";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			// substituir todas ? pelos valores corretos
			preparador.setInt(1, usu.getId());
			// executar o comando
			preparador.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salvar(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() > 0)
			alterar(usuario);
		else
			cadastrar(usuario);
	}

	/**
	 * Busca um registro no banco de dados através do id do usuário
	 * 
	 * @param id
	 *            id do usuário a ser pesquisado no banco de dados
	 * @return objeto de Usuario com os dados, ou null se não houver registro
	 */
	public Usuario buscarPorId(Integer id) {
		String sql = "select * from usuario where id = ?";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Realiza a busca de todos registros da tabela de usuários
	 * 
	 * @return Uma lista com os registros encontrados
	 */
	public List<Usuario> buscarTodos() {
		String sql = "select * from usuario";
		List<Usuario> lista = new ArrayList<>();
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Usuario autenticar(Usuario consulta) {
		String sql = "select * from usuario where login = ? and senha = md5(?)";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, consulta.getLogin());
			preparador.setString(2, consulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			
			if (resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				return usuario;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
