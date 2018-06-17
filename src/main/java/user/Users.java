package user;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> userList = new ArrayList<>();

    /*
    * New user to add, must be unique. Users in list are compare by first name, last name and sex. If new user values will
    * be the same with some user from list data, new user won't be add.
    * */
    public boolean add(User user) {
        if(user != null && userList.stream().noneMatch(u -> u.equals(user))) {
            userList.add(user);
            return true;
        }
        return false;
    }

    public boolean remove(User user) {
        if(user == null) return false;
        return userList.removeIf(u -> u.equals(user));
    }

    public User getUserById(int id) {
        return (isValidId(id)) ? userList.get(id) : null;
    }

    public int getUsersCounter() {
        return userList.size();
    }

    private boolean isValidId(int id) {
        return id >= 0 && id < userList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for(User user : userList) {
            sb.append("* ---------- User: " + (index ++) + "----------");
            sb.append(System.lineSeparator());
            sb.append(user.toString() + System.lineSeparator());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public List<String> userDataToListOfStrings() {
        List<String> data = new ArrayList<>();
        for(User user : userList) data.add(user.userDataToStringLine());
        return data;
    }
}
