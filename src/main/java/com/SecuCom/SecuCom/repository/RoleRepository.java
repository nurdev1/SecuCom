package com.SecuCom.SecuCom.repository;

import com.SecuCom.SecuCom.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRolename(String rolename);
}
