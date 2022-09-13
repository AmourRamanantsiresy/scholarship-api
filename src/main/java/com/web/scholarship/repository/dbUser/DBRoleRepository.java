package com.web.scholarship.repository.dbUser;

import com.web.scholarship.models.dbUser.DBRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBRoleRepository extends JpaRepository<DBRole, Long> {
    DBRole findDBRoleByRole(String role);
}
