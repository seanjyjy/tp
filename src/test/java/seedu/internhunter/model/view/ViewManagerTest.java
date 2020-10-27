package seedu.internhunter.model.view;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;

public class ViewManagerTest {

    private ViewManager viewManager;

    @BeforeEach
    public void setUp() {
        viewManager = new ViewManager();
    }

    @Test
    public void setCompanyViewIndex_false_success() {
        // different company index
        ViewManager viewManagerDifferentCompanyIndex = new ViewManager();
        viewManagerDifferentCompanyIndex.setCompanyViewIndex(Index.fromOneBased(3));
        assertFalse(viewManager.equals(viewManagerDifferentCompanyIndex));
    }

    @Test
    public void setCompanyViewIndex_true_success() {
        // different company index
        ViewManager viewManagerDifferentCompanyIndex = new ViewManager();
        viewManagerDifferentCompanyIndex.setCompanyViewIndex(Index.fromOneBased(1));
        assertTrue(viewManager.equals(viewManagerDifferentCompanyIndex));
    }

    @Test
    public void setApplicationViewIndex_false_success() {
        // different company index
        ViewManager viewManagerDifferentApplicationIndex = new ViewManager();
        viewManagerDifferentApplicationIndex.setApplicationViewIndex(Index.fromOneBased(3));
        assertFalse(viewManager.equals(viewManagerDifferentApplicationIndex));
    }

    @Test
    public void setApplicationViewIndex_true_success() {
        // different company index
        ViewManager viewManagerDifferentApplicationIndex = new ViewManager();
        viewManagerDifferentApplicationIndex.setApplicationViewIndex(Index.fromOneBased(1));
        assertTrue(viewManager.equals(viewManagerDifferentApplicationIndex));
    }

    @Test
    public void setProfileViewIndex_false_success() {
        // different company index
        ViewManager viewManagerDifferentProfileIndex = new ViewManager();
        viewManagerDifferentProfileIndex.setProfileViewIndex(Index.fromOneBased(3));
        assertFalse(viewManager.equals(viewManagerDifferentProfileIndex));
    }

    @Test
    public void setProfileViewIndex_true_success() {
        // different company index
        ViewManager viewManagerDifferentProfileIndex = new ViewManager();
        viewManagerDifferentProfileIndex.setProfileViewIndex(Index.fromOneBased(1));
        assertTrue(viewManager.equals(viewManagerDifferentProfileIndex));
    }

    @Test
    public void getCompanyViewIndex_true_success() {
        // default index for CompanyViewIndex is 1
        assertTrue(viewManager.getCompanyViewIndex().equals(Index.fromOneBased(1)));
    }

    @Test
    public void getProfileViewIndex_true_success() {
        // default index for ProfileViewIndex is 1
        assertTrue(viewManager.getProfileViewIndex().equals(Index.fromOneBased(1)));
    }

    @Test
    public void getApplicationViewIndex_true_success() {
        // default index for ApplicationViewIndex is 1
        assertTrue(viewManager.getApplicationViewIndex().equals(Index.fromOneBased(1)));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(viewManager.equals(viewManager));

        // null -> returns false
        assertFalse(viewManager.equals(null));

        // different types -> returns false
        assertFalse(viewManager.equals(0.5f));
    }
}
