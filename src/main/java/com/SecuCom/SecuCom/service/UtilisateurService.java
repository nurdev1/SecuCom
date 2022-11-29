package com.SecuCom.SecuCom.service;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UtilisateurService {
    Utilisateur addUser(Utilisateur utilisateur);
    Utilisateur donnerUserByUsername(String username);
    String seConnecter(String username,String password);
    List<Utilisateur> Afficher();
    Role addNewRole(Role role);
    void addRoleToUser(String username,String rolename);
   String modifierUtilisateur(Utilisateur utilisateur);
   String supprimer(Long id);

}
