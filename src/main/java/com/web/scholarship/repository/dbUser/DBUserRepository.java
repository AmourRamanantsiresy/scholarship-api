package com.web.scholarship.repository.dbUser;

import com.web.scholarship.models.dbUser.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Long> {
    DBUser findDBUserByUsername(String user);
}
