package brocodex.vpn.handler;

import brocodex.vpn.commands.callback.AddConfig;
import brocodex.vpn.commands.callback.Callback;
import brocodex.vpn.commands.callback.DeleteConfig;
import brocodex.vpn.commands.callback.FAQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
@Component
public class CallbackHandler {
    private Map<String, Callback> callbackMap;

    @Autowired
    private CommandHandler commandHandler;

    public CallbackHandler(AddConfig addConfig,
                           DeleteConfig deleteConfig,
                           FAQ faq) {
        this.callbackMap = Map.of(
                "addConfig", addConfig,
                "delConfig", deleteConfig,
                "faq", faq);
    }

    public SendMessage applyCallback(Update update) {
        String maybeCommand = update.getCallbackQuery().getData();
        if (!callbackMap.containsKey(maybeCommand)) {
            return commandHandler.applyCommand(update); // тут надо подумать как обрабатывать обычные команды из колбэка
        } else {
            var command = callbackMap.get(maybeCommand);
            return command.apply(update);
        }
    }
}
