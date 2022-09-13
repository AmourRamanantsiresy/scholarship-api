package com.web.scholarship.controllers.dbUser;

import com.web.scholarship.models.dbUser.DBUser;
import com.web.scholarship.services.dbUser.DBUserService;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.utils.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class DBUserController {
    private DBUserService dbUserService;

    @PutMapping("")
    public DBUser createOrUpdate(@RequestBody DBUser dbUsers) {
        return dbUserService.createOrUpdate(dbUsers);
    }

    @GetMapping("")
    public DataFormat<DBUser> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Order order
    ) {
        return dbUserService.getAll(page, size, order);
    }

    @GetMapping("/search")
    public DBUser getByUsername(
            @RequestParam(name = "name") String name
    ) {
        return dbUserService.getByUsername(name);
    }
}

