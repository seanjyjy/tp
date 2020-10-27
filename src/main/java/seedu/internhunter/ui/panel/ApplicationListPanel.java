package seedu.internhunter.ui.panel;


import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.ui.cards.ApplicationCard;

/**
 * A UI component that contains all the information of {@code ApplicationItem} and displays it
 * as a scrollable list of cards.
 */
public class ApplicationListPanel extends ListPanel<ApplicationItem> {

    /**
     * Creates a scrollable list panel with information of {@code ApplicationItem}.
     *
     * @param applicationItems List containing all the application item in the storage.
     */
    private ApplicationListPanel(ObservableList<ApplicationItem> applicationItems) {
        super(applicationItems);
        itemListView.setCellFactory(listView -> new ApplicationListViewCell());
    }

    /**
     * Factory method to create the list of cards that displays a list of application information.
     *
     * @return An application list panel.
     */
    public static ListPanel<ApplicationItem> getApplicationListPanel(ObservableList<ApplicationItem> applicationItems) {
        return new ApplicationListPanel(applicationItems);
    }

    /**
     * Creates each cell in the list panel with information of {@code ApplicationItem}.
     */
    static class ApplicationListViewCell extends ListCell<ApplicationItem> {
        @Override
        protected void updateItem(ApplicationItem applicationItem, boolean empty) {
            super.updateItem(applicationItem, empty);

            if (empty || applicationItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ApplicationCard(applicationItem, getIndex() + 1).getRoot());
            }
        }
    }
}
