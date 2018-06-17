package user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

class UserTest {

    /*
     * Valid name must have only characters from a-z (ignore case) and Polish letters like óęąśłńćźż.
     * Length of the name must be between 2 to 32 characters.
     * */
    @DisplayName("Constructor test with valid values. Only first name, last name and sex")
    @Test
    void TestSimpleConstructor() {
        Object[][] data = {
                {"Rafał", "Żółtański", Sex.MALE},
                {"Adrianna", "Brząkiewicz", Sex.FEMALE},
                {"Adam", "Zieliński", Sex.MALE},
                {"So", "Zieliński", Sex.MALE},
                {"Short", "Na", Sex.MALE},
                {"LongNameLongNameLongNameLongName", "TEST", Sex.MALE},
                {"Test", "LongNameLongNameLongNameLongName", Sex.MALE}
        };

        for (Object[] row : data) {
            assertThat(new User((String) row[0], (String) row[1], (Sex) row[2])).isExactlyInstanceOf(User.class);
        }
    }

    @DisplayName("Test constructor with null values of first and last name")
    @Test
    void nullFirstAndLastNameValues() {
        Object[][] data = {
                {null, null, Sex.MALE},
                {null, "Żótański", Sex.MALE},
                {"Rafał", null, Sex.MALE}
        };
        for(Object[] row : data) {
            assertThatIllegalArgumentException().isThrownBy(() -> new User((String) row[0], (String) row[1], (Sex) row[2]));
        }
    }

