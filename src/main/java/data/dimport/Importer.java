package data.dimport;

import user.Users;

import java.io.IOException;

public interface Importer {
    void importData(Users userList) throws IOException;
}
