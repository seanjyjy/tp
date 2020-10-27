package seedu.internhunter.logic.commands.util.internship;

import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_JOB_TITLE;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_PERIOD;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_REQUIREMENT;
import static seedu.internhunter.logic.parser.clisyntax.InternshipCliSyntax.PREFIX_WAGE;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_ALIAS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_JOB_TITLE_DASH;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_PERIOD_EMPTY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_REQUIREMENT_EMPTY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_ZERO;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_THREE_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_TWO_MONTHS;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_R;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_VUE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;

public class InternshipCommandTestUtil {

    public static final String INTERNSHIP_ALIAS_DESC = " " + INTERNSHIP_ALIAS;

    // Valid job titles
    public static final String JOB_TITLE_DESC_SWE = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_SWE;
    public static final String JOB_TITLE_DESC_FE = " " + PREFIX_JOB_TITLE + VALID_JOB_TITLE_FE;

    // Invalid job title
    public static final String INVALID_JOB_TITLE_DESC = " " + PREFIX_JOB_TITLE + INVALID_JOB_TITLE_DASH;

    // Valid wages
    public static final String WAGE_DESC_SWE = " " + PREFIX_WAGE + VALID_WAGE_2000;
    public static final String WAGE_DESC_FE = " " + PREFIX_WAGE + VALID_WAGE_3000;

    // Invalid wage
    public static final String INVALID_WAGE_DESC = " " + PREFIX_WAGE + INVALID_WAGE_ZERO;

    // Valid periods
    public static final String PERIOD_DESC_TWO_MONTHS = " " + PREFIX_PERIOD + VALID_PERIOD_TWO_MONTHS;
    public static final String PERIOD_DESC_THREE_MONTHS = " " + PREFIX_PERIOD + VALID_PERIOD_THREE_MONTHS;

    // Invalid period
    public static final String INVALID_PERIOD_DESC = " " + PREFIX_PERIOD + INVALID_PERIOD_EMPTY;

    // Valid requirements
    public static final String REQUIREMENT_DESC_VUE = " " + PREFIX_REQUIREMENT + VALID_REQUIREMENT_VUE;
    public static final String REQUIREMENT_DESC_R = " " + PREFIX_REQUIREMENT + VALID_REQUIREMENT_R;

    // Invalid requirement
    public static final String INVALID_REQUIREMENT_DESC = " " + PREFIX_REQUIREMENT + INVALID_REQUIREMENT_EMPTY;

}
