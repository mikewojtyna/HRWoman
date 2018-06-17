package data.dimport;

import user.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Objects of this class read data from String array (ex. from console). Data are converted to List of strings, and will
* be processed by ListDataRunner.
* */

public class CommandLineRunner extends RunnerResources {
    private List<String> dataFromConsole = new ArrayList<>();

    public CommandLineRunner(String[] dataFromConsole) {
        if(dataFromConsole != null) {
            this.dataFromConsole.addAll(Arrays.asList(dataFromConsole));
        }
    }

    @Override
    public void importData(Users usersList) {
        new ListDataRunner(dataFromConsole).importData(usersList);
    }

}
