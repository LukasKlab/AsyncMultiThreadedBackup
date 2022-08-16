package net.labindustries.asyncmultithreadedbackup.backup.tasks;

import net.labindustries.asyncmultithreadedbackup.AsyncMultiThreadedBackup;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



public class ZipTasks {


    public static void zipData(List<File> files) {
        //UUID uuid = UUID.randomUUID();


        //System.out.println(files);
        /*
        Path zipFile;
        zipFile = Paths.get("C:\\Users\\lukas\\AsyncMultiTheadedBackup\\run\\backups\\one" + ".zip");
        Path sourceFiles = Paths.get("C:\\Users\\lukas\\AsyncMultiTheadedBackup\\run");

        try(ZipOutputStream zipStream = new ZipOutputStream(Files.newOutputStream(zipFile));
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceFiles)){
            for(Path file : directoryStream) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                zipStream.putNextEntry(zipEntry);
                Files.copy(file, zipStream);
                zipStream.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



         */
    }

}
