package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.application.ApplicationItem;
import seedu.address.model.application.InternshipStatus;
import seedu.address.model.application.StatusDate;
import seedu.address.model.company.Address;
import seedu.address.model.company.CompanyItem;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.Email;
import seedu.address.model.company.Industry;
import seedu.address.model.company.Phone;
import seedu.address.model.internship.InternshipItem;
import seedu.address.model.internship.JobTitle;
import seedu.address.model.internship.Period;
import seedu.address.model.internship.Requirement;
import seedu.address.model.internship.Wage;
import seedu.address.model.profile.Descriptor;
import seedu.address.model.profile.ProfileItem;
import seedu.address.model.profile.ProfileItemType;

public class TempStubData {

    ObservableList<CompanyItem> filteredCompanyItemList;
    ObservableList<ApplicationItem> filteredApplicationItemList;
    ObservableList<ProfileItem> filteredProfileItemList;
    
    TempStubData() {
        initializeAppItem();
        initializeCompanyItem();
        initializeProfileItem();
    }
    
    void initializeAppItem() {
        filteredApplicationItemList = FXCollections.observableArrayList();
        filteredCompanyItemList = FXCollections.observableArrayList();
        filteredProfileItemList = FXCollections.observableArrayList();
        for (int i = 0; i < 5; i++) {
            InternshipStatus internshipStatus = getRandomStatus();
            StatusDate statusDate = getRandomStatusDate();
            InternshipItem internshipItem = getRandomInternshipItem();
            ApplicationItem applicationItem = new ApplicationItem(internshipItem, internshipStatus, statusDate);
            filteredApplicationItemList.add(applicationItem);
        }
    }

    void initializeCompanyItem() {
        for (int i = 0; i < 5; i++) {
            CompanyItem companyItem = makeCompanyItem();
            filteredCompanyItemList.add(companyItem);
        }
    }

    void initializeProfileItem() {
        for (int i = 0; i < 5; i++) {
            ProfileItem profileItem = makeProfileItem();
            filteredProfileItemList.add(profileItem);
        }
    }
    
    public ProfileItem makeProfileItem() {
        int num = (int) (Math.random() * (5));
        ProfileItem profileItem;
        String title;
        ProfileItemType profileItemType;
        Set<Descriptor> d;
        ArrayList<Descriptor> ard;
        Descriptor d1;
        Descriptor d2;
        switch (num) {
        case (0):
            profileItemType = ProfileItemType.EXPERIENCE;
            title = "Hello world";
            d1 = new Descriptor("HTML");
            d2 = new Descriptor("CSS");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        case (1):
            profileItemType = ProfileItemType.ACHIEVEMENT;
            title = "Bye world";
            d1 = new Descriptor("React");
            d2 = new Descriptor("ReactNative");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        case (2):
            profileItemType = ProfileItemType.SKILL;
            title = "good morning";
            d1 = new Descriptor("ahahahah");
            d2 = new Descriptor("hahahah");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        case (3):
            profileItemType = ProfileItemType.EXPERIENCE;
            title = "good night";
            d1 = new Descriptor("bebe");
            d2 = new Descriptor("aeae");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        case (4):
            profileItemType = ProfileItemType.ACHIEVEMENT;
            title = "zzzz world";
            d1 = new Descriptor("zz");
            d2 = new Descriptor("zzzz");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        case (5):
            profileItemType = ProfileItemType.SKILL;
            title = "aaa";
            d1 = new Descriptor("aaaaaaa");
            d2 = new Descriptor("Caaaa");
            ard = new ArrayList<>(List.of(d1, d2));
            d = new HashSet<>(ard);
            profileItem = new ProfileItem(title, profileItemType, d);
            break;
        default:
            profileItem = null;
        }
        return profileItem;
    }
    
