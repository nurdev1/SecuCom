package com.SecuCom.SecuCom.repository;

import com.SecuCom.SecuCom.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO role(rolename) VALUES (\"ADMIN\");",nativeQuery = true)
    void creationrole();
    Role findByRolename(String rolename);
}
