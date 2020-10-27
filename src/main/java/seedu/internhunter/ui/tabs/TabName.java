package seedu.internhunter.ui.tabs;

import seedu.internhunter.commons.util.StringUtil;

/**
 * Type of Tabs available.
 */
public enum TabName {

    APPLICATION, COMPANY, PROFILE;

    /**
     * Returns a string representation of the {@code TabName}.
     *
     * @return String representation of the {@code TabName}.
     */
    @Override
    public String toString() {
        return StringUtil.toTitleCase(super.toString());
    }
}
