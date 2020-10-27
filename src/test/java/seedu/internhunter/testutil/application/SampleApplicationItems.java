package seedu.internhunter.testutil.application;

import static seedu.internhunter.model.util.StatusUtil.ACCEPTED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.OFFERED_KEYWORD;
import static seedu.internhunter.model.util.StatusUtil.REJECTED_KEYWORD;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.STATUS_DATE_JUNE_2022;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_BA;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.LAZADA_DS;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.SHOPEE_SWE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.item.ItemList;

public abstract class SampleApplicationItems {

    public static final ApplicationItem GOLDMAN_OFFERED = new ApplicationItemBuilder()
            .withInternshipItem(GOLDMAN_BA)
            .withStatus(OFFERED_KEYWORD)
            .withStatusDate(STATUS_DATE_JUNE_2022)
            .build();
    public static final ApplicationItem SHOPEE_OFFERED = new ApplicationItemBuilder()
            .withInternshipItem(SHOPEE_SWE)
            .withStatus(OFFERED_KEYWORD)
            .withStatusDate(STATUS_DATE_JUNE_2021)
            .build();
    public static final ApplicationItem LAZADA_REJECTED = new ApplicationItemBuilder()
            .withInternshipItem(LAZADA_DS)
            .withStatus(REJECTED_KEYWORD)
            .withStatusDate(STATUS_DATE_JUNE_2021)
            .build();

    // Manually added
    public static final ApplicationItem FACEBOOK_ACCEPTED = new ApplicationItemBuilder()
            .withInternshipItem(FACEBOOK_FE)
            .withStatus(ACCEPTED_KEYWORD)
            .withStatusDate(STATUS_DATE_JUNE_2022)
            .build();

    /**
     * Returns an {@code ItemList<ApplicationItem>} with all the sample application items.
     */
    public static ItemList<ApplicationItem> getSampleApplicationItemList() {
        ItemList<ApplicationItem> applicationItemList = new ItemList<>();
        for (ApplicationItem applicationItem : getApplicationItems()) {
            applicationItemList.addItem(applicationItem);
        }
        return applicationItemList;
    }

    private static List<ApplicationItem> getApplicationItems() {
        return new ArrayList<>(Arrays.asList(GOLDMAN_OFFERED, SHOPEE_OFFERED, LAZADA_REJECTED));
    }
}
