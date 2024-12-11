package brocodex.vpn.component;

import brocodex.vpn.handler.CallbackHandler;
import brocodex.vpn.handler.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class VpnBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {
    private String token = "token";

    private final TelegramClient telegramClient;

    private final CommandHandler commandHandler;

    private final CallbackHandler callbackHandler;

    public VpnBot(TelegramClient telegramClient, CommandHandler commandHandler, CallbackHandler callbackHandler) {
        this.telegramClient = telegramClient;
        this.commandHandler = commandHandler;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Work with command
            var message = commandHandler.applyCommand(update);
            sendMessage(message);
        } else if (update.hasCallbackQuery()) {
            // Work with callback
            var message = callbackHandler.applyCallback(update);
            sendMessage(message);
        }
    }

    @AfterBotRegistration
    public void afterRegistration(BotSession botSession) {
        System.out.println("Registered bot running state is: " + botSession.isRunning());
    }

    public void sendMessage(SendMessage sendMessage) {
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}
