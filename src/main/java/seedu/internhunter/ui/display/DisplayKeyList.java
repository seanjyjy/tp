package seedu.internhunter.ui.display;

import static seedu.internhunter.ui.panel.PanelDisplayKeyword.ADDRESS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DATE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.EMAIL_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PERIOD_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PHONE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.REQUIREMENTS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.STATUS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TYPE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.WAGE_DISPLAY_NAME;

/**
 * A class that contains the key list to access the information in a particular order.
 */
public class DisplayKeyList {

    /**
     * A fix order for the key list to access the application display.
     */
    public static final String[] APPLICATION_DISPLAY_KEY_LIST = {COMPANY_DISPLAY_NAME, PERIOD_DISPLAY_NAME,
        WAGE_DISPLAY_NAME, REQUIREMENTS_DISPLAY_NAME, STATUS_DISPLAY_NAME, DATE_DISPLAY_NAME
    };

    /**
     * A fix order for the key list to access the company display.
     */
    public static final String[] COMPANY_DISPLAY_KEY_LIST = {COMPANY_DISPLAY_NAME, PHONE_DISPLAY_NAME,
        EMAIL_DISPLAY_NAME, ADDRESS_DISPLAY_NAME, INDUSTRIES_DISPLAY_NAME, INTERNSHIPS_DISPLAY_NAME
    };

    /**
     * A fix order for the key list to access the profile display.
     */
    public static final String[] PROFILE_DISPLAY_KEY_LIST = {TITLE_DISPLAY_NAME, TYPE_DISPLAY_NAME,
        DESCRIPTORS_DISPLAY_NAME
    };
}
