package com.SecuCom.SecuCom.contoller;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import com.SecuCom.SecuCom.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/Utilisateur")
public class UtilisateurController {

    @Autowired
    private final UtilisateurService utilisateurService;

    @PostMapping("/addUser")
    public Utilisateur addUser(@RequestBody Utilisateur user){

        return utilisateurService.addUser(user);
    }

    @PostMapping("/AjouterRole")
    public Role addRole(@RequestBody Role role){

        return utilisateurService.addNewRole(role);
    }

    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody String username,@RequestBody String roleName){
        utilisateurService.addRoleToUser(username, roleName);
    }
    @GetMapping("/Afficher")
    public List<Utilisateur> Afficher(){
        return utilisateurService.Afficher();
    }

    @PostMapping("/seconnecter/{username}/{password}")
    public String Seconnecter( @PathVariable String username, @PathVariable String password){
        return utilisateurService.seConnecter(username,password);
    }

    @PutMapping("/modifier")
    public String modifierUtilisateur(@RequestBody Utilisateur utilisateur){
        return utilisateurService.modifierUtilisateur(utilisateur);

    }


    }
