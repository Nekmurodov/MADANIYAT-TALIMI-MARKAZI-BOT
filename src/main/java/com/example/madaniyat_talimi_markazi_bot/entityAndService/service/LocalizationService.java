package com.example.madaniyat_talimi_markazi_bot.entityAndService.service;

import com.example.madaniyat_talimi_markazi_bot.entityAndService.enums.Languages;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LocalizationService {


    private static final Map<String, Map<Languages, String>> messages = Map.ofEntries(
            Map.entry("welcome", Map.of(
                    Languages.UZ, "📌 Salom hurmatli foydalanuvchi. Ushbu bot orqali \"MADANIYAT TA’LIMI MARKAZI\"ga " +
                            "murojaat va takliflaringizni yuborishingiz mumkin. " +
                            "Bot sizdan ma’lumotlaringizni, taklif va murojaatlaringizni qabul qiladi va ularni qayta ishlaydi. " +
                            "Bu ma’lumotlar orqali biz siz bilan bog‘lanamiz. " +
                            "Iltimos, ma’lumotlarni kiritishda diqqatli bo‘lishingizni so‘raymiz.",
                    Languages.RU, "📌 Здравствуйте, уважаемый пользователь. " +
                            "С помощью этого бота вы можете отправлять свои обращения и предложения в 'ЦЕНТР КУЛЬТУРНОГО ОБРАЗОВАНИЯ'. " +
                            "Бот соберёт вашу информацию, предложения и обращения, обработает их и передаст в соответствующие инстанции. " +
                            "На основе этих данных мы свяжемся с вами. Пожалуйста, будьте внимательны при вводе информации.",
                    Languages.EN, "📌 Hello, dear user. Through this bot, you can send your requests and suggestions to the " +
                            "'CULTURAL EDUCATION CENTER'. The bot will collect your information, suggestions, " +
                            "and requests, process them, and ensure they reach the appropriate place. " +
                            "Based on this information, we will contact you. " +
                            "Please be careful when entering your information."
            )),
            Map.entry("ask_name", Map.of(
                    Languages.UZ, "Ismingizni kiriting:",
                    Languages.RU, "Введите ваше имя:",
                    Languages.EN, "Please enter your name:"
            )),
            Map.entry("ask_surname", Map.of(
                    Languages.UZ, "Familiyangizni kiriting:",
                    Languages.RU, "Введите вашу фамилию:",
                    Languages.EN, "Please enter your surname:"
            )),
            Map.entry("ask_phone", Map.of(
                    Languages.UZ, "Telefon raqamingizni kiriting:",
                    Languages.RU, "Введите ваш номер телефона:",
                    Languages.EN, "Please enter your phone number:"
            )),
            Map.entry("ask_position", Map.of(
                    Languages.UZ, "Lavozimingizni kiriting (Agar oddiy fuqaro bo'lsangiz, 'fuqaro' deb yozib qoldiring):",
                    Languages.RU, "Введите вашу должность (Если вы обычный гражданин, укажите 'гражданин'):",
                    Languages.EN, "Enter your position (If you are an ordinary citizen, write 'citizen'):"
            )),
            Map.entry("ask_workplace", Map.of(
                    Languages.UZ, "Ish joyingiz va manzilini kiriting (Agar ishsiz bo'lsangiz, 'ishsiz' deb qoldiring).\n" +
                            "/Namuna: Toshkent viloyati, Yangi Yo‘l tumani, 5-son BMSM, o‘qituvchi./",
                    Languages.RU, "Введите место работы и адрес (Если вы безработный, укажите 'безработный').\n" +
                            "Пример: Ташкентская область, Янги Йульский район, школа №5, учитель.",
                    Languages.EN, "Enter your workplace and address (If you are unemployed, write 'unemployed').\n" +
                            "/Example: Tashkent region, Yangi Yo‘l district, School No. 5, teacher./"
            )),
            Map.entry("ask_feedback", Map.of(
                    Languages.UZ, "Murojaatingizni yozing:",
                    Languages.RU, "Напишите свое обращение:",
                    Languages.EN, "Please enter your message:"
            )),
            Map.entry("thank_you", Map.of(
                    Languages.UZ, "Rahmat! Murojaatingiz qabul qilindi. " +
                            "Tez orada mutaxassislarimiz siz bilan bog‘lanishadi.",
                    Languages.RU, "Спасибо! Ваш запрос принят. " +
                            "Наши специалисты свяжутся с вами в ближайшее время.",
                    Languages.EN, "Thank you! Your request has been received. " +
                            "Our specialists will contact you soon."
            )),
            Map.entry("main_menu", Map.of(
                    Languages.UZ, "📋 Asosiy menyuga xush kelibsiz!",
                    Languages.RU, "📋 Добро пожаловать в главное меню!",
                    Languages.EN, "📋 Welcome to the main menu!"
            )),
            Map.entry("restart", Map.of(
                    Languages.UZ, "🔄 Botni qayta ishga tushirish...",
                    Languages.RU, "🔄 Перезапуск бота...",
                    Languages.EN, "🔄 Restarting the bot..."
            )),
            Map.entry("change_language", Map.of(
                    Languages.UZ, "🌍 Tilni o'zgartirish uchun tugmani bosing:",
                    Languages.RU, "🌍 Нажмите кнопку, чтобы изменить язык:",
                    Languages.EN, "🌍 Press the button to change the language:"
            ))

    );

    public static String getMessage(String key, Languages language) {
        return messages.getOrDefault(key, Map.of()).getOrDefault(language, "Translation error");
    }

}
