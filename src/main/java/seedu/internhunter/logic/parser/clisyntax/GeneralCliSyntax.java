package seedu.internhunter.logic.parser.clisyntax;

import seedu.internhunter.logic.parser.Prefix;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class GeneralCliSyntax {

    /* Todo: delete Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Common Prefix definitions */
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
}
