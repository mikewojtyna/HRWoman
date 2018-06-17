package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

class UsersTest {
    Users users;

    @BeforeEach
    void createValidUsersList() {
        users = new Users();
    }

    @DisplayName("userDataToStringArray: Test output string lines data from list of users")
    @Test
    void listOfUsers() {
        addDefaultUsers();
        List<String> data = users.userDataToListOfStrings();
        assertThat(data.size()).isEqualTo(users.getUsersCounter());
        //System.out.println(data);
    }

    @DisplayName("Null value in add method")
    @Test
    void checkNullValueInAddMethod() {
        assertThat(users.add(null)).isFalse();
    }

    @DisplayName("Checking if add method is valid, and return true")
    @Test
    void checkAddMethod() {
        assertThat(users.add(new User("TestA", "TestA", Sex.MALE))).isTrue();
        assertThat(users.add(new User("TestB", "TestB", Sex.MALE))).isTrue();
        assertThat(users.add(new User("TestC", "TestC", Sex.MALE))).isTrue();
        assertThat(users.add(new User("TestD", "TestD", Sex.MALE))).isTrue();
        assertThat(users.add(new User("TestE", "TestE", Sex.MALE))).isTrue();
        assertThat(users.add(new User("TestF", "TestF", Sex.MALE))).isTrue();
    }

    @DisplayName("Checking duplicated users")
    @Test
    void checkDuplicatedValues() {
        addDefaultUsers();
        assertThat(users.add(new User("TestA", "TestA", Sex.MALE))).isFalse();
        assertThat(users.add(new User("TestB", "TestB", Sex.MALE))).isFalse();
        assertThat(users.add(new User("TestC", "TestC", Sex.MALE))).isFalse();
        assertThat(users.add(new User("TestD", "TestD", Sex.MALE))).isFalse();
        assertThat(users.add(new User("TestE", "TestE", Sex.MALE))).isFalse();
        assertThat(users.add(new User("TestF", "TestF", Sex.MALE))).isFalse();
    }

    @DisplayName("Check valid user id")
    @Test
    void checkUserId() {
        addDefaultUsers();
        assertThat(users.getUserById(0)).isInstanceOf(User.class);
        assertThat(users.getUserById(users.getUsersCounter() - 1)).isInstanceOf(User.class);
        assertThat(users.getUserById(new Random().nextInt(users.getUsersCounter()))).isInstanceOf(User.class);
        assertThat(users.getUserById(new Random().nextInt(users.getUsersCounter()))).isInstanceOf(User.class);
        assertThat(users.getUserById(new Random().nextInt(users.getUsersCounter()))).isInstanceOf(User.class);
    }

    @DisplayName("Check invalid users id")
    @Test
    void checkInvalidUserId() {
        addDefaultUsers();
        assertThat(users.getUserById(-1)).isNull();
        assertThat(users.getUserById(users.getUsersCounter())).isNull();
    }

    @DisplayName("Check null value in remove method")
    @Test
    void removeNullValue() {
        assertThat(users.remove(null)).isFalse();
    }

    @DisplayName("Check removing users from list")
    @Test
    void removeUsersFromList() {
        Random rand = new Random();
        addDefaultUsers();
        assertThat(users.getUsersCounter()).isGreaterThan(0);
        while(users.getUsersCounter() != 0) {
            assertThat(users.remove(users.getUserById(rand.nextInt(users.getUsersCounter())))).isTrue();
        }
    }

    @DisplayName("Check removing not exits users from list")
    @Test
    void removeNonExistsUsersFromList() {
        addDefaultUsers();
        assertThat(users.remove(new User("NoNA", "NoNA", Sex.MALE))).isFalse();
        assertThat(users.remove(new User("NoNB", "NoNB", Sex.MALE))).isFalse();
        assertThat(users.remove(new User("NoNC", "NoNC", Sex.MALE))).isFalse();
        assertThat(users.remove(new User("NoND", "NoND", Sex.MALE))).isFalse();
    }

    private void addDefaultUsers() {
        List<String> someInterests = new ArrayList<>();
        someInterests.add("lol1");
        someInterests.add("lok2");

        users.add(new User("TestA", "TestA", Sex.MALE));
        users.add(new User("TestB", "TestB", Sex.MALE));
        users.add(new User("TestC", "TestC", Sex.MALE));
        users.add(new User("TestD", "TestD", Sex.MALE));
        users.add(new User("TestE", "TestE", Sex.MALE, 190));
        users.add(new User("TestF", "TestF", Sex.MALE, 0, LocalDate.of(2000, 4, 19)));
        users.add(new User("TestG", "TestG", Sex.MALE, 0, LocalDate.of(2000, 4, 19), someInterests));

    }
}