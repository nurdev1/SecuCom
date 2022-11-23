package com.SecuCom.SecuCom.service;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur addUser(Utilisateur utilisateur);
  //  Utilisateur Seconnecter(String userName,String password);
    Utilisateur donnerUserByUsername(String username);
    Utilisateur modifier(String userName, String password);
    String Sedeconnecter(Utilisateur utilisateur);
    List<Utilisateur> Afficher();
    Role addNewRole(Role role);
    void addRoleToUser(String username,String roleName);

}
