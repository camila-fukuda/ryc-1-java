package entities;

import data.BranchData;

import java.util.Objects;
import java.util.Random;

public class Branch {
    private final String state;
    private String city;
    private String code;

    public Branch(String city, String state) throws IllegalArgumentException {
        if (!validState(state)) {
            throw new IllegalArgumentException("State abbreviation must be exactly 2 characters long.");
        }
        this.city = city;
        this.state = state.toUpperCase();
        this.code = getRandomCode();
    }

    private static String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "BR-" + String.format("%05d", randomNumber);
    }

    public static boolean validState(String state) {
        return state.length() == 2;
    }

    private static String getRandomCode() {
        String code = generateRandomCode();
        while (BranchData.containsCode(code)) {
            code = generateRandomCode();
        }
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(getState(), branch.getState()) && Objects.equals(getCity(), branch.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getCity());
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
