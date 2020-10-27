package seedu.internhunter.model.internship.exceptions;

/**
 * Signals that an application has an internship that does not exist in any company's list of internships.
 */
public class InconsistentInternshipException extends RuntimeException {

    private static final String MESSAGE = "Applications' internships do not match with the ones in the companies'"
            + "lists.\nStarting from an empty company and application list.";

    public InconsistentInternshipException() {
        super(MESSAGE);
    }
}
