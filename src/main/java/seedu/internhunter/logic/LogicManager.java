package seedu.internhunter.logic;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.Command;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.MainParser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.Storage;
import seedu.internhunter.ui.tabs.TabName;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MainParser mainParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        mainParser = new MainParser();

    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command = mainParser.parseCommand(commandText);
        CommandResult commandResult = command.execute(model);

        try {
            storage.saveCompanyItemList(model.getUnfilteredCompanyList());
            storage.saveApplicationItemList(model.getUnfilteredApplicationList());
            storage.saveProfileItemList(model.getUnfilteredProfileList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ObservableList<ApplicationItem> getFilteredApplicationItemList() {
        return model.getFilteredApplicationList();
    }

    @Override
    public ObservableList<CompanyItem> getFilteredCompanyItemList() {
        return model.getFilteredCompanyList();
    }

    @Override
    public ObservableList<ProfileItem> getFilteredProfileItemList() {
        return model.getFilteredProfileList();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public TabName getTabName() {
        return model.getTabName();
    }

    @Override
    public void setTabName(TabName tabName) {
        model.setTabName(tabName);
    }

    @Override
    public Index getCompanyViewIndex() {
        return model.getCompanyViewIndex();
    }

    @Override
    public Index getApplicationViewIndex() {
        return model.getApplicationViewIndex();
    }

    @Override
    public Index getProfileViewIndex() {
        return model.getProfileViewIndex();
    }

    @Override
    public void setCompanyViewIndex(Index index) {
        model.setCompanyViewIndex(index);
    }

    @Override
    public void setApplicationViewIndex(Index index) {
        model.setApplicationViewIndex(index);
    }

    @Override
    public void setProfileViewIndex(Index index) {
        model.setProfileViewIndex(index);
    }
}
