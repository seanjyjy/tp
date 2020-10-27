package seedu.internhunter.logic.commands.util.profile;

import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_CATEGORY;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_DESCRIPTOR;
import static seedu.internhunter.logic.parser.clisyntax.ProfileCliSyntax.PREFIX_TITLE;

public class ProfileCommandTestUtil {
    public static final String VALID_TITLE_HTML = "Learn HTML";
    public static final String VALID_TITLE_INTERNSHIP = "Data Analyst Internship at ByteDance";
    public static final String VALID_TITLE_COMPETITION = "Winner of Special Recognition in Orbital";
    public static final String VALID_CATEGORY_SKILL = "skill";
    public static final String VALID_CATEGORY_EXPERIENCE = "experience";
    public static final String VALID_CATEGORY_ACHIEVEMENT = "achievement";
    public static final String VALID_DESCRIPTOR_IMPLEMENT = "Implement automated tool to boost productivity by 15%";
    public static final String VALID_DESCRIPTOR_LEARN = "Learn how to run a cron job";

    // Valid parameters
    public static final String TITLE_DESC_HTML = " " + PREFIX_TITLE + VALID_TITLE_HTML;
    public static final String TITLE_DESC_INTERNSHIP = " " + PREFIX_TITLE + VALID_TITLE_INTERNSHIP;
    public static final String TITLE_DESC_COMPETITION = " " + PREFIX_TITLE + VALID_TITLE_COMPETITION;

    public static final String CATEGORY_DESC_SKILL = " " + PREFIX_CATEGORY + VALID_CATEGORY_SKILL;
    public static final String CATEGORY_DESC_ACHIEVEMENT = " " + PREFIX_CATEGORY + VALID_CATEGORY_ACHIEVEMENT;
    public static final String CATEGORY_DESC_EXPERIENCE = " " + PREFIX_CATEGORY + VALID_CATEGORY_EXPERIENCE;

    public static final String DESCRIPTOR_DESC_IMPLEMENT = " " + PREFIX_DESCRIPTOR + VALID_DESCRIPTOR_IMPLEMENT;
    public static final String DESCRIPTOR_DESC_LEARN = " " + PREFIX_DESCRIPTOR + VALID_DESCRIPTOR_LEARN;

    // Invalid parameters
    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Internship at cre@tive"; // '@' is not a
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "awards";
    public static final String INVALID_DESCRIPTOR_DESC = " " + PREFIX_DESCRIPTOR + "hubby*"; //
}
