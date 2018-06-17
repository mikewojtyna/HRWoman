package user;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class User {

    public static final int MIN_HEIGHT_VALUE = 0;
    public static final int MAX_HEIGHT_VALUE = 300;

    private String firstName;
    private String lastName;
    private Sex sex;
    private int height;
    private LocalDate birthDate;
    private List<String> interests = new ArrayList<>();

    public User(String firstName, String lastName, Sex sex, int height, LocalDate birthDate, List<String> interests) {
        if(!isValidName(firstName) || !isValidName(lastName)) throw new IllegalArgumentException("Invalid first name or last name argument");
        if(sex == null) throw new IllegalArgumentException("Invalid sex argument value");
        if(!isValidHeight(height)) throw new IllegalArgumentException("Invalid height argument value");

        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.height = height;
        this.birthDate = birthDate;
        if(interests != null) this.interests.addAll(interests);
    }

    public User(String firstName, String lastName, Sex sex, int height, LocalDate birthDate) {
        this(firstName, lastName, sex, height, birthDate, null);
    }

    public User(String firstName, String lastName, Sex sex, int height) {
        this(firstName, lastName, sex, height,null);
    }

    public User(String firstName, String lastName, Sex sex) {
        this(firstName, lastName, sex, 0);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Sex getSex(){
        return this.sex;
    }

    public int getHeight() {
        return this.height;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public List<String> getInterests() {
        return this.interests.subList(0, this.interests.size());
    }

    public String userDataToStringLine() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("firstName=%s lastName=%s sex=%s", getFirstName(), getLastName(), getSex()));
        if(getHeight() > 0) sb.append(" height=" + getHeight());
        if(getBirthDate() != null) sb.append(" birthDate=" + getBirthDate());
        if(getInterests().size() > 0) {
            StringBuilder interestBuilder = new StringBuilder();
            String[] interestArray = new String[getInterests().size()];
            interestArray = getInterests().toArray(interestArray);
            for(String singleInterest : interestArray) interestBuilder.append("\""+singleInterest+"\",");
            interestBuilder.deleteCharAt(interestBuilder.length() - 1);
            sb.append(" interests=");
            sb.append(interestBuilder);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != getClass()) return false;
        if(o == this) return true;

        User user = ((User)o);
        return user.getFirstName().equals(getFirstName()) && user.getLastName().equals(getLastName()) && user.getSex().equals(getSex());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDate = getBirthDate() != null ? getBirthDate().format(formatter) : ("Not initialized");

        return String.format("* First name: %s | Last name: %s" + System.lineSeparator() +
                "* Sex: %s | Height: %d | Birth date: %s" + System.lineSeparator() +
                "* Interests: %s",
                getFirstName(), getLastName(), getSex().getTypeName(), getHeight(), birthDate, getInterests()
        );

    }

    private boolean isValidName(String name) {
        return name != null && name.toLowerCase().matches("^[a-zóęąśłńćźż]{2,32}$");
    }

    private boolean isValidHeight(int height) {
        return height >= User.MIN_HEIGHT_VALUE && height <= User.MAX_HEIGHT_VALUE;
    }

}
