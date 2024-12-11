# 📊 Telegram VPN Bot

[![Test-CI](https://github.com/BroCodeX/BroX-VPNBot/actions/workflows/tests.yml/badge.svg)](https://github.com/BroCodeX/BroX-VPNBot/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/5a27dcb9c19abab7f0bc/maintainability)](https://codeclimate.com/github/BroCodeX/BroX-VPNBot/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/5a27dcb9c19abab7f0bc/test_coverage)](https://codeclimate.com/github/BroCodeX/BroX-VPNBot/test_coverage)

Telegram-бот для генерации и управления конфигурациями VPN. Позволяет пользователям получать индивидуальные настройки для подключения, проверять статус серверов, а также продлевать или изменять подписки.

## 🚀 Основные возможности

- **Генерация конфигураций VPN** с учетом предпочтений пользователя
- **Проверка статуса серверов** для выбора оптимального соединения
- **Управление подписками**: создание, продление и обновление
- **Интеграция с RabbitMQ** для асинхронной обработки запросов
- **Покрытие кода тестами** с использованием TDD-подхода
- **Контейнеризация и деплой в Kubernetes**

---

## 🏗 Стек технологий

- **Backend**: Java, Spring Boot (Web, Data JPA, Security, AMQP)
- **Database**: PostgreSQL
- **Messaging**: RabbitMQ
- **CI/CD**: Docker, Kubernetes
- **Testing**: JUnit, Mockito

---

<details>
<summary>## 📚 Установка и запуск</summary>

### 1. Подготовьте окружение

Убедитесь, что у вас установлены:

- Docker и Docker Compose
- Java 21

### 2. Клонируйте репозиторий

```bash
git clone https://github.com/yourusername/telegram-vpn-bot.git
cd telegram-vpn-bot
```

### 3. Запустите инфраструктуру (PostgreSQL и RabbitMQ)

```bash
docker-compose up -d
```

### 4. Запустите приложение

```bash
./mvnw spring-boot:run
```

</details>

---

<details>
<summary>## 🧪 Тестирование</summary>

Запустите тесты с помощью команды:

```bash
./mvnw test
```

</details>

---

<details>
<summary>## 🛠 Контейнеризация и деплой</summary>

### Сборка Docker-образа

```bash
docker build -t telegram-vpn-bot .
```

### Запуск в Kubernetes

1. **Создайте манифесты Kubernetes** для деплоя (см. папку `/k8s`).
2. **Примените конфигурации**:

   ```bash
   kubectl apply -f k8s/
   ```

</details>

---

## 📈 Примеры команд для бота

| Команда                | Описание                                 |
| ---------------------- | ---------------------------------------- |
| `/start`               | Начало работы и регистрация пользователя |
| `/menu`                | Меню с кнопками управления               |
| `/help         `       | Список доступных комманд                 |

Пример запроса конфигурации:

```plaintext
/get_config
Тип: OpenVPN
Регион: Европа
```

---

## 🔗 Ссылка на бота

[Перейти к боту в Telegram](https://t.me/your_bot_placeholder)

---

## 📬 Контакты и поддержка

Если у вас есть вопросы или предложения, создайте новый Issue или отправьте Pull Request.
