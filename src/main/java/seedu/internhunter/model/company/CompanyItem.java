package seedu.internhunter.model.company;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.internhunter.model.util.CompanyItemUtil.ADDRESS_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.EMAIL_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.INDUSTRIES_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.INTERNSHIPS_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.PHONE_OUTPUT_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.ADDRESS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.COMPANY_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.EMAIL_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INDUSTRIES_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.INTERNSHIPS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.PHONE_DISPLAY_NAME;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.Item;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;

/**
 * Represents a Company.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CompanyItem extends Item {

    // Identity fields
    private final CompanyName companyName;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Set<Industry> industries = new HashSet<>();
    private final ObservableList<InternshipItem> internships = FXCollections.observableList(new ArrayList<>());

    /**
     * Constructs a {@code Company} without internships. Every field must be present and not null.
     *
     * @param companyName The company's name.
     * @param phone The company's phone number.
     * @param email The company's email address.
     * @param address The company's physical address.
     * @param industries The company's industry types.
     */
    public CompanyItem(CompanyName companyName, Phone phone, Email email, Address address, Set<Industry> industries) {
        requireAllNonNull(companyName, phone, email, address, industries);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.industries.addAll(industries);
    }

    /**
     * Constructs a {@code Company} with internships. Every field must be present and not null.
     *
     * @param companyName The company's name.
     * @param phone The company's phone number.
     * @param email The company's email address.
     * @param address The company's physical address.
     * @param industries The company's industry types.
     * @param internships The internships offered by the company.
     */
    public CompanyItem(CompanyName companyName, Phone phone, Email email, Address address, Set<Industry> industries,
            List<InternshipItem> internships) {
        requireAllNonNull(companyName, phone, email, address, industries, internships);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.industries.addAll(industries);
        this.internships.addAll(internships);
    }

    /**
     * Retrieves the name of the {@code CompanyItem}.
     *
     * @return {@code CompanyName} of the {@code CompanyItem}.
     */
    public CompanyName getCompanyName() {
        return companyName;
    }

    /**
     * Retrieves the phone number of the {@code CompanyItem}.
     *
     * @return {@code Phone} of the {@code CompanyItem}.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Retrieves the email address of the {@code CompanyItem}.
     *
     * @return {@code Email} of the {@code CompanyItem}.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Retrieves the physical address of the {@code CompanyItem}.
     *
     * @return {@code Address} of the {@code CompanyItem}.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Retrieves the value of the name of the {@code CompanyItem}.
     *
     * @return Value of the {@code CompanyName} of the {@code CompanyItem}.
     */
    public String getCompanyNameValue() {
        return companyName.getValue();
    }

    /**
     * Retrieves the value of the phone number of the {@code CompanyItem}.
     *
     * @return Value of the {@code Phone} of the {@code CompanyItem}.
     */
    public String getPhoneValue() {
        return phone.getValue();
    }

    /**
     * Retrieves the value of the email address of the {@code CompanyItem}.
     *
     * @return Value of the {@code Email} of the {@code CompanyItem}.
     */
    public String getEmailValue() {
        return email.getValue();
    }

    /**
     * Retrieves the value of the physical address of the {@code CompanyItem}.
     *
     * @return Value of the {@code Address} of the {@code CompanyItem}.
     */
    public String getAddressValue() {
        return address.getValue();
    }

    /**
     * Retrieves an immutable industry set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return Set of industries of the {@code CompanyItem}.
     */
    public Set<Industry> getIndustries() {
        return Collections.unmodifiableSet(industries);
    }

    /**
     * Retrieves a list of all the internships in the {@code CompanyItem}.
     *
     * @return List of all the internships of the {@code CompanyItem}.
     */
    public List<InternshipItem> getInternships() {
        return Collections.unmodifiableList(internships);
    }

    /**
     * Retrieves a single internship from the {@code CompanyItem}.
     *
     * @param internshipIndex The index of the internship in the company's internship list.
     * @return The internship in the company's internship list indexed by the given index.
     * @throws CommandException If index given is larger than the number of internships in the company's internship
     * list.
     */
    public InternshipItem getInternship(Index internshipIndex) throws CommandException {
        if (internshipIndex.getZeroBased() >= internships.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, INTERNSHIP_NAME));
        }
        return internships.get(internshipIndex.getZeroBased());
    }

    /**
     * Adds an internship to the company's internship list.
     *
     * @param internship The internship to add.
     */
    public void addInternship(InternshipItem internship) {
        internships.add(internship);
    }

    /**
     * Removes the internship specified by the given index from the company's internship list.
     *
     * @param internshipIndex The index of the internship in the company's internship list.
     * @throws CommandException If index given is larger than the number of internships in the company's internship
     * list.
     */
    public void removeInternship(Index internshipIndex) throws CommandException {
        if (internshipIndex.getZeroBased() >= internships.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, INTERNSHIP_NAME));
        }
        internships.remove(internshipIndex.getZeroBased());
    }

    /**
     * Updates all internships in the company's internship list to take on the company's name.
     */
    public void updateAllInternshipsCompanyName() {
        for (InternshipItem internship : internships) {
            internship.setCompanyName(companyName);
        }
    }

    /**
     * Retrieves the number of internships in the company's internship list.
     *
     * @return The number of internships in the company's internship list.
     */
    public int getNumberOfInternships() {
        return internships.size();
    }

    /**
     * Checks if an internship already exists in the company's internship list.
     *
     * @param internshipItem Internship to check against.
     * @return True if {@code internshipItem} exists in the company's internship list. False otherwise.
     */
    public boolean containsInternship(InternshipItem internshipItem) {
        return internships.stream().anyMatch(x -> x.isSameItem(internshipItem));
    }

    /**
     * Checks if an Item is the same as the {@code CompanyItem} using the weaker notion of equality. The Item is
     * considered the same item as the {@code CompanyItem} if it is also a {@code CompanyItem} with the same
     * {@code CompanyName}.
     *
     * @param otherItem Other Item to compare to.
     * @return True if other Item is a {@code CompanyItem} with the same {@code CompanyName}.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (this == otherItem) {
            return true;
        }

        if (!(otherItem instanceof CompanyItem)) {
            return false;
        }

        CompanyItem otherCompanyItem = (CompanyItem) otherItem;

        return otherCompanyItem.getCompanyName().equals(getCompanyName());
    }

    /**
     * Checks if an Object is the same as the {@code CompanyItem} using a stronger notion of equality.
     *
     * @param other Other Object to compare to.
     * @return True if other Object is a {@code CompanyItem} with all the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CompanyItem)) {
            return false;
        }

        CompanyItem otherCompanyItem = (CompanyItem) other;
        return otherCompanyItem.getCompanyName().equals(getCompanyName())
                && otherCompanyItem.getPhone().equals(getPhone())
                && otherCompanyItem.getEmail().equals(getEmail())
                && otherCompanyItem.getAddress().equals(getAddress())
                && otherCompanyItem.getIndustries().equals(getIndustries())
                && otherCompanyItem.getInternships().equals(getInternships());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, phone, email, address, industries, internships);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompanyName())
                .append(PHONE_OUTPUT_NAME)
                .append(getPhone())
                .append(EMAIL_OUTPUT_NAME)
                .append(getEmail())
                .append(ADDRESS_OUTPUT_NAME)
                .append(getAddress())
                .append(INDUSTRIES_OUTPUT_NAME)
                .append(getIndustries().isEmpty() ? "-" : getIndustries())
                .append(INTERNSHIPS_OUTPUT_NAME)
                .append(getInternships().isEmpty() ? "-" : getInternships());
        return builder.toString();
    }

    @Override
    public String getItemName() {
        return COMPANY_NAME;
    }

    @Override
    public LinkedHashMap<String, Object> getMapping() {
        LinkedHashMap<String, Object> mapping = new LinkedHashMap<>();
        mapping.put(COMPANY_DISPLAY_NAME, companyName);
        mapping.put(PHONE_DISPLAY_NAME, phone);
        mapping.put(EMAIL_DISPLAY_NAME, email);
        mapping.put(ADDRESS_DISPLAY_NAME, address);
        mapping.put(INDUSTRIES_DISPLAY_NAME, industries);
        mapping.put(INTERNSHIPS_DISPLAY_NAME, internships);
        return mapping;
    }

    @Override
    public JsonAdaptedCompanyItem getJsonAdaptedItem() {
        return new JsonAdaptedCompanyItem(this);
    }

}
