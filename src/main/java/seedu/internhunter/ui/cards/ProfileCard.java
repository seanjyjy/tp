package seedu.internhunter.ui.cards;

import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;

import javafx.scene.image.Image;
import javafx.scene.transform.Translate;
import seedu.internhunter.model.profile.ProfileItem;

/**
 * A UI component that displays some information of a {@code ProfileItem}.
 */
public class ProfileCard extends Card<ProfileItem> {

    //Image Link
    private static final String ACHIEVEMENT_IMAGE_LINK = "/images/achievement.png";
    private static final String EXPERIENCE_IMAGE_LINK = "/images/experience.png";
    private static final String SKILLS_IMAGE_LINK = "/images/skills.png";

    //FXML properties
    private static final int IMAGE_HEIGHT_WIDTH = 60;
    private static final int IMAGE_TRANSLATE_Y = -25;

    /**
     * Creates a card display with information of {@code profileItem} and with a index of {@code displayIndex}.
     *
     * @param profileItem The profile item to be displayed.
     * @param displayedIndex The index of the profile item.
     */
    public ProfileCard(ProfileItem profileItem, int displayedIndex) {
        super(profileItem, displayedIndex);
        initializeUserCardGui();
    }

    /**
     * Sets the id, name and image on the card.
     */
    private void initializeUserCardGui() {
        setId(displayedIndex);
        setTitle();
        setImageType();
    }

    /**
     * Sets the name on the card.
     */
    protected void setTitle() {
        Object title = mapping.get(TITLE_DISPLAY_NAME);
        setName(title.toString());
    }

    /**
     * Sets the image on the card.
     */
    protected void setImageType() {
        Image image;
        switch (getItem().getCategory()) {
        case ACHIEVEMENT:
            image = new Image(this.getClass().getResourceAsStream(ACHIEVEMENT_IMAGE_LINK));
            break;
        case EXPERIENCE:
            image = new Image(this.getClass().getResourceAsStream(EXPERIENCE_IMAGE_LINK));
            break;
        case SKILL:
            image = new Image(this.getClass().getResourceAsStream(SKILLS_IMAGE_LINK));
            break;
        default:
            image = null;
            assert false : "NO SUCH TYPE EXISTS";
            break;
        }
        setImageStyling();
        imageView.setImage(image);
    }

    /**
     * Sets the image styling on the card.
     */
    private void setImageStyling() {
        imageView.setFitHeight(IMAGE_HEIGHT_WIDTH);
        imageView.setFitWidth(IMAGE_HEIGHT_WIDTH);
        Translate translate = new Translate();
        translate.setY(IMAGE_TRANSLATE_Y);
        imageView.getTransforms().addAll(translate);
    }
}
