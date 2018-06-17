package data.dimport;

import user.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/*
* Object of this class, will read users data from file that is specified in pathToFile argument from constructor.
* The read data will be processed by class ListDataRunner.
* */
public class FileDataRunner implements Importer {

    private final Path pathToFile;

    public FileDataRunner(Path pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public void importData(Users usersList) throws IOException {
        if(Files.exists(pathToFile)) { //Do not throw exception! If it's first start, file can't exists and its no mistake.
            List<String> lines = Files.readAllLines(pathToFile);
            new ListDataRunner(lines).importData(usersList);
        }
    }
}
