package seedu.internhunter.ui.cards;

import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_LIST_STRING;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.ADDRESS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.EMAIL_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PHONE_DISPLAY_NAME;

import seedu.internhunter.model.company.CompanyItem;

/**
 * A UI component that displays some information of a {@code CompanyItem}.
 */
public class CompanyCard extends Card<CompanyItem> {

    /**
     * Creates a card display with information of {@code companyItem} and with a index of {@code displayIndex}.
     *
     * @param companyItem The company item to be displayed.
     * @param displayedIndex The index of the profile item.
     */
    public CompanyCard(CompanyItem companyItem, int displayedIndex) {
        super(companyItem, displayedIndex);
        initializeCompanyCardGui();
    }

    /**
     * Sets the id, name, industries, phone, email, address on the card.
     */
    private void initializeCompanyCardGui() {
        setId(displayedIndex);
        setCompanyName();
        setIndustries();
        setPhone();
        setEmail();
        setAddress();
    }

    /**
     * Sets the name on the card.
     */
    private void setCompanyName() {
        Object companyName = mapping.get(COMPANY_DISPLAY_NAME);
        setName(companyName.toString());
    }

    /**
     * Sets the industries information on the card.
     */
    private void setIndustries() {
        Object industries = mapping.get(INDUSTRIES_DISPLAY_NAME);
        if (!IS_EMPTY_LIST_STRING.test(industries.toString())) {
            setTags(industries.toString());
        }
    }

    /**
     * Sets the phone information on the card.
     */
    private void setPhone() {
        Object phone = mapping.get(PHONE_DISPLAY_NAME);
        setTextAt(PHONE_DISPLAY_NAME, phone.toString(), LineNumber.L1);
    }

    /**
     * Sets the email on the card.
     */
    private void setEmail() {
        Object email = mapping.get(EMAIL_DISPLAY_NAME);
        setTextAt(EMAIL_DISPLAY_NAME, email.toString(), LineNumber.L2);
    }

    /**
     * Sets the address on the card.
     */
    private void setAddress() {
        Object address = mapping.get(ADDRESS_DISPLAY_NAME);
        setTextAt(ADDRESS_DISPLAY_NAME, address.toString(), LineNumber.L3);
    }
}
