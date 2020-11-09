---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" />

The ***Architecture Diagram*** given above explains the high-level design of InternHunter. Given below is a quick
overview of each component. As seen in the diagram, InternHunter follows a multi-layered architecture whereby
components of lower layers are independent of higher layers. e.g. the `Logic` component can make use of methods in
 the `Model` but not vice versa.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-T15-4/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

<div style="page-break-after: always;"></div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

<div style="page-break-after: always;"></div>

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
 the command `delete me 1` to delete a profile item.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

<div style="page-break-after: always;"></div>

### Ui component

![Structure of the Ui Component](images/UiClassDiagram.png)

<div markdown="span" class="alert alert-ui">
  
  :information_source: <strong>Note:</strong> The class diagram for the Ui is a simplified version of the implementation. Generics are used in the implementation but is omitted in the class diagram.
  
</div>

<div style="page-break-after: always;"></div>

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/ui/Ui.java)

The Ui consists of a `MainWindow` that is made up of parts e.g. `CommandBox`, `ResultDisplay`, `ListPanel`, `InformationDisplay`, `Tabs` etc. 
All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `Tabs` is located at the most left of the application and it consists of three tabs which defines the main three data item `CompanyItem`, `ApplicationItem`, `ProfileItem`. <br/>

The `ListPanel` is located at the center of the application and it consists of Ui `CompanyCard`, `ApplicationCard`, `ProfileCard` to display key information of each data idea. <br/>

The `InformationDisplay` appears at the right of the application and it consists of Ui `CompanyDisplay`, `ApplicationDisplay`, `ProfileDisplay`. The `InformationDisplay` displays the full information regarding the data item. <br/>

The `ResultDisplay` and `CommandBox` appears at the bottom of the application and the `ResultDisplay` is above the
 `CommandBox` even though the Ui does not show it explicitly. <br/>

The `Ui` components uses the JavaFX UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/resources/view/MainWindow.fxml). The styling of the application is mainly at [`MainWindow.css`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/resources/view/MainWindow.css).

The `Ui` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

<div style="page-break-after: always;"></div>

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

<div markdown="span" class="alert alert-info">

  :information_source: **Note:** Implementation of the command class is not accurate for commands
  independent of type as the diagram is simplified for better readability.
  Refer to the [command implementation](#command-classes) to see how is it implemented in full.

</div>

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/logic/Logic.java)

1. `Logic` uses the `MainParser` class to parse the user command.
2. This results in a `Command` object which is executed by the `LogicManager`.
3. The command execution can affect the `Model` (e.g. deleting an application).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as switching
tabs or displaying the matching internships window to the user.

<div style="page-break-after: always;"></div>

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete app 1")`
API call.

![Interactions Inside the Logic Component for the `delete app 1` Command](images/DeleteSequenceDiagram1.png)

<div style="page-break-after: always;"></div>

### Model component

<p id="model-class-diagram"><img src="images/dg-model/ModelClassDiagram.png" width="60%" height="60%"/></p>

Breakdown of the Company, Internship and Application packages:
![Structure of the Company, Internship and Application Classes](images/dg-model/CompanyInternshipApplicationClassDiagram.png)

Breakdown of the Profile package:
<p id="profile-class-diagram"><img src="images/dg-model/ProfileClassDiagram.png" width="45%" height="45%"/></p>

<div style="page-break-after: always;"></div>

**API** :
[`Model.java`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/model/Model.java)

The `Model`,

* stores a `UserPrefs` object that represents the user’s preferences i.e. GUI settings and file paths of saved data.
* stores the application data.
* exposes an unmodifiable `ObservableList` for `CompanyItem`, `ApplicationItem` and `ProfileItem` respectively, that
 can be 'observed' i.e. the UI can be bound to this list so that the UI automatically updates when the data in the
 list changes.
* does not depend on any of the other three components.

<div style="page-break-after: always;"></div>

### Storage component

<p id="storage-class-diagram"><img src="images/StorageClassDiagram.png"/></p>

