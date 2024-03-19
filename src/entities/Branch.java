package entities;

import java.util.Objects;

public class Branch {
    private String city;
    private String state;
    private String code;

    public Branch(String city, String state, String code) {
        this.city = city;
        this.state = state;
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(getCity(), branch.getCity()) && Objects.equals(getState(), branch.getState()) && Objects.equals(getCode(), branch.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getState(), getCode());
    }

    @Override
    public String toString() {
        return "Branch{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
