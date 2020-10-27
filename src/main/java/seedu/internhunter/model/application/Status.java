package seedu.internhunter.model.application;

import seedu.internhunter.commons.util.StringUtil;

/**
 * Status class which contains the valid statuses for an internship application.
 */
public enum Status {
    APPLIED,
    INTERVIEW,
    WAITING,
    REJECTED,
    OFFERED,
    ACCEPTED;

    public static final String MESSAGE_CONSTRAINTS =
            "Application status can only be applied, interview, waiting, rejected, offered or accepted.";

    /**
     * Returns true if the given status is valid.
     *
     * @param status Input status.
     * @return True if status is valid, false otherwise.
     */
    public static boolean isValidStatus(String status) {
        try {
            Status.valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Returns a string representation of the {@code Status}.
     *
     * @return String representation of the {@code Status}.
     */
    @Override
    public String toString() {
        return StringUtil.toTitleCase(super.toString());
    }

}
