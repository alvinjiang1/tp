package seedu.rc4hdb.model.resident.fields;

import static seedu.rc4hdb.commons.util.AppUtil.checkArgument;

import seedu.rc4hdb.commons.util.StringUtil;

/**
 * Represents a Resident's name in RC4HDB.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name extends ResidentField {

    public static final String IDENTIFIER = "Name";

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name string.
     */
    public Name(String name) {
        super(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && value.equals(((Name) other).value)); // state check
    }

    /**
     * Returns true if given {@code Name} string is contained in name
     * @param name the name substring that is checked to be contained in this name
     * @return true if the name is contained in the fullName
     */
    public boolean contains(String name) {
        return StringUtil.containsWordIgnoreCase(this.value.replaceAll("\\s", ""),
                name.replaceAll("\\s", ""));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
