package com.example.madaniyat_talimi_markazi_bot.entityAndService.dto;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private Long chatId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String password;

    private Role role;
}
