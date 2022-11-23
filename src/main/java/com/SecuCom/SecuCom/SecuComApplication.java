package com.SecuCom.SecuCom;

import com.SecuCom.SecuCom.repository.RoleRepository;
import com.SecuCom.SecuCom.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SecuComApplication implements CommandLineRunner  {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    public SecuComApplication(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(SecuComApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        if (utilisateurRepository.findAll().size()==0){
            roleRepository.creationrole();
            utilisateurRepository.creationadmin();
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
