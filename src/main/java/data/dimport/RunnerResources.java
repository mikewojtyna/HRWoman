package data.dimport;

import user.Sex;
import user.User;
import user.Users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* This abstract class contains most of methods to process users data.
* */

public abstract class RunnerResources implements Importer {

    final String FIRST_NAME = "firstName";
    final String LAST_NAME = "lastName";
    final String SEX = "sex";
    final String HEIGHT = "height";
    final String BIRTH_DATE = "birthDate";
    final String INTERESTS = "interests";

    void addUserToList(Users usersList, String firstName, String lastName, Sex sex, int height, LocalDate birthDate, List<String> interests) {
        usersList.add(new User(firstName, lastName, sex, height, birthDate, interests));
    }

    Map<String, String> parseSingleLine(String line) {
        Map<String, String> dataMap = new HashMap<>();
        if(line != null) {
            String[] splitLine = line.split(" ");
            String[] keyValue;

            for (String row : splitLine) {
                row = row.trim();
                if (row.indexOf("=") > 0 && row.indexOf("=") < row.length() - 1) {
                    keyValue = row.split("=");
                    dataMap.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return dataMap;
    }

    boolean validKeysAndValues(Map<String, String> map) {
        if(map == null) return false;
        if(map.containsKey(FIRST_NAME) && map.containsKey(LAST_NAME) && map.containsKey(SEX)) {
            return true;
        }
        return false;
    }

    Sex getSexFromName(String name) {
        Sex sex;
        try {
            sex = Sex.valueOf(name.toUpperCase());
        } catch(IllegalArgumentException e) {
            sex = null;
        }
        return sex;
    }

    Sex getSexFromMap(Map<String, String> map) {
        return getSexFromName(map.get(SEX));
    }

    int getHeightFromMap(Map<String, String> map) {
        if(map.containsKey(HEIGHT)) {
            return Integer.valueOf(map.get(HEIGHT));
        }
        return 0;
    }

    LocalDate getBirthDateFromMap(Map<String, String> map) {
        if(map.containsKey(BIRTH_DATE)) {
            String birthDate = map.get(BIRTH_DATE);
            if(birthDate.matches("^[1-9][\\d]{3}-[\\d]{1,2}-[\\d]{1,2}$")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                return LocalDate.parse(birthDate, formatter);
            }
        }
        return null;
    }

    List<String> getInterestsListFromMap(Map<String, String> map) {
        if(map.containsKey(INTERESTS)) {
            List<String> listOfInterests = new ArrayList<>();
            String[] splittedInterests = map.get(INTERESTS).trim().split(",");
            for(String singleInterest : splittedInterests) {
                if(singleInterest.endsWith("\"")) singleInterest = singleInterest.substring(0, singleInterest.length() - 1);
                if(singleInterest.startsWith("\"")) singleInterest = singleInterest.replaceFirst("\"", "");
                listOfInterests.add(singleInterest);
            }
            return listOfInterests;
        }
        return null;
    }
}
