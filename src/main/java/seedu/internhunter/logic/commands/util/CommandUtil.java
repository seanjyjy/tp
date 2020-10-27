package seedu.internhunter.logic.commands.util;

import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_NAME;
import static seedu.internhunter.ui.tabs.TabName.APPLICATION;
import static seedu.internhunter.ui.tabs.TabName.COMPANY;
import static seedu.internhunter.ui.tabs.TabName.PROFILE;

import java.util.List;
import java.util.function.Consumer;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Encapsulates common / shared execution processes between commands.
 */
public class CommandUtil {

    public static CompanyItem getCompany(Model model, Index companyIndex) throws CommandException {
        List<CompanyItem> lastShownList = model.getFilteredCompanyList();

        if (companyIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, COMPANY_NAME));
        }

        return model.getCompanyItemFromFilteredList(companyIndex.getZeroBased());
    }

    public static ApplicationItem getApplication(Model model, Index applicationIndex) throws CommandException {
        List<ApplicationItem> lastShownList = model.getFilteredApplicationList();

        if (applicationIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, APPLICATION_NAME));
        }

        return model.getApplicationItemFromFilteredList(applicationIndex.getZeroBased());
    }

    public static ProfileItem getProfileItem(Model model, Index profileItemIndex) throws CommandException {
        List<ProfileItem> lastShownList = model.getFilteredProfileList();

        if (profileItemIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, PROFILE_NAME));
        }

        return model.getProfileItemFromFilteredList(profileItemIndex.getZeroBased());
    }

    /**
     * Gets the feedback message of the operation result for display and indicates whether tabs need to be switched or
     * not.
     *
     * @param model Model of application.
     * @param message Feedback message of the operation result for display.
     * @param tabName The tab that is being switched to.
     * @return Feedback message of the operation result for display.
     */
    public static CommandResult getCommandResult(Model model, String message, TabName tabName) {
        requireAllNonNull(model, message, tabName);

        if (model.getTabName() != tabName) {
            model.setTabName(tabName);
            return new CommandResult(message, false, false, true, true);
        } else {
            return new CommandResult(message);
        }
    }

    /**
     * Gets the feedback message of the operation result for display and indicates whether tabs and display view.
     * need to be switched or not.
     *
     * @param model Model of application.
     * @param message Feedback message of the operation result for display.
     * @param currentTabName The current tab of the application.
     * @param changedTabName The tab that is being switched to.
     * @param newIndex The desired index of the tab that is being switch to.
     * @return Feedback message of the operation result for display.
     */
    public static CommandResult getCommandResult(Model model, String message, TabName currentTabName,
        TabName changedTabName, Index newIndex) {

        requireAllNonNull(model, message, currentTabName, changedTabName, newIndex);
        handleDeleteDisplaySwitchIndex(model, changedTabName, newIndex);
        if (currentTabName != changedTabName) {
            model.setTabName(changedTabName);
            return new CommandResult(message, false, false, true, true);
        } else {
            return new CommandResult(message);
        }
    }

    /**
     * Checks which tab's display needs to be switch.
     *
     * @param model Model of application.
     * @param changedTabName The tab that is being switched to.
     * @param newIndex The desired index of the tab that is being switch to.
     */
    private static void handleDeleteDisplaySwitchIndex(Model model, TabName changedTabName, Index newIndex) {
        Index currentIndex;
        assert (changedTabName.equals(COMPANY) || changedTabName.equals(APPLICATION) || changedTabName.equals(PROFILE));

        switch (changedTabName) {
        case COMPANY:
            currentIndex = model.getCompanyViewIndex();
            handleViewIndex(model::setCompanyViewIndex, currentIndex, newIndex);
            break;
        case APPLICATION:
            currentIndex = model.getApplicationViewIndex();
            handleViewIndex(model::setApplicationViewIndex, currentIndex, newIndex);
            break;
        case PROFILE:
            currentIndex = model.getProfileViewIndex();
            handleViewIndex(model::setProfileViewIndex, currentIndex, newIndex);
            break;
        default:
            assert false;
        }
    }

    /**
     * Checks whether there is a need for a switch in the view index.
     *
     * @param changeViewIndex Handles the switching of the view index in the respective tab.
     * @param currentIndex The current view index in the respective tab.
     * @param newIndex The new view index to be switched to in the respective tab.
     */
    private static void handleViewIndex(Consumer<Index> changeViewIndex, Index currentIndex, Index newIndex) {
        if (currentIndex.getOneBased() >= newIndex.getOneBased()) {
            // currentIndex have to minus 1
            int shiftIndex = currentIndex.getOneBased();
            if (shiftIndex - 1 > 0) {
                changeViewIndex.accept(Index.fromOneBased(shiftIndex - 1));
            }
        }
    }

}
