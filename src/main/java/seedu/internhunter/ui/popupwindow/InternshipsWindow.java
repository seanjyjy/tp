package seedu.internhunter.ui.popupwindow;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.ui.panel.InternshipListPanel;
import seedu.internhunter.ui.panel.ListPanel;

/**
 * Controller for the InternshipsWindow page.
 */
public class InternshipsWindow extends PopupWindow {

    private static final Logger logger = LogsCenter.getLogger(InternshipsWindow.class);
    private static final String FXML = "InternshipsWindow.fxml";

    @FXML
    private StackPane matchingInternships;

    /**
     * Creates a new InternshipsWindow.
     */
    public InternshipsWindow() {
        super(FXML, new Stage());
    }

    /**
     * Shows the matching internships window.
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
        logger.fine("Showing list of matching internships.");
        super.show();
    }

    /**
     * Sets the internship list for display.
     *
     * @param internshipList List of internships.
     */
    public void setInternshipList(ObservableList<InternshipItem> internshipList) {
        matchingInternships.getChildren().clear();
        ListPanel<? extends Item> internshipListPanel = new InternshipListPanel(internshipList);
        matchingInternships.getChildren().add(internshipListPanel.getRoot());
    }

}
