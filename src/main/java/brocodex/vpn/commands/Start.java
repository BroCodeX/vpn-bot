package brocodex.vpn.commands;

import brocodex.vpn.utils.Keyboards;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Start implements Command {
    @Override
    public SendMessage apply(Update update) {
        Long chatId = update.getMessage().getChatId();
        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();

        StringBuilder builder = new StringBuilder();
        builder.append("Hello! ").append(userName).append("\n");
        builder.append("Your id: ").append(userId).append("\n").append("\n");
        builder.append("Type /help for view commands");

        var markup = Keyboards.configOptions();

        return SendMessage.builder()
                .text(builder.toString())
                .replyMarkup(markup)
                .chatId(chatId)
                .build();
    }
}
