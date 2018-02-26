package entities;

import java.util.Objects;

public class Data {
    private int Id;
    private Info info;
    private Refs refs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return getId() == data.getId() &&
                Objects.equals(getInfo(), data.getInfo()) &&
                Objects.equals(getRefs(), data.getRefs());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getInfo(), getRefs());
    }

    public Refs getRefs() {
        return refs;
    }

    public void setRefs(Refs refs) {
        this.refs = refs;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "Id=" + Id +
                ", info=" + info +
                ", refs=" + refs +
                '}';
    }

    public void setId(int id) {
        Id = id;
    }
}
