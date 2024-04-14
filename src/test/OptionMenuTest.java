package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.OptionMenu;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class OptionMenuTest {

    private final InputStream systemIn = System.in;
    private final Scanner scanner = new Scanner(System.in);
    private ByteArrayInputStream testIn;

    @BeforeEach
    public void setUpInput() {
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    @Test
    public void testRunMenu_ValidOption() {
        List<String> actionsList = new ArrayList<>();
        actionsList.add("Option 1");
        actionsList.add("Option 2");

        OptionMenu optionMenu = new OptionMenu(scanner);

        String input = "1\n";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        assertDoesNotThrow(() -> optionMenu.runMenu());
    }

    @Test
    public void testRunMenu_InvalidOption() {
        List<String> actionsList = new ArrayList<>();
        actionsList.add("Option 1");
        actionsList.add("Option 2");

        OptionMenu optionMenu = new OptionMenu(scanner);

        String input = "3\n";
        testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);

        assertDoesNotThrow(() -> optionMenu.runMenu());
    }

}
