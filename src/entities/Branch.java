package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Branch {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();

    static {
        FIELD_LABEL_TO_NAME.put("City", "city");
        FIELD_LABEL_TO_NAME.put("State", "state");
    }

    private final String city;
    private final String state;
    private final String code;

    public Branch(String city, String state, String code) throws IllegalArgumentException {
        if (!validState(state)) {
            throw new IllegalArgumentException("State abbreviation must be exactly 2 characters long.");
        }

        this.city = city.toUpperCase();
        this.state = state.toUpperCase();
        this.code = code.toUpperCase();
    }

    public static boolean validState(String state) {
        return state.length() == 2;
    }


    public static Map<String, String> getFieldLabelToName() {
        return FIELD_LABEL_TO_NAME;
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
