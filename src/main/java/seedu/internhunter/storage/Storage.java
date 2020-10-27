package seedu.internhunter.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.model.ReadOnlyUserPrefs;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.profile.ProfileItem;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    /**
     * Returns the file path of the application data file.
     */
    Path getApplicationItemListFilePath();

    /**
     * Returns application ItemList data as a {@link ItemList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList<ApplicationItem>> readApplicationItemList() throws DataConversionException, IOException;

    /**
     * @see #getApplicationItemListFilePath()
     */
    Optional<ReadOnlyItemList<ApplicationItem>> readApplicationItemList(
            Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ItemList} to the storage.
     *
     * @param itemList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveApplicationItemList(ReadOnlyItemList<ApplicationItem> itemList) throws IOException;

    /**
     * @see #saveApplicationItemList(ReadOnlyItemList)
     */
    void saveApplicationItemList(ReadOnlyItemList<ApplicationItem> itemList, Path filePath) throws IOException;

    /**
     * Returns the file path of the company data file.
     */
    Path getCompanyItemListFilePath();

    /**
     * Returns company ItemList data as a {@link ItemList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList<CompanyItem>> readCompanyItemList() throws DataConversionException, IOException;

    /**
     * @see #getCompanyItemListFilePath()
     */
    Optional<ReadOnlyItemList<CompanyItem>> readCompanyItemList(
            Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ItemList} to the storage.
     *
     * @param itemList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCompanyItemList(ReadOnlyItemList<CompanyItem> itemList) throws IOException;

    /**
     * @see #saveCompanyItemList(ReadOnlyItemList)
     */
    void saveCompanyItemList(ReadOnlyItemList<CompanyItem> itemList, Path filePath) throws IOException;

    /**
     * Returns the file path of the profile data file.
     */
    Path getProfileItemListFilePath();

    /**
     * Returns profile ItemList data as a {@link ItemList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList<ProfileItem>> readProfileItemList() throws DataConversionException, IOException;

    /**
     * @see #getProfileItemListFilePath()
     */
    Optional<ReadOnlyItemList<ProfileItem>> readProfileItemList(
            Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ItemList} to the storage.
     *
     * @param itemList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProfileItemList(ReadOnlyItemList<ProfileItem> itemList) throws IOException;

    /**
     * @see #saveProfileItemList(ReadOnlyItemList)
     */
    void saveProfileItemList(ReadOnlyItemList<ProfileItem> itemList, Path filePath) throws IOException;

}
