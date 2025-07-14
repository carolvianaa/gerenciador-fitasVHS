package edu.ifpr.trabalho_semestral.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import edu.ifpr.trabalho_semestral.entities.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        boolean acessoLiberado = 
            uri.startsWith("/login") || 
            uri.startsWith("/cadastro") ||
            uri.startsWith("/css") || 
            uri.startsWith("/js") ||
            uri.startsWith("/images");

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

        if (usuario != null || acessoLiberado) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login");
        }
    }
}