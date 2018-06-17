package dataimport;

import user.Sex;
import user.User;
import user.Users;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class CommandLineRunner extends InputResources {
    private Users usersList;
    private String[] dataFromConsole;

    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";


    public CommandLineRunner(Users usersList, String[] dataFromConsole) {
        this.dataFromConsole = dataFromConsole;
        this.usersList = usersList;
    }

    @Override
    public void importData() {
        if(dataFromConsole != null) {

            Map<String, String> keysAndValues;

            for (String line : dataFromConsole) {
                keysAndValues = parseSingleLine(line);
                if (validKeysAndValues(keysAndValues)) {
                    addUserToList(usersList, keysAndValues.get(FIRST_NAME), keysAndValues.get(LAST_NAME), getSexFromMap(keysAndValues), getHeightFromMap(keysAndValues), getBirthDateFromMap(keysAndValues), getInterestsListFromMap(keysAndValues));
                }
                keysAndValues.clear();
            }
        }
    }


}
