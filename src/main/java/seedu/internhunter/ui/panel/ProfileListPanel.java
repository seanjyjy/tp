package seedu.internhunter.ui.panel;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.cards.ProfileCard;

/**
 * A UI component that contains all the information of {@code ProfileItem} and displays it
 * as a scrollable list of cards.
 */
public class ProfileListPanel extends ListPanel<ProfileItem> {

    /**
     * Creates a scrollable list panel with information of {@code ProfileItem}.
     *
     * @param profileItems List containing all the profile item in the storage.
     */
    private ProfileListPanel(ObservableList<ProfileItem> profileItems) {
        super(profileItems);
        itemListView.setCellFactory(listView -> new ProfileListViewCell());
    }

    /**
     * Factory method to create the list of cards that displays a list of profile information.
     *
     * @return A profile list panel.
     */
    public static ListPanel<ProfileItem> getProfileListPanel(ObservableList<ProfileItem> profileItems) {
        return new ProfileListPanel(profileItems);
    }

    /**
     * Creates each cell in the list panel with information of {@code ProfileItem}.
     */
    static class ProfileListViewCell extends ListCell<ProfileItem> {

        protected void updateItem(ProfileItem profileItem, boolean empty) {
            super.updateItem(profileItem, empty);

            if (empty || profileItem == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileCard(profileItem, getIndex() + 1).getRoot());
            }
        }
    }
}
