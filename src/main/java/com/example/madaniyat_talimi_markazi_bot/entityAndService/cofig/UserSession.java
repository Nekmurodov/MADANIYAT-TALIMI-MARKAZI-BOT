package com.example.madaniyat_talimi_markazi_bot.entityAndService.cofig;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.dto.UserDto;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.User;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    private final UserMapper userMapper;

    public UserSession(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public UserDto getUser(){
        UserDto userDto = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            userDto = userMapper.toDto((User) authentication.getPrincipal());
        }
        return userDto;
    }
}
