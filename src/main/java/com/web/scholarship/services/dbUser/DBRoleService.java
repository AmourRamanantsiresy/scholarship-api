package com.web.scholarship.services.dbUser;

import com.web.scholarship.models.dbUser.DBRole;
import com.web.scholarship.repository.dbUser.DBRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DBRoleService {
    private DBRoleRepository DBRoleRepository;

    public List<DBRole> createOrUpdate(List<DBRole> dbRoles) {
        return DBRoleRepository.saveAll(dbRoles);
    }

    public List<DBRole> getAll() {
        return DBRoleRepository.findAll();
    }
}
