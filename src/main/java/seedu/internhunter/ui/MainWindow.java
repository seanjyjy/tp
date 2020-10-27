package seedu.internhunter.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.ui.GuardClauseUi.IS_EMPTY_DISPLAY;
import static seedu.address.ui.display.ApplicationDisplay.getApplicationDisplay;
import static seedu.address.ui.display.CompanyDisplay.getCompanyDisplay;
import static seedu.address.ui.display.ProfileDisplay.getProfileDisplay;
import static seedu.address.ui.panel.ApplicationListPanel.getApplicationListPanel;
import static seedu.address.ui.panel.CompanyListPanel.getCompanyListPanel;
import static seedu.address.ui.panel.ProfileListPanel.getProfileListPanel;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.item.Item;
import seedu.address.model.profile.ProfileItem;
import seedu.address.ui.display.InformationDisplay;
import seedu.address.ui.panel.ListPanel;
import seedu.address.ui.popupwindow.HelpWindow;
import seedu.address.ui.popupwindow.InternshipsWindow;
import seedu.address.ui.popupwindow.PopupWindow;
import seedu.address.ui.tabs.TabName;
import seedu.address.ui.tabs.Tabs;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    //FXML
    private static final String FXML = "MainWindow.fxml";

    //FXML properties
    private static final int PERSON_LIST_HEIGHT_SHRINK = 255;
    private static final int RESULT_HEIGHT_SHRINK = 350;

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // data portion
    private ObservableList<CompanyItem> companyItems;
    private ObservableList<ApplicationItem> applicationItems;
    private ObservableList<ProfileItem> profileItems;

    // Independent Ui parts residing in this Ui container
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private InternshipsWindow internshipsWindow;
    private Tabs tabs;
    private ListPanel<? extends Item> listPanel;
    private InformationDisplay<? extends Item> informationDisplay;
    private CommandBox commandBox;

    @FXML
    private VBox listPanelPlaceholder;
    @FXML
    private VBox displayPlaceholder;
    @FXML
    private ScrollPane resultDisplayPlaceholder;
    @FXML
    private VBox commandBoxPlaceholder;
    @FXML
    private VBox tabsPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     *
     * @param primaryStage The main stage of the app.
     * @param logic The logic unit of the app.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        // Configure the UI
        initializeUi(primaryStage, logic);

        // linking to logic
        companyItems = logic.getFilteredCompanyItemList();
        applicationItems = logic.getFilteredApplicationItemList();
        profileItems = logic.getFilteredProfileItemList();
    }

    /**
     * Retrieves the primary stage.
     *
     * @return the {@code primaryStage} of the main window.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets up the GUI properties in the {@code primaryStage} using the stored user settings in {@code logic}.
     *
     * @param primaryStage The main stage of the app.
     * @param logic The logic unit of the app.
     */
    private void initializeUi(Stage primaryStage, Logic logic) {
        setWindowDefaultSize(logic.getGuiSettings());
        bindHeights(primaryStage);
        helpWindow = new HelpWindow();
        internshipsWindow = new InternshipsWindow();
        primaryStage.setOnCloseRequest(event -> {
            GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                    (int) primaryStage.getX(), (int) primaryStage.getY());
            logic.setGuiSettings(guiSettings);

            ExitDialog exitDialog = new ExitDialog(event, helpWindow, internshipsWindow);
            exitDialog.show();
        });
    }

    /**
     * Binds the height of {@code personList} and {@code resultDisplayPlaceHolder} in the {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     */
    private void bindHeights(Stage primaryStage) {
        listPanelPlaceholder.prefWidthProperty().bind(primaryStage.widthProperty().subtract(PERSON_LIST_HEIGHT_SHRINK));
        resultDisplayPlaceholder.prefWidthProperty().bind(primaryStage.widthProperty().subtract(RESULT_HEIGHT_SHRINK));
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     *
     * @param guiSettings The stored GUI settings of the app.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        addResultDisplay();
        addListPanel();
        addCommandBox();
        addInformationDisplay();
        addTabs();
    }

    /**
     * Adds the tab display to the {@code MainWindow}.
     */
    void addTabs() {
        tabs = Tabs.getTabs();
        tabsPlaceholder.getChildren().add(tabs.getRoot());
    }

    /**
     * Adds the result display to the {@code MainWindow}.
     */
    void addResultDisplay() {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.setContent(resultDisplay.getRoot());
    }

    /**
     * Adds the information display to the {@code MainWindow}.
     */
    void addInformationDisplay() {
        Optional<InformationDisplay<? extends Item>> newInformationDisplay = getCompanyDisplay(companyItems,
            0, primaryStage);

        if (!IS_EMPTY_DISPLAY.test(newInformationDisplay)) {
            informationDisplay = newInformationDisplay.get();
            displayPlaceholder.getChildren().add(informationDisplay.getRoot());
        }
    }

    /**
     * Adds the command box display to the {@code MainWindow}.
     */
    void addCommandBox() {
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Adds the list panel display to the {@code MainWindow}.
     */
    void addListPanel() {
        listPanel = getCompanyListPanel(companyItems);
        listPanelPlaceholder.getChildren().add(listPanel.getRoot());
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        handlePopupWindow(helpWindow);
    }

    /**
     * Opens the internships window or focuses on it if it's already opened.
     */
    @FXML
    private void handleMatchingInternships(ObservableList<InternshipItem> internshipList) {
        internshipsWindow.setInternshipList(internshipList);
        handlePopupWindow(internshipsWindow);
    }

    /**
     * Opens the popup window or focuses on it if it's already opened.
     *
     * @param popupWindow Popup window.
     */
    private void handlePopupWindow(PopupWindow popupWindow) {
        if (!popupWindow.isShowing()) {
            popupWindow.show();
        } else {
            popupWindow.focus();
        }
    }

    /**
     * Displays the GUI.
     */
    public void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Switches the tabs of the application to {@code tabName}.
     *
     * @param tabName The tab to be switched to.
     */
    private void switchTab(TabName tabName) {
        requireNonNull(tabName);
        tabs.switchTab(tabName);
    }

    /**
     * Changes the list panel view to the list panel view that resides in {@code tabName}.
     *
     * @param tabName The tab to be switched to.
     */
    private void changeListPanelView(TabName tabName) {
        requireNonNull(tabName);
        listPanelPlaceholder.getChildren().clear();
        switch (tabName) {
        case COMPANY:
            listPanelPlaceholder.getChildren().add(getCompanyListPanel(companyItems).getRoot());
            break;
        case APPLICATION:
            listPanelPlaceholder.getChildren().add(getApplicationListPanel(applicationItems).getRoot());
            break;
        case PROFILE:
            listPanelPlaceholder.getChildren().add(getProfileListPanel(profileItems).getRoot());
            break;
        default:
            assert false;
            break;
        }
    }

    /**
     * Switches the display based on the {@code index} and {@code tabName}.
     */
    public void changeDisplay() {
        TabName tabName = logic.getTabName();
        int index;
        Optional<InformationDisplay<? extends Item>> newInformationDisplay = Optional.empty();
        switch (tabName) {
        case COMPANY:
            index = logic.getCompanyViewIndex().getZeroBased();
            newInformationDisplay = getCompanyDisplay(companyItems, index, primaryStage);
            break;
        case APPLICATION:
            index = logic.getApplicationViewIndex().getZeroBased();
            newInformationDisplay = getApplicationDisplay(applicationItems, index, primaryStage);
            break;
        case PROFILE:
            index = logic.getProfileViewIndex().getZeroBased();
            newInformationDisplay = getProfileDisplay(profileItems, index, primaryStage);
            break;
        default:
            assert false;
            break;
        }
        displayPlaceholder.getChildren().clear();
        if (!IS_EMPTY_DISPLAY.test(newInformationDisplay)) {
            displayPlaceholder.getChildren().add(newInformationDisplay.get().getRoot());
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param commandText The text that the user input.
     * @return A command result which contains the result of executing the text input.
     * @throws CommandException thrown when there is an invalid command inputted.
     * @throws ParseException thrown when there is an invalid text to be parsed.
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowMatchingInternships()) {
                handleMatchingInternships(commandResult.getMatchingInternships());
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isSwitchTab()) {
                switchTab(logic.getTabName());
                changeListPanelView(logic.getTabName());
            }

            if (commandResult.isSwitchDisplay()) {
                changeDisplay();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
