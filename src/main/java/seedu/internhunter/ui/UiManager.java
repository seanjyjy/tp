package seedu.internhunter.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.internhunter.MainApp;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.util.StringUtil;
import seedu.internhunter.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/InternHunterLogo.png";

    private Logic logic;
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     *
     * @param logic The logic unit of the app.
     */
    public UiManager(Logic logic) {
        super();
        this.logic = logic;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        //Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            mainWindow = new MainWindow(primaryStage, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();

        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    /**
     * Retrieves the image from the image path.
     *
     * @param imagePath A String representing the path of the image.
     * @return an Image that is from the {@code imagePath}.
     */
    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters at the given {@code primaryStage}.
     * This method only returns after the user has closed the alert dialog.
     *
     * @param type The alert type.
     * @param title The title for the alert.
     * @param headerText The header for the text.
     * @param contentText The content for the text.
     */
    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters.
     * This method only returns after the user has closed the alert dialog.
     *
     * @param owner The main stage.
     * @param type The alert type.
     * @param title The title for the alert.
     * @param headerText The header for the text.
     * @param contentText The content for the text.
     */
    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
        String contentText) {

        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/MainWindow.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setId(ALERT_DIALOG_PANE_FIELD_ID);
        alert.showAndWait();
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     *
     * @param title The error message title.
     * @param e The information of the error.
     */
    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

}
