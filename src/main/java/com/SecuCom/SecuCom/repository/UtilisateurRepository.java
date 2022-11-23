package com.SecuCom.SecuCom.repository;

import com.SecuCom.SecuCom.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO utilisateur(username,password) VALUES (\"Fatoumata\",\"123\");",
            nativeQuery = true)
    void creationadmin();
    Utilisateur findByUsername(String username);

}
