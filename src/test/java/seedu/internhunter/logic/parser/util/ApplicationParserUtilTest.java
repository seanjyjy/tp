package seedu.internhunter.logic.parser.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS_DATE_TIME;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_WITH_TIME;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.util.DateUtil;

public class ApplicationParserUtilTest {

    private static final Status EXPECTED_STATUS = Status.valueOf(APPLIED_KEYWORD.toUpperCase());
    private static final StatusDate EXPECTED_STATUS_DATE =
            new StatusDate(DateUtil.convertToDateTime(STATUS_DATE_WITH_TIME));

    @Test
    public void parseStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ApplicationParserUtil.parseStatus(null));
    }

    @Test
    public void parseStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class,
                Status.MESSAGE_CONSTRAINTS, () -> ApplicationParserUtil.parseStatus(INVALID_STATUS));
    }

    @Test
    public void parseStatus_validValueWithoutWhitespace_returnsStatus() throws Exception {
        assertEquals(EXPECTED_STATUS, ApplicationParserUtil.parseStatus(APPLIED_KEYWORD));
    }

    @Test
    public void parseStatus_validValueWithWhitespace_returnsStatus() throws Exception {
        String statusWithWhitespace = " " + APPLIED_KEYWORD + " ";
        assertEquals(EXPECTED_STATUS, ApplicationParserUtil.parseStatus(statusWithWhitespace));
    }

    @Test
    public void parseStatusDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ApplicationParserUtil.parseStatusDate(null));
    }

    @Test
    public void parseStatusDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class,
                StatusDate.MESSAGE_CONSTRAINTS, () -> ApplicationParserUtil.parseStatusDate(INVALID_STATUS_DATE_TIME));
    }

    @Test
    public void parseStatusDate_validValueWithoutWhitespace_returnsStatusDate() throws Exception {
        assertEquals(EXPECTED_STATUS_DATE, ApplicationParserUtil.parseStatusDate(STATUS_DATE_WITH_TIME));
    }

    @Test
    public void parseStatusDate_validValueWithWhitespace_returnsStatusDate() throws Exception {
        String statusDateWithWhitespace = " " + STATUS_DATE_WITH_TIME + " ";
        assertEquals(EXPECTED_STATUS_DATE, ApplicationParserUtil.parseStatusDate(statusDateWithWhitespace));
    }

}
