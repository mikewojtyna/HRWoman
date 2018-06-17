package data.dexport;

import user.Users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportToFile implements Exporter {

    private final Path filePath;

    public ExportToFile(Path filePath) throws IOException {
        if(filePath == null) throw new NullPointerException("File path to file to export data is not initialized");
        if(filePath.getFileName().toString().isEmpty()) throw new FileNotFoundException("Empty file path");
        if(!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
        this.filePath = filePath;
    }

    @Override
    public void export(Users userList) throws IOException {
        if(filePath == null || !Files.exists(filePath)) throw new NullPointerException("File path is null or file not exists");
        if(userList == null) throw new NullPointerException("List to save does note exists");
        Files.write(filePath, userList.userDataToListOfStrings());
    }
}
