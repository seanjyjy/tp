package seedu.internhunter.testutil.internship;

/**
 * Contains valid fields in the InternshipItem class.
 */
public class InternshipItemFieldsUtil {

    // Valid company names
    public static final String VALID_COMPANY_NAME_SHOPEE = "Shopee";
    public static final String VALID_COMPANY_NAME_LAZADA = "Lazada";
    public static final String VALID_COMPANY_NAME_FACEBOOK = "Facebook";
    public static final String VALID_COMPANY_NAME_GOLDMAN = "Goldman Sachs";

    // Valid job titles
    public static final String VALID_JOB_TITLE_SWE = "Software Engineer";
    public static final String VALID_JOB_TITLE_FE = "Front End Engineer";
    public static final String VALID_JOB_TITLE_DS = "Data Scientist Intern";
    public static final String VALID_JOB_TITLE_BA = "Business Analyst";

    // Invalid job titles
    public static final String INVALID_JOB_TITLE_DASH = "Software - Engineer";
    public static final String INVALID_JOB_TITLE_BLANK = " ";

    // Valid wages
    public static final String VALID_WAGE_2000 = "2000";
    public static final String VALID_WAGE_3000 = "3000";
    public static final String VALID_WAGE_3500 = "3500";
    public static final String VALID_WAGE_4000 = "4000";

    // Invalid wages
    public static final String INVALID_WAGE_ZERO = "0";
    public static final String INVALID_WAGE_NEGATIVE = "-5";
    public static final String INVALID_WAGE_DECIMAL = "3555.4";

    // Valid periods
    public static final String VALID_PERIOD_TWO_MONTHS = "2 months";
    public static final String VALID_PERIOD_THREE_MONTHS = "3 months";
    public static final String VALID_PERIOD_MAY_TO_JULY = "May - July";
    public static final String VALID_PERIOD_SUMMER = "Summer break";

    // Invalid periods
    public static final String INVALID_PERIOD_EMPTY = "";
    public static final String INVALID_PERIOD_SPACES = "   ";

    // Valid requirements
    public static final String VALID_REQUIREMENT_VUE = "Vue";
    public static final String VALID_REQUIREMENT_R = "R";
    public static final String VALID_REQUIREMENT_RN = "React Native";
    public static final String VALID_REQUIREMENT_PYTHON = "Python";
    public static final String VALID_REQUIREMENT_TENSOR = "TensorFlow";

    // Invalid requirements
    public static final String INVALID_REQUIREMENT_EMPTY = "";
    public static final String INVALID_REQUIREMENT_SPACES = "   ";

}
