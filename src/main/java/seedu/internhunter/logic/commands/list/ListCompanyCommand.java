package seedu.internhunter.logic.commands.list;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.core.Messages.MESSAGE_LIST_SUCCESS;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.ui.tabs.TabName;

/**
 * todo javadocs
 */
public class ListCompanyCommand extends ListCommand {

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_LIST_SUCCESS, COMPANY_NAME);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCompanyList(PREDICATE_SHOW_ALL_ITEMS);
        model.setCompanyViewIndex(Index.fromOneBased(1)); // reset to zero for consistency
        return getCommandResult(model, MESSAGE_SUCCESS, TabName.COMPANY);
    }
}
