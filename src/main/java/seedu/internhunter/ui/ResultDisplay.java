package seedu.internhunter.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    // FXML
    private static final String FXML = "ResultDisplay.fxml";

    // FXML properties
    private static final String TEXT_COLOR = "#5A4DEB";
    private static final String TEXT_FONT = "Nunito";

    @FXML
    private TextFlow resultDisplay;

    /**
     * Creates a display containing the results of the command.
     */
    public ResultDisplay() {
        super(FXML);
    }

    /**
     * Displays the {@code feedbackToUser} in {@code resultDisplay}.
     *
     * @param feedbackToUser The feedback message to the user.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        Text text = new Text(feedbackToUser);
        clearHistory();
        setNewText(text);
    }

    /**
     * Sets the color and font of the {@code text}.
     *
     * @param text The text to be styled.
     */
    private void setTextStyle(Text text) {
        requireNonNull(text);
        text.setFill(Color.web(TEXT_COLOR));
        text.setFont(Font.font(TEXT_FONT));
    }

    /**
     * Clears any old text in {@code resultDisplay}.
     */
    private void clearHistory() {
        resultDisplay.getChildren().clear();
    }

    /**
     * Displays the {@code text} in {@code resultDisplay}.
     *
     * @param text The new text to be displayed.
     */
    private void setNewText(Text text) {
        requireNonNull(text);
        setTextStyle(text);
        resultDisplay.getChildren().add(text);
    }
}
