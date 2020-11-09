package seedu.internhunter.logic.parser.util;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;

/**
 * Contains utility methods used for parsing all the fields in a CompanyItem.
 */
public class CompanyParserUtil {

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param companyName The string to parse into a CompanyName.
     * @return CompanyName parsed from the string.
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param phone The string to parse into a Phone.
     * @return Phone parsed from the string.
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email The string to parse into an Email.
     * @return Email parsed from the string.
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address The string to parse into an Address.
     * @return Address parsed from the string.
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String industry} into an {@code Industry}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param industry The string to parse into an Industry.
     * @return Industry parsed from the string.
     * @throws ParseException if the given {@code industry} is invalid.
     */
    public static Industry parseIndustry(String industry) throws ParseException {
        requireNonNull(industry);
        String trimmedIndustry = industry.trim();
        if (!Industry.isValidIndustryName(trimmedIndustry)) {
            throw new ParseException(Industry.MESSAGE_CONSTRAINTS);
        }
        return new Industry(trimmedIndustry);
    }

    /**
     * Parses {@code Collection<String> industries} into a {@code Set<Industry>}.
     *
     * @param industries The collection of industry strings to parse into a {@code Set<Industry>}.
     * @return {@code Set<Industry>} parsed from the collection of industry strings.
     * @throws ParseException if the given {@code industries} collection is invalid.
     */
    public static Set<Industry> parseIndustries(Collection<String> industries) throws ParseException {
        requireNonNull(industries);
        final Set<Industry> industrySet = new HashSet<>();
        for (String industryName : industries) {
            industrySet.add(parseIndustry(industryName));
        }
        return industrySet;
    }
}
