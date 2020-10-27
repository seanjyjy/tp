package seedu.internhunter.logic.commands.util.application;

import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS;
import static seedu.internhunter.logic.parser.clisyntax.ApplicationCliSyntax.PREFIX_STATUS_DATE;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.INVALID_STATUS_DATE;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;

import seedu.internhunter.commons.core.Messages;

public class ApplicationCommandTestUtil {

    public static final String APPLICATION_ALIAS_DESC = " " + APPLICATION_ALIAS;

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
            String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME);

}
