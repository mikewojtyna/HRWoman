package data.dimport;

import user.Users;

import java.util.List;
import java.util.Map;

/*
* Major class, to processing users data from sources. Most of method used in this class, is in abstract class called "RunnerResources".
* */
public class ListDataRunner extends RunnerResources {

    private final List<String> dataList;

    public ListDataRunner(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public void importData(Users usersList) {
        if(usersList == null) throw new NullPointerException("User list to save data has null value");
        if(dataList == null) throw new NullPointerException("List to read data has null value");

        Map<String, String> keysAndValues;

        for(String line : dataList) {
            keysAndValues = parseSingleLine(line);
            if(validKeysAndValues(keysAndValues)) {
                addUserToList(usersList, keysAndValues.get(FIRST_NAME), keysAndValues.get(LAST_NAME), getSexFromMap(keysAndValues), getHeightFromMap(keysAndValues), getBirthDateFromMap(keysAndValues), getInterestsListFromMap(keysAndValues));
            } else {
                //Should throw exception or only info?
                //System.out.println("Invalid data input format in importData method: " + keysAndValues);
            }
            keysAndValues.clear();
        }

    }
}
