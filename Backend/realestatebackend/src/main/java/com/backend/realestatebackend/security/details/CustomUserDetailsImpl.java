package com.backend.realestatebackend.security.details;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.realestatebackend.collections.UserCollection;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomUserDetailsImpl implements UserDetails {
    private String username;
    private String email;
    private String id;
    private String dp;
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    public CustomUserDetailsImpl(String id, String username, String email, String password, String dp,
    Collection<? extends GrantedAuthority> authorities) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.authorities = authorities;
            this.dp = dp;
        }

    public static CustomUserDetailsImpl build(UserCollection user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getERole().name()))
				.collect(Collectors.toList());
        
        return new CustomUserDetailsImpl(user.getUserId(), 
                    user.getName(), 
                    user.getEmail(),
                    user.getPassword(),
                    user.getDp(), 
                    authorities);
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
