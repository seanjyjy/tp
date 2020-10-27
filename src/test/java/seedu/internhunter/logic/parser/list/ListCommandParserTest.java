package seedu.internhunter.logic.parser.list;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.PROFILE_ALIAS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.list.ListApplicationCommand;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * todo javadocs
 */
public class ListCommandParserTest {

    private ListCommandParser listCommandParser;

    @BeforeEach
    public void setUp() {
        listCommandParser = new ListCommandParser();
    }

    // todo shawn

    // todo isaac
    // Note can just write inside these 4 methods, if i do forget any test case do inform me.

    @Test
    public void parse_invalidTypes_throwsParseException() {
        assertThrows(ParseException.class, () -> listCommandParser.parse("hello"));
        assertThrows(ParseException.class, () -> listCommandParser.parse("1"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(""));
    }

    @Test
    public void parse_listAppTrue_success() throws ParseException {
        assertTrue(listCommandParser.parse(APPLICATION_ALIAS) instanceof ListApplicationCommand);
    }

    @Test
    public void parse_listAppFalse_success() throws ParseException {
        assertFalse(listCommandParser.parse(COMPANY_ALIAS) instanceof ListApplicationCommand);
        assertFalse(listCommandParser.parse(PROFILE_ALIAS) instanceof ListApplicationCommand);
    }

    @Test
    public void parse_listExcessInput_throwsParseException() {
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + APPLICATION_ALIAS + "great"));
        assertThrows(ParseException.class, () -> listCommandParser.parse(" " + APPLICATION_ALIAS + "1"));
    }

}
