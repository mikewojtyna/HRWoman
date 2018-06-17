package start;

import data.dexport.ExportToFile;
import data.dimport.CommandLineRunner;
import data.dimport.FileDataRunner;
import user.Users;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
* *** HRWoman project ***
* In this simple project, You can create list of users and save and load them from file and command line.
* Users are save in format (single line = single user):
*   firstName=[data] lastName=[data] Sex=[data] height=[data] birthDate=[data] interests=[data]
* Example:
*   firstName=Adam lastName=Kowalski sex=male height=184 birthDate=1986-04-2 interests="łowienie ryb","jazda samochodem"
*   firstName=Alicja lastName=Woźniak sex=female height=163 birthDate=1990-6-22 interests="taniec","sporty walki","kuchnia włoska"
* firstName, lastName and sex value are obligatory, other are optional. Remember - You can't add two of the same users. Users are
* compared by obligatory args.
*
* Note to birthDate: You must write birth date in format yyyy-mm-dd (where yyyy is year, mm is month and dd is day).
*
* Important! Interests must be last of argument list!
*
* As You see, You can add multiple lines to command line, but remember - each must be between quotes symbols.
* Check this in console:
*   "firstName=Adam lastName=Kowalski sex=male height=184 birthDate=1986-04-2 interests=\"łowienie ryb\",\"jazda samochodem\"" "firstName=Alicja lastName=Woźniak sex=female height=163 birthDate=1990-6-22 interests=\"taniec\",\"sporty walki\",\"kuchnia włoska\""
*
* */

public class Main {
    private static final Path usersDataPath = Paths.get("usersResources", "userdata.txt");

    public static void main(String[] args) {
        /*
         * Important: Command line is more important than read data from file, so it should be first!
         * */

        final Users usersList = new Users();
        try {
            new CommandLineRunner(args).importData(usersList);
            new FileDataRunner(usersDataPath).importData(usersList);
            new ExportToFile(usersDataPath).export(usersList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(usersList);

    }
}
