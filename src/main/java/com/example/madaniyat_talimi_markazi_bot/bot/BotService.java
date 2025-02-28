package com.example.madaniyat_talimi_markazi_bot.bot;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.User;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Languages;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.State;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BotService {

    public final UserRepository userRepository;
    private final JavaMailSender mailSender;

    public User getUser(Long chatId, String username) {
        return userRepository.findByChatIdAndDeletedFalse(chatId).map(user -> {
            if (username != null && !username.equals(user.getUsername())) {
                user.setUsername(username);
                userRepository.save(user);
            }
            return user;
        }).orElseGet(() -> {
            User newUser = new User();
            newUser.setChatId(chatId);
            newUser.setUsername(username);
            newUser.setState(State.NEW);
            newUser.setLanguage(Languages.UZ); // Default til
            return userRepository.save(newUser);
        });
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void sendUserDataToEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = "nekmurodovdilshod2@gmail.com";
        message.setTo(email);
        message.setSubject("Yangi Murojaat");

        String text = String.format("""
            🆕 Yangi foydalanuvchi murojaati:
            📌 Telegram Username: @%s
            📌 Ism: %s
            📌 Familiya: %s
            📌 Telefon: %s
            📌 Ish joyi: %s
            📌 Xabar: %s
            """, user.getUsername(),
                user.getName(), user.getSurname(), user.getPhoneNumber(),
                user.getWorkplace(), user.getMessage());

        message.setText(text);
        mailSender.send(message);
    }

    public void resetUser(Long chatId, String username) {
        User user = getUser(chatId, username);
        user.setLanguage(null);
        user.setState(State.NEW);
        user.setName(null);
        user.setSurname(username);
        user.setUsername(null);
        user.setPhoneNumber(null);
        user.setWorkplace(null);
        user.setMessage(null);
        updateUser(user);
    }


}
