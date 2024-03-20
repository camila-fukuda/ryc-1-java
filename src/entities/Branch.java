package entities;

import data.BranchData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Branch {
    private static final Map<String, String> LABEL_TO_ATTRIBUTE = new HashMap<>();

    static {
        LABEL_TO_ATTRIBUTE.put("City", "city");
        LABEL_TO_ATTRIBUTE.put("State", "state");
    }

    private final String city;
    private final String state;
    private final String code;

    public Branch(String city, String state) throws IllegalArgumentException {
        if (!validState(state)) {
            throw new IllegalArgumentException("State abbreviation must be exactly 2 characters long.");
        }
        this.city = city.toUpperCase();
        this.state = state.toUpperCase();
        this.code = getRandomCode().toUpperCase();
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

    public static Map<String, String> getLabelMap() {
        return LABEL_TO_ATTRIBUTE;
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


    public String getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code +
                " - Branch {city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
