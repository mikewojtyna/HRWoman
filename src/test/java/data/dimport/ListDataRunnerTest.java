package data.dimport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Users;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ListDataRunnerTest {
    ListDataRunner importer;
    Users usersList;

    @BeforeEach
    void initializeImporter() {
        usersList = new Users();
        importer = new ListDataRunner(defaultStringListWithData());
    }

    @DisplayName("importDataFromArray: Check null array to read")
    @Test
    void checkNullArrayToRead() {
        importer = new ListDataRunner(null);
        assertThatNullPointerException().isThrownBy(() -> importer.importData(usersList));
    }

    @DisplayName("importDataFromArray: Check null users list to save data")
    @Test
    void checkNullListToSave() {
        assertThatNullPointerException().isThrownBy(() -> importer.importData(null));
    }

    @DisplayName("importDataFromArray: Check empty array to read")
    @Test
    void checkEmptyArrayToRead() {
        List<String> data = new ArrayList<>();
        importer = new ListDataRunner(data);
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(0);
    }

    @DisplayName("importDataFromArray: Check not empty, but invalid values to read")
    @Test
    void checkNotValidValues() {
        List<String> data = new ArrayList<>();
        data.add("asadsads");
        data.add("dasdasad");
        data.add("test");

        importer = new ListDataRunner(data);
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(0);
    }

    @DisplayName("importDataFromArray: Check valid values from defaultValues")
    @Test
    void importValidValues() {
        importer.importData(usersList);
        assertThat(usersList.getUsersCounter()).isEqualTo(defaultStringListWithData().size());
        //System.out.println(usersList);
    }

    /*
     * Tests for 'parseSingleLine'.
     * */
    @DisplayName("parseSingleLine: test null values")
    @Test
    void testNullInLineParser() {
        assertThat(importer.parseSingleLine(null)).isEmpty();
    }

    @DisplayName("parseSingleLine: test empty, but existing line")
    @Test
    void testEmptyLineInParser() {
        assertThat(importer.parseSingleLine("")).isEmpty();
    }

    @DisplayName("parseSingleLine: test string, but not in key=value format")
    @Test
    void testNotMapStringLine() {
        assertThat(importer.parseSingleLine("asdasd")).isEmpty();
    }

    @DisplayName("parseSingleLine: valid values")
    @Test
    void validValuesToLineParser() {
        List<String> data = defaultStringListWithData();
        for(String line : data) {
            assertThat(importer.parseSingleLine(line)).isNotEmpty();
        }
    }
    /*
     * End of testing parseSingleLine
     * */

    /*
     * Test validKeysAndValues method
     * In this method, values are not checking. Check only keys. To check values, is another class (User).
     * */
    @DisplayName("validKeysAndValues: null list")
    @Test
    void nullValuesInValidator() {
        assertThat(importer.validKeysAndValues(null)).isFalse();
    }

    @DisplayName("validKeysAndValues: empty, but existing map")
    @Test
    void emptyMapInValidator() {
        assertThat(importer.validKeysAndValues(new HashMap<>())).isFalse();
    }

    @DisplayName("validKeysAndValues: correct map, but without required values")
    @Test
    void testInvalidKeysInMap() {
        Map<String, String> map = new HashMap<>();
        map.put("testA", "testA");
        map.put("testB", "testB");
        map.put("testC", "testC");

        assertThat(importer.validKeysAndValues(map)).isFalse();
    }

    @DisplayName("validKeysAndValues: correct map, with valid required values")
    @Test
    void testValidKeysInMap() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "testA");
        map.put("lastName", "testB");
        map.put("sex", "testC");

        assertThat(importer.validKeysAndValues(map)).isTrue();
    }
    /*
     * End of testing validKeysAndValues
     * */

    private List<String> defaultStringListWithData() {
        return Arrays.asList(
                "firstName=TestA lastName=TestA sex=Male",
                "firstName=TestB lastName=TestB sex=Male",
                "firstName=TestC lastName=TestC sex=Female",
                "firstName=TestD lastName=TestD sex=Female",
                "firstName=TestE lastName=TestE sex=Male height=190",
                "firstName=TestF lastName=TestF sex=Female birthDate=1991-04-19",
                "firstName=TestG lastName=TestG sex=Female interests=\"interest1\",\"interest2\"",
                "firstName=TestH lastName=TestH sex=Female height=174 interests=\"interest1\",\"interest2\"",
                "firstName=TestI lastName=TestI sex=Female height=174 birthDate=2000-2-5 interests=\"interest1A\",\"interest2B\""
        );
    }
}