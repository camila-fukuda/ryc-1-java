package test;

import entities.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerTest {

    @Test
    public void testConstructor() {
        Customer customer = new Customer("John Doe", "123456789");
        assertEquals("John Doe", customer.name());
        assertEquals("123456789", customer.document());
    }

    @Test
    public void testEquals() {
        Customer customer1 = new Customer("John Doe", "123456789");
        Customer customer2 = new Customer("Jane Smith", "987654321");
        Customer customer3 = new Customer("John Doe", "123456789");

        assertEquals(customer1, customer3);
        assertNotEquals(customer1, customer2);
    }

    @Test
    public void testToString() {
        Customer customer = new Customer("John Doe", "123456789");
        assertEquals("Customer{name='John Doe', document='123456789'}", customer.toString());
    }

}
