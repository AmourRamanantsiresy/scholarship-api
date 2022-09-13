package com.web.scholarship.services.dbUser;

import com.web.scholarship.models.dbUser.DBRole;
import com.web.scholarship.models.dbUser.DBUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class DBUserDetails implements UserDetails {
    private DBUser dbUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(dbUser.getDbRoles().size());
        for (DBRole dbRole : dbUser.getDbRoles()) {
            Assert.isTrue(!dbRole.getRole().startsWith("ROLE_"),
                    () -> dbRole.getRole() + " cannot start with ROLE_ (it is automatically added)");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + dbRole.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return dbUser.getPassword();
    }

    @Override
    public String getUsername() {
        return dbUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
