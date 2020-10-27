package seedu.internhunter.ui.textstyle;

import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Contains a {@code tile} and a {@code description}.
 */
public class TitleDescription {

    private static final String DISPLAY_FONT = "Nunito";
    private static final int DISPLAY_SIZE = 12;
    private static final String TITLE_COLOR = "#363F80";
    private static final String DESCRIPTION_COLOR = "#8E8FB5";

    private final Text title;
    private final Text description;

    /**
     * Creates a {@code TitleDescription} containing a title and a description.
     *
     * @param title The title of the information to be styled.
     * @param description The description of the information to be styled.
     */
    private TitleDescription(Text title, Text description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Retrieves the title.
     *
     * @return The styled {@code title};
     */
    public Text getTitle() {
        return title;
    }

    /**
     * Retrieves the description.
     *
     * @return The styled {@code description};
     */
    public Text getDescription() {
        return description;
    }

    /**
     * Creates a {@code TitleDescription} that contains both the styled title and the styled description.
     *
     * @param displayTitle The title of the information.
     * @param displayDescription The description of the information.
     * @return A titleDescription object that contains a styled title and a styled description.
     */
    public static TitleDescription createTitleDescription(String displayTitle, String displayDescription) {
        requireAllNonNull(displayDescription, displayTitle);
        Text styledTitle = new Text(displayTitle);
        Text styledDescription = new Text(displayDescription);
        setTextStyle(styledTitle, Color.web(TITLE_COLOR));
        setTextStyle(styledDescription, Color.web(DESCRIPTION_COLOR));
        return new TitleDescription(styledTitle, styledDescription);
    }

    /**
     * Sets a text with a pre-defined font and its {@code color}.
     *
     * @param text The text to be styled.
     * @param color The color of the text.
     */
    private static void setTextStyle(Text text, Color color) {
        requireAllNonNull(text, color);
        text.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, DISPLAY_SIZE));
        text.setFill(color);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof TitleDescription)) {
            return false;
        }

        TitleDescription other = (TitleDescription) obj;
        return title.toString().equals(other.title.toString())
                && description.toString().equals(other.description.toString());
    }
}
