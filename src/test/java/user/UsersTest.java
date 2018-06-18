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

    // 1. Trochę to zamieszane, za dużo ukryasz w metodac a opis też nie jest dla mnie jasny
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

    // 1. Co to właściwie znaczy Valid? W tej chwili po prostu sprawszasz, czy metoda users.add zwraca true dla roznych argumentow
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

    // 1. Tak samo jak w analogicznym tescie - efektywnie sprawdzasz tylko, czy ta metoda zwraca boolean - nie sprawdzasz, czy rzeczywiscie cos zostalo dodane
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

    // 1. nie masz po co sprawdzac czy metoda, ktora zwraca typ rzeczywiscie go zwraca jako taki typ
    // 2. juz predzej sprawdz czy isNotNull, ale i tak mi sie tutaj to nie podoba
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

    // 1. Opis jest niejasny, powinines zmienic na "should return null when find user by non-existent id"
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

    // 1. While w tescie - to juz wyglada zle
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

    // 1. Znowu - sprawdzasz tylko boolean, nic wiecej
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
