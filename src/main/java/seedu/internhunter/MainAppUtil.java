package seedu.internhunter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.internhunter.commons.core.Config;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.commons.util.ConfigUtil;
import seedu.internhunter.commons.util.StringUtil;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.ReadOnlyUserPrefs;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.exceptions.InconsistentInternshipException;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.Storage;
import seedu.internhunter.storage.UserPrefsStorage;

/**
 * A collection of utility functions for the MainApp.
 */
public class MainAppUtil {

    private static final Logger logger = LogsCenter.getLogger(MainAppUtil.class);

    /**
     * Gets the initial application item list.
     *
     * @param storage storage the application item list is saved in.
     * @return initial application item list.
     */
    public static ReadOnlyItemList<ApplicationItem> initApplicationItemList(Storage storage) {
        Optional<ReadOnlyItemList<ApplicationItem>> itemListOptional;
        ReadOnlyItemList<ApplicationItem> initialItemListData;
        try {
            itemListOptional = storage.readApplicationItemList();
            if (itemListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample application item list");
            }
            //to do sample data
            initialItemListData = itemListOptional.orElse(new ItemList<>());
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Will be starting with an empty application item list");
            initialItemListData = new ItemList<>();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty application item list");
            initialItemListData = new ItemList<>();
        }

        return initialItemListData;
    }

    /**
     * Gets the initial company item list.
     *
     * @param storage storage the company item list is saved in.
     * @return initial company item list.
     */
    public static ReadOnlyItemList<CompanyItem> initCompanyItemList(Storage storage) {
        Optional<ReadOnlyItemList<CompanyItem>> itemListOptional;
        ReadOnlyItemList<CompanyItem> initialItemListData;
        try {
            itemListOptional = storage.readCompanyItemList();
            if (itemListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample company item list");
            }
            //to do sample data
            initialItemListData = itemListOptional.orElse(new ItemList<>());
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Will be starting with an empty company item list");
            initialItemListData = new ItemList<>();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty company item list");
            initialItemListData = new ItemList<>();
        }

        return initialItemListData;
    }

    /**
     * Gets the initial profile item list.
     *
     * @param storage storage the profile item list is saved in.
     * @return initial profile item list.
     */
    public static ReadOnlyItemList<ProfileItem> initProfileItemList(Storage storage) {
        Optional<ReadOnlyItemList<ProfileItem>> itemListOptional;
        ReadOnlyItemList<ProfileItem> initialItemListData;
        try {
            itemListOptional = storage.readProfileItemList();
            if (itemListOptional.isEmpty()) {
                logger.info("Data file not found. Will be starting with a sample profile item list");
            }
            //to do sample data
            initialItemListData = itemListOptional.orElse(new ItemList<>());
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. "
                    + "Will be starting with an empty profile item list");
            initialItemListData = new ItemList<>();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. "
                    + "Will be starting with an empty profile item list");
            initialItemListData = new ItemList<>();
        }

        return initialItemListData;
    }

    /**
     * Matches internships in the application list with the ones in the company list.
     *
     * @param applicationItemList list of application items.
     * @param companyItemList     list of company items.
     * @throws InconsistentInternshipException an application has an internship
     *                                         that does not exist in any company's list of internships.
     */
    private static void matchInternships(ItemList<ApplicationItem> applicationItemList,
            ItemList<CompanyItem> companyItemList) throws InconsistentInternshipException {
        for (ApplicationItem applicationItem : applicationItemList.getItemList()) {
            final InternshipItem applicationInternshipItem = applicationItem.getInternshipItem();

            final Optional<InternshipItem> correctInternshipItem = companyItemList.getItemList().stream()
                    .map(CompanyItem::getInternships).reduce(new ArrayList<>(), (x, y) -> {
                        x.addAll(y);
                        return x;
                    }).stream().filter(companyInternshipItem -> companyInternshipItem.equals(applicationInternshipItem))
                    .findAny();

            if (correctInternshipItem.isEmpty()) {
                applicationItemList.resetData(new ItemList<>());
                companyItemList.resetData(new ItemList<>());
                throw new InconsistentInternshipException();
            }

            //Changes the current application item with the one with the correct internship object.
            applicationItemList.setItem(applicationItem, new ApplicationItem(correctInternshipItem.get(),
                    applicationItem.getStatus(), applicationItem.getStatusDate()));
        }
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    public static Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        ItemList<Person> addressBook = new ItemList<>();
        ItemList<CompanyItem> companyItemList = new ItemList<>(initCompanyItemList(storage));
        ItemList<ApplicationItem> applicationItemList = new ItemList<>(initApplicationItemList(storage));
        ItemList<ProfileItem> profileItemList = new ItemList<>(initProfileItemList(storage));

        try {
            matchInternships(applicationItemList, companyItemList);
        } catch (InconsistentInternshipException e) {
            logger.warning(e.getMessage());
        }

        return new ModelManager(addressBook, companyItemList, applicationItemList, profileItemList, userPrefs);
    }

    public static void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    public static Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    public static UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty AddressBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }
}
