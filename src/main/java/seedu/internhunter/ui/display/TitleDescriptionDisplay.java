package seedu.internhunter.ui.display;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.ui.textstyle.TitleDescription.createTitleDescription;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import seedu.internhunter.ui.MainWindow;
import seedu.internhunter.ui.tabs.TabName;
import seedu.internhunter.ui.textstyle.TitleDescription;

/**
 * A UI component that displays the {@code title} and {@code description} of an {@code Item}.
 */
public class TitleDescriptionDisplay extends HBox {

    //FXML
    private static final String FXML = "/view/TitleDescriptionDisplay.fxml";

    //FXML properties
    private static final String PROFILE_INFORMATION_WIDTH = "-fx-min-width: 95";
    private static final String COMPANY_AND_APPLICATION_INFORMATION_WIDTH = "-fx-min-width: 105";

    @FXML
    private TextFlow title;
    @FXML
    private TextFlow description;
    @FXML
    private VBox titleInformation;
    @FXML
    private VBox descriptionInformation;

    /**
     * Creates a {@code TitleDescriptionDisplay} with {@code displayTitle}, {@code displayDescription} for
     * {@code tab} display.
     *
     * @param displayTitle A string representing the title for the display.
     * @param displayDescription A string representing the description for the display.
     * @param tab The tab that this displayed should be shown at.
     */
    private TitleDescriptionDisplay(String displayTitle, String displayDescription, TabName tab) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addDisplayInformation(displayTitle, displayDescription);
        setWidth(tab);
    }

    /**
     * An accessor to add a {@code TitleDescriptionDisplay} with {@code displayTitle}, {@code displayDescription} for
     * {@code tab} display.
     *
     * @param displayTitle A string representing the title for the display.
     * @param displayDescription A string representing the description for the display.
     * @param tab The tab that this displayed should be shown at.
     * @return A TitleDescriptionDisplay.
     */
    public static TitleDescriptionDisplay addTitleDescriptionDisplay(String displayTitle, String displayDescription,
        TabName tab) {

        return new TitleDescriptionDisplay(displayTitle, displayDescription, tab);
    }

    /**
     * Adds a title and description into the {@code TitleDescriptionDisplay}.
     *
     * @param displayTitle A string representing the title for the display.
     * @param displayDescription A string representing the description for the display.
     */
    private void addDisplayInformation(String displayTitle, String displayDescription) {
        requireNonNull(displayDescription, displayDescription);
        TitleDescription titleDescription = createTitleDescription(displayTitle, displayDescription);
        title.getChildren().addAll(titleDescription.getTitle());
        description.getChildren().addAll(titleDescription.getDescription());
    }

    /**
     * Adjusts the width of the title based on which {@code tab} display.
     *
     * @param tab The tab that the display is currently at.
     */
    private void setWidth(TabName tab) {
        assert (tab.equals(TabName.PROFILE) || tab.equals(TabName.APPLICATION) || tab.equals(TabName.COMPANY));
        switch (tab) {
        case COMPANY:
            //Fallthrough
        case APPLICATION:
            titleInformation.setStyle(COMPANY_AND_APPLICATION_INFORMATION_WIDTH);
            break;
        case PROFILE:
            titleInformation.setStyle(PROFILE_INFORMATION_WIDTH);
            break;
        default:
            assert false;
        }
    }
}
