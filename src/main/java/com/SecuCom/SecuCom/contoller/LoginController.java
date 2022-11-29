package com.SecuCom.SecuCom.contoller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.StandardClaimAccessor;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/login")

public class LoginController {

    @RolesAllowed("USER")
    @RequestMapping("/*")
    public String getUser(Authentication authentication){
        return "welcome "+authentication.getName();
    }

    @RequestMapping("/admin")
    @RolesAllowed({"USER","ADMIN"})
    public String getAdmin(Authentication authentication){
        return "welcome "+authentication.getName();
    }

  /*  @RequestMapping("/*")
    public String getGithub(Authentication authentication){
        return "welcome, "+authentication.getName()+ "connecter avec github";
    }*/
    private OidcIdToken getIdToken(OAuth2User principal){
        if (principal instanceof DefaultOidcUser){
            DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
        }
            return null;
    }
    private String getName(Authentication authentication){
        return Optional.of(authentication)
                .filter(OAuth2AuthenticationToken.class::isInstance)
                .map(OAuth2AuthenticationToken.class::cast)
                .map(OAuth2AuthenticationToken::getPrincipal)
                .map(DefaultOidcUser.class::cast)
                .map(StandardClaimAccessor::getEmail)
                .orElse(authentication.getName());
    }
  private OidcIdToken getidToken(OAuth2User principal){
        if (principal instanceof DefaultOidcUser){
            DefaultOidcUser oidcUser = (DefaultOidcUser) principal;
            return oidcUser.getIdToken();
        }
        return null;
  }
}
