package seedu.internhunter.model.profile;

import seedu.internhunter.model.wrapper.AlphaNumericWord;

/**
 * Represents a title of a field in a {@code ProfileItem}
 * Guarantees: immutable; is valid as declared in {@link #isValidAlphaNumericWord(String)}
 */
public class Title extends AlphaNumericWord {

    public static final String MESSAGE_CONSTRAINTS =
            "Profile Item Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code Title} for the {@code ProfileItem}
     *
     * @param title A valid title for the profile item.
     */
    public Title(String title) {
        super(title, MESSAGE_CONSTRAINTS);
    }

}
