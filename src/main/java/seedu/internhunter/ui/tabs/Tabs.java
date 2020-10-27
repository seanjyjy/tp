package seedu.internhunter.ui.tabs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.UiPart;

/**
 * Tabs which allow switching of screens for different information.
 */
public class Tabs extends UiPart<Region> {

    //FXML
    private static final String FXML = "Tabs.fxml";

    //FXML properties
    private static final String TRANSPARENT = "-fx-background-color: transparent;";
    private static final String TAB_COLOR = "-fx-background-color: #BED0F7;";
    private static final String APPLICATION_Y_TRANSLATE = "-fx-translate-y: -13.5";
    private static final String COMPANY_Y_TRANSLATE = "-fx-translate-y: 0";
    private static final String PROFILE_Y_TRANSLATE = "-fx-translate-y: -27";

    // Image links
    private static final String APPLICATION_IMAGE_LINK = "/images/application.png";
    private static final String COMPANY_IMAGE_LINK = "/images/company.png";
    private static final String PROFILE_IMAGE_LINK = "/images/profile.png";

    @FXML
    private Button applicationButton;
    @FXML
    private Button companyButton;
    @FXML
    private Button profileButton;
    @FXML
    private VBox application;
    @FXML
    private VBox company;
    @FXML
    private VBox profile;
    @FXML
    private ImageView applicationIcon;
    @FXML
    private ImageView companyIcon;
    @FXML
    private ImageView profileIcon;

    /**
     * Creates the {@code Tabs} in the given {@code primaryStage}.
     */
    private Tabs() {
        super(FXML);

        // initialize the GUI of the tabs.
        company.setStyle(TAB_COLOR);
        setTabIcons();
    }

    /**
     * Creates the {@code Tabs} information in the {@code primaryStage}.
     *
     * @return A tab display.
     */
    public static Tabs getTabs() {
        return new Tabs();
    }

    /**
     * Switches the tab display depending on {@code tabName}.
     *
     * @param tabName The tab to be switched to.
     */
    public void switchTab(TabName tabName) {
        switch (tabName) {
        case COMPANY:
            setTabToCompany();
            break;
        case APPLICATION:
            setTabToApplication();
            break;
        case PROFILE:
            setTabToProfile();
            break;
        default:
            assert false;
        }
    }

    /**
     * Sets up the tab icons in GUI of {@code MainWindow}.
     */
    private void setTabIcons() {
        Image applicationIconImage = new Image(getClass().getResourceAsStream(APPLICATION_IMAGE_LINK));
        applicationIcon.setImage(applicationIconImage);

        Image companyIconPicture = new Image(getClass().getResourceAsStream(COMPANY_IMAGE_LINK));
        companyIcon.setImage(companyIconPicture);

        Image profileIconPicture = new Image(getClass().getResourceAsStream(PROFILE_IMAGE_LINK));
        profileIcon.setImage(profileIconPicture);
    }

    /**
     * Sets the tab to be of {@code application} in the {@code stage}.
     */
    private void setTabToApplication() {
        // adjust tab bar position
        setColor(application, APPLICATION_Y_TRANSLATE);
        setTransparent(company, COMPANY_Y_TRANSLATE);
        setTransparent(profile, PROFILE_Y_TRANSLATE);
    }

    /**
     * Sets the tab to be of {@code company} in the {@code stage}.
     */
    private void setTabToCompany() {
        // adjust tab bar position
        setTransparent(application, APPLICATION_Y_TRANSLATE);
        setColor(company, COMPANY_Y_TRANSLATE);
        setTransparent(profile, PROFILE_Y_TRANSLATE);
    }

    /**
     * Sets the tab to be of {@code profile} in the {@code stage}.
     */
    private void setTabToProfile() {
        // adjust tab bar position
        setTransparent(application, APPLICATION_Y_TRANSLATE);
        setTransparent(company, COMPANY_Y_TRANSLATE);
        setColor(profile, PROFILE_Y_TRANSLATE);
    }

    /**
     * Sets the color of the {@code scene} to be transparent.
     *
     * @param scene The scene to be changed in color.
     * @param distance The distance of the scene in the {@code mainWindow}.
     * @param <T> The type of scene.
     */
    private <T extends Pane> void setTransparent(T scene, String distance) {
        scene.setStyle(TRANSPARENT + distance);
    }

    /**
     * Sets the color of the {@code scene} to be of its own {@code TAB_COLOR}.
     *
     * @param scene The scene to be changed in color.
     * @param distance The distance of the scene in the {@code mainWindow}.
     * @param <T> The type of scene.
     */
    private <T extends Pane> void setColor(T scene, String distance) {
        scene.setStyle(TAB_COLOR + distance);
    }
}
