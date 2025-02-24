package com.example.madaniyat_talimi_markazi_bot.entityAndService.service;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.mapper.UserMapper;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


}
