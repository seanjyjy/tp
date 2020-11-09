---
layout: page
title: Keane Chan Jun Yu - Project Portfolio
---

InternHunter is a CLI-centric desktop application which aids university students in applying for tech internships.
It lets users manage their own customisable collection of companies, internships, internship applications and their 
own user profile, so that they can keep track of internships that they are interested in.

It has a GUI created with JavaFX. It is written in Java, and has about 35k LoC.

**Summary of contributions**

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=keanecjy)

* **Features and Enhancements implemented:**
  * Match Command (Pull request [\#217](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/217))
    * What it does: Generates a list of matching internships.
    * Highlights: A key feature in our application, this command was the most challenging command to implement in our
    application since it does not only deal with CRUD features. This command filters down the list of
    internships that matches the users' skills in the profile. Implementation of this command required a thorough
    consideration of design alternatives, since it required both the company and profile lists to interact with
    each other. Keen insight on the types of algorithms needed was also required to allow more accurate matching.
  * Delete an internship (Pull request [\#140](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/140))
    * What it does: Deletes an internship from the internship list.
    * Highlights: This command was particularly tricky to implement, since deleting an internship would mean that
    any application linked to this internship has to be deleted as well. Difficulty comes in since internship does not
    have any association nor dependency to the application item. Therefore, the deletion has to be done through the
    model instead and there is a need to create additional methods to delete an application that corresponds to this
    internship.
  * Apply for an internship (Pull request [\#132](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/132))
    * What it does: Selects an internship from a company and adds it to the list of applications.
    * Highlights: This command required careful parsing of the input since an internship can only be accessed
    through a company in the company list. Thus double indexing was required for the user to reference the internship
    and add it into the application list. Moreover, all internship applications also come with a date, which required
    proper parsing so that user matches to the date formats accepted in our app.
  * Added Item, ItemList and UniqueItemList classes (Pull request [\#98](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/98))
    * Justification: By adding the idea of a general Item class and the generic Item lists, it paved the way for the
    general code structure of our application, as this helped to greatly reduce code duplication and allowed for smooth
    interaction between the 4 data types in the model, logic and storage component.
  * View an application (Pull request [\#152](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/152))
    * What it does: Allows the user to view an application in full detail
  * Delete an application (Pull request [\#140](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/140))
  * Update an application (Pull request [\#152](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/152))
  * Created and maintained the ApplicationItem and InternshipItem classes and fields in the model. (Pull request [\#98](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/98))
  * Refactor the code to match application (Pull request [\#284](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/284))
  * Created general wrapper regex classes for items in the model (Pull request [\#126](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/126))
  * Created GeneralParserUtil to hold common parsing methods for all classes (Pull request [\#152](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/152))
  * Contributed significantly to improving the test coverage (Pull requests 
        [\#156](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/156), [\#169](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/169),
        [\#180](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/180), [\#192](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/192),
        [\#220](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/220), [\#341](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/341)
        [\#355](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/355))

- **Documentation:**
    - User Guide:
        - Added documentation for all commands in application, i.e. add, delete, edit, view, find, list. (Pull requests:
        [\#78](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/78), [\#125](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/125))
        - Added documentation for match internships command (Pull requests: [\#282](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/282))
    - Developer Guide:
        - Added implementation details and UML diagrams for Command implementation (Pull request [\#214](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/214))
        - Added implementation details and UML diagrams for Logic component (Pull request [\#276](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/276))
        - Added implementation details and UML diagrams for Match Command feature (Pull request [\#282](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/282))
        - Added glossary section and some use cases (Pull request [\#85](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/85))

* **Team-based tasks**:
  * Update readme and landing page (Pull requests [\#81](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/81),
  [\#162](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/162))
  * Setup codecov for the team so that we can track our code coverage and work on remaining tests 
  (Pull request [\#60](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/60), [\#357](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/357))
  * Setup codacy for the team so that we can track our code quality 
  (Pull request [\#397](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/397))
  * Managed the issue tracker ([Link to the 70 issues I created](https://github.com/AY2021S1-CS2103T-T15-4/tp/issues?q=is%3Aissue+author%3Akeanecjy)) and milestones on GitHub

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#79](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/79),
  [\#92](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/92), [\#97](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/97),
  [\#123](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/123), [\#142](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/142),
  [\#159](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/159), [\#161](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/161),
  [\#178](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/178), [\#190](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/190),
  [\#256](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/256), [\#258](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/258),
  [\#277](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/277), [\#373](https://github.com/AY2021S1-CS2103T-T15-4/tp/pull/373)
  * All other PRs reviewed: [Link to the other 40+ PRs reviewed](https://github.com/AY2021S1-CS2103T-T15-4/tp/pulls?q=is%3Apr+reviewed-by%3Akeanecjy+is%3Aclosed+sort%3Acomments-asc)
  * Reported bugs and suggestions for other teams in the class: [1](https://github.com/keanecjy/ped/issues/1),
  [2](https://github.com/keanecjy/ped/issues/2), [3](https://github.com/keanecjy/ped/issues/3),
  [4](https://github.com/keanecjy/ped/issues/4), [5](https://github.com/keanecjy/ped/issues/5),
  [6](https://github.com/keanecjy/ped/issues/6), [7](https://github.com/keanecjy/ped/issues/7),
  [8](https://github.com/keanecjy/ped/issues/8), [9](https://github.com/keanecjy/ped/issues/9),
  [10](https://github.com/keanecjy/ped/issues/10), [11](https://github.com/keanecjy/ped/issues/9)

## Links to sections I contributed
- User Guide:
    - Added [documentation](https://ay2021s1-cs2103t-t15-4.github.io/tp/UserGuide.html#application) for all commands in application, i.e. add, delete, edit, view, find, list
    - Added [documentation](https://ay2021s1-cs2103t-t15-4.github.io/tp/UserGuide.html#generating-matching-internships-match) for match internships command
- Developer Guide:
    - Added UML and documentation for [Logic component](https://ay2021s1-cs2103t-t15-4.github.io/tp/DeveloperGuide.html#logic-component),
    [Implementation of Command classes](https://ay2021s1-cs2103t-t15-4.github.io/tp/DeveloperGuide.html#implementation-of-command-classes), 
    [Match Command feature](https://ay2021s1-cs2103t-t15-4.github.io/tp/DeveloperGuide.html#match-command-feature),
    [Glossary](https://ay2021s1-cs2103t-t15-4.github.io/tp/DeveloperGuide.html#appendix-e-glossary)
