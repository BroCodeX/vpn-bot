package brocodex.vpn.commands;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Help implements Command {
    @Override
    public SendMessage apply(Update update) {
        Long chatId = update.getMessage().getChatId();
        StringBuilder builder = new StringBuilder();
        builder.append("/start - reboot the bot").append("\n");
        builder.append("/menu - request to main menu").append("\n");
        builder.append("/help - watch availible commands").append("\n");
        return SendMessage.builder()
                .chatId(chatId)
                .text(builder.toString())
                .build();

    }
}
