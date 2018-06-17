package dataexport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Sex;
import user.User;
import user.Users;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ExportToFileTest {
    Exporter exporter;
    Users usersList;

    @BeforeEach
    void initializeExporter() {
        Path path = Paths.get("usersResources","userdata.txt");
        usersList = new Users();
        exporter = new ExportToFile(path);
    }

    @DisplayName("export: trying add user data from list to file")
    @Test
    void empty() {
        addDefaultUsers();
        exporter.export(usersList);
    }

    private void addDefaultUsers() {
        List<String> someInterests = new ArrayList<>();
        someInterests.add("lol1");
        someInterests.add("lok2");

        usersList.add(new User("TestA", "TestA", Sex.MALE));
        usersList.add(new User("TestB", "TestB", Sex.MALE));
        usersList.add(new User("TestC", "TestC", Sex.MALE));
        usersList.add(new User("TestD", "TestD", Sex.MALE));
        usersList.add(new User("TestE", "TestE", Sex.MALE, 190));
        usersList.add(new User("TestF", "TestF", Sex.MALE, 0, LocalDate.of(2000, 4, 19)));
        usersList.add(new User("TestG", "TestG", Sex.MALE, 0, LocalDate.of(2000, 4, 19), someInterests));

    }

}