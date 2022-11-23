package com.SecuCom.SecuCom.service.Implementation;

import com.SecuCom.SecuCom.service.UtilisateurService;
import lombok.AllArgsConstructor;
import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.SecuCom.SecuCom.repository.RoleRepository;
import com.SecuCom.SecuCom.repository.UtilisateurRepository;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
//@Transactional
public class UtilisateurServImple implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur addUser(Utilisateur utilisateur) {
        String pw = utilisateur.getPassword();
        utilisateur.setPassword(passwordEncoder.encode(pw));
       return utilisateurRepository.save(utilisateur);
    }


    @Override
    public Utilisateur donnerUserByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }

    @Override
    public Utilisateur modifier(String userName, String password) {
        return null;
    }

    @Override
    public String Sedeconnecter(Utilisateur utilisateur) {
        return null;
    }


    @Override
    public List<Utilisateur> Afficher() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        Role role = roleRepository.findByRolename(roleName);
        utilisateur.getRoles().add(role);

    }
}
