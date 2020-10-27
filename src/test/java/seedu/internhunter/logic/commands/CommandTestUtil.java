package seedu.internhunter.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_ADDRESS;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_EMAIL;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_INDEX;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_NAME;
import static seedu.internhunter.logic.parser.clisyntax.GeneralCliSyntax.PREFIX_PHONE;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.person.NameContainsKeywordsPredicate;
import seedu.internhunter.model.person.Person;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemContainsKeywordPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;

    // Preamble
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY_RANDOM = "NonEmptyPreamble";
    public static final String PREAMBLE_EMPTY = "";

    // Valid indexes
    public static final String VALID_INDEX_ONE = " " + PREFIX_INDEX + INDEX_FIRST;
    public static final String VALID_INDEX_TWO = " " + PREFIX_INDEX + INDEX_SECOND;

    // Invalid indexes
    public static final String INVALID_INDEX_RANDOM_STRING = " " + PREFIX_INDEX + "random";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ItemList<CompanyItem> expectedCompanyList = new ItemList<>(actualModel.getUnfilteredCompanyList());
        List<CompanyItem> expectedFilteredCompanyList = new ArrayList<>(actualModel.getFilteredCompanyList());
        ItemList<ApplicationItem> expectedApplicationList = new ItemList<>(actualModel.getUnfilteredApplicationList());
        List<ApplicationItem> expectedFilteredApplicationList =
                new ArrayList<>(actualModel.getFilteredApplicationList());
        ItemList<ProfileItem> expectedProfileList = new ItemList<>(actualModel.getUnfilteredProfileList());
        List<ProfileItem> expectedFilteredProfileList = new ArrayList<>(actualModel.getFilteredProfileList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCompanyList, actualModel.getUnfilteredCompanyList());
        assertEquals(expectedFilteredCompanyList, actualModel.getFilteredCompanyList());
        assertEquals(expectedApplicationList, actualModel.getUnfilteredApplicationList());
        assertEquals(expectedFilteredApplicationList, actualModel.getFilteredApplicationList());
        assertEquals(expectedProfileList, actualModel.getUnfilteredProfileList());
        assertEquals(expectedFilteredProfileList, actualModel.getFilteredProfileList());
    }

    /**
     * Todo: remove
     *
     * @param model
     * @param targetIndex
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getAddressBook().getFilteredItemList().size());
        Person person = model.getAddressBook().getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.getAddressBook().updateFilteredItemList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getAddressBook().getFilteredItemList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the profileItem at the given {@code targetIndex} in the
     * {@code model}'s profile list.
     */
    public static void showProfileItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProfileList().size());

        ProfileItem profileItem = model.getProfileItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitTitle = profileItem.getTitle().toString().split("\\s+");
        model.updateFilteredProfileList(new ProfileItemContainsKeywordPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredProfileListSize());
    }

    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s application list.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        ApplicationItem applicationItem = model.getApplicationItemFromFilteredList(targetIndex.getZeroBased());
        final String[] splitJobTitle = applicationItem.getJobTitleOfInternshipItem().toString().split("\\s+");
        model.updateFilteredApplicationList(
                new ApplicationNameContainsKeyWordsPredicate(Arrays.asList(splitJobTitle[0])));

        assertEquals(1, model.getFilteredApplicationListSize());
    }

}
