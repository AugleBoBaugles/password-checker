import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;

import org.junit.jupiter.api.Test;

public class PasswordCheckerTest {
    @Test
    void testDescribePasswordLengthShort() {
        // Arrange
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act
        String actual = checker.describePasswordLength("a");
        // Assert
        assertEquals("short", actual);
    }

 
    @Test 
    void testDescribePasswordLengthLong() {
        // Arrange
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act 
        String actual = checker.describePasswordLength("alongpassword");
        // Assert
        assertEquals("long", actual);
    }
    @Test
    void testDescribePasswordLengthMedium() {
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act
        String actual = checker.describePasswordLength("abcd");
        // Assert
        assertEquals("medium", actual);
    }

    @Test
    void testIsAlphanumeric() {

        // Check:
        // !!!!! -r DONE
        // 12345 -a
        // "    " -r DONE
        // turtles -a

    }

    @Test 
    void testIsAlphanumericSymbols() {
        // Arrange
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act
        boolean alphanumericOrNot = checker.isAlphanumeric("!!!!!");
        // Assert
        assertEquals(false, alphanumericOrNot);
    }

    @Test
    void testIsAlphanumericSpaces() {
        // Arrange 
        PasswordChecker checker = new PasswordChecker(2,7);
        // Act
        boolean alphanumericOrNot = checker.isAlphanumeric("     ");
        // Assert
        assertEquals(false, alphanumericOrNot);
    }

    @Test
    void testIsBannedPassword() {

        // check
        // 123456 -r DONE
        // QWERTY -a
        // MANGO - r DONE
        // MANGO (if constructor adds "MANGO" to forbidden passwords via constructor) -r


    // NOTE: Constructor lets you ADD to the set of banned passwords - I'm going to try adding MANGO to list of forbidden passwords
    }

    @Test
    void testIsBannedPasswordFromList() {
        // Arrange
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act
        boolean isBanned = checker.isBannedPassword("123456");
        // Assert
        assertEquals(true, isBanned);
    }

    @Test
    void testIsBannedNotOnList() {
        // Arrange
        PasswordChecker checker = new PasswordChecker(2, 7);
        // Act
        boolean isBanned = checker.isBannedPassword("MANGO");
        // Assert
        assertEquals(false, isBanned);
    }


    @Test
    void testIsBannedPasswordAddedInConstructor() {
        // Arrange
        Set<String> moreBannedPasswords = new HashSet<>();
        moreBannedPasswords.add("MANGO");
        PasswordChecker checker = new PasswordChecker(2, 7, moreBannedPasswords);
        // Act
        boolean isBanned = checker.isBannedPassword("MANGO");
        // Assert
        assertEquals(true, isBanned);   

    }
}
