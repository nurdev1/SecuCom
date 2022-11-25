package com.SecuCom.SecuCom.service.Implementation;

import com.SecuCom.SecuCom.service.UtilisateurService;
import lombok.AllArgsConstructor;
import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.SecuCom.SecuCom.repository.RoleRepository;
import com.SecuCom.SecuCom.repository.UtilisateurRepository;

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
    public String seConnecter(String username, String password) {
        if (utilisateurRepository.existsByUsername(username) && utilisateurRepository.existsByPassword(password)) {
            return "Connecter avec succès";
        }
        ;
        if (utilisateurRepository.existsByUsername(username) == false) {
            return "nom utilisateur non trouver";
        }
        return "Mots de passe ou nom utilisateur incorrect";
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
        utilisateur.getRole().add(role);

    }

    @Override
    public String modifierUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findById(utilisateur.getId()) !=null) {
            return utilisateurRepository.findById(utilisateur.getId())
                    .map(u -> {
                        u.setPassword(utilisateur.getPassword());
                        u.setPassword(utilisateur.getPassword());
                        utilisateurRepository.save(u);
                       return ("User modifié avec succes");
                    }).orElseThrow(() -> new RuntimeException("Cet utilisateur n'existe pas"));
        }else {
            return ("User non trouvée ");
        }
    }

    @Override
    public String supprimer(Long id) {
          if(utilisateurRepository.findById(id) != null){
            utilisateurRepository.deleteById(id);
          return ("User supprimé avec succes");
        }else {
            return ("User non trouvée");
        }
    }
}