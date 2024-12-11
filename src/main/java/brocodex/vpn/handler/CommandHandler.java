package brocodex.vpn.handler;

import brocodex.vpn.commands.Command;
import brocodex.vpn.commands.Help;
import brocodex.vpn.commands.Menu;
import brocodex.vpn.commands.Start;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Slf4j
@Component
public class CommandHandler {
    private Map<String, Command> commandMap;

    public CommandHandler(Menu menuCommand,
                          Start startCommand,
                          Help helpCommand) {
        this.commandMap = Map.of(
                "/start", startCommand,
                "/menu", menuCommand,
                "/help", helpCommand);
    }

    public SendMessage applyCommand(Update update) {
        String maybeCommand = update.getMessage().getText();
        if (!commandMap.containsKey(maybeCommand)) {
            return unexpectedMessage(update);
        } else {
            var command = commandMap.get(maybeCommand);
            return command.apply(update);
        }
    }

    public SendMessage unexpectedMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        String receivedMessage = update.getMessage().getText();
        log.info(receivedMessage);

        StringBuilder builder = new StringBuilder();
        builder.append("I don't understand your message: ").append(receivedMessage).append("\n");
        builder.append("You can type /help for assistance.");
        // Create a message object
        return SendMessage // Create a message object
                .builder()
                .chatId(chatId)
                .text(builder.toString())
                .build();

    }
}
