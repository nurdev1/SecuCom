package com.SecuCom.SecuCom.configuration.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentificationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

    }
    public Authentication authentication(HttpServletRequest request, HttpServletResponse response) throws  Exception{
       String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    protected void successfulAuthentification(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)throws Exception{
       System.out.println("authentification avec succés");
        User user = (User) authResult.getPrincipal();
        Algorithm algo1= Algorithm.HMAC256("mySecret123");
        String jwtAccessToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+5*600))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles",user.getAuthorities().stream().map(ga ->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algo1);
        Map<String,String> idToken= new HashMap<>();
        idToken.put("access-token",jwtAccessToken);
        idToken.put("refresh_token",jwtAccessToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(),idToken);
        response.setHeader("Authorisation",jwtAccessToken);

     //   super.successfulAuthentication(request,response,chain,authResult);
    }
}
