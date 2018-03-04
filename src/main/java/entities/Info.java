package entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Info {
    private String email;
    private String firstName;
    private String lastName;

    public Info() {
    }

    public Info(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRequestData() {
        StringBuilder result = new StringBuilder();
        List<String> resultList = new ArrayList<>();
        result.append("{");
        if (email != null)
            resultList.add("\"email\":\"" + (email) + ("\""));
        if (firstName != null)
            resultList.add("\"firstName\":\"" + (firstName) + ("\""));
        if (lastName != null)
            resultList.add("\"lastName\":\"" + (lastName) + ("\""));
        for (int i = 0; i < resultList.size(); i++) {
            result.append(resultList.get(i));
            if (i != resultList.size() - 1)
                result.append(",");
        }
        result.append("}");
        return String.valueOf(result);

//        return "{\"email\":\"" + email  +"\",\"firstName\":\"" + firstName + "\",\"lastName\":\"" +lastName +"\"}";
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

    public boolean checkNullFields() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) == null || f.get(this) == "")
                return false;
        return true;
    }
}
