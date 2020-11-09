package seedu.internhunter.testutil.company;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.model.internship.InternshipItem;

/**
 * A utility class to help with building CompanyItem objects.
 */
public class CompanyItemBuilder {

    public static final String DEFAULT_COMPANY_NAME = "Facebook";
    public static final String DEFAULT_PHONE = "61231234";
    public static final String DEFAULT_EMAIL = "FacebookHires@facebook.org";
    public static final String DEFAULT_ADDRESS = "9 Straits View, Marina One";

    private CompanyName companyName;
    private Phone phone;
    private Email email;

    private Address address;
    private Set<Industry> industries;
    private ObservableList<InternshipItem> internships;


    /**
     * Creates a {@code CompanyItemBuilder} with the default details.
     */
    public CompanyItemBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        industries = new HashSet<>();
        internships = FXCollections.observableList(new ArrayList<>());
    }

    /**
     * Initializes the CompanyItemBuilder with the data of {@code companyToCopy}.
     */
    public CompanyItemBuilder(CompanyItem companyToCopy) {
        requireNonNull(companyToCopy);
        companyName = companyToCopy.getCompanyName();
        phone = companyToCopy.getPhone();
        email = companyToCopy.getEmail();
        address = companyToCopy.getAddress();
        industries = new HashSet<>(companyToCopy.getIndustries());
        internships = FXCollections.observableList(new ArrayList<>(companyToCopy.getInternships()));
    }

    /**
     * Sets the {@code CompanyName} of the {@code CompanyItem} that we are building.
     *
     * @param companyName The company name to set.
     * @return The builder with the company name set.
     */
    public CompanyItemBuilder withCompanyName(String companyName) {
        requireNonNull(companyName);
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code CompanyItem} that we are building.
     *
     * @param phone The phone to set.
     * @return The builder with the phone set.
     */
    public CompanyItemBuilder withPhone(String phone) {
        requireNonNull(phone);
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code CompanyItem} that we are building.
     *
     * @param email The email to set.
     * @return The builder with the email set.
     */
    public CompanyItemBuilder withEmail(String email) {
        requireNonNull(email);
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code CompanyItem} that we are building.
     *
     * @param address The address to set.
     * @return The builder with the address set.
     */
    public CompanyItemBuilder withAddress(String address) {
        requireNonNull(address);
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Industry} set of the {@code CompanyItem} that we are building.
     *
     * @param industries The industries to set.
     * @return The builder with the industries set.
     */
    public CompanyItemBuilder withIndustries(String... industries) {
        requireNonNull(industries);
        this.industries = getIndustrySet(industries);
        return this;
    }

    /**
     * Sets the {@code Internship} list of the {@code CompanyItem} that we are building.
     *
     * @param internships The internships to set.
     * @return The builder with the internships set.
     */
    public CompanyItemBuilder withInternships(InternshipItem... internships) {
        requireNonNull(internships);
        this.internships = FXCollections.observableList(new ArrayList<>(Arrays.asList(internships)));
        return this;
    }

    /**
     * Creates a company based on the company fields stored in the builder.
     */
    public CompanyItem build() {
        return new CompanyItem(companyName, phone, email, address, industries, internships);
    }

    /**
     * Returns a set of industries containing the list of strings given.
     *
     * @param strings Representative of industries.
     * @return A set of industries containing the list of strings given.
     */
    private Set<Industry> getIndustrySet(String... strings) {
        return Arrays.stream(strings)
                .map(Industry::new)
                .collect(Collectors.toSet());
    }

}
