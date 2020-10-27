package seedu.internhunter.ui.panel;


import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.ui.cards.CompanyCard;

/**
 * A UI component that contains all the information of {@code CompanyItem} and displays it
 * as a scrollable list of cards.
 */
public class CompanyListPanel extends ListPanel<CompanyItem> {

    /**
     * Creates a scrollable list panel with information of {@code CompanyItem}.
     *
     * @param companyItems List containing all the company item in the storage.
     */
    private CompanyListPanel(ObservableList<CompanyItem> companyItems) {
        super(companyItems);
        itemListView.setCellFactory(listView -> new CompanyListViewCell());
    }

    /**
     * Factory method to create the list of cards that displays a list of company information.
     *
     * @return A company list panel.
     */
    public static ListPanel<CompanyItem> getCompanyListPanel(ObservableList<CompanyItem> companyItems) {
        return new CompanyListPanel(companyItems);
    }

    /**
     * Creates each cell in the list panel with information of {@code CompanyItem}.
     */
    static class CompanyListViewCell extends ListCell<CompanyItem> {
        @Override
        protected void updateItem(CompanyItem companyItem, boolean empty) {
            super.updateItem(companyItem, empty);

            if (empty || companyItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompanyCard(companyItem, getIndex() + 1).getRoot());
            }
        }
    }
}
