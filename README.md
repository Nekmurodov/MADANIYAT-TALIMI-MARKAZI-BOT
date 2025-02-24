# Telegram Bot

Bu loyiha Java Spring asosida yozilgan Telegram bot bo‘lib, u PostgreSQL ma'lumotlar bazasidan foydalanadi. Loyiha Gradle yordamida boshqariladi va Java 17 versiyasida ishlaydi.

## Xususiyatlar
- Telegram bilan integratsiya
- REST API qo‘llab-quvvatlovi
- PostgreSQL ma’lumotlar bazasi
- Docker yordamida joylashtirish
- Cron jobs orqali avtomatik ishga tushirish

## Talablar
Quyidagi dasturlar o‘rnatilgan bo‘lishi kerak:
- Java 17
- Gradle
- PostgreSQL
- Docker (agar konteynerda ishga tushirilsa)

## O‘rnatish

### 1. Loyihani klonlash
```sh
git clone <repository_url>
cd <project_directory>
```

### 2. Ma’lumotlar bazasini sozlash
PostgreSQL ma'lumotlar bazasini yaratib, `application.properties` yoki `application.yml` faylida quyidagi sozlamalarni moslashtiring:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

### 3. Loyiha bog‘liqliklarini yuklash
```sh
gradle build
```

### 4. Botni ishga tushirish
```sh
java -jar build/libs/your-bot.jar
```

## Docker yordamida ishga tushirish

### 1. Docker Image yaratish
```sh
docker build -t telegram-bot .
```

### 2. Docker konteynerini ishga tushirish
```sh
docker run -d --name telegram-bot -e BOT_TOKEN=your_bot_token telegram-bot
```

## Loyiha tuzilmasi
```
├── src
│   ├── main
│   │   ├── java/com/example/bot
│   │   │   ├── BotApplication.java  # Bosh dastur fayli
│   │   │   ├── config  # Konfiguratsiya fayllari
│   │   │   ├── service  # Xizmatlar (Services)
│   │   │   ├── repository  # Ma’lumotlar bazasi uchun repository-lar
│   │   │   ├── controller  # REST API controller-lari
│   ├── resources
│   │   ├── application.yml  # Konfiguratsiya fayli
│   │   ├── logback.xml  # Log sozlamalari
├── build.gradle  # Gradle konfiguratsiya fayli
├── Dockerfile  # Docker konfiguratsiyasi
├── README.md  # Hujjat
```

## Xatoliklarni tuzatish
Agar biror muammo yuzaga kelsa, quyidagilarni tekshiring:
- Ma'lumotlar bazasi sozlamalari to‘g‘ri ekanligiga ishonch hosil qiling.
- Telegram bot tokeni to‘g‘ri kiritilganligini tekshiring.
- `gradle build` va `java -jar` komandalarini tekshirib ko‘ring.
- Loglarni tekshirish uchun `logs/app.log` faylidan foydalaning.

## Muallif
Loyiha muallifi: Dilshodbek Nekmurodov

## Litsenziya
Bu loyiha [Litsenziya nomi] ostida tarqatiladi.
