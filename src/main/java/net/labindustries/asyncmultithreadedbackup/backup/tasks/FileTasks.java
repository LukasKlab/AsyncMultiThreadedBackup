package net.labindustries.asyncmultithreadedbackup.backup.tasks;

import net.labindustries.asyncmultithreadedbackup.AsyncMultiThreadedBackup;
import org.bukkit.Bukkit;


import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class FileTasks {
    AsyncMultiThreadedBackup asyncMultiThreadedBackup;

    public FileTasks(AsyncMultiThreadedBackup asyncMultiThreadedBackup) {
        this.asyncMultiThreadedBackup = asyncMultiThreadedBackup;
    }

    // Look into ScatterZipOutputStream

    public static void pathTask(Path path, String prefix, String backupDirectoryName) throws IOException {
        File[] rootFiles = path.toFile().listFiles(); // Gets correct files..
        Path pathOriginal = Path.of(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup().getServer().getWorldContainer().getCanonicalPath());
        File backupDirectory = new File(pathOriginal + "\\" + backupDirectoryName + "\\" + prefix);

        if (!backupDirectory.isDirectory()) {
            Files.createDirectory(backupDirectory.toPath());
        }

        Path backupPath = Path.of(backupDirectory.getPath());

        //Path backupPath = Path.of(pathOriginal + "\\backups\\" + "backup1");

        if (rootFiles == null) return;
        // Loop through the files
        for (File file : rootFiles) {
            Bukkit.getScheduler().runTaskAsynchronously(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup(), () -> {
                try {
                    if (file.isDirectory()) {
                        if (file.getName().equals("backups")) return;
                        Path subDirectory = Path.of(file.getCanonicalPath());
                        Path finalPath = Path.of(backupPath.toString() + "\\" + pathOriginal.relativize(subDirectory));

                        File subPath = new File(finalPath.toUri()).getCanonicalFile();
                        Files.copy(subDirectory, subPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        pathTask(subDirectory, prefix, backupDirectoryName);
                    }

                    if (file.isFile()) {
                        if (file.getName().equals("session.lock")) return;
                        Path filePath = Path.of(file.getCanonicalPath());
                        Path finalPath = Path.of(backupPath.toString() + "\\" + pathOriginal.relativize(filePath));

                        File subFile = new File(finalPath.toUri()).getCanonicalFile();
                        Files.copy(filePath, subFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
