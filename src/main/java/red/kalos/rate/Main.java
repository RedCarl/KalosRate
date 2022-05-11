package red.kalos.rate;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import red.kalos.rate.command.RateCommand;
import red.kalos.rate.listener.EssentialsListener;
import red.kalos.rate.util.api.ColorParser;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance=this;
        log(getName() + " " + getDescription().getVersion() + " &7开始加载...");
        long startTime = System.currentTimeMillis();

        log("正在注册配置文件...");
        this.saveDefaultConfig();
        this.getConfig();

        log("正在注册监听器...");
        regListener(new EssentialsListener());

        log("正在注册指令...");
        regCommand("KalosRate",new RateCommand());

        log("加载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");
        showAD();
    }


    @Override
    public void onDisable() {
        log(getName() + " " + getDescription().getVersion() + " 开始卸载...");
        long startTime = System.currentTimeMillis();

        log("卸载监听器...");
        Bukkit.getServicesManager().unregisterAll(this);

        log("卸载完成 ，共耗时 " + (System.currentTimeMillis() - startTime) + " ms 。");

        showAD();
    }

    //文件保存至本地
    public void saveYmlConfig(String file) {
        File configFile = new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder(), file);
        if (!configFile.exists()) {
            this.saveResource(file, false);
        }

    }
    /**
     * 注册监听器
     *
     * @param listener 监听器
     */
    public static void regListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getInstance());
    }

    /**
     * 注册指令
     *
     * @param name 指令名字
     * @param command 指令
     */
    public static void regCommand(String name, CommandExecutor command) {
        Bukkit.getPluginCommand(name).setExecutor(command);
    }

    /**
     * 日志
     * @param message 日志消息
     */
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorParser.parse("[" + getInstance().getName() + "] " + message));
    }

    /**
     * 作者信息
     */
    private void showAD() {
        log("&7感谢您使用 &c&lKalosRate v" + getDescription().getVersion());
        log("&7本插件由 &c&lKalos Studios &7提供长期支持与维护。");
    }
}
