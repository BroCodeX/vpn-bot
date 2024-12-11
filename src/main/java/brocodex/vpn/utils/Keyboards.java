package brocodex.vpn.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.List;

public class Keyboards {
    public static InlineKeyboardMarkup configOptions() {

        InlineKeyboardButton addConf = new InlineKeyboardButton("Add VPN config");
        InlineKeyboardButton delConf = new InlineKeyboardButton("Delete VPN config");
        InlineKeyboardButton faq = new InlineKeyboardButton("Information and FAQ");

        addConf.setCallbackData("addConfig");
        delConf.setCallbackData("delConfig");
        faq.setCallbackData("faq");

        InlineKeyboardRow row = new InlineKeyboardRow(addConf, delConf);
        InlineKeyboardRow row2 = new InlineKeyboardRow(faq);

        List<InlineKeyboardRow> keyboard = List.of(row, row2);

        return new InlineKeyboardMarkup(keyboard);
    }
}
