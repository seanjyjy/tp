package seedu.internhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Application;

public class AppParametersTest {

    private ParametersStub parametersStub;
    private AppParameters expected;

    @BeforeEach
    public void setUp() {
        parametersStub = new ParametersStub();
        expected = new AppParameters();
    }

    @Test
    public void parse_validConfigPath_success() {
        parametersStub.namedParameters.put("config", "config.json");
        expected.setConfigPath(Paths.get("config.json"));
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_nullConfigPath_success() {
        parametersStub.namedParameters.put("config", null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void parse_invalidConfigPath_success() {
        parametersStub.namedParameters.put("config", "a\0");
        expected.setConfigPath(null);
        assertEquals(expected, AppParameters.parse(parametersStub));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(expected.equals(expected));

        // null -> returns false
        assertFalse(expected.equals(null));

        // different types -> returns false
        assertFalse(expected.equals(0.5f));
    }

    @Test
    public void hashCode_equalHashCode_success() {
        // set json
        expected.setConfigPath(Paths.get("config.json"));
        parametersStub.namedParameters.put("config", "config.json");
        assertEquals(expected.hashCode(), AppParameters.parse(parametersStub).hashCode());
    }

    @Test
    public void hashCode_unEqualHashCode_success() {
        // set json
        expected.setConfigPath(Paths.get("config123.json"));
        parametersStub.namedParameters.put("config", "config.json");
        assertNotEquals(expected.hashCode(), AppParameters.parse(parametersStub).hashCode());
    }

    private static class ParametersStub extends Application.Parameters {
        private Map<String, String> namedParameters = new HashMap<>();

        @Override
        public List<String> getRaw() {
            throw new AssertionError("should not be called");
        }

        @Override
        public List<String> getUnnamed() {
            throw new AssertionError("should not be called");
        }

        @Override
        public Map<String, String> getNamed() {
            return Collections.unmodifiableMap(namedParameters);
        }
    }
}
