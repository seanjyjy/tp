package seedu.internhunter.model.tab;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.ui.tabs.TabName;

public class TabManagerTest {

    private TabManager tabManager;

    @BeforeEach
    public void setUp() {
        tabManager = new TabManager();
    }

    @Test
    public void setTabName_true_success() {
        // default tab state for tab manager is at Company
        TabManager tabManagerCompanyTab = new TabManager();
        tabManagerCompanyTab.setTabName(TabName.COMPANY);
        assertTrue(tabManagerCompanyTab.equals(tabManager));
    }

    @Test
    public void setTabName_false_success() {
        // default tab state for tab manager is at Company

        // tab manager state for tab is now at profile
        TabManager tabManagerProfileTab = new TabManager();
        tabManagerProfileTab.setTabName(TabName.PROFILE);
        assertFalse(tabManagerProfileTab.equals(tabManager));

        // tab manager state for tab is now at profile
        TabManager tabManagerApplicationTab = new TabManager();
        tabManagerApplicationTab.setTabName(TabName.APPLICATION);
        assertFalse(tabManagerApplicationTab.equals(tabManager));
    }

    @Test
    public void getTabName_true_success() {
        TabManager tabManagerCompanyTab = new TabManager();
        tabManagerCompanyTab.setTabName(TabName.COMPANY);
        assertTrue(tabManagerCompanyTab.getTabName().equals(TabName.COMPANY));

        TabManager tabManagerProfileTab = new TabManager();
        tabManagerProfileTab.setTabName(TabName.PROFILE);
        assertTrue(tabManagerProfileTab.getTabName().equals(TabName.PROFILE));

        TabManager tabManagerApplicationTab = new TabManager();
        tabManagerApplicationTab.setTabName(TabName.APPLICATION);
        assertTrue(tabManagerApplicationTab.getTabName().equals(TabName.APPLICATION));
    }

    @Test
    public void getTabName_false_success() {
        TabManager tabManagerCompanyTab = new TabManager();
        tabManagerCompanyTab.setTabName(TabName.COMPANY);
        assertFalse(tabManagerCompanyTab.getTabName().equals(TabName.PROFILE));
        assertFalse(tabManagerCompanyTab.getTabName().equals(TabName.APPLICATION));

        TabManager tabManagerProfileTab = new TabManager();
        tabManagerProfileTab.setTabName(TabName.PROFILE);
        assertFalse(tabManagerProfileTab.getTabName().equals(TabName.COMPANY));
        assertFalse(tabManagerProfileTab.getTabName().equals(TabName.APPLICATION));

        TabManager tabManagerApplicationTab = new TabManager();
        tabManagerApplicationTab.setTabName(TabName.APPLICATION);
        assertFalse(tabManagerApplicationTab.getTabName().equals(TabName.PROFILE));
        assertFalse(tabManagerApplicationTab.getTabName().equals(TabName.COMPANY));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(tabManager.equals(tabManager));

        // null -> returns false
        assertFalse(tabManager.equals(null));

        // different types -> returns false
        assertFalse(tabManager.equals(0.5f));
    }
}
