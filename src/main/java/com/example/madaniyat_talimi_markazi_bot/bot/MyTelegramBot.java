package com.example.madaniyat_talimi_markazi_bot.bot;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.entity.user.User;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Languages;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.State;
import com.example.madaniyat_talimi_markazi_bot.entityAndService.service.LocalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.username}")
    private String botUsername;

    private final BotService botService;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = null;
        User user;

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
        }

        if (chatId == null) return;

        user = botService.getUser(chatId);

        if (user == null) {
            user = new User();
            user.setChatId(chatId);
            user.setState(State.NEW);
            botService.updateUser(user);
        }

        if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            if (user.getState() == null) {
                user.setState(State.NEW);
                botService.updateUser(user);
            }
            processTextMessage(update);
        }
    }

    private void processTextMessage(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        if (text.equals("/start")) {
            sendLanguageButtons(chatId);
        } else {
            User user = botService.getUser(chatId);
            String response = handleUserState(user, text);
            sendMessage(chatId, response, user.getLanguage(), "");
        }
    }

    private void processCallbackQuery(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String data = update.getCallbackQuery().getData();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

        switch (data) {
            case "CHANGE_LANGUAGE" -> sendLanguageButtons(chatId);
            case "RESTART" -> {
                botService.resetUser(chatId);
                sendLanguageButtons(chatId);
            }
            default -> handleLanguageSelection(chatId, data, messageId);
        }
    }

    private String handleUserState(User user, String text) {
        if (user.getLanguage() == null) {
            user.setLanguage(Languages.UZ);
        }
        switch (user.getState()) {
            case NEW -> {
                user.setState(State.ASK_NAME);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_name", user.getLanguage());
            }
            case ASK_NAME -> {
                user.setName(text);
                user.setState(State.ASK_SURNAME);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_surname", user.getLanguage());
            }
            case ASK_SURNAME -> {
                user.setSurname(text);
                user.setState(State.ASK_PHONE);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_phone", user.getLanguage());
            }
            case ASK_PHONE -> {
                user.setPhoneNumber(text);
                user.setState(State.ASK_POSITION);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_position", user.getLanguage());
            }
            case ASK_POSITION -> {
                user.setPosition(text);
                user.setState(State.ASK_WORKPLACE);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_workplace", user.getLanguage());
            }
            case ASK_WORKPLACE -> {
                user.setWorkplace(text);
                user.setState(State.ASK_FEEDBACK);
                botService.updateUser(user);
                return LocalizationService.getMessage("ask_feedback", user.getLanguage());
            }
            case ASK_FEEDBACK -> {
                user.setMessage(text);
                user.setState(State.COMPLETED);
                botService.updateUser(user);
                botService.sendUserDataToEmail(user);
                return LocalizationService.getMessage("thank_you", user.getLanguage());
            }
            default -> {
                return LocalizationService.getMessage("thank_you", user.getLanguage());
            }
        }
    }

    private void sendMessage(Long chatId, String text, Languages language, String key) {
        if (Objects.equals(key, "welcome")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(text);
            try {
            execute(message);
            } catch (TelegramApiException e) {
            log.error("Error sending message", e);
            }
        } else {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(text);
            message.setReplyMarkup(getMainMenuKeyboard(language));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Error sending message", e);
            }
        }
    }

    private InlineKeyboardMarkup getMainMenuKeyboard(Languages language) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(List.of(createInlineButton(LocalizationService.getMessage("restart", language), "RESTART")));
        markup.setKeyboard(keyboard);
        return markup;
    }

    private void handleLanguageSelection(Long chatId, String languageCode, Integer messageId) {
        try {

            Languages selectedLanguage = Languages.valueOf(languageCode);

            User user = botService.getUser(chatId);
            user.setLanguage(selectedLanguage);
            user.setState(State.ASK_NAME);
            botService.updateUser(user);
            deleteMessage(chatId, messageId);

            sendMessage(chatId, LocalizationService.getMessage("welcome", selectedLanguage), selectedLanguage, "welcome" );

            sendMessage(chatId, LocalizationService.getMessage("ask_name", selectedLanguage), selectedLanguage, "ask_name");

        } catch (IllegalArgumentException e) {
            log.error("Noto‘g‘ri til kodi: {}", languageCode, e);
            sendMessage(chatId, LocalizationService.getMessage("invalid_language", Languages.UZ), Languages.UZ, "invalid_language");
        }
    }

    private void sendLanguageButtons(Long chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> row = List.of(
                createInlineButton("🇷🇺 Русский", "RU"),
                createInlineButton("🇺🇿 O'zbek", "UZ"),
                createInlineButton("🇬🇧 English", "EN")
        );
        markup.setKeyboard(List.of(row));

        sendMessageWithMarkup(chatId, "Tilni tanlang/Choose language/Выберите язык:", markup);
    }

    private InlineKeyboardButton createInlineButton(String text, String callbackData) {
        return InlineKeyboardButton.builder().text(text).callbackData(callbackData).build();
    }

    private void sendMessageWithMarkup(Long chatId, String text, InlineKeyboardMarkup markup) {
        try {
            execute(SendMessage.builder().chatId(chatId.toString()).text(text).replyMarkup(markup).build());
        } catch (TelegramApiException e) {
            log.error("Error sending message with markup", e);
        }
    }

    private void deleteMessage(Long chatId, Integer messageId) {
        try {
            execute(DeleteMessage.builder().chatId(chatId.toString()).messageId(messageId).build());
        } catch (TelegramApiException e) {
            log.error("Error deleting message", e);
        }
    }
}

