package seedu.internhunter.logic.commands.util.application;

import static seedu.internhunter.commons.core.Messages.MESSAGE_DUPLICATE_ITEM;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS_DATE;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;

/**
 * Contains the description of fields for the application command tests.
 */
public class ApplicationCommandTestUtil {

    // Valid Index
    public static final String INDEX_DESC_FIRST = " " + PREFIX_INDEX + INDEX_FIRST.getOneBased();

    // Valid Statuses
    public static final String STATUS_DESC_ACCEPTED = " " + PREFIX_STATUS + ACCEPTED_KEYWORD;
    public static final String STATUS_DESC_REJECTED = " " + PREFIX_STATUS + REJECTED_KEYWORD;

    // Invalid status
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + INVALID_STATUS;

    // Valid Status dates
    public static final String STATUS_DATE_DESC_JUNE_2021 = " " + PREFIX_STATUS_DATE + STATUS_DATE_JUNE_2021;
    public static final String STATUS_DATE_DESC_JUNE_2022 = " " + PREFIX_STATUS_DATE + STATUS_DATE_JUNE_2022;

    // Invalid status dates
    public static final String INVALID_STATUS_DATE_DESC = " " + PREFIX_STATUS_DATE + INVALID_STATUS_DATE;

    // Invalid application index
    public static final String INVALID_APPLICATION_INDEX_MESSAGE =
            String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME);

    // Duplicate application message
    public static final String DUPLICATE_APPLICATION_MESSAGE =
            String.format(MESSAGE_DUPLICATE_ITEM, APPLICATION_NAME);

}
