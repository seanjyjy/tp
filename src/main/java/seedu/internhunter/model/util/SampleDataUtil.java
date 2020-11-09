package seedu.internhunter.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.application.StatusDate;
import seedu.internhunter.model.company.Address;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyName;
import seedu.internhunter.model.company.Email;
import seedu.internhunter.model.company.Industry;
import seedu.internhunter.model.company.Phone;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.Descriptor;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemCategory;
import seedu.internhunter.model.profile.Title;

/**
 * Contains utility methods for populating {@code InternHunter} with sample data.
 */
public class SampleDataUtil {

    public static final InternshipItem GOLDMAN_BA = new InternshipItem(new CompanyName("Goldman Sachs"),
        new JobTitle("Business Analyst"), new Period("Summer break"), new Wage("4000"), getRequirementSet());

    public static final InternshipItem GOLDMAN_FE = new InternshipItem(new CompanyName("Goldman Sachs"),
        new JobTitle("Front End Engineer"), new Period("May to July"), new Wage("3000"), getRequirementSet());

    public static final InternshipItem SHOPEE_SWE = new InternshipItem(new CompanyName("Shopee"),
        new JobTitle("Software Engineer"), new Period("2 months"), new Wage("2000"),
        getRequirementSet("React Native"));

    public static final InternshipItem LAZADA_DS = new InternshipItem(new CompanyName("Lazada"),
        new JobTitle("Data Scientist"), new Period("3 months"), new Wage("3000"),
        getRequirementSet("Python", "TensorFlow"));

    public static final InternshipItem FACEBOOK_FE = new InternshipItem(new CompanyName("Facebook"),
        new JobTitle("Front End Engineer"), new Period("May to July"), new Wage("3500"),
        getRequirementSet("CSS", "HTML"));

    public static final InternshipItem FACEBOOK_SWE = new InternshipItem(new CompanyName("Facebook"),
        new JobTitle("Software Engineer"), new Period("Summer break"), new Wage("4000"),
        getRequirementSet("React", "JavaScript"));

    public static final InternshipItem FACEBOOK_BA = new InternshipItem(new CompanyName("Facebook"),
        new JobTitle("Business Analyst"), new Period("Summer break"), new Wage("3000"), getRequirementSet());

    public static final InternshipItem GOOGLE_SWE = new InternshipItem(new CompanyName("Google"),
        new JobTitle("Software Engineer"), new Period("Summer break"), new Wage("5000"), getRequirementSet());

    private static ApplicationItem[] getSampleApplicationItems() {
        return new ApplicationItem[]{
            new ApplicationItem(GOLDMAN_BA, Status.OFFERED, new StatusDate(DateUtil.getTodayDate())),
            new ApplicationItem(SHOPEE_SWE, Status.OFFERED,
                new StatusDate(DateUtil.convertToDateTime("11-10-21"))),
            new ApplicationItem(LAZADA_DS, Status.REJECTED,
                new StatusDate(DateUtil.convertToDateTime("21-09-21"))),
            new ApplicationItem(FACEBOOK_FE, Status.ACCEPTED,
                new StatusDate(DateUtil.convertToDateTime("2-11-21"))),
        };
    }

    /**
     * Returns a sample application item list.
     *
     * @return application item list
     */
    public static ItemList<ApplicationItem> getSampleApplicationItemList() {
        ItemList<ApplicationItem> sampleItemList = new ItemList<>();
        for (ApplicationItem sampleApplicationItem : getSampleApplicationItems()) {
            sampleItemList.addItem(sampleApplicationItem);
        }
        return sampleItemList;
    }

