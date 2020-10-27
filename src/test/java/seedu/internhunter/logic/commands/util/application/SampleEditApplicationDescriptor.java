package seedu.internhunter.logic.commands.util.application;

import static seedu.internhunter.testutil.application.SampleApplicationItems.GOLDMAN_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;

import seedu.internhunter.logic.commands.edit.EditApplicationCommand.EditApplicationDescriptor;

/**
 * Contains the sample edit application descriptors for the {@code EditApplicationCommand} tests.
 */
public class SampleEditApplicationDescriptor {

    public static final EditApplicationDescriptor DESC_GOLDMAN =
            new EditApplicationDescriptorBuilder(GOLDMAN_OFFERED).build();

    public static final EditApplicationDescriptor DESC_SHOPEE =
            new EditApplicationDescriptorBuilder(SHOPEE_OFFERED).build();

}
