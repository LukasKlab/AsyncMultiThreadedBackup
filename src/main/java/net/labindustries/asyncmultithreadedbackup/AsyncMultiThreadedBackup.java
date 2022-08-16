package net.labindustries.asyncmultithreadedbackup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Path;

import static net.labindustries.asyncmultithreadedbackup.backup.tasks.FileTasks.*;

public final class AsyncMultiThreadedBackup extends JavaPlugin {

    private static AsyncMultiThreadedBackup asyncMultiThreadedBackup;
    public static AsyncMultiThreadedBackup getAsyncMultiThreadedBackup() {
        return asyncMultiThreadedBackup;
    }





    // Create thread needs a new bukkit runnable

    @Override
    public void onEnable() {
        asyncMultiThreadedBackup = this;
        saveDefaultConfig();


        try {
            Path path = Path.of(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup().getServer().getWorldContainer().getCanonicalPath());
            pathTask(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

/*
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                //print();
            }
        },100, 1000);

 */








    }



    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(asyncMultiThreadedBackup);
        // Plugin shutdown logic
    }
}
