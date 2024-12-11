package brocodex.vpn.commands.callback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class FAQ implements Callback{
    @Override
    public SendMessage apply(Update update) {
        return null;
    }
}
