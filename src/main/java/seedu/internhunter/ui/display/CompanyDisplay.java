package seedu.internhunter.ui.display;

import static seedu.address.ui.GuardClauseUi.IS_EMPTY_DATA_LIST;
import static seedu.address.ui.display.DisplayKeyList.COMPANY_DISPLAY_KEY_LIST;
import static seedu.address.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.address.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.item.Item;
import seedu.address.ui.tabs.TabName;

/**
 * A UI component that displays all the information of a {@code CompanyItem}.
 */
public class CompanyDisplay extends InformationDisplay<CompanyItem> {

    /**
     * A Predicate that checks if the current key in the key list is of industries or internships.
     */
    private final Predicate<String> isIndustriesOrInternship = key -> key.equals(INDUSTRIES_DISPLAY_NAME)
        || key.equals(INTERNSHIPS_DISPLAY_NAME);

    /**
     * A function that takes in a string containing information of the internship and formats it with bullet
     * points and new line for each attributes in the internship information.
     */
    private final Function<String, String> formatInternshipDetail = string -> {
        String s = string.substring(0, string.length() - 1);
        s = s.replaceAll("\n, ", "\n");
        StringBuffer buffer = new StringBuffer(s);
        buffer.insert(0, 1 + ". ");
        formatNumbering(buffer);
        return formatBulletPoints(buffer);
    };

    /**
     * Creates a {@code CompanyDisplay} in the given {@code primaryStage}.
     *
     * @param primaryStage The main stage of the app.
     * @param companyItem The company item to be displayed.
     */
    private CompanyDisplay(Stage primaryStage, CompanyItem companyItem) {
        super(primaryStage, companyItem);

        initializeCompanyDisplayGui();
    }

    /**
     * Factory method that creates and displays the particular company item's information at that index.
     *
     * @param index The Index of the display to be displayed.
     * @return An Optional containing the display information of the company at that particular Index.
     */
    public static Optional<InformationDisplay<? extends Item>> getCompanyDisplay(
        ObservableList<CompanyItem> companyItems, int index, Stage primaryStage) {

        if (IS_EMPTY_DATA_LIST.test(companyItems)) {
            return Optional.empty();
        }
        return Optional.of(new CompanyDisplay(primaryStage, companyItems.get(index)));
    }

    /**
     * Sets the title and information of the {@code companyItem} for display.
     */
    private void initializeCompanyDisplayGui() {
        setCompanyName();
        setCompanyInformation();
    }

    /**
     * Sets the title of the {@code companyItem} for display.
     */
    private void setCompanyName() {
        Object jobTitle = mapping.get(COMPANY_DISPLAY_NAME);
        setInformationTitle(jobTitle.toString());
    }

    /**
     * Sets the information of the {@code companyItem} for display.
     */
    private void setCompanyInformation() {
        setInformation(editString, formatInternshipDetail, isIndustriesOrInternship, TabName.COMPANY,
            COMPANY_DISPLAY_KEY_LIST);
    }

    /**
     * Formats the {@code buffer} to number the internships.
     *
     * @param buffer A stringbuffer containing information of the internships.
     */
    private void formatNumbering(StringBuffer buffer) {
        int index = buffer.indexOf("\n");
        int counter = 2;
        while (index != -1) {
            String replacement = "\n" + counter + ". ";
            buffer.replace(index, index + 1, replacement);
            index += replacement.length();
            index = buffer.indexOf("\n", index);
            counter++;
        }
    }

    /**
     * Formats the {@code buffer} to have bullet points for each attributes.
     *
     * @param buffer A stringbuffer containing information of the internship.
     * @return A string that is formatted with bullet points.
     */
    private String formatBulletPoints(StringBuffer buffer) {
        String string = buffer.toString();
        string = string.replaceAll(COMMA_TWO_SPACE, "\n" + BULLET_WITH_TWO_SPACE_FRONT_ONE_BACK);
        return string;
    }
}
