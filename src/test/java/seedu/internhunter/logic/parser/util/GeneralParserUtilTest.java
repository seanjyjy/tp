package seedu.internhunter.logic.parser.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.parser.exceptions.ParseException;

public class GeneralParserUtilTest {

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, GeneralParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, GeneralParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, () -> GeneralParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_INVALID_INDEX, () -> GeneralParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

}
