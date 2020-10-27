package seedu.internhunter.storage.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internhunter.commons.exceptions.IllegalValueException;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.storage.internship.JsonAdaptedInternshipItem;
import seedu.internhunter.storage.item.JsonAdaptedItem;

/**
 * Jackson-friendly version of {@link CompanyItem}.
 */
public class JsonAdaptedCompanyItem extends JsonAdaptedItem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company item's %s field is missing!";

    private final String companyName;
    private final String phone;
    private final String email;
    private final String address;

    private final Set<JsonAdaptedIndustry> industries = new HashSet<>();
    private final List<JsonAdaptedInternshipItem> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCompanyItem} with the given company item details.
     */
    @JsonCreator
    public JsonAdaptedCompanyItem(@JsonProperty("companyName") String companyName,
            @JsonProperty("phone") String phone, @JsonProperty("email") String email,
            @JsonProperty("address") String address, @JsonProperty("industries") Set<JsonAdaptedIndustry> industries,
            @JsonProperty("internships") List<JsonAdaptedInternshipItem> internships) {
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.industries.addAll(industries);
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code CompanyItem} into this class for Jackson use.
     */
    public JsonAdaptedCompanyItem(CompanyItem source) {
        assert source != null : JsonAdaptedItem.NULL_SOURCE_ERROR_MESSAGE;
        companyName = source.getCompanyNameValue();
        phone = source.getPhoneValue();
        email = source.getEmailValue();
        address = source.getAddressValue();

        industries.addAll(source.getIndustries().stream()
                .map(JsonAdaptedIndustry::new)
                .collect(Collectors.toList()));
        internships.addAll(source.getInternships().stream()
                .map(JsonAdaptedInternshipItem::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted company item object into the model's {@code CompanyItem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted company item.
     */
    @Override
    public CompanyItem toModelType() throws IllegalValueException {
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidAlphaNumericWord(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName itemCompanyName = new CompanyName(companyName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone itemPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email itemEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address itemAddress = new Address(address);

        final Set<Industry> itemIndustries = new HashSet<>();
        for (JsonAdaptedIndustry industry : industries) {
            itemIndustries.add(industry.toModelType());
        }

        final List<InternshipItem> itemInternships = new ArrayList<>();
        for (JsonAdaptedInternshipItem internship : internships) {
            itemInternships.add(internship.toModelType());
        }

        return new CompanyItem(itemCompanyName, itemPhone, itemEmail, itemAddress, itemIndustries, itemInternships);
    }
}
