package seedu.internhunter;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.internhunter.commons.core.Config;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.core.Version;
import seedu.internhunter.commons.util.StringUtil;
import seedu.internhunter.logic.Logic;
import seedu.internhunter.logic.LogicManager;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.ItemListStorage;
import seedu.internhunter.storage.JsonItemListStorage;
import seedu.internhunter.storage.JsonUserPrefsStorage;
import seedu.internhunter.storage.Storage;
import seedu.internhunter.storage.StorageManager;
import seedu.internhunter.storage.UserPrefsStorage;
import seedu.internhunter.storage.application.JsonAdaptedApplicationItem;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;
import seedu.internhunter.storage.profile.JsonAdaptedProfileItem;
import seedu.internhunter.ui.Ui;
import seedu.internhunter.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 3, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing InternHunter ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = MainAppUtil.initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = MainAppUtil.initPrefs(userPrefsStorage);
        ItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
                new JsonItemListStorage<>(userPrefs.getApplicationItemListFilePath(), ApplicationItem.class,
                        JsonAdaptedApplicationItem.class);
        ItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage = new JsonItemListStorage<>(
                userPrefs.getCompanyItemListFilePath(), CompanyItem.class, JsonAdaptedCompanyItem.class);
        ItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage = new JsonItemListStorage<>(
                userPrefs.getProfileItemListFilePath(), ProfileItem.class, JsonAdaptedProfileItem.class);

        storage = new StorageManager(applicationItemListStorage, companyItemListStorage, profileItemListStorage,
                userPrefsStorage);

        MainAppUtil.initLogging(config);

        model = MainAppUtil.initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting InternHunter " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping InternHunter ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
