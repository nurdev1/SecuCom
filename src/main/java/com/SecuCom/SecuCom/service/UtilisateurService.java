package com.SecuCom.SecuCom.service;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UtilisateurService {
    Utilisateur addUser(Utilisateur utilisateur);
  //  Utilisateur Seconnecter(String userName,String password);
    Utilisateur donnerUserByUsername(String username);
    Utilisateur modifier(String userName, String password);
    String Sedeconnecter(Utilisateur utilisateur);
    String seConnecter(String username,String password);
    List<Utilisateur> Afficher();
    Role addNewRole(Role role);
    void addRoleToUser(String username,String roleName);
   String modifierUtilisateur(Utilisateur utilisateur);
   String supprimer(Long id);

}
