package com.SecuCom.SecuCom.configuration;

import com.SecuCom.SecuCom.configuration.filter.JwtAuthentificationFilter;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.SecuCom.SecuCom.service.UtilisateurService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            private UtilisateurService utilisateurService;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Utilisateur utilisateur = utilisateurService.donnerUserByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                utilisateur.getRoles().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority(role.getRolename()));
                });
                return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/he-console/**").permitAll();
        //  http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthentificationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthentificationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
