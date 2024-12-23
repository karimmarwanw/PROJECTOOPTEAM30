package Entity;
public abstract class Person {
    protected String username;
    protected String password;
    protected String dateOfBirth;

    public Person(String username, String password, String dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (isValidUsername(username)) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username must contain only letters.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must contain only numbers.");
        }
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public abstract void displayRole();

    public boolean isValidUsername(String username) {
        return username != null && username.matches("^[a-zA-Z]+$");
    }

    public boolean isValidPassword(String password) {
        return password != null && password.matches("^[0-9]+$");
    }
}

