package seedu.internhunter.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.DateUtil.DATE_INPUT_FORMAT;
import static seedu.internhunter.model.util.DateUtil.DATE_TIME_INPUT_FORMAT;
import static seedu.internhunter.model.util.DateUtil.DEFAULT_TIME;
import static seedu.internhunter.model.util.DateUtil.ERROR_MESSAGE;
import static seedu.internhunter.model.util.DateUtil.convertOutputFormat;
import static seedu.internhunter.model.util.DateUtil.convertToDateTime;
import static seedu.internhunter.model.util.DateUtil.extractDayAndMonth;
import static seedu.internhunter.model.util.DateUtil.formatterDateTime;
import static seedu.internhunter.model.util.DateUtil.isValidDateFormat;
import static seedu.internhunter.model.util.DateUtil.isValidDateTimeFormat;
import static seedu.internhunter.model.util.DateUtil.isValidOutputDate;
import static seedu.internhunter.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateUtilTest {

    // DATE_TIME FORMATS
    private static final String VALID_DATE_TIME_ONE = "18-9-21 2240";
    private static final String VALID_DATE_TIME_TWO = "9-09-21 0000";
    private static final String INVALID_DATE_TIME_ONE = "18-9-21   2240";
    private static final String INVALID_DATE_TIME_TWO = "9-09-21 00 00";

    // DATE FORMATS
    private static final String VALID_DATE_ONE = "18-9-21";
    private static final String VALID_DATE_TWO = "9-09-21";
    private static final String INVALID_DATE_ONE = "20-09-20 ";
    private static final String INVALID_DATE_TWO = " 9-09-21";

    // OUTPUT FORMATS
    private static final String VALID_OUTPUT_FORMAT = "18 Sep 2021 @ 10.40 PM";
    private static final String INVALID_OUTPUT_FORMAT = "18 Sep 2021 @ 10.40 pm";
    private static final String VALID_SHORT_FORMAT = "18 Sep";

    @Test
    public void isValidDateTimeFormat_validFormats_success() {
        assertTrue(isValidDateTimeFormat(VALID_DATE_TIME_ONE));
        assertTrue(isValidDateTimeFormat(VALID_DATE_TIME_TWO));
    }

    @Test
    public void isValidDateTimeFormat_invalidFormats_success() {
        assertFalse(isValidDateTimeFormat(INVALID_DATE_TIME_ONE));
        assertFalse(isValidDateTimeFormat(INVALID_DATE_TIME_TWO));
        assertFalse(isValidDateTimeFormat(VALID_DATE_ONE));
        assertFalse(isValidDateTimeFormat(VALID_DATE_TWO));
    }

    @Test
    public void isValidDateFormat_validFormats_success() {
        assertTrue(isValidDateFormat(VALID_DATE_ONE));
        assertTrue(isValidDateFormat(VALID_DATE_TWO));
    }

    @Test
    public void isValidDateFormat_invalidFormats_success() {
        assertFalse(isValidDateFormat(INVALID_DATE_ONE));
        assertFalse(isValidDateFormat(INVALID_DATE_TWO));
        assertFalse(isValidDateFormat(VALID_DATE_TIME_ONE));
        assertFalse(isValidDateFormat(VALID_DATE_TIME_TWO));
    }


    @Test
    public void convertToDateTime_validFormats_success() {
        // Converting LocalDateTime
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse(VALID_DATE_TIME_ONE,
                DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT));
        assertEquals(expectedLocalDateTime, convertToDateTime(VALID_DATE_TIME_ONE));

        // Converting LocalDate
        LocalDate localDate = LocalDate.parse(VALID_DATE_ONE, DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT));
        LocalDateTime expectedLocalDateTimeDefaultTime = LocalDateTime.of(localDate, LocalTime.parse(DEFAULT_TIME));
        assertEquals(expectedLocalDateTimeDefaultTime, convertToDateTime(VALID_DATE_ONE));
    }

    @Test
    public void convertToDateTime_invalidFormats_throwsAssertionError() {
        assertThrows(AssertionError.class, ERROR_MESSAGE, () -> convertToDateTime(INVALID_DATE_TIME_ONE));
        assertThrows(AssertionError.class, ERROR_MESSAGE, () -> convertToDateTime(INVALID_DATE_TIME_TWO));
        assertThrows(AssertionError.class, ERROR_MESSAGE, () -> convertToDateTime(INVALID_DATE_ONE));
        assertThrows(AssertionError.class, ERROR_MESSAGE, () -> convertToDateTime(INVALID_DATE_TWO));
    }

    @Test
    public void convertOutputFormat_validFormats_success() {
        assertEquals(LocalDateTime.parse(VALID_DATE_TIME_ONE, DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT)),
                convertOutputFormat(VALID_OUTPUT_FORMAT));
    }

    @Test
    public void isValidOutputDate_validFormat_success() {
        assertTrue(isValidOutputDate(VALID_OUTPUT_FORMAT));
    }

    @Test
    public void isValidOutputDate_invalidFormat_success() {
        assertFalse(isValidOutputDate(INVALID_OUTPUT_FORMAT));
    }

    @Test
    public void formatterDateTime_validInput_success() {
        DateTimeFormatter expected = DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT, Locale.ENGLISH);
        // Comparison using toString since different DateTimeFormatter objects are not equal
        assertEquals(expected.toString(), formatterDateTime(DATE_TIME_INPUT_FORMAT).toString());
    }

    @Test
    public void extractDayAndMonth_validInput_success() {
        String[] dateInfoArray = VALID_OUTPUT_FORMAT.split(" ");
        assertEquals(VALID_SHORT_FORMAT, extractDayAndMonth(dateInfoArray));
    }

}
