package seedu.internhunter.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.ui.cards.InternshipCard;

/**
 * A UI component that contains all the information of {@code InternshipItem} and displays it
 * as a scrollable list of cards.
 */
public class InternshipListPanel extends ListPanel<InternshipItem> {

    /**
     * Creates a scrollable list panel with information of {@code InternshipItem}.
     *
     * @param itemList List containing all the InternshipItem.
     */
    public InternshipListPanel(ObservableList<InternshipItem> itemList) {
        super(itemList);
        itemListView.setCellFactory(listView -> new InternshipListViewCell());
    }

    /**
     * Creates each cell in the list panel with information of {@code InternshipItem}.
     */
    static class InternshipListViewCell extends ListCell<InternshipItem> {
        @Override
        protected void updateItem(InternshipItem internshipItem, boolean empty) {
            super.updateItem(internshipItem, empty);

            if (empty || internshipItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new InternshipCard(internshipItem, getIndex() + 1).getRoot());
            }
        }
    }
}
