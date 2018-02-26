package entities;

import java.util.Objects;

public class Refs {
    private String patch;
    private String get;
    private String delete;
    private String put;

    @Override
    public String toString() {
        return "Refs{" +
                "patch='" + patch + '\'' +
                ", get='" + get + '\'' +
                ", delete='" + delete + '\'' +
                ", put='" + put + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refs)) return false;
        Refs refs = (Refs) o;
        return Objects.equals(getPatch(), refs.getPatch()) &&
                Objects.equals(getGet(), refs.getGet()) &&
                Objects.equals(getDelete(), refs.getDelete()) &&
                Objects.equals(getPut(), refs.getPut());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPatch(), getGet(), getDelete(), getPut());
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getPut() {
        return put;
    }

    public void setPut(String put) {
        this.put = put;
    }
}
