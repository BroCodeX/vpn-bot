package brocodex.vpn.commands.callback;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Callback {
    SendMessage apply(Update update);
}
