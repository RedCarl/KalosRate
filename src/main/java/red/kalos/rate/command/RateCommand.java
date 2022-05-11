package red.kalos.rate.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import red.kalos.rate.Main;
import red.kalos.rate.util.api.ColorParser;

public class RateCommand implements CommandExecutor {

    public RateCommand(){}

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = Bukkit.getPlayer(commandSender.getName());

        try {
            if (commandSender.hasPermission("kalos.rate.reload") && strings[0].equals("reload")){
                Main.getInstance().reloadConfig();
                commandSender.sendMessage(ColorParser.parse("&aReload!"));
            }
        }catch (ArrayIndexOutOfBoundsException e){
            player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7您输入的参数有误，请重新输入!"));
        }

        return true;
    }
}
