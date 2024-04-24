package com.quadrart.Config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quadrart.Models.Usuario.Usuario;
import com.quadrart.Services.JwtService.JwtService;
import com.quadrart.Services.UsuarioService.UsuarioService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtService jwtService;

    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jwt = null;

        final String username;

        if (request.getCookies() != null){
            for (Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("accessToken")){
                    jwt = cookie.getValue();
                }
            }
        }

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.extractUsername(jwt);

        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
			Usuario usuario = usuarioService.loadUserByUsername(username);

			if (jwtService.isTokenValid(jwt, usuario)) {
				SecurityContext context = SecurityContextHolder.createEmptyContext(); 
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						usuario, null);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
		}
		filterChain.doFilter(request, response);


    }
    
}
