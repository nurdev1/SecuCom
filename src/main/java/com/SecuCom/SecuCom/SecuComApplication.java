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
/*    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public SecuComApplication(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }*/


    public static void main(String[] args) {
        SpringApplication.run(SecuComApplication.class, args);
    }
    /*@Override
    public void run(String... args) throws Exception {
        if (utilisateurRepository.findAll().size()==0){
            roleRepository.creationrole();
            utilisateurRepository.creationadmin();
        }
    }*/

    @Bean
    CommandLineRunner start(UtilisateurService utilisateurService){
        return args -> {
            utilisateurService.addNewRole(new Role(null,"ADMIN"));
            utilisateurService.addNewRole(new Role(null,"USER"));

            utilisateurService.addUser(new Utilisateur( null,"Fatoumata","123",new ArrayList<>()));
            utilisateurService.addUser(new Utilisateur( null,"Fatou","123",new ArrayList<>()));

           /* utilisateurService.addRoleToUser("Fatoumata","ADMIN");
            utilisateurService.addRoleToUser("Fatoumata","USER");
            utilisateurService.addRoleToUser("Fatou","USER");
            */


        };
    }
/*return  args -> {
        Role r1=userService.saveRole(new Role(1L,"ROLE_USER"));
        Role r2=userService.saveRole(new Role(2L,"ROLE_ADMIN"));


        if(userService.getUser("john")==null){
            User u1=userService.saveUser(new User(null, "John Travolta", "john","1234",new ArrayList<>()));
        }

        if(userService.getUser("Kim")==null){
            User u2=userService.saveUser(new User(null, "Kim Kardashian", "Kim","1234",new ArrayList<>()));
        }

        if(userService.getUser("will")==null){
            User u3=userService.saveUser(new User(null, "Will Smith", "will","1234",new ArrayList<>()));
        }

        userService.addRoleToUser("john",r1.getNom());
        userService.addRoleToUser("Kim", r2.getNom());
        userService.addRoleToUser("will", r1.getNom());
    }*/

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