    /*
    * Valid name must have only characters from a-z (ignore case) and Polish letters like óęąśłńćźż.
    * Length of the name must be between 2 to 32 characters.
    * */
    @DisplayName("Test constructor with invalid first and last name values")
    @Test
    void invalidFirstAndLastNameValues() {
        Object[][] data = {
                {" Rafał", "Żótański", Sex.MALE},
                {"Rafał ", "Żótański", Sex.MALE},
                {"Ra fał", "Żótański", Sex.MALE},
                {"Rafał", " Żótański", Sex.MALE},
                {"Rafał", "Żótański ", Sex.MALE},
                {"Rafał", "Żót ański", Sex.MALE},
                {"Raf3ał", "Żótański", Sex.MALE},
                {"Rafał", "Żót3ański", Sex.MALE},
                {"", "Żótański", Sex.MALE},
                {"Rafał", "", Sex.MALE},
                {"", "", Sex.MALE},
                {"R", "Żótański", Sex.MALE},
                {"Rafał", "Z", Sex.MALE},
                {"Rafałaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Żótański", Sex.MALE},
                {"Żótański", "Rafałaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Sex.MALE},

        };
        for(Object[] row : data) {
            assertThatIllegalArgumentException().isThrownBy(() -> new User((String) row[0], (String) row[1], (Sex) row[2]));
        }
    }

    @DisplayName("Test null value in sex argument")
    @Test
    void testNullInSex() {
        assertThatIllegalArgumentException().isThrownBy(() -> new User("Rafał", "Żółtański", null));
    }

    @DisplayName("Test valid height values")
    @Test
    void validHeightValues() {
        Object[][] data = {
                {"Test", "Test", Sex.MALE, User.MIN_HEIGHT_VALUE},
                {"Test", "Test", Sex.MALE, User.MAX_HEIGHT_VALUE}
        };

        for (Object[] row : data) {
            assertThat(new User((String) row[0], (String) row[1], (Sex) row[2], (int) row[3])).isExactlyInstanceOf(User.class);
        }
    }

    @DisplayName("Test invalid height values")
    @Test
    void invalidHeightValues() {
        Object[][] data = {
                {"Test", "Test", Sex.MALE, User.MIN_HEIGHT_VALUE - 1},
                {"Test", "Test", Sex.MALE, User.MAX_HEIGHT_VALUE + 1}
        };

        for (Object[] row : data) {
            assertThatIllegalArgumentException().isThrownBy(() -> new User((String) row[0], (String) row[1], (Sex) row[2], (int) row[3]));
        }
    }

    @DisplayName("Test valid birth dates in constructor")
    @Test
    void validBirthDates() {
        Object[][] data = {
                {"Test", "Test", Sex.MALE, User.MIN_HEIGHT_VALUE, LocalDate.of(1991, 4, 29)},
                {"Test", "Test", Sex.MALE, User.MIN_HEIGHT_VALUE, LocalDate.of(1991, 1, 1)},
                {"Test", "Test", Sex.MALE, User.MIN_HEIGHT_VALUE, LocalDate.of(1991, 12, 31)}
        };

        for (Object[] row : data) {
            assertThat(new User((String) row[0], (String) row[1], (Sex) row[2], (int) row[3], (LocalDate) row[4])).isExactlyInstanceOf(User.class);
        }
    }

    @DisplayName("Test invalid birth dates in constructor")
    @Test
    void invalidBirthDates() {
        assertThatExceptionOfType(DateTimeException.class).isThrownBy(() -> new User("Test", "Test", Sex.MALE, 0, LocalDate.of(1991, 0, 29)));
        assertThatExceptionOfType(DateTimeException.class).isThrownBy(() -> new User("Test", "Test", Sex.MALE, 0, LocalDate.of(1991, 13, 29)));
        assertThatExceptionOfType(DateTimeException.class).isThrownBy(() -> new User("Test", "Test", Sex.MALE, 0, LocalDate.of(1991, 4, 0)));
        assertThatExceptionOfType(DateTimeException.class).isThrownBy(() -> new User("Test", "Test", Sex.MALE, 0, LocalDate.of(1991, 4, 31)));
    }

    @DisplayName("Test valid interests in constructor")
    @Test
    void validInterests() {
        List<String> interests = new ArrayList<>();
        interests.add("test 1");
        interests.add("test 2");
        interests.add("test 3");

        assertThat(new User("Test", "Test", Sex.MALE, 0, null, interests)).isExactlyInstanceOf(User.class);
    }

    @DisplayName("Test null value in interests list (null is correct value)")
    @Test
    void validNullInterests() {
        List<String> interests = null;

        assertThat(new User("Test", "Test", Sex.MALE, 0, null, interests)).isExactlyInstanceOf(User.class);
    }

    @DisplayName("Test saving data from constructor to class fields (data from getters)")
    @Test
    void checkConstructorDataSaving() {
        List<List<String>> interestsCollector = new ArrayList<>();
        interestsCollector.add(new ArrayList<>());
        interestsCollector.add(new ArrayList<>());
        interestsCollector.add(new ArrayList<>());

        interestsCollector.get(0).add("Test 0 interest A");
        interestsCollector.get(0).add("Test 0 interest B");
        interestsCollector.get(0).add("Test 0 interest C");

        interestsCollector.get(1).add("Test 1 interest A");
        interestsCollector.get(1).add("Test 1 interest B");
        interestsCollector.get(1).add("Test 1 interest C");

        interestsCollector.get(2).add("Test 2 interest A");
        interestsCollector.get(2).add("Test 2 interest B");
        interestsCollector.get(2).add("Test 2 interest C");

        Object[][] data = {
                {"TestA", "TestA", Sex.MALE, User.MIN_HEIGHT_VALUE, LocalDate.of(1991, 4, 29), new Random().nextInt(interestsCollector.size())},
                {"TestB", "TestB", Sex.FEMALE, User.MAX_HEIGHT_VALUE - 22, LocalDate.of(1990, 4, 3), new Random().nextInt(interestsCollector.size())},
                {"TestC", "TestC", Sex.MALE, User.MIN_HEIGHT_VALUE + 44, LocalDate.of(2014, 12, 23), new Random().nextInt(interestsCollector.size())}
        };

        User user;
        for (Object[] row : data) {
            int index = (int) row[5];
            user = new User((String) row[0], (String) row[1], (Sex) row[2], (int) row[3], (LocalDate) row[4], interestsCollector.get(index));
            assertThat(user.getFirstName()).isEqualTo((String)row[0]);
            assertThat(user.getLastName()).isEqualTo((String)row[1]);
            assertThat(user.getSex()).isEqualTo((Sex)row[2]);
            assertThat(user.getHeight()).isEqualTo((int)row[3]);
            assertThat(user.getBirthDate()).isEqualTo((LocalDate)row[4]);

            String[] stringInterests = new String[interestsCollector.get(index).size()];
            stringInterests = interestsCollector.get(index).toArray(stringInterests);
            assertThat(user.getInterests()).containsOnly(stringInterests);
        }
    }

    /*
    * Important: equal function testing only names and sex values. Other like birth date etc. are doesn't matter.
    * */
    @DisplayName("Test valid equals users")
    @Test
    void testValidEqualMethod() {
        User[] users_1 = {
                new User("Rafał", "Żółtański", Sex.MALE),
                new User("Adrianna", "Brząkiewicz", Sex.FEMALE),
                new User("Adam", "Zieliński", Sex.MALE),
                new User("So", "Zieliński", Sex.MALE),
                new User("Short", "Na", Sex.MALE),
                new User("LongNameLongNameLongNameLongName", "TEST", Sex.MALE),
                new User("Test", "LongNameLongNameLongNameLongName", Sex.MALE)
        };
        User[] users_2 = {
                new User("Rafał", "Żółtański", Sex.MALE),
                new User("Adrianna", "Brząkiewicz", Sex.FEMALE),
                new User("Adam", "Zieliński", Sex.MALE),
                new User("So", "Zieliński", Sex.MALE),
                new User("Short", "Na", Sex.MALE),
                new User("LongNameLongNameLongNameLongName", "TEST", Sex.MALE),
                new User("Test", "LongNameLongNameLongNameLongName", Sex.MALE)
        };

        for(int i = 0; i < users_1.length; i ++) {
            assertThat(users_1[i].equals(users_2[i])).isTrue();
        }
    }

    @DisplayName("Test invalid equals users")
    @Test
    void testInvalidEqualMethod() {
        User[] users_1 = {
                new User("Rafał", "Żółtański", Sex.MALE),
                new User("Adrianna", "Brząkiewicz", Sex.FEMALE),
                new User("Adam", "Zieliński", Sex.MALE),
                new User("So", "Zieliński", Sex.MALE),
                new User("Short", "Na", Sex.MALE),
                new User("LongNameLongNameLongNameLongName", "TEST", Sex.MALE),
                new User("Test", "LongNameLongNameLongNameLongName", Sex.MALE)
        };
        User users_2 = new User("Inna", "Nazwa", Sex.MALE);

        for(int i = 0; i < users_1.length; i ++) {
            assertThat(users_1[i].equals(users_2)).isFalse();
        }
    }
}