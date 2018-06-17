package dataimport;

import user.Users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FileDataRunner extends InputResources {

    private final Users usersList;
    private final Path pathToFile;

    public FileDataRunner(Users usersList, Path pathToFile) {
        this.usersList = usersList;
        this.pathToFile = pathToFile;
    }

    @Override
    public void importData() {
        if(Files.exists(pathToFile)) {
            try {
                List<String> lines = Files.readAllLines(pathToFile);
                if(lines != null) {

                    Map<String, String> keysAndValues;

                    for (String line : lines) {
                        keysAndValues = parseSingleLine(line);
                        if (validKeysAndValues(keysAndValues)) {
                            addUserToList(usersList, keysAndValues.get(FIRST_NAME), keysAndValues.get(LAST_NAME), getSexFromMap(keysAndValues), getHeightFromMap(keysAndValues), getBirthDateFromMap(keysAndValues), getInterestsListFromMap(keysAndValues));
                        }
                        keysAndValues.clear();
                    }
                }
            } catch (IOException e) {
                System.out.println("Cannot read data from file");
            }
        }
    }
}
