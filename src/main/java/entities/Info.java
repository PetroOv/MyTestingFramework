package entities;

import java.util.Objects;

public class Info {
    private String email;
    private String firstName;
    private String lastName;

    public String getEmail() {
        return email;
    }

    public Info() {
    }

    @Override
    public String toString() {
        return "Info{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Info)) return false;
        Info info = (Info) o;
        return Objects.equals(getEmail(), info.getEmail()) &&
                Objects.equals(getFirstName(), info.getFirstName()) &&
                Objects.equals(getLastName(), info.getLastName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getEmail(), getFirstName(), getLastName());
    }

    public String getRequestData(){
        return "{\"email\":\"" + getEmail() +"\",\"firstName\":\"" + firstName + "\",\"lastName\":\"" +lastName +"\"}";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Info(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
