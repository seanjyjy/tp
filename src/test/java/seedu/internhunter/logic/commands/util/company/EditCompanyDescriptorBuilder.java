package seedu.internhunter.logic.commands.util.company;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internhunter.logic.commands.edit.EditCompanyCommand.EditCompanyDescriptor;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {
    private EditCompanyDescriptor descriptor;

    /**
     * Constructs an {@code EditCompanyDescriptorBuilder}.
     */
    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    /**
     * Constructs an {@code EditCompanyDescriptorBuilder} with the given {@code EditCompanyDescriptor}.
     *
     * @param descriptor The details to edit the company with.
     */
    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Constructs an {@code EditCompanyDescriptor} with fields containing {@code companyItem}'s details.
     *
     * @param companyItem The company whose details will be used in the created descriptor.
     */
    public EditCompanyDescriptorBuilder(CompanyItem companyItem) {
        descriptor = new EditCompanyDescriptor();
        descriptor.setAddress(companyItem.getAddress());
        descriptor.setEmail(companyItem.getEmail());
        descriptor.setName(companyItem.getCompanyName());
        descriptor.setPhone(companyItem.getPhone());
        descriptor.setIndustries(companyItem.getIndustries());
    }

    /**
     * Sets the {@code Address} of the {@code EditCompanyDescriptor} that we are building.
     *
     * @param address The address to set.
     * @return The builder with the descriptor's address set.
     */
    public EditCompanyDescriptorBuilder withAddress(String address) {
        requireNonNull(address);
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditCompanyDescriptor} that we are building.
     *
     * @param companyName The company name to set.
     * @return The builder with the descriptor's company name set.
     */
    public EditCompanyDescriptorBuilder withCompanyName(String companyName) {
        requireNonNull(companyName);
        descriptor.setName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCompanyDescriptor} that we are building.
     *
     * @param email The email to set.
     * @return The builder with the descriptor's email set.
     */
    public EditCompanyDescriptorBuilder withEmail(String email) {
        requireNonNull(email);
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCompanyDescriptor} that we are building.
     *
     * @param phone The phone to set.
     * @return The builder with the descriptor's phone set.
     */
    public EditCompanyDescriptorBuilder withPhone(String phone) {
        requireNonNull(phone);
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Industry} set of the {@code EditCompanyDescriptor} that we are building.
     *
     * @param industries The industries to set.
     * @return The builder with the descriptor's industries set.
     */
    public EditCompanyDescriptorBuilder withIndustries(String... industries) {
        requireNonNull(industries);
        descriptor.setIndustries(getIndustrySet(industries));
        return this;
    }

    /**
     * Returns the descriptor.
     *
     * @return The descriptor.
     */
    public EditCompanyDescriptor build() {
        return descriptor;
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
