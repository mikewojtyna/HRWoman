package dataexport;

import user.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportToFile implements Exporter {

    private final Path filePath;

    public ExportToFile(Path path) {
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Can't create directory path");
            }
        }
        filePath = path;
    }

    @Override
    public void export(Users userList) {
        if(Files.exists(filePath)) {
            try {
                Files.write(filePath, userList.userDataToListOfStrings());
            } catch (IOException e) {
                System.out.println("Cannot write data to file...");
            }
        }
    }
}
