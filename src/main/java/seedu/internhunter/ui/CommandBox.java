package seedu.internhunter.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    // Style class
    public static final String ERROR_STYLE_CLASS = "error";

    // FXML
    private static final String FXML = "CommandBox.fxml";

    // Image Link
    private static final String ARROW_IMAGE_LINK = "/images/sendarrow.png";

    @FXML
    private TextField commandTextField;
    @FXML
    private ImageView buttonImage;
    @FXML
    private Button sendButton;

    private final CommandExecutor commandExecutor;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     *
     * @param commandExecutor A function that executes the command.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        setCommandTextFieldProperties();
        setSendButtonProperties();
    }

    /**
     * Loads the arrow image and set the image as the button icon.
     */
    private void setSendButtonIcon() {
        Image arrow = new Image(this.getClass().getResourceAsStream(ARROW_IMAGE_LINK));
        buttonImage.setImage(arrow);
    }

    /**
     * Sets the button style.
     */
    private void setSendButtonProperties() {
        HBox.setHgrow(sendButton, Priority.ALWAYS);
        setSendButtonIcon();
    }

    /**
     * Sets the command text field style.
     */
    private void setCommandTextFieldProperties() {
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        HBox.setHgrow(commandTextField, Priority.ALWAYS);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @param commandText The text that the user input.
         * @return A command result which contains the result of executing the text input.
         * @throws CommandException thrown when there is an invalid command inputted.
         * @throws ParseException thrown when there is an invalid text to be parsed.
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
