package br.pro.software.eleicoes2020.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import br.pro.software.eleicoes2020.model.Login;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//pega a sessão
		HttpSession session = request.getSession();
		//se ainda não logou, manda para a página de login
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/login");
			return false;
		}
		Login logado = (Login) session.getAttribute("login");
		if (request.getRequestURI().contains("/master/") && !logado.getLogin().startsWith("master")) {
			response.sendRedirect("/logout");
			return false;
		}
		//se já logou, deixa a requisição passar e chegar no controller
		return true;
	}
}