    public CompanyItem makeCompanyItem() {
        int num = (int) (Math.random() * (6));
        CompanyItem companyItem;
        CompanyName c;
        Phone p;
        Email e;
        Address a;
        Set<Industry> industry;
        ArrayList<Industry> i;
        Industry i1;
        Industry i2;
        List<InternshipItem> internshipItem = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
            internshipItem.add(getRandomInternshipItem());
        }
        switch (num) {
        case (0):
            c = new CompanyName("Google");
            p = new Phone("99999991");
            e = new Email("www.abc@gmail.com");
            a = new Address("tampines 123");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        case (1):
            c = new CompanyName("Facebook");
            p = new Phone("123123123");
            e = new Email("www.abc123123@gmail.com");
            a = new Address("tampines 321");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        case (2):
            c = new CompanyName("Shopee");
            p = new Phone("321321321");
            e = new Email("www.321321231@gmail.com");
            a = new Address("tampines 321123");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        case (3):
            c = new CompanyName("Amazon");
            p = new Phone("1111222333");
            e = new Email("www.abc11122233333@gmail.com");
            a = new Address("bedok 123");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        case (4):
            c = new CompanyName("Netflix");
            p = new Phone("5361712314");
            e = new Email("www.abc@gmail.com");
            a = new Address("bedok 321");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        case (5):
            c = new CompanyName("Apple");
            p = new Phone("9999999191");
            e = new Email("www.abc1111122233334444@gmail.com");
            a = new Address("nus 123");
            i1 = new Industry("Software");
            i2 = new Industry("Hardware");
            i = new ArrayList<>(List.of(i1, i2));
            industry = new HashSet<>(i);
            companyItem = new CompanyItem(c, p, e, a, industry, internshipItem);
            break;
        default:
            companyItem = null;
        }
        return companyItem;
    }


    // TEMPORARY FUNCTION TO ALLOW DISPLAYS
    private InternshipStatus getRandomStatus() {
        int num = (int) (Math.random() * (6));
        InternshipStatus internshipStatus;
        switch (num) {
        case (0):
            internshipStatus = InternshipStatus.APPLIED;
            break;
        case (1):
            internshipStatus = InternshipStatus.INTERVIEW;
            break;
        case (2):
            internshipStatus = InternshipStatus.WAITING;
            break;
        case (3):
            internshipStatus = InternshipStatus.REJECTED;
            break;
        case (4):
            internshipStatus = InternshipStatus.OFFERED;
            break;
        case (5):
            internshipStatus = InternshipStatus.ACCEPTED;
            break;
        default:
            internshipStatus = null;
        }
        return internshipStatus;
    }

    private StatusDate getRandomStatusDate() {
        int num = (int) (Math.random() * (6));
        StatusDate statusDate;
        switch (num) {
        case (0):
            statusDate = new StatusDate(LocalDateTime.of(2017, 11, 10, 12,35));
            break;
        case (1):
            statusDate = new StatusDate(LocalDateTime.of(2018, 12, 1, 11,35));
            break;
        case (2):
            statusDate = new StatusDate(LocalDateTime.of(2019, 1, 2, 9,35));
            break;
        case (3):
            statusDate = new StatusDate(LocalDateTime.of(2020, 3, 7, 7,35));
            break;
        case (4):
            statusDate = new StatusDate(LocalDateTime.of(2016, 7, 12, 5,35));
            break;
        case (5):
            statusDate = new StatusDate(LocalDateTime.of(2015, 9, 5, 3,35));
            break;
        default:
            statusDate = null;
        }
        return statusDate;
    }

    private InternshipItem getRandomInternshipItem() {
        int num = (int) (Math.random() * (6));
        InternshipItem internshipItem;
        CompanyName c;
        JobTitle j;
        Period p;
        Wage w;
        Requirement r1;
        Requirement r2;
        Requirement r3;
        Requirement r4;
        List<Requirement> lr;
        Set<Requirement> r;
        switch (num) {
        case (0):
            c = new CompanyName("Google");
            j = new JobTitle("Software Engineer");
            p = new Period("2011/10/11 - 2011/10/12");
            w = new Wage("3000");
            r1 = new Requirement("HTML");
            r2 = new Requirement("CSS");
            r3 = new Requirement("React Native");
            r4 = new Requirement("Angular");
            lr = new ArrayList<>(List.of(r1, r2 ,r3 ,r4));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        case (1):
            c = new CompanyName("Facebook");
            j = new JobTitle("Front end");
            p = new Period("2012/11/12 - 2012/11/25");
            w = new Wage("7000");
            r1 = new Requirement("Java");
            r2 = new Requirement("Javascript");
            lr = new ArrayList<>(List.of(r1, r2));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        case (2):
            c = new CompanyName("Shopee");
            j = new JobTitle("Software Engineer");
            p = new Period("2014/7/7 - 2015/10/12");
            w = new Wage("5000");
            r1 = new Requirement("Java");
            r2 = new Requirement("Javascript");
            r3 = new Requirement("React Native");
            r4 = new Requirement("Angular");
            lr = new ArrayList<>(List.of(r1, r2 ,r3 ,r4));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        case (3):
            c = new CompanyName("Amazon");
            j = new JobTitle("Software Engineer");
            p = new Period("2010/10/11 - 2010/10/12");
            w = new Wage("8000");
            r1 = new Requirement("HTML");
            r2 = new Requirement("CSS");
            lr = new ArrayList<>(List.of(r1, r2));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        case (4):
            c = new CompanyName("Netflix");
            j = new JobTitle("Back end");
            p = new Period("2011/10/11 - 2011/10/12");
            w = new Wage("10000");
            r1 = new Requirement("C");
            r2 = new Requirement("Java");
            r3 = new Requirement("React");
            r4 = new Requirement("Python");
            lr = new ArrayList<>(List.of(r1, r2 ,r3 ,r4));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        case (5):
            c = new CompanyName("Apple");
            j = new JobTitle("Full stack developer");
            p = new Period("2019/10/11 - 2019/10/12");
            w = new Wage("1000000");
            r1 = new Requirement("Objective C");
            r2 = new Requirement("React");
            r3 = new Requirement("React Native");
            lr = new ArrayList<>(List.of(r1, r2 ,r3));
            r = new HashSet<>(lr);
            internshipItem = new InternshipItem(c, j, p, w , r);
            break;
        default:
            internshipItem = null;
        }
        return internshipItem;
    }
    
}