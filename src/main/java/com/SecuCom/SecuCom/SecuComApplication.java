package com.SecuCom.SecuCom;

import com.SecuCom.SecuCom.models.Role;
import com.SecuCom.SecuCom.models.Utilisateur;
import com.SecuCom.SecuCom.repository.RoleRepository;
import com.SecuCom.SecuCom.repository.UtilisateurRepository;
import com.SecuCom.SecuCom.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecuComApplication   {



    public static void main(String[] args) {
        SpringApplication.run(SecuComApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner start(UtilisateurService serviceUtilisateur) {
        return args -> {
            serviceUtilisateur.addNewRole(new Role(null, "USER"));
            serviceUtilisateur.addNewRole(new Role(null, "ADMIN"));
            serviceUtilisateur.addUser(new Utilisateur(null, "Fatoumata", "1234", new ArrayList<>()));
            serviceUtilisateur.addUser(new Utilisateur(null, "Fatou", "1234",  new ArrayList<>()));
            serviceUtilisateur.addRoleToUser("Fatoumata","ADMIN");
            serviceUtilisateur.addRoleToUser("Fatou","USER");
        };
    }

}
