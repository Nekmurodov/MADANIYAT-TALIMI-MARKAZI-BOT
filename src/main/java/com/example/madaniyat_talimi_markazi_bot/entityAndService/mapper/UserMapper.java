package com.example.madaniyat_talimi_markazi_bot.entityAndService.mapper;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.dto.UserDto;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setChatId(user.getChatId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
