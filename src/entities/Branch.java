package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record Branch(String city, String state, String code) {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();

    static {
        FIELD_LABEL_TO_NAME.put("City", "city");
        FIELD_LABEL_TO_NAME.put("State", "state");
    }

    /**
     * @throws IllegalArgumentException
     */
    public Branch(String city, String state, String code) {
        if (!validState(state)) {
            throw new IllegalArgumentException("State abbreviation must be exactly 2 characters long.");
        }

        this.city = city;
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
        return Objects.equals(state().toLowerCase(), branch.state().toLowerCase()) && Objects.equals(city().toLowerCase(), branch.city().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(state(), city());
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
