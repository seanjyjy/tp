package seedu.internhunter.model.view;

import seedu.internhunter.commons.core.index.Index;

/**
 * The API of the Tab component.
 */
public interface View {

    /**
     * Replaces the current {@code companyIndex} with {@code index}.
     */
    void setCompanyViewIndex(Index index);

    /**
     * Replaces the current {@code applicationIndex} with {@code index}.
     */
    void setApplicationViewIndex(Index index);

    /**
     * Replaces the current {@code profileIndex} with {@code index}.
     */
    void setProfileViewIndex(Index index);

    /**
     * Retrieves the current Index of company.
     */
    Index getCompanyViewIndex();

    /**
     * Retrieves the current Index of application.
     */
    Index getApplicationViewIndex();

    /**
     * Retrieves the current Index of profile.
     */
    Index getProfileViewIndex();

}
