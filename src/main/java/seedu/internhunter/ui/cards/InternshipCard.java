package seedu.internhunter.ui.cards;

import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_LIST_STRING;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

import seedu.internhunter.model.internship.InternshipItem;

/**
 * A UI component that displays some information of a {@code InternshipItem}.
 */
public class InternshipCard extends Card<InternshipItem> {

    /**
     * Creates a card with information regarding {@code InternshipItem} with index of {@code displayedIndex}.
     *
     * @param internshipItem The internship item to be displayed.
     * @param displayedIndex The index of the item.
     */
    public InternshipCard(InternshipItem internshipItem, int displayedIndex) {
        super(internshipItem, displayedIndex);
        initializedInternshipCardGui();
    }

    /**
     * sets the id, company name, requirements, job title, wage, period.
     */
    private void initializedInternshipCardGui() {
        setId(displayedIndex);
        setCompanyName();
        setRequirements();
        setJobTitle();
        setWage();
        setPeriod();
    }

    /**
     * Sets the company name on the card.
     */
    private void setJobTitle() {
        Object jobTitle = mapping.get(JOB_TITLE_DISPLAY_NAME);
        setName(jobTitle.toString());
    }

    /**
     * Sets the requirements on the card.
     */
    private void setRequirements() {
        Object requirements = mapping.get(REQUIREMENTS_DISPLAY_NAME);
        if (!IS_EMPTY_LIST_STRING.test(requirements.toString())) {
            setTags(requirements.toString());
        }
    }

    /**
     * Sets the name on the card.
     */
    private void setCompanyName() {
        Object companyName = mapping.get(COMPANY_DISPLAY_NAME);
        setTextAt(COMPANY_DISPLAY_NAME, companyName.toString(), LineNumber.L1);
    }

    /**
     * Sets the wages on the card.
     */
    private void setWage() {
        Object wage = mapping.get(WAGE_DISPLAY_NAME);
        setTextAt(WAGE_DISPLAY_NAME, wage.toString(), LineNumber.L2);
    }

    /**
     * Sets the period on the card.
     */
    private void setPeriod() {
        Object period = mapping.get(PERIOD_DISPLAY_NAME);
        setTextAt(PERIOD_DISPLAY_NAME, period.toString(), LineNumber.L3);
    }
}
