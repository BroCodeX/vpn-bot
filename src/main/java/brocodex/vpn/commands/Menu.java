package brocodex.vpn.commands;

import brocodex.vpn.utils.Keyboards;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Menu implements Command{
    @Override
    public SendMessage apply(Update update) {
        Long chatId = update.getMessage().getChatId();

        var markup = Keyboards.configOptions();

        return SendMessage.builder()
                .replyMarkup(markup)
                .chatId(chatId)
                .build();
    }
}
