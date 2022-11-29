package com.SecuCom.SecuCom.configuration;

import com.SecuCom.SecuCom.configuration.filter.JwtAuthentificationFilter;
import com.SecuCom.SecuCom.configuration.filter.jwtAuthorizationFilter;
import com.SecuCom.SecuCom.models.Utilisateur;
import com.SecuCom.SecuCom.service.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private UtilisateurService utilisateurService;

    public SpringSecurityConfig(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(new UserDetailsService() {

                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    Utilisateur utilisateur = utilisateurService.donnerUserByUsername(username);
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    utilisateur.getRole().forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role.getRolename()));
                    });
                    return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
                }

        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        //http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated();
        http.formLogin();
        http.oauth2Login();
        http.addFilter(new JwtAuthentificationFilter(authenticationManagerBean()));
        http.addFilterBefore(new jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
