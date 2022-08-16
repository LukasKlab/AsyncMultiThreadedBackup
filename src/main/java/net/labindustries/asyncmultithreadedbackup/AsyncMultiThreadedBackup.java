package net.labindustries.asyncmultithreadedbackup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static net.labindustries.asyncmultithreadedbackup.backup.tasks.subtasks.ReadTasks.readTask;

public final class AsyncMultiThreadedBackup extends JavaPlugin {

    public String backupDestination = "Backups//";
    private static AsyncMultiThreadedBackup asyncMultiThreadedBackup;
    public static AsyncMultiThreadedBackup getAsyncMultiThreadedBackup() {
        return asyncMultiThreadedBackup;
    }





    // Create thread needs a new bukkit runnable

    @Override
    public void onEnable() {
        asyncMultiThreadedBackup = this;
        saveDefaultConfig();
        readTask();


    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(asyncMultiThreadedBackup);
        // Plugin shutdown logic
    }
}
