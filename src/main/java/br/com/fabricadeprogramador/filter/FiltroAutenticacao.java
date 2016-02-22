package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes={DispatcherType.REQUEST}, urlPatterns="/*") //filtra todas as requisições
public class FiltroAutenticacao implements Filter {

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI(); // para verificação se é tentativa de login (ou a tela após login bem sucedido)
		HttpSession sessao = httpRequest.getSession(false); // para verificação de sessão existente (usuário logado)
		
		if (sessao != null || uri.lastIndexOf("login.html") != -1 || 
				uri.lastIndexOf("autenticador.do") != -1){ // verifica se não deve filtrar (com as condições descritas acima)
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect("login.html");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		
	}

}
