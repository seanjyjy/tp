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
 * Represents a Person in the address book. todo javadocs (shawn)
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CompanyItem extends Item {

    // Identity fields
    private final CompanyName companyName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Industry> industries = new HashSet<>();
    private final ObservableList<InternshipItem> internships = FXCollections.observableList(new ArrayList<>());

    /**
     * Every field must be present and not null.
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
     * Every field must be present and not null.
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

    public CompanyName getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getCompanyNameValue() {
        return companyName.getValue();
    }

    public String getPhoneValue() {
        return phone.getValue();
    }

    public String getEmailValue() {
        return email.getValue();
    }

    public String getAddressValue() {
        return address.getValue();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Industry> getIndustries() {
        return Collections.unmodifiableSet(industries);
    }

    /**
     * todo javadocs (shawn)
     */
    public List<InternshipItem> getInternships() {
        return Collections.unmodifiableList(internships);
    }

    /**
     * todo javadocs (shawn)
     */
    public InternshipItem getInternship(Index internshipIndex) throws CommandException {
        if (internshipIndex.getZeroBased() >= internships.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, INTERNSHIP_NAME));
        }
        return internships.get(internshipIndex.getZeroBased());
    }

    /**
     * todo javadocs (shawn)
     */
    public void addInternship(InternshipItem internship) {
        internships.add(internship);
    }

    /** todo javadocs (shawn) */
    public void removeInternship(Index internshipIndex) throws CommandException {
        if (internshipIndex.getZeroBased() >= internships.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, INTERNSHIP_NAME));
        }
        internships.remove(internshipIndex.getZeroBased());
    }

    /** todo javadocs (shawn) */
    public void updateAllInternshipsCompanyName() {
        for (InternshipItem internship : internships) {
            internship.setCompanyName(companyName);
        }
    }

    /** todo javadocs (shawn) */
    public int getNumberOfInternships() {
        return internships.size();
    }

    /**
     * Checks if matching internship has same identity fields.
     *
     * @param internshipItem to check against.
     * @return true if {@code internshipItem} exists in company.
     */
    public boolean containsInternship(InternshipItem internshipItem) {
        return internships.stream().anyMatch(x -> x.isSameItem(internshipItem));
    }

    @Override
    public boolean isSameItem(Item otherItem) {
        if (this == otherItem) {
            return true;
        }

        if (!(otherItem instanceof CompanyItem)) {
            return false;
        }

        CompanyItem otherCompanyItem = (CompanyItem) otherItem;

        return otherCompanyItem != null
                && otherCompanyItem.getCompanyName().equals(getCompanyName())
                && otherCompanyItem.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
