package br.com.fabricadeprogramador.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class AutenticadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sessao = req.getSession(false); // tenta acessar a sessão, não criando uma nova caso não encontre
		
		if (sessao!= null){
			sessao.invalidate();
		}
		resp.sendRedirect("login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.autenticar(usuario);
		
		if (usuario != null){ //foi autenticado?
			HttpSession sessao = request.getSession(); //tenta acessar a sessão, criando uma nova se não existir
			sessao.setAttribute("usuario", usuario); //coloca o usuário na sessão
			sessao.setMaxInactiveInterval(60*15); //configura para invalidar a sessão após 15min de inatividade
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response); //redireciona para a página inicial, autenticado
		} else {
			response.getWriter().print("<script> window.alert ('Falha na autenticação!');"
					+ " location.href='login.html'</script>");
			
		}
		
		
	}

}
