package seedu.internhunter.commons.util;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_TYPE_ABRIDGED;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class TabUtil {

    /**
     * Takes in a {@code tab} and converts the {@code tab} into it's {@code TabName}.
     */
    public static TabName getSwitchTabName(String tab) throws ParseException {
        TabName tabName;
        switch (tab) {
        case COMPANY_ALIAS:
            tabName = TabName.COMPANY;
            break;
        case APPLICATION_ALIAS:
            tabName = TabName.APPLICATION;
            break;
        case PROFILE_ALIAS:
            tabName = TabName.PROFILE;
            break;
        default:
            throw new ParseException(MESSAGE_INVALID_ITEM_TYPE_ABRIDGED);
        }
        return tabName;
    }

}
