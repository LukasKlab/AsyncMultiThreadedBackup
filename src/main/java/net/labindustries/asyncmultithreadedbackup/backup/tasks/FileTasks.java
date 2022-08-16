package net.labindustries.asyncmultithreadedbackup.backup.tasks;

import net.labindustries.asyncmultithreadedbackup.AsyncMultiThreadedBackup;
import org.bukkit.Bukkit;


import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


public class FileTasks {

    public static final ConcurrentHashMap<Path, File> pathFileCollection = new ConcurrentHashMap<Path, File>();
    public static final List<File> files = new ArrayList<>();
    private static final HashMap<Path, Path> ignoredPaths = new HashMap<>();


    AsyncMultiThreadedBackup asyncMultiThreadedBackup;

    public FileTasks(AsyncMultiThreadedBackup asyncMultiThreadedBackup) {
        this.asyncMultiThreadedBackup = asyncMultiThreadedBackup;
    }


    // Look into ScatterZipOutputStream


    public static void pathTask(Path path) {
        File[] rootFiles = path.toFile().listFiles(); // Gets correct files..

        if (rootFiles == null) return;
        // Loop through the files
        for (File file : rootFiles) {
            Bukkit.getScheduler().runTaskAsynchronously(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup(), () -> {
                Path pathOriginal = Path.of(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup().getServer().getWorldContainer().getAbsolutePath());
                Path backupPath = Path.of(pathOriginal + "\\backups\\" + "backup1");
                try {
                    if (file.isDirectory()) {
                        if(file.getName().equals("backups")) return;
                        Path subDirectory = Path.of(file.getCanonicalPath());
                        Path finalPath = Path.of(backupPath.toString() + "\\" + pathOriginal.relativize(subDirectory));

                        File subPath = new File(finalPath.toUri()).getCanonicalFile();
                        Files.copy(subDirectory, subPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        pathTask(subDirectory);
                    }

                if (file.isFile()) {
                    if(file.getName().equals("session.lock")) return;
                    Path filePath = Path.of(file.getCanonicalPath());
                    Path finalPath = Path.of(backupPath.toString() + "\\" + pathOriginal.relativize(filePath));

                    File subFile = new File(finalPath.toUri()).getCanonicalFile();
                    Files.copy(filePath, subFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                } catch (IOException e ) {
                    e.printStackTrace();
                }
            });
        }
    }
}
