package net.labindustries.asyncmultithreadedbackup.backup;

import net.labindustries.asyncmultithreadedbackup.AsyncMultiThreadedBackup;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.HashMap;

public class Scheduler {


    private final AsyncMultiThreadedBackup asyncMultiThreadedBackup;

    public Scheduler(AsyncMultiThreadedBackup asyncMultiThreadedBackup) {
        this.asyncMultiThreadedBackup = asyncMultiThreadedBackup;
    }




}