**API** :
[`Storage.java`](https://github.com/AY2021S1-CS2103T-T15-4/tp/blob/master/src/main/java/seedu/internhunter/storage/Storage.java)

The Storage component,
* can save UserPref objects in json format and read it back.
* can save the InternHunter data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.internhunter.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### `Command` classes

#### Implementation
There are 4 different subclasses of `Item` in InternHunter, namely `CompanyItem`, `InternshipItem`, `ApplicationItem`, and
`ProfileItem`. <br/>

There are 2 types of commands:
- Commands that are dependent on the type of `Item`
    - e.g. `AddCommand`, `DeleteCommand`, `EditCommand`
    - These commands are implemented as _abstract_ classes that inherit from the `Command` class. Type specific
    commands like `AddCompanyCommand` and `AddApplicationCommand` inherit from the _abstract_ `AddCommand`
    class.
- Commands that are not dependent on the type of `Item`
    - e.g. `HelpCommand`, `ExitCommand`
    - These commands are implemented as _concrete_ classes and inherit directly from the `Command` class.

From this point on, we will be using `ABCCommand` to represent commands that are dependent on type and
`XYZCommand` to represent commands that are independent of type.

<div style="page-break-after: always;"></div>

The following shows the class diagram for `Command` and its subclasses:

![CommandClassDiagram](images/CommandClassDiagram.png)

#### Design considerations

##### Aspect: Whether `ABCCommand` should be abstract and split into 4 other `ABCItemCommand` or handle the 4 `Item` types on its own

**Alternatives considered**

**Alternative 1 (current choice)**: `ABCCommand` is split into 4 other `ABCItemCommand`. InternHunter parses the
user input and creates the specific `ABCItemCommand` for execution. The following activity diagram shows how the 
execution of the `AddApplicationCommand` will work.

![AddApplicationCommandActivityDiagram](images/AddApplicationCommandActivityDiagram.png)

<div style="page-break-after: always;"></div>

- Pros: 
    - Each command has its own specific task to execute. This means that classes are more flexible and can be changed
    very easily. 
    - Higher cohesion as the class is only dependent on the one `Item` type
    - Short and concise `execute` method, providing better readability and maintainability
- Cons:
    - More classes have to be created

<div style="page-break-after: always;"></div>
    
**Alternative 2**: `ABCCommand` is a _concrete_ class and handles the execution of all 4 `Item` types.
InternHunter parses the user input and creates the general `ABCCommand` for execution. The following
activity diagram shows how the `AddCommand` will work.

<p id="add-command-activity-diagram"><img src="images/AddCommandActivityDiagram.png" width="60%" height="60%"/></p>

<div style="page-break-after: always;"></div>

- Pros:
    - Only one command is needed, reducing the number of classes created
- Cons:
    - `execute` method becomes extremely long as it needs to contain switch statements to handle the execution of
    `ABCCommand` for the 4 different types of `Item`
    - `ABCCommand` class is vulnerable to drastic changes when the parsing method of any one `Item` class changes
    - `ABCCommand` class holds more dependencies as it is now dependent on the 4 `Item` classes  
    - Poor readability and maintainability
    - A slight overhead increase as `Item` type needs to be passed in as a parameter to the `ABCCommand`,
    additional check for nullity in the parameter passed in is required

**Conclusion**: Our group settled on the first design, since it better adheres to OOP principles such as
Single Responsibility Principle. Our design meant that each specific `ABCItemCommand` is only dependent on the `Item`
itself and not subjected to the changes in implementation of the other `Item` classes. This means that it will only
have one reason to change. Moreover, this leads to lower coupling, which makes maintenance, integration and
testing easier. This ended up being a good choice as we had some changes in the parsing requirements of one
of the `Item` classes, `InternshipItem`. If we had gone with the second design, the concrete `ABCCommand` might
have broken down as it might not be suited to the different parsing requirements in the `InternshipItem`.

<div style="page-break-after: always;"></div>

### Delete company feature

#### What it is
Users are able to execute a command to delete a company from their list of companies in InternHunter. Upon the 
successful deletion of a company, all internships within that company, if any, will also be deleted (as they are a 
part of the company, i.e. companies and the internships that they offer have a whole-part / composition relationship). 
As a result, all applications made to internships from the company to be deleted, if any, will also be deleted as per 
the delete internship feature. This feature is intended to be used when the user is viewing the Company tab. However, 
the user is free to execute the delete company command while they are on another tab, and InternHunter will simply 
switch over to the Company tab if and when the command has finished executing successfully.

**Command format**: `delete com INDEX`
* Where `INDEX` is the index of the company to be deleted in the list of companies

#### Implementation
Upon a user’s entry of a valid delete company command, a `DeleteCompanyCommand` object is created. 
`DeleteCompanyCommand` is a class that extends the `DeleteCommand` abstract class that in turn extends the 
`Command` abstract class.

<p id="delete-company-command-class-diagram"><img src="images/dg-company/DeleteCompanyCommandClassDiagram.png" width="70%" height="70%"/></p>

`DeleteCompanyCommand` implements the `execute()` method from the `Command` abstract class whereby upon execution, the 
method will delete the respective company in the model’s list of companies if a valid index is given.

This is how the `DeleteCompanyCommand#execute()` method works upon execution:

1. The tab that the user is currently viewing is obtained via the `Model#getTabName()` method.
2. The company to be deleted is retrieved from the model’s `companyList` via the `CommandUtil#getCompany()` method.
3. All the internships within the company, if any, are deleted via a self-invocation to `DeleteCompanyCommand`’s own 
 `deleteAllInternshipsInCompany()` method.
4. The company is then deleted from the model via the `model#deleteCompany()` method.
5. The deletion is successful and a `CommandResult` is returned with an appropriate success message to indicate 
 operation success via the `CommandUtil#getCommandResult()` method. The `CommandResult` also indicates whether the tab 
 needs to be switched to the Company tab or not, based on if the user was already viewing the Company tab or not as 
 retrieved in Step 1.

<br />

The following sequence diagrams show how the delete company feature works successfully, using the example command 
`delete com 3`:

![DeleteCompanyCommandSequenceDiagram](images/dg-company/DeleteCompanyCommandSequenceDiagram.png)
![ExecuteDeleteCompany3CommandSequenceDiagram](images/dg-company/ExecuteDeleteCompany3CommandSequenceDiagram.png)

<p align="center"><img src="images/GetDeleteCommandResultSequenceDiagram.png" width="80%" height="80%"/></p>

Sequence diagram for handle delete display switch index can be found [here](#handle-delete-display-switch-index-sequence-diagram)

#### Design considerations

##### Aspect: How applications made to internships from the company to be deleted are deleted

**Alternatives considered**

* **Alternative 1 (current choice)**: Delete all applications made to internships from the company to be deleted by
 executing delete internship commands.
  * Pros:
    * Avoids rewriting code / code duplication by calling methods that have already been implemented to achieve the 
    desired functionality.
    * Ensures consistent behaviour - when an internship is deleted because a company is deleted, what happens to any 
    application for that internship will be the same as what would happen to it if a delete internship command was 
    executed directly by the user for the same internship.
  * Cons:
    * Introduces a dependency on `DeleteInternshipCommand`.

<div style="page-break-after: always;"></div>

* **Alternative 2**: Delete all applications made to internships from the company to be deleted without executing
delete internship commands, i.e. by implementing delete internship command’s internal workings.
  * Pros:
    * Not dependent on `DeleteInternshipCommand`.
  * Cons:
    * Introduces dependencies on `InternshipItem` and `ApplicationItem`.
    * Violates the DRY principle:
      * Introduces code duplication.
      * Decentralizes the behaviour of what happens to an application made for an internship that is being deleted, 
      since there are now 2 separate implementations for this (one in `DeleteCompanyCommand` and the other in 
      `DeleteInternshipCommand`). The implications of this are:
        * Cannot guarantee consistent behaviour - when an internship is deleted because a company is deleted, what 
        happens to any application for that internship may not be the same as what would happen to it if a delete 
        internship command was executed directly by the user for the same internship.
        * Updating this behaviour will require updating code in both places rather than one centralised place.

<div style="page-break-after: always;"></div>

### User profile feature

#### What it is

The user profile feature behaves like a resume for the user to keep track of noteworthy events and milestones in one's professional life.
There are three categories of profile items namely: `achievement`, `skill` and `experience`. One of the operations
that can be applied on profile items is editing.

#### Editing user profile item

The `edit me` command for the user profile allows the user to update the fields of the each profile item by
specifying the targeted index and at least one field. 

#### Implementation

* The `edit me` command is implemented by the `EditProfileCommandParser` and `EditProfileCommand`.
* `EditProfileCommandParser#parse(...)` method creates a `EditProfileItemDescriptor` based on the fields of the input
 provided by the user. The `EditProfileItemDescriptor` is then used in instantiating the `EditProfileCommand` by the 
 `EditProfileCommandParser`.

* `EditProfileCommand` implements the `execute(...)` method from the `Command` abstract class whereby upon execution, 
the method will edit the specified profile item in the model’s profile list.

![EditProfileCommandClassDiagram](images/dg-profile/EditCommandClassDiagram.png)

The following sequence diagrams show how the editing profile Item feature works successfully, using the example command 

`edit me 1 t/Learn HTML`:

![EditProfileCommandSequenceDiagramSimplified](images/dg-profile/EditProfileCommandSequenceDiagramSimplified.png)

<div style="page-break-after: always;"></div>

1. After the user enters an input to edit the user profile, the input is first parsed by the `MainParser` looks out
 for the command word, recognizes the `edit` command and  funnels the input to `EditCommandParser`.
2. The `EditCommandParser` then identifies the item type, which is profile item and returns the
 `EditProfileCommandParser`.
3. The `EditProfileCommandParser` then parses for the index and fields to be edited and returns a `EditProfileCommand` 
containing a editProfileItemDescriptor. The following sequence diagram depicts how the `EditProfileCommand` works:

![ExecuteEditMeCommand](images/dg-profile/ExecuteEditMeCommand.png)

4. When `EditProfileCommand` is executed it which retrieves the targeted `profileItemToEdit` from the
 `lastShownList` *(which contains the profile items the user is able to see)* and updates the model with the
 `editedProfileItem` created from the `editProfileItemDescriptor` using the `createEditedProfileItem(...)` of the
  `EditProfileCommand`.
5. CommandResult is return to indicate a successful operation.
 
<div style="page-break-after: always;"></div>
 
#### Design considerations

##### Aspect: How Logic components interact with Model

**Alternatives considered**

* **Alternative 1 (current choice)**: Logic components interact with the model interface solely and not directly
 with model's internal components: `profileList` and `FilteredList` in the `profileList`.
  * Pros:
    * This obeys the Law of Demeter which stresses for components to avoid interacting directly with internal
     components of other objects. This reduces coupling which increases testability as `EditProfileCommand` only
     requires one model stub as opposed to more objects stubs of the model for testing.
    * This also increases maintainability as `EditProfileCommand` only has to be concerned with the methods that
     Model interface provides and not the other implementation details should they be subjected to change.
  * Cons:
    * This increases code volume within `Model` as the model interface needs to provide every method to interact with
     all the collections it contains.

* **Alternative 2 (used in v1.2)**: The model acts as a container for its collections, allowing clients to retrieve
 these collections and directly operate on it. For example, `FilteredList` and `profileList` are both retrieved from the
  model from within the `EditProfileCommand` and then the `setItemList()` operation is called directly on the
  `profileList` to update its value.
  
  ```
  model.getProfileList().setItem(profileItemToEdit, EditedProfileItem)
  ```
  
  <div style="page-break-after: always;"></div>
  
The following sequence diagram shows how the logic components bypass with model interface to interact directly with the
`FilteredList` and the `profileList`:

![ExecuteEditMeCommandAlt.png](images/dg-profile/ExecuteEditMeCommandAlt.png)

  * Pros: 
    * This reduces code volume by keeping the model interface lean as it no longer has to provide methods for all
     operations of its internal components.
    * This may marginally improve performance as it bypasses the model interface to interact with the `profileList
    ` and `FilteredList` directly.
  * Cons:
    * This exposes the internal components of the `Model` which increases coupling as `EditProfileCommand` is now
     dependent on `FilteredList` and the `ItemListManager` which reduces testability and maintainability.

<div style="page-break-after: always;"></div>

### Switch screen feature

#### What it is
Users are able to execute a command to switch their tabs in InternHunter. There are 3 tabs: Company, Application, and 
Profile. Take for example, switching to the company tab. Upon the successful switching of tabs, the screen will 
display a list of companies and also display the information of the last known index of that tab, i.e if the index that was previously saved in that tab was the 3rd index, when switching back to this tab, it will show the information of the 3rd index.

**Command format**: `switch TYPE` <br />
`TYPE` is the type of tab. <br />
There are three `TYPE`s:
* `com`
* `app`
* `me`

#### Implementation
Upon a user’s entry of a valid switch command, a `SwitchCommand` object is created. `SwitchCommand` is a class that extends the `Command` abstract class as well as having direct association with `TabName`, an enumeration, as well as having a dependency to the `Model` interface as it relies on some of its method.

<p align="left"><img src="images/switchcommand/SwitchCommandClassDiagram.png" width="70%" height="70%"/></p>

`SwitchCommand` implements the `execute(...)` method from the `Command` abstract class whereby upon execution, the 
method will switch the tab and the screen if a valid command is provided.

This is how the `SwitchCommand#execute(...)` method works upon execution:
1. The current tab that the user is viewing is obtained via the `Model#getTabName()` method.
2. The input tab will be check against the current tab. <br/>
 2a. If both the tabs are the same, a same tab message will be passed to `CommandUtil#getCommandResult(...)`method. <br/>
 2b. If both the tabs are different, a success message will be passed to `CommandUtil#getCommandResult(...)`method. <br/>

<p align="center">The overall process of how <code>SwitchCommand</code> was generated:</p>

<p align="center"><img src="images/switchcommand/SwitchCommandSequenceDiagram.png"/></p>

<p align="center">The process of how <code>SwitchCommand</code> interacts with the model:</p>

<p align="center"><img src="images/ExecuteSwitchMeCommandSequenceDiagram.png" width="75%" height="75%"/></p>

<div style="page-break-after: always;"></div>

This is how the `CommandUtil#getCommandResult(...)` method works upon execution:
1. The current tab that the user is viewing is obtained via the `Model#getTabName()` method.
2. The input tab will be check against the current tab. <br/>
 2a. If both the tabs are the same, a `CommandResult` with a same tab message is return. <br/>
 2b. If both the tabs are different, we will change the tab to the input's tab name via `Model#setTabName(...)`. A `CommandResult` with a success message is return.

<p align="center">The process of how <code>getCommandResult</code> is being generated:</p>

<p align="center"><img src="images/GetCommandResultSequenceDiagram.png" width="95%" height="95%"/></p>

<div style="page-break-after: always;"></div>

The following activity diagram summarizes what happens when a user executes a switch command:

![SwitchCommandActivityDiagram](images/switchcommand/SwitchCommandActivityDiagram.png)

The above activity diagram shows the logic and the path execution when the switch command is executed. The code will check if there is any missing input or if the input is not one of the three mentioned in the `Command format` above. If the aforementioned 2 conditions are not met, an error message is displayed. If the input is one of the three mentioned above in the `Command format`, there will be further checks if the user are already in the same tab.

<div style="page-break-after: always;"></div>

#### Design considerations

##### Aspect: Should the tabs be allowed to change only by the `SwitchCommand`.

**Alternatives Considered**

* **Alternative 1 (current choice):** Allow the switch of tabs to not only be accessible via the switch command, but rather extract it out for all commands excluding `exit`, `help` and `clear`.
    * Pros:
        * Allows user to type once instead of twice when executing a single command and wanting to view it. (This optimization is to allow for a faster way to type and view the changes). <br/>
        * By abstract the method out from switch command, it obeys the DRY principle as all the commands will be calling a single method.
        * This allows and obeys the Open-Close principle as new implementation of commands can just be calling this single method at the end.
        * Allows user to have a second alternative to switch tabs just for viewing purpose.
        * Reduces some dependency between each commands as they would not need to call the switch command to switch tabs.
    * Cons:
        * User might switch tab accidentally because of inputting the wrong `TYPE`.
        * Increases some form of coupling between all commands as they are now linked to this single method.
* **Alternative 2:** Only allow switch command to be the only way to switch tabs.
    * Pros:
        * This introduces a "type-safe" checks like in Java where only if the user is in the correct tab, then he or she will be able to add items to that item type.
        * Easier to implement. <br />
    * Cons:
        * This introduces the need to type twice in order to view the execution of the command.

<div style="page-break-after: always;"></div>

### Storage feature

#### What it is
After a command is successfully executed, InternHunter automatically saves users' data to JSON files. Moreover, 
every time the `GuiSettings` is modified, InternHunter updates the user preferences JSON file. Users can transfer or 
backup the JSON files manually. The storage component is responsible for both reading and saving the data.

#### Implementation
InternHunter uses Jackson, a high-performance JSON processor for Java. It can  serialize Java objects into JSON and 
deserialize JSON into Java objects. InternHunter's model has 4 different types of data: `ApplicationItem`, 
`CompanyItem`, `InternshipItem`, and `ProfileItem`. They first need to be converted to Jackson-friendly versions of 
themselves, where each field is a string or another Jackson-friendly object. User preference is saved as a `UserPrefs` 
object.
 
 [Storage structure diagram](#storage-class-diagram)
 
 * `Storage` handles the storage for all `Item` lists and user preferences.
 * `UserPrefsStorage` handles the storage for user preferences.
 * `ItemListStorage` handles the storage for `Item` lists.
 * `JsonSerializableItemList` represents a Jackson-friendly version of an `Item` list.
 * `JsonAdaptedItem` represents a Jackson-friendly version of an `Item`.
 
 <div style="page-break-after: always;"></div>
 
 ![JsonAdaptedItemClassDiagram](images/JsonAdaptedItemClassDiagram.png)
 
 `JsonAdaptedItem` is an abstract class representing a Jackson-friendly version of the `Item` class in the model component.
  It has one method `toModelType()` which convert itself to an `Item` object. There are 4 classes extending 
  `JsonAdaptedItem`:
  * `JsonAdaptedApplicationItem` the Jackson-friendly version of `ApplicationItem`.
  * `JsonAdaptedCompanyItem` the Jackson-friendly version of `CompanyItem`.
  * `JsonAdaptedInternshipItem` the Jackson-friendly version of `InternshipItem`.
  * `JsonAdaptedProfileItem` the Jackson-friendly version of `ProfileItem`.
  
InternHunter automatically saves user data after every command. The following sequence diagram demonstrates how
InternHunter does it. Let `commandString` be any valid command string.
  
![SavingDataSequenceDiagram](images/SavingDataSequenceDiagram.png)

 <div style="page-break-after: always;"></div>
 
This is how the `LogicManager#execute()` method works upon execution:

1. `LogicManager` will execute the given `commandString`.
2. `LogicManager` calls the `ModelManager#getUnfilteredApplicationList()` to get the current application item list.
3. `LogicManager` calls the `StorageManager#saveApplicationItemList(...)` to save the current application item list.
4. `LogicManager` calls the `ModelManager#getUnfilteredCompanyList()` to get the current company item list.
5. `LogicManager` calls the `StorageManager#saveCompanyItemList(...)` to save the current company item list.
6. `LogicManager` calls the `ModelManager#getUnfilteredProfileList()` to get the current profile item list.
7. `LogicManager` calls the `StorageManager#saveProfileItemList(...)` to save the current profile item list.
  
#### Design considerations

##### Aspect: How to handle 3 types of `Item` list

InternHunter maintains 3 types of `Item` lists: `ApplicationItem`, `CompanyItem`, and `ProfileItem` lists.
Both `ItemListStorage` and `JsonSerializableItemList` use  the same logic regardless of the `Item` type.

**Alternatives Considered**

* **Alternative 1: current choice**: Creates a base abstract class `JsonAdaptedItem` and makes `ItemListStorage` 
and `JsonSerializableItemList` use generics.
    * Pros: 
        * Adheres to OOP principle, specifically polymorphism.
        * Less code duplication.
        * Makes adding a new `Item` type easy. To be able to save and read a new `Item` type, only a new 
        class representing its Jackson-friendly version needs to be created.
        * Makes further extension to the `ItemListStorage` and `JsonSerializableItemList` faster.

    * Cons:
        * More complicated as Jackson does not provide a direct way to convert a generic object to its JSON format.

<div style="page-break-after: always;"></div>

* **Alternative 2**: Each `Item` type has their own `ItemListStorage` and `JsonSerializableItemList`.
    * Pros:
        * Easier to implement.
    
    * Cons:
        * Much longer code with much duplication.
        * Adding a new `Item` type requires at least 3 new classes to be made.
        * Extending the `ItemListStorage` and `JsonSerializableItemList` class would require changes to all the
        different versions corresponding to the different `Item` types.

<div style="page-break-after: always;"></div>

### Clear feature

#### What it is
In the beginning, users can see how the app works with sample data. After that, users can decide to 
clear all the entries in InternHunter with just a `clear` command.

#### Implementation

The following diagram illustrates whether InternHunter uses sample data.

![SampleDataActivityDiagram](images/SampleDataActivityDiagram.png)

When users enters the `clear` command, InternHunter will reset all three lists. Here is a sequence diagram showcasing how
InternHunter does it.

![ClearCommandSequenceDiagram](images/ClearCommandSequenceDiagram.png)

This is how the `ClearCommand#execute()` method works upon execution:

1. A new empty `ItemList` for the application item list is created.
2. `ModelManager#setApplicationList(...)` is called with the recently created empty list as the argument.
3. A new empty `ItemList` for the company item list is created.
4. `ModelManager#setCompanyList(...)` is called with the recently created empty list as the argument.
5. A new empty `ItemList` for the profile item list is created.
6. `ModelManager#setProfileList(...)` is called with the recently created empty list as the argument.

<div style="page-break-after: always;"></div>
 
#### Design considerations

##### Aspect: How to clear the lists

InternHunter only lets users create applications for internships already added to companies. When users apply for an internship,
 InternHunter will create an `ApplicationItem` with the given `InternsipItem`. Hence, InternHunter needs to
check whether the internships in both lists are consistent.

**Alternatives Considered**

* **Alternative 1: current choice**: Clear all three lists at once.
    * Pros: 
        * Guarantees data consistency.
        
    * Cons:
        * Less freedom for users.

* **Alternative 2**: Clear each list individually.
    * Pros:
        * Users can choose which list to be cleared.
    
    * Cons:
        * High risk of data inconsistency due to the linkage between company and application lists.

<div style="page-break-after: always;"></div>

### Match command feature

#### What it is
Users are able to execute a command to generate a list of matching internships that matches their current profile
skills. This matching is done by filtering the list of profile items that has the category `skill` and
using it to filter the list of internships. Remaining internships are those that consist of at least one
`Requirement` that matches the user's list of skills. 

**Command format:** `match`

#### Implementation

`MatchCommand` is a class that extends the `Command` _abstract_ class. It has a dependency to the `Model`
interface as it relies on getting the profile list and company list from the model.

Here is a class diagram to show how the `MatchCommand` is implemented:

![MatchCommandClassDiagram](images/MatchCommandClassDiagram.png)

<div style="page-break-after: always;"></div>
 
This is how the `MatchCommand#execute(...)` method works upon execution:

1. The list of profile items and company items are first obtained via the `Model#getProfileItemList()` method and
`Model#getCompanyItemList()` respectively.
2. Then, the list of profile skills that the user has is obtained via a self-invocation to
`MatchCommand#getSkillList(...)` method.
3. Next, the list of all internships from the list of companies is obtained via its own
`MatchCommand#getInternshipList(...)` method.
4. Then, `MatchCommand#getMatchingInternships(...)` is invoked to obtain
the list of matching internships.
5. Finally, `MatchCommand#getMatchingInternshipsCommandResult(...)` method is used to generate the
`CommandResult`. This method returns a different `CommandResult` depending if the matchingInternships is empty or not. <br/>
 5a. If the matchingInternships list is empty, then the no matching internships message will be passed to the `CommandResult`. <br/>
 5b. Otherwise, the showing matching internships message will be passed to the `CommandResult`. This internship list
 will be passed into `CommandResult#setMatchingInternships(...)` method for display in the Ui. <br/>

The following sequence diagrams show how the match command works:

<p align="center"><img src="images/MatchCommandSequenceDiagram1.png" width="72%" height="72%"/></p>

<p align="center"><img src="images/MatchExecutionSequenceDiagram.png" width="72%" height="72%"/></p>

<div style="page-break-after: always;"></div>

#### Design considerations

##### Aspect: How to generate the matching internships

**Alternatives Considered**

**Alternative 1 (current choice):** Methods to generate the matching internships, namely `getSkillList`, 
`getInternshipList`, and `getMatchingInternships` are implemented within `MatchCommand`.

- Pros:
    - Still adheres to the Single Responsibility Principle as the `MatchCommand` is meant to generate the list of
    matching internships. Having additional methods to match internships to profile skills are still within the
    responsibility of this class.
    - Higher cohesion as `Model` does not need to have additional responsibilities like executing algorithms.
    - Increased flexibility as the matching algorithm can be easily changed within this `MatchCommand`.
    
- Cons:
    - `MatchCommand` becomes longer as it needs additional methods to generate the list of matching internships.

**Alternative 2:** Methods to generate the matching internships, namely `getSkillList`, 
`getInternshipList`, and `getMatchingInternships` are implemented within `Model`.

- Pros:
    - `MatchCommand` will be very short as it just needs to call the `getMatchingInternships` method from the
    `Model` interface and generate the correct `CommandResult` from there.
    
- Cons:
    - `Model` becomes more complicated as it needs to support more methods. This in turns causes the `ModelManager`
    class, which implements the `Model` interface to be overly complex as it needs to provide the algorithms to
    filter and generate the matching internship list.
    - Lower cohesion as the `Model` interface has additional responsibilities and functionalities.
    `Model` now has to deal with algorithms, instead of keeping to what a `Model` does which is to manage the app data
    of the user.

<div style="page-break-after: always;"></div>

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)
       
## **Appendix**
### Appendix A: Product Scope

**Target user profile**:

* university students applying for tech internships
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Improves your planning, confidence and readiness for tech-related internship applications by
 improving your interview skills and search strategy.

<div style="page-break-after: always;"></div>

### Appendix B: User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

Priority | As a …​    | I want to …​                                                   | So that I can…​                                                           
-------- | ---------- | -------------------------------------------------------------- | --------------------------------------------------------------------------------- 
`* * *`  | user       | maintain a list of company profiles                            | keep track of companies that I'm interested in                                    
`* * *`  | user       | add a company profile                                          | keep track of companies that I'm interested in                                    
`* * *`  | user       | delete a company profile                                       | remove company profiles that I no longer need / am no longer interested in        
`* * *`  | user       | edit a company profile                                         | keep my company profiles updated and accurate                                     
`* * *`  | user       | view a company profile                                         | see its details                                                                   
`* *`    | user       | find company profiles by name                                  | quickly look up company profiles without having to search manually                
`* *`    | user       | list all company profiles in my list                           | see my full list of companies
`* * *`  | user       | add an internship to a company profile                         | keep track of the internships that that company is offering                       
`* * *`  | user       | delete an internship from a company profile                    | remove erroneous / outdated entries                                               
`* * *`  | user       | edit an internship from a company profile                      | keep the list of internships that a company offers updated and accurate           
`* * *`  | user       | view a company’s internships when I view their profile         | see what internships they are offering                                            

<div style="page-break-after: always;"></div>

Priority | As a …​    | I want to …​                                                   | So that I can…​                                                           
-------- | ---------- | -------------------------------------------------------------- | --------------------------------------------------------------------------------- 
`* * *`  | user       | maintain a list of my internship applications                  | keep track of them                                                                
`* * *`  | user       | add an internship application                                  | keep track of the internships that I have applied for                             
`* * *`  | user       | delete an internship application                               | remove internship applications that I no longer need / am no longer interested in 
`* * *`  | user       | edit an internship application                                 | keep my internship applications updated and accurate                              
`* * *`  | user       | view an internship application                                 | see its details                                                                   
`* *`    | user       | find internship applications by job title                      | quickly look up my internship applications without having to search manually
`* *`    | user       | list all internship applications in my list                    | see my full list of internship applications
`* * *`  | user       | record and see an internship application's status              | keep track of them                                                                
`* * *`  | user       | save the dates of my upcoming interviews                       | keep track of them                                                                
`* * *`  | user       | maintain a user profile                                        | have an overview of my experience, skills and achievements                        
`* * *`  | user       | add information to my user profile                             | keep my user profile updated and accurate                                         
`* * *`  | user       | delete information from my user profile                        | keep my user profile updated and accurate                                         
`* * *`  | user       | edit information in my user profile                            | keep my user profile updated and accurate                                         

<div style="page-break-after: always;"></div>

Priority | As a …​    | I want to …​                                                   | So that I can…​                                                           
-------- | ---------- | -------------------------------------------------------------- | --------------------------------------------------------------------------------- 
`* * *`  | user       | view information in my user profile                            | see its details  
`* *`    | user       | find information in my user profile by title                   | quickly look up specific information in my user profile without having to search manually
`* *`    | user       | list all the information in my user profile                    | see the full list of information in my user profile
`* * *`  | user       | switch between the multiple pages of the app                   |
`* *`    | user       | automatically find internships based on my skill set           | find internships that may be more suitable for me
`* * *`  | new user   | see usage instructions                                         | refer to instructions when I forget how to use the app                            
`* * *`  | user       | get error feedback when a command fails                        | know what went wrong                                                              
`* *`    | user       | navigate the application easily through a clear user interface |                                                                                   
`* *`    | user       | get fast feedback from the app                                 |                                                                                   
`* *`    | user       | easily clear all data from the app                             | start from a clean slate

<div style="page-break-after: always;"></div>

### Appendix C: Use Cases

(For all use cases below, the **System** is `InternHunter` and the **Actor** is the `user`)

**Use case: UC01 - Add a company**

Guarantees: Addition of company is successful.

**MSS**

1. User requests to add a company and provides details.
2. InternHunter adds the company to the list of companies.<br/>
Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.
    		
**Use case: UC02 - Delete a company**

Precondition: User already has an existing list of companies. <br/>
Guarantees: Deletion of company is successful.

**MSS**

1. User requests to delete a company.
2. InternHunter removes the company from the list of companies.<br/>
Use case ends.
    
**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.

 1b. InternHunter detects an invalid index. <br/>
  1b1. InternHunter displays an error message and informs the user that the index is out of bounds. <br/>
  Use case resumes from step 1.

<div style="page-break-after: always;"></div>

**Use case: UC03 - Edit a company**

Precondition: User already has an existing list of companies. <br/>
Guarantees: Editing of company is successful.

**MSS**

1. User requests to edit the details of a company and provides details.
2. InternHunter updates the details of the company. <br/>
Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.

 1b. InternHunter detects an invalid index. <br/>
  1b1. InternHunter displays an error message and informs the user that the index is out of bounds. <br/>
  Use case resumes from step 1.

**Use case: UC04 - View a company**

Precondition: User already has an existing list of companies. <br/>
Guarantees: Viewing of company is successful.

**MSS**

1. User requests to view a company.
2. InternHunter displays the company.<br/>
Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.

 1b. InternHunter detects an invalid index. <br/>
  1b1. InternHunter displays an error message and informs the user that the index is out of bounds. <br/>
  Use case resumes from step 1.

<div style="page-break-after: always;"></div>

**Use case: UC05 - Find companies**

Precondition: User already has an existing list of companies. <br/>
Guarantees: Companies whose names matches the keywords specified are listed.

**MSS**

1. User searches for a company by name.
2. InternHunter displays the companies sought.<br/>
Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.

 2a. InternHunter does not find any companies that matches query. <br/>
  2a1. InternHunter informs user that no companies are found. <br/>
  Use case ends.

**Use case: UC06 - List all companies**

Precondition: User already has an existing list of companies. <br/>
Guarantees: All companies stored in InternHunter are shown.

**MSS**

1. User queries for all companies.
2. InternHunter displays all company it stores.<br/>
Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1.

<div style="page-break-after: always;"></div>

**Use case: UC07 - Add an internship**

Precondition: User already has an existing list of companies. <br/>
Guarantees: Addition of internship to company is successful.

**MSS**
*  Similar MSS to UC01 - adding a company except user is now adding an internship.

**Extensions**

 1a. Similar to extension 1a of UC01 - adding a company except user is adding an internship.

 1b. InternHunter detects an invalid index. <br/>
  1b1. InternHunter displays an error message and informs the user that the index is out of bounds. <br/>
  Use case resumes from step 1.

**Use case: UC08 - Delete an internship**

* Similar to UC02 - delete a company except user is deleting an internship.
  
**Use case: UC09 - Edit an internship**

* Similar to UC03 - editing a company except user is editing an internship.

**Use case: UC10 - Add an application**

Precondition: User already has an existing list of internships in a company. <br/>
Guarantees: Addition of application is successful.

**MSS**

*  Similar MSS to UC01 - adding a company except user is adding an application.

**Extensions**

* Similar to extension of UC07 - adding an internship except user is adding an application.

**Use case: UC11 - Delete an application**

* Similar to UC02 - deleting a company except user is deleting an application.

**Use case: UC12 - Edit an application**

* Similar to UC03 - editing a company except user is editing an application.

<div style="page-break-after: always;"></div>

**Use case: UC13 - View an application**

* Similar to UC04 - viewing a company except  user is viewing an application.

**Use case: UC14 - Find applications**

* Similar to UC05 - finding companies except user is finding applications.

**Use case: UC15 - List all applications**

* Similar to UC06 - listing all companies except user is listing all applications.

**Use case: UC16 - Add user profile item**

* Similar to UC01 - adding a company except user is adding a user profile item.

**Use case: UC17 - Delete a user profile item**

* Similar to UC02 - deleting a company except user is deleting a user profile item.

**Use case: UC18 - Edit a user profile item**

* Similar to UC03 - editing a company except user is editing a user profile item.

**Use case: UC19 - View a user profile item**

* Similar to UC04 - viewing a company except  user is viewing a user profile item.

**Use case: UC20 - Find user profile items**

* Similar to UC05 - finding companies except user is finding user profile items.

**Use case: UC21 - List all user profile items**

* Similar to UC06 - listing all companies except user is listing all user profiles items.

**Use case: UC22 - Match skills in user profile to internship requirements**

**MSS**

1. User requests to see the list of internships that matches her profile skills.
2. InternHunter generates and displays the list of internships.

**Extensions**

 2a. User have no internships that matches her profile skills. <br/>
  2a1. InternHunter displays an error message and informs the user that she has no matching internships. <br/>
  2a2. Use case ends.

<div style="page-break-after: always;"></div>

**Use case: UC23 - Switch tabs**

Guarantees: InternHunter switches to the queried tab.

**MSS**

1.  User requests to switch the tab of the screen.
2.  InternHunter switches to the requested tab. <br/>
    Use case ends.

**Extensions**

 1a. InternHunter detects an error in the input format. <br/>
  1a1. InternHunter displays an error message and informs the user of the valid input format. <br/>
  Use case resumes from step 1. <br/>
 1b. InternHunter detects that the user is already in that tab. <br/>
  1b1. InternHunter displays a message and informs the user that he is already in that tab. <br/>
  Use case ends. <br/>

**Use case: UC24 - Clear all entries**

Guarantees: All entries in InternHunter will be cleared.

**MSS**

1.  User requests to clear all entries.
2.  InternHunter deletes all of its entries. <br/>
    Use case ends.

**Use case: UC25 - Get help**

Guarantees: User will get directions to the user guide.

**MSS**

1.  User requests to see the help window.
2.  InternHunter displays help message directing user to user guide. <br/>
    Use case ends.

<div style="page-break-after: always;"></div>

**Use case: UC26 - Exit**

**MSS**

1.  User requests to exit InternHunter.
2.  InternHunter prompts for confirmation.
3.  User confirms intention to exit.
4.  InternHunter exits. <br/>
    Use case ends.

**Extensions**

 2a. User chooses to cancel the confirmation. <br/>
  Use case ends.

<div style="page-break-after: always;"></div>
    
### Appendix D: Non-Functional Requirements

* Should work on any mainstream OS as long as it has Java 11 or above installed.
* Should work on both 32-bit and 64-bit environment.
* Should be for a single user i.e. (not a multi-user product).
* Should respond to commands within 2 seconds.
* The data should be stored locally and should be in a human editable text file.
* Should work without requiring an installer.
* Should only use third-party frameworks or libraries which are free, open-source and have permissive license term and 
do not require installation by user of the software.

### Appendix E: Glossary

* **CLI**: Command-Line Interface
* **OS**: Operating System
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **JSON**: JavaScript Object Notation
* **DRY**: Don't Repeat Yourself
* **OOP**: Object-oriented programming

<div style="page-break-after: always;"></div>

### Appendix F: Instructions for Manual Testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">

:information_source: <strong>Note:</strong> These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

**Launch and shutdown**

1. Initial launch.

   1. Download the jar file and copy into an empty folder.

   1. Double-click the jar file. <br>
       Expected: Shows the GUI with a set of sample data. The window size may not be optimum.

1. Saving window preferences.

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file. <br>
       Expected: The most recent window size and location is retained.
       
1. Shutting down.

   1. Quit the app by typing `exit`. <br>
      Expected: An Exit pop-out dialog will confirm the intention to exit InternHunter. For all users, you can use <kbd>tab</kbd> on keyboard to navigate. For **MacOS** users, use <kbd>spacebar</kbd> to confirm the exit, while the **rest** can use <kbd>Enter</kbd> to confirm the exit.


**Note**

* If an item exist before in InternHunter, a duplicate error message will be shown.
* If the command relating to a certain item type is executed on other tab, InternHunter will automatically switch the tab to correct tab. i.e  executing `add com` commands on application tab will bring the tab state of InternHunter from application tab to company tab.
* When doing `add`, `edit`, the right display will automatically adjust and reflect the recently executed command.

**Adding a company**

1. Adding a company called Amazon with its non-optional relevant fields such as address being `201 Victoria St`, email being `AmazonHires@amazon.com` and phone number being `65000545`.

   1. Prerequisites: Amazon not already added in InternHunter.

   1. Test case: `add com n/Amazon a/201 Victoria St e/AmazonHires@amazon.com p/65000545`  <br>
      Expected: A card displaying information of the company is added. The right display will show full information of the company added.
      
   1. Test case: `add com n/Google Florida a/101 Tampines St e/GoogleHires@google.com p/62343434 t/Cloud Computing t/Artificial Intelligence` <br>
      Expected: The difference compared to the above test case is, this adds some tags to the card which will show both `Cloud Computing` and `Artificial Intelligence`. It is reflected inside a blue box inside the card.
   
   1. Test case: `add com n/Facebook a/301 Raffles St e/FacebookHires@fb.com`  <br>
      Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.
      
**Adding an internship**

1. Adding an internship with Job title `Machine Learning Engineer`.

   1. Prerequisites: A company have to exist first, and add it to the company via a valid index.
   
   1. Test case: `add int 1 j/Machine Learning Engineer` <br>
   Expected: In the right display, the information of the internship will be shown.
   
   1. Test case: `add int 1 j/Frontend developer r/React` <br>
   Expected: In the right display, the information of the internship will be shown with requirements.
  
   1. Test case: `add int 1` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.


**Adding an application**

1. Adding an application is simulating applying for an application.

   1. Prerequisites: An internship must exist first before you can apply for it.
   
   1. Test case: `add app 5 i/1 d/24-12-20 s/interview` <br>
   Expected: A card displaying information of this application is added. There will be a status showing as **interview**. The date will be shown as 24 dec and the year is taken to be from year 2000-2099(Note that the date have to be in the future). 
   
   1. Test case: `add app 5 i/3` (ensure that you have a third internship first) <br>
   Expected: Similar to the above test case, however the status will be shown as the default status **applied**. The date will be today's date and the time will be taken to be 2359.
   
   1. Test case: `add app 1` <br>
      Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.
      
**Adding a profile item**

1. Adding a skill/experience/achievement set into our profile list.

   1. Prerequisites: This particular profile item's title should not be already added in InternHunter.
   
   1. Test case: `add me c/skill t/React d/Created a mini MOBA game` <br>
   Expected: A card displaying information of this profile item is added. There will be a circular colorful icon on the right of the card that will represent skills.
   
   1. Test case: `add me c/achievement t/Hackathon d/1st place` <br>
   Expected: Similar to the above test case, instead of a circular colorful icon, there will be a trophy representing achievement.
   
   1. Test case: `add me c/experience t/Interned at google d/Worked as a frontend developer` <br>
   Expected: Similar to both the above test case, instead, there will be a clipboard icon to represent experience.
   
   1. Test case: `add me` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Editing a company**

1. Editing some fields in company.
   
   1. Prerequisites: The company must exist and be accessed via a valid index.
   
   1. Test case: `edit com 1 t/Frontend developer t/Backend developer t/Fullstack developer` <br>
   Expected: The tags that are in the blue box will be changed to Frontend developer, Backend developer, Fullstack developer. It is visible on both the card and the right display.
   
   1. Test case: `edit com 1 p/91910808` <br>
   Expected: The phone number of the company will be changed. It is visible on both the card and the right display.
   
   1. Test case: `edit com 1 t/` <br>
   Expected: Removes all the tags for this card.
   
   1. Test case: `edit com 1` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

  
**Editing an internship**

1. Editing some fields in internship.

   1. Prerequisites: The internship must exist and be accessed via a valid index.
   
   1. Test case: `edit int 1 i/1 r/Java r/Python` <br>
   Expected: The 1st internship at the first company will have the requirement java and python. Note that it overrides any existing requirements in that internship. (Note that if an application for this internship exist, these changes will be reflected as tags in blue boxes on the card in the application tab). Execute `switch app` to confirm it.
   
   1. Test case: `edit int 1 i/2 r/React r/CSS r/HTML` <br>
   Expected: The 2nd internship at the first company will have the requirement React native. Similarly to the above test case, any existing requirements in that internship will be overidden.
   
   1. Test case: `edit int 1` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.


**Editing an application**

1. Editing some fields in application.

   1. Prerequisites: The application must exist and be accessed via a valid index.
   
   1. Test case: `edit app 1 s/accepted` <br>
   Expected: The status that was on the card will be changed into a green status with the word accepted.
   
   1. Test case: `edit app` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Editing a profile item**

1. Editing some fields in the profile item.

   1. Prerequisites: The profile item must exist and be accessed via a valid index.
   
   1. Test case: `edit me 2 c/achievement t/Hackathon at Shoppee d/2nd place` <br>
   Expected: Its description on the right display will be 2nd place instead.
   
   1. Test case: `edit me` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.
   
**Viewing a company**

1. Viewing full information of a company.

   1. Prerequisites: The company item must exist and the card be access via a valid index. Also, having more than 2 cards and current right display is showing information of the first card. You can execute `view com 1` first.
   
   1. Test case: `view com 2` <br>
   Expected: The right display will change and show the full information of the 2nd company in the list.
   
   1. Test case: `view com 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `view com` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Viewing an application**

1. Viewing full information of an application.

   1. Prerequisites: The application item must exist and the card be access via a valid index. Also, having more than 2 cards and current right display is showing information of the first card. You can execute `view app 1` first.
   
   1. Test case: `view app 2` <br>
   Expected: The right display will change and show the full information of the 2nd application in the list.
   
   1. Test case: `view app 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `view app` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**View a profile Item**

1. Viewing full information of a profile item.

   1. Prerequisites: The profile item must exist and the card be access via a valid index. Also, having more than 2 cards and current right display is showing information of the first card. You can execute `view me 1` first.
   
   1. Test case: `view me 2` <br>
   Expected: The right display will change and show the full information of the 2nd profile item in the list.
   
   1. Test case: `view me 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `view me` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.
   
**Finding company/companies**

1. Finding specific keyword(s) in the list of company/companies.

   1. Prerequisites: List all companies using the `list com` command. At least one company. `find` method and `list` method works hand in hand.
   
   1. Test case: `find com google` <br>
   Expected: Any titles in the card that contains `google` will be matched. i.e `Google`, `Google Florida`. However `GoogleMalaysia` will not be matched.
   
   1. Test case: `find com` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Listing out all company/companies**

1. Listing out all company/companies.

   1. Prerequisites: Assuming that you have used `find` for company in the earlier manual testing.
   
   1. Test case: `list com` <br>
   Expected: All the companies that the user have will be displayed.
   
   1. Test case: `list com 2` <br>
   Expected: An error message will be shown, stating that there should not be any inputs after the `ITEM_TYPE`.

**Finding application(s)**

1. Finding specific keyword(s) in the list of application(s).

   1. Prerequisites: List all applications using the `list app` command. At least one application. `find` method and `list` method works hand in hand.
   
   1. Test case: `find app engineer` <br>
   Expected: Any titles in the card that contains `software` will be matched. i.e `Engineer`, `Front End Engineer`. However `FrontEndEngineer` will not be matched.
   
   1. Test case: `find app` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Listing out all application(s)**

1. Listing out all application(s).

   1. Prerequisites: Assuming that you have used `find` for application in the earlier manual testing.
   
   1. Test case: `list app` <br>
   Expected: All the application that the user have will be displayed.
   
   1. Test case: `list app 2` <br>
   Expected: An error message will be shown, stating that there should not be any inputs after the `ITEM_TYPE`.

**Finding profile item(s)**

1. Finding specific keyword(s) in the list of profile item(s).

   1. Prerequisites: List all profile items using the `list me` command. At least one profile item. `find` method and `list` method works hand in hand.
   
   1. Test case: `find me hackathon` <br>
   Expected: Any titles in the card that contains `hackathon` will be matched. i.e `Hackathon`, `2020 Hackathon`. However `ShoppeeHackathon` will not be matched.
   
   1. Test case: `find me` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.
   
**Listing out all profile item(s)**

1. Listing out all profile item(s).

   1. Prerequisites: Assuming that you have used `find` for profile items in the earlier manual testing.
   
   1. Test case: `list me` <br>
   Expected: All the profile items that the user have will be displayed.
   
   1. Test case: `list me 2` <br>
   Expected: An error message will be shown, stating that there should not be any inputs after the `ITEM_TYPE`.

**Deleting a company**

1. Deleting a module according to the index shown on the card.

   1. Prerequisites: The company item must exist and access via a valid index as indicated on a card.
   
   1. Test case: `delete com 2` <br>
   Expected: The second card will be deleted. Details of the deleted company can be seen in the result display. All the internships in this company will be deleted as well. Note that if there is an application that is linked to any internships that this company had, it will be deleted as well.
   
   1. Test case: `delete com 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case:`delete com` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Deleting an internship**

1. Deleting an internship from a certain company.

   1. Prerequisites: The company item and internship item must exist and both to be access via a valid index.
   
   1. Test case: `delete int 1 i/1` <br>
   Expected: On the right display, it will remove the internship item from the company.
   
   1. Test case: `delete int 1 i/0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `delete int 1` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Deleting an application**

1. Deleting an application.
   
   1. Prerequisites: The application item must exist and be access via a valid index.
   
   1. Test case: `delete app 1` <br>
   Expected: The first card will be deleted. Details of the deleted application can be seen in the result display.
   
   1. Test case: `delete app 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `delete app` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Deleting a profile item**

1. Deleting a profile item.
   
   1. Prerequisites: The profile item must exist and be access via a valid index. At least 2 profile items for this manual testing.
   
   1. Test case: `delete me 2` <br>
   Expected: The second card will be deleted. The rest of the cards will shift upwards with index being updated. Details of the deleted profile can be seen in the result display.
   
   1. Test case: `delete me 0` <br>
   Expected: An error message informing you that index is not a non-zero unsigned integer. The command box text will turn red to inform you of the invalid command.
   
   1. Test case: `delete me` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Switching of tabs**

1. Switching tabs.

   1. Prerequisites: Assuming you are on the company tab. If not execute `switch com`.
  
   1. Test case: `switch com` <br>
   Expected: A message will be displayed in the result display to inform you that you are already on the company tab.
  
   1. Test case: `switch app` <br>
   Expected: The top left will show the tab will be at application. A message will be displayed in the result display to inform you that you have switched to application tab. The screen should now display cards that holds application information and the right display will show information of the last know index of the application tab.
  
   1. Test case: `switch int` <br>
   Expected: An error message wil be shown, stating that int is a invalid item type for this case. The command box text will turn red to inform you of the invalid command.
  
   1. Test case: `switch` <br>
   Expected: An error message will be shown, showing information of how this command should be entered. The command box text will turn red to inform you of the invalid command.

**Matching skills to internship requirements**

1. Finding if any internships requirements matches the skills that you have.

   1. Prerequisites: There is some internships added and the skills that you have put into the profile matches the requirements in internship. Now that now you have learnt how to use `switch` and `view`, use `switch com` and `switch me` and the relevant `view` commands to check the skills as well as some internships requirements.
   
   1. Test case: `match` <br>
   Expected: A new window will pop-up and show you all the matched internships. Note that you can use <kbd>esc</kbd> on your keyboard to close the window.

**Viewing help**

1. Viewing help.

   1. Test case: `help` <br>
   Expected: Help window appears with InternHunter's user guide url. Note that you can use <kbd>esc</kbd> on your keyboard to close the window.

**Clearing all the data in the app**

1. Clearing all existing data in InternHunter.

   1. Prerequisites: Some data has been added to InternHunter.
   
   1. Test case: `clear` <br>
   Expected: All data cleared from all tabs.

**Saving the data**

1. Dealing with corrupted data files.

   1. Corrupt the current save file under ./data/. Edit the json with some random characters that make the JSON format unreadable. Alternatively, you could go to `profileitemlist.json` and add `-` to descriptors.

   1. Double-click the jar file <br>
   Expected: Shows the GUI with no data.

   1. Delete the current save file under ./data/.

   1. Double-click the jar file <br>
   Expected: Shows the GUI with no data.

<div style="page-break-after: always;"></div>

### Appendix G: Effort 

InternHunter is a challenging endeavour as it deals with 4 different entities (company, internship, application and
profile items) and much consideration has to be put into the interaction, management and storage of these entities
that seeks to reduce code duplication as much as possible by using Object Oriented Principles and other design
 patterns. 

To approach the management of different entities in the model. The abstract `Item` class is used a blueprint for
the other entities to extend. After which, `Itemlist` can be used as a collection type offering common methods to
manipulate the collection of different entities by leveraging on the polymorphism and dynamic binding. This negates
 the need for the creation of different individual collections types for each entity, thereby abiding to
 the DRY principle.

Another challenge faced was dealing the the close coupling of the company, internship and application objects whereby
company shares a composition relationship with its internships and an internship share a composition 
relationship with the application. This means that an internship cannot exists without the company it
belongs to and the application cannot exist without the internship that it is applied to. This creates a need for
interesting commands such as the delete company command which has a cascading effect, where a deletion of a company
will require its internships and the internship's applications to be deleted as well. This preserves data integrity
 of InternHunter by ensuring that data is consistent.

The UI has also been completely revamped to have a different look and feel from Address Book 3. It uses a tab
 interface for switching between and displaying the different entities of InternHunter and a right display panel to show
 the details of each entity item which the cards in the list cannot show.

<div style="page-break-after: always;"></div>

### Appendix H: Sequence Diagrams

<p align="center">Sequence diagram for handle delete display switch index</p>

<p id="handle-delete-display-switch-index-sequence-diagram" align="center"><img src="images/HandleDeleteDisplaySwitchIndexSequenceDiagram.png" width ="70%" height="70%"/></p>


--------------------------------------------------------------------------------------------------------------------
