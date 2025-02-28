package com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.AbsEntity;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Languages;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Role;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbsEntity implements UserDetails {

    private Long chatId;
    private String name;
    private String surname;
    private String username;
    private String phoneNumber;
    private String workplace;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private State state;

    @Enumerated(EnumType.STRING)
    private Languages language;

    private String message;

    private boolean enabled = false;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonExpiredOrCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

}
