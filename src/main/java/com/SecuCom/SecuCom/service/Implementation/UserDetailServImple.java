package com.SecuCom.SecuCom.service.Implementation;

import com.SecuCom.SecuCom.models.Utilisateur;
import com.SecuCom.SecuCom.service.UtilisateurService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
@Transactional
public class UserDetailServImple  {
  /*  private UtilisateurService utilisateurService;


    public UserDetails donnerUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.donnerUserByUsername(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRole().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRolename()));
        });
        return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);

    }*/
}
