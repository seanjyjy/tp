package seedu.internhunter.logic.commands.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.EXECUTION_SHOULD_NOT_FAIL_MESSAGE;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_COMPANY_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.DUPLICATE_INTERNSHIP_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_BA;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_MAY_TO_JULY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_SUMMER;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_GRAPHQL;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_4000;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOOGLE_SWE;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;
import seedu.internhunter.model.item.ItemList;

public class AddInternshipCommandTest {

    private Model model;
    private ItemList<CompanyItem> expectedCompanyList;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(),
                new UserPrefs());
        expectedCompanyList = getSampleCompanyList();
    }

    @Test
    public void constructor_nullFields_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInternshipCommand(
            null, null, null, null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_addSuccess() {
        AddInternshipCommand addInternshipCommand = makeAddInternshipCommand();
        InternshipItem internshipToAdd = GOOGLE_SWE;

        String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, INTERNSHIP_NAME, internshipToAdd);
        // add internship to expected company
        expectedCompanyList.getItemList()
                .get(INDEX_FIRST.getZeroBased())
                .addInternship(internshipToAdd);
        CommandResult expectedResult = new CommandResult(addSuccessMessage);
        assertAddInternshipSuccess(expectedCompanyList, addInternshipCommand, model, expectedResult);
    }


    @Test
    public void execute_validIndexesDuplicateInternship_throwsCommandException() {
        AddInternshipCommand addInternshipCommand = makeAddInternshipCommand();
        CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
        companyItem.addInternship(GOOGLE_SWE);

        assertCommandFailure(addInternshipCommand, model, DUPLICATE_INTERNSHIP_MESSAGE);

    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        AddInternshipCommand addInternshipCommand = new AddInternshipCommand(outOfBoundIndex,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>());

        assertCommandFailure(addInternshipCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidCompanyIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        AddInternshipCommand addInternshipCommand = new AddInternshipCommand(outOfBoundIndex,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>());

        assertCommandFailure(addInternshipCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        AddInternshipCommand addInternshipCommand = makeAddInternshipCommand();

        // same object -> returns true
        assertEquals(addInternshipCommand, addInternshipCommand);

        // same values -> returns true
        AddInternshipCommand addCommandCopy = makeAddInternshipCommand();
        assertEquals(addCommandCopy, addInternshipCommand);

        // different type -> returns false
        assertNotEquals(addInternshipCommand, new AddCompanyCommand(GOLDMAN));

        // null -> returns false
        assertNotEquals(addInternshipCommand, null);

        // different company index -> returns false
        assertNotEquals(addInternshipCommand, new AddInternshipCommand(INDEX_THIRD,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>()));


        // different Wage -> returns false
        assertNotEquals(addInternshipCommand, new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_2000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>()));

        // different Period -> returns false
        assertNotEquals(addInternshipCommand, new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_MAY_TO_JULY),
                new HashSet<Requirement>()));

        // different Requirements -> returns false
        assertNotEquals(addInternshipCommand, new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<>(Arrays.asList(new Requirement(VALID_REQUIREMENT_GRAPHQL)))));

        // different Job Title -> return false
        assertNotEquals(addInternshipCommand, new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_BA),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>()));
    }

    private AddInternshipCommand makeAddInternshipCommand() {
        return new AddInternshipCommand(INDEX_FIRST,
                new JobTitle(VALID_JOB_TITLE_SWE),
                new Wage(VALID_WAGE_4000),
                new Period(VALID_PERIOD_SUMMER),
                new HashSet<Requirement>());
    }

    /**
     * Executes the given {@code command}, confirms that
     * - the returned {@link CommandResult} matches {@code expectedResult} <br>
     * - the company list in {@code model} matches {@code expectedCompanyList}
     */
    private void assertAddInternshipSuccess(ItemList<CompanyItem> expectedCompanyList,
                                            AddInternshipCommand command, Model model, CommandResult expectedResult) {
        try {
            CommandResult result = command.execute(model);
            assertEquals(expectedCompanyList, model.getUnfilteredCompanyList());
            assertEquals(result, expectedResult);
        } catch (CommandException ce) {
            throw new AssertionError(EXECUTION_SHOULD_NOT_FAIL_MESSAGE, ce);
        }
    }
}
