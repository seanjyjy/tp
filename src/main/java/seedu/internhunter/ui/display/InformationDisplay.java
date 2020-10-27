package seedu.internhunter.ui.display;

import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_STRING;
import static seedu.internhunter.ui.GuardClauseUi.IS_SAME_STRING;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;

import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.ui.UiPart;
import seedu.internhunter.ui.tabs.TabName;

/**
 * A UI component that displays all information of a {@code Item}.
 */
public class InformationDisplay<T extends Item> extends UiPart<Region> {

    protected static final String BULLET_WITH_ONE_SPACE = "\u2022 ";
    protected static final String BULLET_WITH_TWO_SPACE_FRONT_ONE_BACK = "  \u2022 ";
    protected static final String COMMA_TWO_SPACE = ", {2}";

    //FXML
    private static final String FXML = "InformationDisplay.fxml";

    //FXML properties
    private static final int INFORMATION_HEIGHT_SHRINK = 175;
    private static final int INFORMATION_WIDTH_SHRINK = 100;
    protected T item;
    protected LinkedHashMap<String, Object> mapping;

    /**
     * A function that removes the bracket in the string.
     */
    protected final Function<String, String> editString = string -> string.substring(1, string.length() - 1);

    @FXML
    private ScrollPane scrollableInformationDisplay;
    @FXML
    private HBox nameBar;
    @FXML
    private Label informationTitle;
    @FXML
    private VBox information;
    @FXML
    private VBox informationDisplay;
    @FXML
    private VBox informationDisplayContainer;

    /**
     * Creates a {@code InformationDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param item The item to be displayed.
     */
    public InformationDisplay(Stage primaryStage, T item) {
        super(FXML);
        initializeInformationDisplay(primaryStage);
        this.item = item;
        this.mapping = item.getMapping();
    }

    /**
     * Sets the size of {@code InformationDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     */
    private void initializeInformationDisplay(Stage primaryStage) {
        scrollableInformationDisplay.prefHeightProperty()
            .bind(primaryStage.heightProperty().subtract(INFORMATION_HEIGHT_SHRINK));
        nameBar.maxWidthProperty().bind(informationDisplay.widthProperty().subtract(INFORMATION_WIDTH_SHRINK));
    }

    /**
     * Adds {@code titleDescriptionDisplay} into {@code InformationDisplay}.
     *
     * @param titleDescriptionDisplay A display that contains a title and description.
     */
    void addInformation(TitleDescriptionDisplay titleDescriptionDisplay) {
        information.getChildren().addAll(titleDescriptionDisplay);
    }

    /**
     * Adds {@code title} into {@code InformationDisplay}.
     *
     * @param title A string representing the title for the display.
     */
    protected void setInformationTitle(String title) {
        informationTitle.setText(title);
    }

    /**
     * Sets the information onto the display in {@code MainWindow}.
     *
     * @param editString A function that removes the brackets of the string.
     * @param formatString A function that formats the string depending the the {@code tabName}.
     * @param haveBrackets A predicate that checks if the string has brackets.
     * @param tabName The tab that will be displayed.
     * @param displayKeyList A pre-fixed order of keys to access the display depending on the {@code tabName}.
     */
    protected void setInformation(Function<String, String> editString, Function<String, String> formatString,
        Predicate<String> haveBrackets, TabName tabName, String ... displayKeyList) {
        for (String key : displayKeyList) {
            Object object = mapping.get(key);
            String detail = object.toString();
            if (haveBrackets.test(key)) {
                detail = editString.apply(detail);
            }
            if (IS_SAME_STRING.test(key, INTERNSHIPS_DISPLAY_NAME) && !IS_EMPTY_STRING.test(detail)) {
                detail = formatString.apply(detail);
            }
            if (IS_SAME_STRING.test(key, DESCRIPTORS_DISPLAY_NAME) && !IS_EMPTY_STRING.test(detail)) {
                detail = formatString.apply(detail);
            }
            if (IS_EMPTY_STRING.test(detail)) {
                detail = "-";
            }
            addInformation(TitleDescriptionDisplay.addTitleDescriptionDisplay(key, detail, tabName));
        }
    }

}
