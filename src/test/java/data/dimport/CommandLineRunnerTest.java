package data.dimport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Users;

import static org.assertj.core.api.Assertions.*;

class CommandLineRunnerTest {
    CommandLineRunner importer;
    Users usersList;

    @BeforeEach
    void initializeImporter() {
        usersList = new Users();
        importer = new CommandLineRunner(defaultStringArrayWithData());
    }

    @DisplayName("importDataFromArray: Check null array to read")
    @Test
    void checkNullArrayToRead() {
        importer = new CommandLineRunner(null);
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(0);
    }

    @DisplayName("importDataFromArray: Check empty array to read")
    @Test
    void checkEmptyArrayToRead() {
        String[] data = {"",""};
        importer = new CommandLineRunner(data);
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(0);
    }

    @DisplayName("importDataFromArray: Check not empty, but invalid values to read")
    @Test
    void checkNotValidValues() {
        String[] data = {"asd", "dsa", "das"};
        importer = new CommandLineRunner(data);
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(0);
    }

    @DisplayName("importDataFromArray: Check valid values from defaultValues")
    @Test
    void importValidValues() {
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(defaultStringArrayWithData().length);
        System.out.println(usersList);
    }

    private String[] defaultStringArrayWithData() {
        return new String[] {
                "firstName=TestA lastName=TestA sex=Male",
                "firstName=TestB lastName=TestB sex=Male",
                "firstName=TestC lastName=TestC sex=Female",
                "firstName=TestD lastName=TestD sex=Female",
                "firstName=TestE lastName=TestE sex=Male height=190",
                "firstName=TestF lastName=TestF sex=Female birthDate=1991-04-19",
                "firstName=TestG lastName=TestG sex=Female interests=\"interest1\",\"interest2\"",
                "firstName=TestH lastName=TestH sex=Female height=174 interests=\"interest1\",\"interest2\"",
                "firstName=TestI lastName=TestI sex=Female height=174 birthDate=2000-2-5 interests=\"interest1A\",\"interest2B\""
        };
    }
}