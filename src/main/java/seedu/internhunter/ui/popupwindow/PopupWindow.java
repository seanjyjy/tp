package seedu.internhunter.ui.popupwindow;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.internhunter.ui.UiPart;

/**
 * Controller for PopupWindow.
 */
public abstract class PopupWindow extends UiPart<Stage> {

    /**
     * Creates a UiPart with the specified FXML file within {@link #FXML_FILE_FOLDER} and root object.
     *
     * @param fxmlFileName String representation of the fxml file name.
     * @param root The root object of the scene graph of this UiPart.
     */
    public PopupWindow(String fxmlFileName, Stage root) {
        super(fxmlFileName, root);
        addCloseWindowOnEsc(root);
    }

    /**
     * Shows the secondary window.
     *
     * @throws IllegalStateException <ul>
     * <li>
     * if this method is called on a thread other than the JavaFX Application Thread.
     * </li>
     * <li>
     * if this method is called during animation or layout processing.
     * </li>
     * <li>
     * if this method is called on the primary stage.
     * </li>
     * <li>
     * if {@code dialogStage} is already showing.
     * </li>
     * </ul>
     */
    public void show() {
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     *
     * @return A boolean value representing whether a help window is being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Closes the help window when the escape button is pressed and released.
     *
     * @param stage Current stage.
     */
    private static void addCloseWindowOnEsc(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
    }

}
