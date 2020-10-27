package seedu.internhunter.model.profile;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.internhunter.commons.util.StringUtil;


/**
 * Tests that a {@code ProfileItems}'s {@code Title} matches any of the keywords given.
 */
public class ProfileItemContainsKeywordPredicate implements Predicate<ProfileItem> {
    private final List<String> keywords;

    public ProfileItemContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(ProfileItem profileItem) {
        requireNonNull(profileItem);
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        profileItem.getTitleValue(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileItemContainsKeywordPredicate// instanceof handles nulls
                && keywords.equals(((ProfileItemContainsKeywordPredicate) other).keywords)); // state check
    }
}
