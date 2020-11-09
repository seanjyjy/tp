package seedu.internhunter.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.internhunter.commons.util.StringUtil;

/**
 * Tests that a {@code CompanyItem}'s {@code CompanyName} matches any of the keywords given.
 */
public class CompanyNameContainsKeyWordsPredicate implements Predicate<CompanyItem> {
    private final List<String> keywords;

    /**
     * Constructs a {@code CompanyNameContainsKeyWordsPredicate}.
     *
     * @param keywords The keywords to check against.
     */
    public CompanyNameContainsKeyWordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CompanyItem companyItem) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        companyItem.getCompanyNameValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyNameContainsKeyWordsPredicate // instanceof handles nulls
                && keywords.equals(((CompanyNameContainsKeyWordsPredicate) other).keywords)); // state check
    }
}
