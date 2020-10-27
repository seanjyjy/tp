package seedu.internhunter.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.internhunter.commons.util.StringUtil;

/**
 * todo javadocs
 */
public class CompanyNameContainsKeyWordsPredicate implements Predicate<CompanyItem> {
    private final List<String> keywords;

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
