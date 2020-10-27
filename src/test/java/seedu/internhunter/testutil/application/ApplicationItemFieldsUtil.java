package seedu.internhunter.testutil.application;

/**
 * Contains fields in the ApplicationItem class.
 */
public class ApplicationItemFieldsUtil {

    // Valid status formats
    public static final String VALID_STATUS_APPLIED_MIX_CASE = "applIed";
    public static final String VALID_STATUS_REJECTED_MIX_CASE = "reJected";

    // Invalid status formats
    public static final String STATUS_BLANK = "";
    public static final String INVALID_STATUS = "rest";

    // Valid Date-time formats
    public static final String STATUS_DATE_WITH_TIME = "12-06-21 2230";

    // Invalid Date-time formats
    public static final String INVALID_STATUS_DATE_TIME = "12-06-21 22:30";

    // Date formats
    public static final String STATUS_DATE_JUNE_2021 = "12-06-21";
    public static final String STATUS_DATE_MAY_2021 = "12-05-21";
    public static final String STATUS_DATE_JUNE_2022 = "12-06-22";
    public static final String EXPECTED_DATE_JUNE_2021 = "12 Jun 2021 @ 11.59 PM";

    // Invalid date formats
    public static final String BLANK_STATUS_DATE = "";
    public static final String INVALID_STATUS_DATE = "12-12-2021";

}
