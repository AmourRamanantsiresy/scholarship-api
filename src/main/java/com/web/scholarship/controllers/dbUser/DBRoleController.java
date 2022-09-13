package com.web.scholarship.controllers.dbUser;

import com.web.scholarship.models.dbUser.DBRole;
import com.web.scholarship.services.dbUser.DBRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class DBRoleController {
    private DBRoleService dbRoleService;

    @PutMapping("")
    public List<DBRole> createOrUpdate(@RequestBody List<DBRole> dbRoles) {
        return dbRoleService.createOrUpdate(dbRoles);
    }

    @GetMapping("")
    public List<DBRole> getAll() {
        return dbRoleService.getAll();
    }
}
