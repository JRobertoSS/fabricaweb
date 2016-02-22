package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		System.out.println("Construtor");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Init");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		UsuarioDAO usuDAO = new UsuarioDAO();
		if (acao.equals("excluir")) {
			String id = req.getParameter("id");
			Usuario usuario = new Usuario();
			if (id != null) {
				usuario.setId(Integer.valueOf(id));
				usuDAO.excluir(usuario);
				resp.sendRedirect("usucontroller.do?acao=listar");
			}
		} else if (acao.equals("listar")) {
			List<Usuario> lista = usuDAO.buscarTodos();
			req.setAttribute("lista", lista);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("alterar")){
			String id = req.getParameter("id");
			Usuario usuario = usuDAO.buscarPorId(Integer.valueOf(id));
			req.setAttribute("usuario", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp"); // indica pra onde redirecionar
			dispatcher.forward(req, resp); //redireciona
		} else if (acao.equals("cadastrar")){
			req.getRequestDispatcher("WEB-INF/formusuario.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usu = new Usuario();
		usu.setId(Integer.valueOf(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usu);

		resp.getWriter().print("Cadastro realizado com sucesso!");
	}

	@Override
	public void destroy() {
		System.out.println("Destroy");
		super.destroy();
	}
}
