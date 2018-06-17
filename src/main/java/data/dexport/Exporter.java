package data.dexport;

import user.Users;

import java.io.IOException;

public interface Exporter {
    void export(Users usersList) throws IOException;
}
