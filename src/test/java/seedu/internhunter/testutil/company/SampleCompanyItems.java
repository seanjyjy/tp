package seedu.internhunter.testutil.company;

import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GARENA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GARENA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GARENA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_BANKING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_CLOUD_COMPUTING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GARENA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GOLDMAN;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GOOGLE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_COMPANY_NAME_GOLDMAN;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_BA;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_SWE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_BA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;

/**
 * A utility class containing a list of {@code CompanyItem} objects to be used in tests.
 */
public abstract class SampleCompanyItems {

    public static final CompanyItem GOOGLE = new CompanyItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
            .withPhone(VALID_PHONE_GOOGLE)
            .withEmail(VALID_EMAIL_GOOGLE)
            .withAddress(VALID_ADDRESS_GOOGLE)
            .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE, VALID_INDUSTRY_CLOUD_COMPUTING)
            .build();
    public static final CompanyItem GOLDMAN = new CompanyItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GOLDMAN)
            .withPhone(VALID_PHONE_GOLDMAN)
            .withEmail(VALID_EMAIL_GOLDMAN)
            .withAddress(VALID_ADDRESS_GOLDMAN)
            .withIndustries(VALID_INDUSTRY_BANKING)
            .withInternships(GOLDMAN_BA)
            .build();
    public static final CompanyItem FACEBOOK = new CompanyItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
            .withPhone(VALID_PHONE_FACEBOOK)
            .withEmail(VALID_EMAIL_FACEBOOK)
            .withAddress(VALID_ADDRESS_FACEBOOK)
            .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE, VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
            .withInternships(FACEBOOK_BA, FACEBOOK_FE, FACEBOOK_SWE)
            .build();
    public static final CompanyItem GARENA = new CompanyItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_GARENA)
            .withPhone(VALID_PHONE_GARENA)
            .withEmail(VALID_EMAIL_GARENA)
            .withAddress(VALID_ADDRESS_GARENA)
            .build();
    public static final CompanyItem AMAZON = new CompanyItemBuilder()
            .withCompanyName(VALID_COMPANY_NAME_AMAZON)
            .withPhone(VALID_PHONE_AMAZON)
            .withEmail(VALID_EMAIL_AMAZON)
            .withAddress(VALID_ADDRESS_AMAZON)
            .withIndustries(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
            .build();

    /**
     * Returns an {@code ItemList<CompanyItem>} with some sample company items.
     *
     * @return An {@code ItemList<CompanyItem>} with some sample company items.
     */
    public static ItemList<CompanyItem> getSampleCompanyList() {
        ItemList<CompanyItem> companyItemList = new ItemList<>();
        for (CompanyItem companyItem : getCompanyItems()) {
            companyItemList.addItem(companyItem);
        }
        return companyItemList;
    }

    /**
     * Returns a {@code List<CompanyItem>} with some sample company items.
     *
     * @return A {@code List<CompanyItem>} with some sample company items.
     */
    public static List<CompanyItem> getCompanyItems() {
        return new ArrayList<>(Arrays.asList(new CompanyItemBuilder(GOOGLE).build(),
                new CompanyItemBuilder(GOLDMAN).build(), new CompanyItemBuilder(FACEBOOK).build()));
    }
}
