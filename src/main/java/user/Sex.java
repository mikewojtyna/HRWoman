package user;

public enum Sex {
    MALE("Mężczyzna"), FEMALE("Kobieta");

    //----------------------------------

    private String typeName;

    Sex(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

}
