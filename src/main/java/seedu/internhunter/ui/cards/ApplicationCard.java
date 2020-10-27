package seedu.internhunter.ui.cards;

import static seedu.internhunter.model.util.DateUtil.extractDayAndMonth;
import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.APPLIED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.INTERVIEW_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.WAITING_KEYWORD;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_LIST_STRING;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.JOB_TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

import javafx.scene.image.Image;
import seedu.internhunter.model.application.ApplicationItem;

/**
 * A UI component that displays some information of a {@code ApplicationItem}.
 */
public class ApplicationCard extends Card<ApplicationItem> {

    //Image Link
    private static final String CALENDAR_IMAGE_LINK = "/images/calendar.png";

    //FXML properties
    private static final String MIN_WIDTH_STATUS_BOX = " -fx-min-width: 60;";
    private static final String MAX_WIDTH_STATUS_BOX = " -fx-max-width: 60;";
    private static final String MIN_HEIGHT_STATUS_BOX = " -fx-min-height: 20;";
    private static final String MAX_HEIGHT_STATUS_BOX = " -fx-max-height: 20;";
    private static final String TRANSLATE_Y_STATUS_BOX = "-fx-translate-y: 5;";
    private static final String ALIGNMENT_STATUS_BOX = "-fx-alignment: center;";
    private static final String DEFAULT_STATUS_BOX = MIN_WIDTH_STATUS_BOX + MIN_HEIGHT_STATUS_BOX
        + MAX_HEIGHT_STATUS_BOX + MAX_WIDTH_STATUS_BOX + TRANSLATE_Y_STATUS_BOX + ALIGNMENT_STATUS_BOX;
    private static final String ACCEPTED_COLOR = "-fx-background-color: #3ADB9D;";
    private static final String WAITING_COLOR = "-fx-background-color: #F4D014;";
    private static final String REJECTED_COLOR = "-fx-background-color: #F02E62;";
    private static final String APPLIED_COLOR = "-fx-background-color: #399fd2;";
    private static final String INTERVIEW_COLOR = "-fx-background-color: #f86a23;";
    private static final String OFFERED_COLOR = "-fx-background-color: #0e2578;";
    private static final int IMAGE_HEIGHT_WIDTH = 23;

    /**
     * Creates a card display with information of {@code ApplicationItem} and with a index of {@code displayIndex}.
     *
     * @param applicationItem The application item to be displayed.
     * @param displayedIndex The index of the profile item.
     */
    public ApplicationCard(ApplicationItem applicationItem, int displayedIndex) {
        super(applicationItem, displayedIndex);
        initializeInternshipCardGui();
    }

    /**
     * Sets the id, name, requirements, status, period, wages, company name, date on the card.
     */
    private void initializeInternshipCardGui() {
        setId(displayedIndex);
        setJobTitle();
        setRequirements();
        setStatus();
        setPeriod();
        setWage();
        setCompanyName();
        setDate();
    }

    /**
     * Sets the name on the card.
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
     * Sets the status on the card.
     */
    private void setStatus() {
        Object status = mapping.get(STATUS_DISPLAY_NAME);
        if (!IS_EMPTY_LIST_STRING.test(status.toString())) {
            setStatusStyle(status.toString());
        }
    }

    /**
     * Set the style of the status depending on {@code statusType}
     *
     * @param statusType The type of status the user inputted in.
     */
    private void setStatusStyle(String statusType) {
        String updatedStatusBoxStyle = DEFAULT_STATUS_BOX;
        switch (statusType) {
        case (APPLIED_KEYWORD):
            status.setText(APPLIED_KEYWORD);
            updatedStatusBoxStyle += APPLIED_COLOR;
            break;
        case (INTERVIEW_KEYWORD):
            status.setText(INTERVIEW_KEYWORD);
            updatedStatusBoxStyle += INTERVIEW_COLOR;
            break;
        case (WAITING_KEYWORD):
            status.setText(WAITING_KEYWORD);
            updatedStatusBoxStyle += WAITING_COLOR;
            break;
        case (REJECTED_KEYWORD):
            status.setText(REJECTED_KEYWORD);
            updatedStatusBoxStyle += REJECTED_COLOR;
            break;
        case (OFFERED_KEYWORD):
            status.setText(OFFERED_KEYWORD);
            updatedStatusBoxStyle += OFFERED_COLOR;
            break;
        case (ACCEPTED_KEYWORD):
            status.setText(ACCEPTED_KEYWORD);
            updatedStatusBoxStyle += ACCEPTED_COLOR;
            break;
        default:
            assert false;
            break;
        }
        statusBox.setStyle(updatedStatusBoxStyle);
    }

    /**
     * Sets the company name on the card.
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

    /**
     * Sets the date and image on the card.
     */
    private void setDate() {
        Image calendarIcon = new Image(this.getClass().getResourceAsStream(CALENDAR_IMAGE_LINK));
        imageView.setImage(calendarIcon);
        setImageStyling();

        Object dateInformation = mapping.get(DATE_DISPLAY_NAME);
        String[] dateInformationArray = dateInformation.toString().split(" ");
        date.setText(extractDayAndMonth(dateInformationArray));
    }

    /**
     * Sets the image styling on card.
     */
    private void setImageStyling() {
        imageView.setFitHeight(IMAGE_HEIGHT_WIDTH);
        imageView.setFitWidth(IMAGE_HEIGHT_WIDTH);
    }

}
