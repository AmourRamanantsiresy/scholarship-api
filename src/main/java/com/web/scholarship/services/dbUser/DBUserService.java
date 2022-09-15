package com.web.scholarship.services.dbUser;

import com.web.scholarship.models.dbUser.DBRole;
import com.web.scholarship.models.dbUser.DBUser;
import com.web.scholarship.repository.dbUser.DBRoleRepository;
import com.web.scholarship.repository.dbUser.DBUserRepository;
import com.web.scholarship.utils.pagination.DataFormat;
import com.web.scholarship.utils.pagination.DataParser;
import com.web.scholarship.utils.utils.Order;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DBUserService {
    private DBUserRepository dbUserRepository;
    private DBRoleRepository dbRoleRepository;

    public DBUser createOrUpdate(DBUser dbUser){
        dbUser.setPassword(new BCryptPasswordEncoder().encode(dbUser.getPassword()));
        List<DBRole> temp = dbUser.getDbRoles();
        if(temp == null){
            temp = List.of(dbRoleRepository.findDBRoleByRole("user"));
        }
        dbUser.setDbRoles(temp);
        return dbUserRepository.save(dbUser);
    }

    public DataFormat<DBUser> getAll(int page, int size, Order order) {
        DataParser<DBUser> parse;
        parse = new DataParser<>(dbUserRepository.
                findAll(Sort.by(Sort.Direction.fromString(order.toString()), "username")));
        return parse.format(page, size);
    }

    public DBUser getByUsername(String username){
        return dbUserRepository.findDBUserByUsername(username);
    }
}
