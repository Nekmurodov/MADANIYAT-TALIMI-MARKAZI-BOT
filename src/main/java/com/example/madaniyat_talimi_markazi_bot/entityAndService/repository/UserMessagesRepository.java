package com.example.madaniyat_talimi_markazi_bot.entityAndService.repository;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.UserMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessagesRepository extends JpaRepository<UserMessages, Long> {
}
