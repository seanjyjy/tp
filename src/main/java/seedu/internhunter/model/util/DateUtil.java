package seedu.internhunter.model.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * DateUtil class which provides the input and output formats for all dates, as well as methods for date format
 * matching.
 */
public abstract class DateUtil {

    // Output date formats
    public static final String DATE_TIME_LONG_FORMAT = "d MMM yyyy @ h.mm a";

    // Input date formats
    public static final String DATE_INPUT_FORMAT = "d-M-yy";
    public static final String DATE_TIME_INPUT_FORMAT = "d-M-yy HHmm";

    // Default timing if user does not input a time
    public static final String DEFAULT_TIME = "23:59";

    // Error message
    public static final String ERROR_MESSAGE = "Checks for status date validity failed";

    /**
     * Checks if the input given matches the d-M-yy HHmm format and is a dateTime that is not before the current
     * dateTime.
     *
     * @param input User input.
     * @return True if input has the date and time format, false otherwise.
     */
    public static boolean isValidDateTimeFormat(String input) {
        requireNonNull(input);
        try {
            LocalDateTime inputDateTime = LocalDateTime.parse(input, formatterDateTime(DATE_TIME_INPUT_FORMAT));
            LocalDateTime currentDateTime = LocalDateTime.now();
            return !inputDateTime.isBefore(currentDateTime);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input given matches the d-M-yy format and is a date that is not before the current date.
     *
     * @param input User input.
     * @return True if input has the date format, false otherwise.
     */
    public static boolean isValidDateFormat(String input) {
        requireNonNull(input);
        try {
            LocalDate inputDate = LocalDate.parse(input, formatterDateTime(DATE_INPUT_FORMAT));
            LocalDate currentDate = LocalDate.now();
            return !inputDate.isBefore(currentDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a LocalDateTime object with d MMM yyyy @ h.mm a format.
     *
     * @param date Input date from user.
     * @return A LocalDate object.
     */
    private static LocalDateTime formatDate(String date) {
        LocalDate localDate = LocalDate.parse(date, formatterDateTime(DATE_INPUT_FORMAT));
        LocalTime currentTime = LocalTime.parse(DEFAULT_TIME);
        return LocalDateTime.of(localDate, currentTime);
    }

    /**
     * Creates a LocalDateTime object with d MMM yyyy @ h.mm a format.
     *
     * @param dateAndTime Input date and time from user.
     * @return Formatted date and time.
     */
    private static LocalDateTime formatDateTime(String dateAndTime) {
        return LocalDateTime.parse(dateAndTime, formatterDateTime(DATE_TIME_INPUT_FORMAT));
    }

    /**
     * Converts the input status date from user to a LocalDateTime object.
     *
     * @param statusDate Input status date.
     * @return LocalDateTime object.
     */
    public static LocalDateTime convertToDateTime(String statusDate) {
        requireNonNull(statusDate);
        if (isValidDateTimeFormat(statusDate)) {
            return formatDateTime(statusDate);
        } else if (isValidDateFormat(statusDate)) {
            return formatDate(statusDate);
        } else {
            assert false : ERROR_MESSAGE;
            return null;
        }
    }

    /**
     * Obtains a LocalDateTime object of today's date and time of 2359.
     *
     * @return LocalDateTime object of today's date and time of 2359.
     */
    public static LocalDateTime getTodayDate() {
        LocalDate todayDate = LocalDate.now();
        LocalTime defaultTime = LocalTime.parse(DEFAULT_TIME);
        return LocalDateTime.of(todayDate, defaultTime);
    }

    /**
     * Converts the string status date output format to a LocalDateTime object.
     *
     * @param statusDate Input status date.
     * @return LocalDateTime object.
     */
    public static LocalDateTime convertOutputFormat(String statusDate) {
        requireNonNull(statusDate);
        return LocalDateTime.parse(statusDate, formatterDateTime(DATE_TIME_LONG_FORMAT));
    }

    /**
     * Checks if the input given matches the d MMM yyyy @ h.mm a format.
     *
     * @param input User input.
     * @return True if input has the valid output date format, false otherwise.
     */
    public static boolean isValidOutputDate(String input) {
        requireNonNull(input);
        try {
            LocalDateTime.parse(input, formatterDateTime(DATE_TIME_LONG_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a DateTimeFormatter using the input pattern, set to Locale.English.
     *
     * @param pattern String pattern.
     * @return The DateTimeFormatter based the pattern.
     */
    public static DateTimeFormatter formatterDateTime(String pattern) {
        requireNonNull(pattern);
        return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
    }

    /**
     * todo Javadocs
     */
    public static String extractDayAndMonth(String... dateInformation) {
        return dateInformation[0] + " " + dateInformation[1];
    }

}
