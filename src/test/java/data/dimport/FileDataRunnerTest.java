package data.dimport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.Users;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

class FileDataRunnerTest {
    @DisplayName("importData: Test null value of file path")
    @Test
    void testNullValueInFilePath() {
        Users usersList = new Users();
        Importer importer = new FileDataRunner(null);
        assertThatNullPointerException().isThrownBy(() -> importer.importData(usersList));
    }

    @DisplayName("importData: Test empty value of file path")
    @Test
    void testEmptyValueInFilePath() {
        Users usersList = new Users();
        Importer importer = new FileDataRunner(Paths.get("", ""));
        assertThatIOException().isThrownBy(() -> importer.importData(usersList));
    }
}