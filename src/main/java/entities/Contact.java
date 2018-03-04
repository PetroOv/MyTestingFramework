package entities;

import java.util.Arrays;
import java.util.Objects;

public class Contact {
    private Data[] data;
    private String message;
    private int status;

    public Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "data=" + Arrays.toString(data) +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return getStatus() == contact.getStatus() &&
                Arrays.equals(getData(), contact.getData()) &&
                Objects.equals(getMessage(), contact.getMessage());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getMessage(), getStatus());
        result = 31 * result + Arrays.hashCode(getData());
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return this.getData()[0].getId();
    }

    public Info getInfo() {
        return this.getData()[0].getInfo();
    }

}
