package com.SecuCom.SecuCom.contoller;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/Login")

public class LoginController {

    /*@RequestMapping("/**")
    @RolesAllowed("USER")
    public String getUser(){
        return "welcome, User";
    }

    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    public String getAdmin(){
        return "welcome, Admin";
    }

    @RequestMapping("/*")
    public String getGithub(){
        return "welcome, GitHub User";
    }
    private OidcIdToken getIdToken(OAuth2User principal){
        if (principal instanceof DefaultOidcUser){
            DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
        }
            return null;
    }*/
    @GetMapping("/public")
    public String publicPage(){
        return "Hello!";
    }
    @GetMapping("/private")
    public String privatePage(){
        return "this page is private!";
    }
}
