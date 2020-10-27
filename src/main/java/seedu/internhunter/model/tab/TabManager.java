package seedu.internhunter.model.tab;

import static java.util.Objects.requireNonNull;

import seedu.internhunter.ui.tabs.TabName;

/**
 * todo javadoc
 */
public class TabManager implements Tab {

    private TabName tabName;

    /**
     * Initializes TabManger which controls the current {@code tabName}. By default, it is always Company tab.
     */
    public TabManager() {
        tabName = TabName.COMPANY; // default tab that is displayed to user each time the app is opened.
    }

    /**
     * Retrieves the current {@code tabName}.
     */
    public TabName getTabName() {
        return tabName;
    }

    /**
     * Replaces {@code this.tabName} with {@code tabName}.
     */
    public void setTabName(TabName tabName) {
        requireNonNull(tabName);
        this.tabName = tabName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof TabManager)) {
            return false;
        }

        TabManager other = (TabManager) obj;
        return tabName.equals(other.tabName);
    }
}
