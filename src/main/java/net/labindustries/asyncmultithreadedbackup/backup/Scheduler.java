package net.labindustries.asyncmultithreadedbackup.backup;

import net.labindustries.asyncmultithreadedbackup.AsyncMultiThreadedBackup;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;


public class Scheduler extends BukkitRunnable {


    private final AsyncMultiThreadedBackup asyncMultiThreadedBackup;

    public Scheduler(AsyncMultiThreadedBackup asyncMultiThreadedBackup) {
        this.asyncMultiThreadedBackup = asyncMultiThreadedBackup;
    }


    @Override
    public void run() {

    }
}
