package start;

import data.dexport.ExportToFile;
import data.dimport.CommandLineRunner;
import data.dimport.FileDataRunner;
import user.Users;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/*
*
*
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
