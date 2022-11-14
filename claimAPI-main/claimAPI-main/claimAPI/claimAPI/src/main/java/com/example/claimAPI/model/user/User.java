package com.example.claimAPI.model.user;
import com.example.claimAPI.util.Role;
import com.example.claimAPI.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.RolesAllowed;
import javax.management.relation.RoleList;
import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table (name = "users")
public class User implements UserDetails {
    @Id
    private long userID;
    @Column(name = "userName")
    private String username;
    private String email;
    @Column(name = "userPassword")
    private String password;
    private Role rolePrivilege = Role.USER;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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
