package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public record Customer(String name, String document) {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();

    static {
        FIELD_LABEL_TO_NAME.put("Name", "name");
        FIELD_LABEL_TO_NAME.put("Document", "document");
    }

    public Customer(String name, String document) {
        this.name = name.toUpperCase();
        this.document = document.toUpperCase();
    }

    public static Map<String, String> getFieldLabelToName() {
        return FIELD_LABEL_TO_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(document(), customer.document());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
