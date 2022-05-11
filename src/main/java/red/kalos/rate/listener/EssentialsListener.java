package red.kalos.rate.listener;

import net.ess3.api.events.UserBalanceUpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import red.kalos.rate.Main;

public class EssentialsListener implements Listener {

    @EventHandler
    public void UserBalanceUpdateEvent(UserBalanceUpdateEvent event){
        Player player = event.getPlayer();
        UserBalanceUpdateEvent.Cause cause = event.getCause();
        float money = event.getNewBalance().floatValue()-event.getOldBalance().floatValue();
        if (cause.equals(UserBalanceUpdateEvent.Cause.COMMAND_PAY)){
            if (money<0){
                if (Main.getInstance().getConfig().getBoolean("Object")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),Main.getInstance().getConfig().getString("command")
                            .replace("%player%",player.getName())
                            .replace("%cost%",String.valueOf(Math.abs(money)*Main.getInstance().getConfig().getDouble("Cost")))
                    );
                }
            }else if (money>0){
                if (!Main.getInstance().getConfig().getBoolean("Object")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),Main.getInstance().getConfig().getString("command")
                            .replace("%player%",player.getName())
                            .replace("%cost%",String.valueOf(money*Main.getInstance().getConfig().getDouble("Cost")))
                    );
                }
            }


        }
    }
}
