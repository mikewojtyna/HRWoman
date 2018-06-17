package data.dexport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import user.Sex;
import user.User;
import user.Users;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ExportToFileTest {
    Exporter exporter;
    Users usersList;
    Path path;

    @BeforeEach
    void initializeExporter() {
        path = Paths.get("usersResources","userdata.txt");
        usersList = new Users();
        //exporter = new ExportToFile(path);
    }

    @DisplayName("ExportToFile: trying initialize class with null file path")
    @Test
    void nullFileArgument() {
        assertThatNullPointerException().isThrownBy(() -> new ExportToFile(null));
    }

    @DisplayName("ExportToFile: trying initialize class with empty file path")
    @Test
    void emptyFileArgument() {
        assertThatExceptionOfType(FileNotFoundException.class).isThrownBy(() -> new ExportToFile(Paths.get("")));
        assertThatExceptionOfType(InvalidPathException.class).isThrownBy(() -> new ExportToFile(Paths.get("   ")));
        assertThatExceptionOfType(FileNotFoundException.class).isThrownBy(() -> new ExportToFile(Paths.get("","", "")));
    }

    @DisplayName("export: test null file path")
    @Test
    void nullFilePathToExport() {
        try {
            exporter = new ExportToFile(null);
        } catch (Exception e) {
            //ignore
        }

        assertThatNullPointerException().isThrownBy(() -> exporter.export(usersList));
    }

    @DisplayName("export: test null list to export")
    @Test
    void nullListToExport() {
        usersList = null;
        assertThatNullPointerException().isThrownBy(() -> exporter.export(usersList));
    }

    @DisplayName("export: test write to file")
    @Test
    void writeToFileTest() throws Exception {
        Path tmpFile = Files.createTempFile("tmp", null);
        exporter = new ExportToFile(tmpFile);
        addDefaultUsers();
        exporter.export(usersList);
        assertThat(contentOf(tmpFile.toFile())).contains(usersList.userDataToListOfStrings());
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