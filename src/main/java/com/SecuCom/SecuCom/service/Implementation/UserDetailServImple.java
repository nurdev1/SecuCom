package com.SecuCom.SecuCom.service.Implementation;

import com.SecuCom.SecuCom.service.UtilisateurService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServImple  {
    private UtilisateurService utilisateurService;

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.donnerUserByUsername(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(utilisateur.getUsername(), utilisateur.getPassword(),authorities);
    }*/
}
