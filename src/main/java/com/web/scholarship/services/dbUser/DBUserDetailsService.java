package com.web.scholarship.services.dbUser;

import com.web.scholarship.models.dbUser.DBUser;
import com.web.scholarship.repository.dbUser.DBUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DBUserDetailsService implements UserDetailsService {
    private DBUserRepository DBUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DBUser dbUser = DBUserRepository.findDBUserByUsername(username);
        if(dbUser == null){
            throw new UsernameNotFoundException(username);
        }
        return new DBUserDetails(dbUser);
    }
}