    private static CompanyItem[] getSampleCompanyItems() {
        return new CompanyItem[]{
            new CompanyItem(new CompanyName("Goldman Sachs"), new Phone("69792525"),
                new Email("GoldmanSachsHires@gms.com"), new Address("60 Anson Rd, #14-01 Mapletree Anson"),
                getIndustrySet("Banking"), getInternshipList(GOLDMAN_BA, GOLDMAN_FE)),
            new CompanyItem(new CompanyName("Shopee"), new Phone("62066610"),
                new Email("ShopeeHR@shopee.com"), new Address("5 Science Park Dr"),
                getIndustrySet("Marketplace"), getInternshipList(SHOPEE_SWE)),
            new CompanyItem(new CompanyName("Garena"), new Phone("986876587"),
                new Email("GarenaHires@sea.com"), new Address("201 Victoria St"),
                getIndustrySet("Online Gaming"), getInternshipList()),
            new CompanyItem(new CompanyName("Lazada"), new Phone("65738913"), new Email("hiring@lazada.com"),
                new Address("8 Shenton Way"), getIndustrySet("Marketplace"), getInternshipList(LAZADA_DS)),
            new CompanyItem(new CompanyName("Facebook"), new Phone("63574902"), new Email("recruit@facebook.com"),
                new Address("9 Straits View"), getIndustrySet(),
                getInternshipList(FACEBOOK_BA, FACEBOOK_FE, FACEBOOK_SWE)),
            new CompanyItem(new CompanyName("Google"), new Phone("6328571"), new Email("hr@google.com"),
                new Address("70 Pasir Panjang Rd"), getIndustrySet("Cloud Computing",
                "Artificial Intelligence"), getInternshipList(GOOGLE_SWE))
        };
    }

    /**
     * Returns a sample company item list.
     *
     * @return company item list
     */
    public static ItemList<CompanyItem> getSampleCompanyItemList() {
        ItemList<CompanyItem> sampleItemList = new ItemList<>();
        for (CompanyItem sampleCompanyItem : getSampleCompanyItems()) {
            sampleItemList.addItem(sampleCompanyItem);
        }
        return sampleItemList;
    }

    private static ProfileItem[] getSampleProfileItems() {
        return new ProfileItem[]{
            new ProfileItem(new Title("HTML"), ProfileItemCategory.SKILL,
                getDescriptorSet("Learn to create divs.", "learn to create tables.")),
            new ProfileItem(new Title("Won second place in Code Jam"), ProfileItemCategory.ACHIEVEMENT,
                getDescriptorSet("Solved the Board Meeting Question.",
                    "Solved the Jungle Struggle question.")),
            new ProfileItem(new Title("Internship with Govtech"), ProfileItemCategory.EXPERIENCE,
                getDescriptorSet("Implemented DevOps solution to speed up automated testing by 50%",
                    "Developed dashboard to keep track of key performance metrics",
                    "Built internal tooling for syncing data with remote servers")),
            new ProfileItem(new Title("GraphQL"), ProfileItemCategory.SKILL, getDescriptorSet()),
            new ProfileItem(new Title("Contribute to NUSMODS"), ProfileItemCategory.EXPERIENCE,
                getDescriptorSet("Fix UI bugs in Modules View")),
            new ProfileItem(new Title("Participation in MS OpenHack"), ProfileItemCategory.SKILL,
                getDescriptorSet("Solve the Agriculture challenge with satellite imagery")),
        };
    }

    /**
     * Returns a sample profile item list.
     *
     * @return profile item list
     */
    public static ItemList<ProfileItem> getSampleProfileItemList() {
        ItemList<ProfileItem> sampleItemList = new ItemList<>();
        for (ProfileItem sampleProfileItem : getSampleProfileItems()) {
            sampleItemList.addItem(sampleProfileItem);
        }
        return sampleItemList;
    }

    /**
     * Returns a requirement set containing the list of strings given.
     */
    public static Set<Requirement> getRequirementSet(String... strings) {
        return Arrays.stream(strings)
            .map(Requirement::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns an industry set containing the list of strings given.
     */
    public static Set<Industry> getIndustrySet(String... strings) {
        return Arrays.stream(strings)
            .map(Industry::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns an internship set containing the list of strings given.
     */
    public static List<InternshipItem> getInternshipList(InternshipItem... items) {
        return Arrays.stream(items)
            .collect(Collectors.toList());
    }

    /**
     * Returns a descriptor set containing the list of strings given.
     */
    public static Set<Descriptor> getDescriptorSet(String... strings) {
        return Arrays.stream(strings)
            .map(Descriptor::new)
            .collect(Collectors.toSet());
    }

}
