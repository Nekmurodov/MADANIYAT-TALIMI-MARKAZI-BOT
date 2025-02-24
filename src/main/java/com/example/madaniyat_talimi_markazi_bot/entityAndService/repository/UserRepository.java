package com.example.madaniyat_talimi_markazi_bot.entityAndService.repository;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByChatIdAndDeletedFalse(Long chatId);
    Optional<User> findByChatIdAndDeletedFalse(Long chatId);
}
