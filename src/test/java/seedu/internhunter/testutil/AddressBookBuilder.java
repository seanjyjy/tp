package seedu.internhunter.testutil;

import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ItemList<Person> addressBook;

    public AddressBookBuilder() {
        addressBook = new ItemList<>();
    }

    public AddressBookBuilder(ItemList<Person> addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addItem(person);
        return this;
    }

    public ItemList<Person> build() {
        return addressBook;
    }
}
