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



public class AqquireTasks {

    public static final ConcurrentHashMap<Path, File> pathFileCollection = new ConcurrentHashMap<Path, File>();
    public static final  List<File> files = new ArrayList<>();
    private static final HashMap<Path, Path> ignoredPaths = new HashMap<>();


    AsyncMultiThreadedBackup asyncMultiThreadedBackup;

    public AqquireTasks(AsyncMultiThreadedBackup asyncMultiThreadedBackup) {
        this.asyncMultiThreadedBackup = asyncMultiThreadedBackup;
    }


    // Look into ScatterZipOutputStream


    public static void print() {
        System.out.println(pathFileCollection);
    }

    public static void pathTask(Path path) {
        // Creates a new ConcurrentHashMap of path files


        File[] rootFiles = path.toFile().listFiles(); // Gets correct files..
        // UUID uuid = UUID.randomUUID();
        System.out.println(path);

        File directory = new File(path.toFile() + "\\backups\\" + "backup1").getAbsoluteFile();
        System.out.println(directory);
        try {
            Files.createDirectory(directory.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (rootFiles == null) return;

        // Loop through the files
        for (File file : rootFiles) {

            Bukkit.getScheduler().runTaskAsynchronously(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup(), () -> {
                if (file.isDirectory()) {
                    try {
                        Path subPath = Path.of(file.getCanonicalPath());
                        File directorySource = new File(directory + path.relativize(subPath).toFile().getCanonicalPath());
                        System.out.println(directorySource);
                        // Reiterates through subdirectories.
                        Files.copy(directorySource.toPath(), directorySource.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        pathTask(subPath);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (file.isFile()) {
                    try {
                        Path filePath = Path.of(file.getCanonicalPath());
                        File subFile = new File(directory + path.relativize(filePath).toFile().getCanonicalPath());
                        System.out.println(subFile);
                        Files.copy(subFile.toPath(), subFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
    }



/*
    public static void fileTask() {
        try {
            File root = new File(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup().getServer().getWorldContainer().getCanonicalPath());


            File[] rootFiles = root.listFiles();
            if (rootFiles == null) return;


            for (File rootFile : rootFiles) {
                if(rootFile.getName().equals("backups")) {
                    System.out.println("Backups folder ignored");
                    continue;
                }

                    //System.out.println(root + "\\backups\\" + rootFile.getName());

                    String path = rootFile.getCanonicalPath();
                    Bukkit.getScheduler().runTaskAsynchronously(AsyncMultiThreadedBackup.getAsyncMultiThreadedBackup(), () -> {
                        String destinationPath = root + "\\backups\\" + rootFile.getName();
                        File source = new File(path);
                        File destination = new File(destinationPath);


                        try {
                            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            System.out.println(path + " -> " + destination + " were successful");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }



            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

 */
}
