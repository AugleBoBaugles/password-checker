import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;

public class PasswordChecker {
    private int shortThreshold;
    private int mediumThreshold;
    private Set<String> bannedPasswords;

    /**
     * Constructor that allows setting custom thresholds for short and medium password lengths,
     * and merges a set of custom banned passwords with the default banned passwords.
     *
     * @param shortThreshold  Length threshold for short passwords
     * @param mediumThreshold Length threshold for medium passwords
     * @param customBannedPasswords Set of banned passwords to be added to the default set
     */
    public PasswordChecker(int shortThreshold, int mediumThreshold, Set<String> customBannedPasswords) {

        this.shortThreshold = shortThreshold;
        this.mediumThreshold = mediumThreshold;

        // Initialize with default banned passwords
        this.bannedPasswords = getDefaultBannedPasswords();



        // Merge with custom banned passwords if provided
        // this.bannedPasswords.addAll(customBannedPasswords);

        Set<String> newBannedPasswordsList = new HashSet<>();
        for (String element : customBannedPasswords){
            newBannedPasswordsList.add(element.toLowerCase());
        }

        this.bannedPasswords.addAll(newBannedPasswordsList);
    }

    /**
     * Constructor that uses only default banned passwords.
     *
     * @param shortThreshold  Length threshold for short passwords
     * @param mediumThreshold Length threshold for medium passwords
     */
    public PasswordChecker(int shortThreshold, int mediumThreshold) {
        this(shortThreshold, mediumThreshold, new HashSet<>());
    }

    /**
     * Evaluates the length of the password as "short", "medium", or "long" based on
     * the thresholds set in the constructor.
     *
     * @param password The password to evaluate
     * @return "short", "medium", or "long"
     */
    public String describePasswordLength(String password) {
        int length = password.length();

        if (length < shortThreshold) {
            return "short";
        } else if (length < mediumThreshold) {
            return "medium";
        }
        return "long";
    }

    /**
     * Checks if the password is alphanumeric (contains only letters and digits).
     *
     * @param password The password to check
     * @return true if the password is alphanumeric, false otherwise
     */
    public boolean isAlphanumeric(String password) {
        for (int i = 0; i < password.length() - 1; i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * Checks if the password is in the banned password list. The check is case-insensitive,
     * meaning "password123" and "PASSWORD123" will both be treated as banned if present.
     *
     * @param password The password to check
     * @return true if the password is banned, false otherwise
     */
    public boolean isBannedPassword(String password) {
        // Bug fixed! 
        /* Previously, if the user added banned passwords that were 
        not case sensitive, this method would miss it because it was 
        only checking the string set to lower case*/
        
        boolean inBannedList = false;
        if (bannedPasswords.contains(password) || bannedPasswords.contains(password.toLowerCase())){
            inBannedList = true;
        }
        return inBannedList;
    }

    /**
     * Provides a default set of commonly used weak passwords.
     *
     * @return A set of default banned passwords
     */
    private Set<String> getDefaultBannedPasswords() {

        return new HashSet<>(Arrays.asList(
            "password123", "123456", "qwerty", "letmein", "password", "hello"
        ));
    }
}
