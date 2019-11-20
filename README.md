# Lab01_TeamA-<br>

# Dev 3 - Employee Functionality
#### -Employee Functionality<br> - Add/Remove Services from Clinic<br>-Add Shifts to each Clinic<br>-(BONUS)Update Clinic Hours 

#### An employee account is needed in order to use employee functionality, create an employee account if you do not have one using the sign up page. If you would like, you can use this account, which I have created beforehand:<br><br>User: kingarthur<span></span>@gmail.com<br>Pass: kingarthur

### Collaborators

Kirjan<br>
Devon<br>
Bryson<br>
Kene<br>
Keon<br>

### Repository

**All changes for Dev 3 are in branch f/Deliverable 3 (Default Branch)**<br>
https://github.com/professor-forward/lab01_TeamA- <br>

## Unit Tests
**Unit Tests can be found here**<br> 
https://github.com/professor-forward/lab01_TeamA-/blob/f/Deliverable3/app/src/test/java/com/teama/walkinclinic/ShiftTest.java

https://github.com/professor-forward/lab01_TeamA-/blob/f/Deliverable3/app/src/test/java/com/teama/walkinclinic/ClinicTest.java

2 Unit Tests were created.

One Unit Test was created for the Clinic page
One Unit Test was created for the Shift page



### CirlceCI Integration
For our CircleCI integration, just like Deliverable 2, it was not possible for me to create the test using the repository in the professor forward organization.

Instead I had copied the repository privately outside of the organization and ran the tests using that repository. 

Here is a link to the repository for the Deliverable 3 unit tests: https://github.com/kprime21/Deliverable3CircleCI

It contains all the code of our latest running model

Since the project is private, I had created a token in the API permissions on CircleCI to be able to pass over the **Passed** result as you can see below. 

**The results of the Unit tests**  <br>
[![CircleCI](https://circleci.com/gh/kprime21/Deliverable3CircleCI/tree/master.svg?style=svg&circle-token=e897a1232afb1475642595d665d8eaeb3fc908bc)](https://circleci.com/gh/kprime21/Deliverable3CircleCI/tree/master)

## Classes, Dev 3
**Shift** - This is the Shift class that is used to instantiate objects of type Shift. A Shift has the hours and Clinic the employee works at<br>
**Clinic** - This is the Clinic class that is used to instantiate Clinics that are created by the Employee<br>
**ClinicInfoScreen** - **Create Clinics/Add Services to Clinicss** After Employee logs in, if he clicks on ManageClinics button he is taken to a page where he can create Clinic. Or **Update (BONUS)** a clinic, that is change it's working hours along with other info. If the info of a clinic is changed, it will have all its employees shifts deleted. **Employee can not leave Clinic info blank when creating a clinic, when updating a clinic employee must enter an existing clinic name and must have at least one payment type checked**<br>
**ManageClinics** - At the ManageClinics screen, the employee can choose to click on View Clinics, to view all the current made Clinics. If he selects a clinic on the list he is then taken to a page where he can add the predefined services which were made by the admin to that specific clinic<br>
**ClinicServiceScreen** - **Add/Remove services created by admin** At the ClinicServiceScreen, the employee can add services and remove services from that specific clinic. The Top listview represents services that are active in that specific clinic and the bottom listview represents services that are not active in the clinic. All you need to do is simply tap a service in either listview to make it move from one to the other<br>
**PickAClinicScreen** - going back to the EmployeeScreen when he first logs in. If he clicks on Add Shifts button, he then is taken to another screen to pick a clinic. This screen holds a single listview for the employee to select, to which he will add shift hours to that clinic he selected<br>
**WorkDaysScreen** - **Employee setting his Shift hours**, this screen is where the employee will choose his shift hours. He needs to first click on the calendar day, and then select a time that is in the time slot of the clinics operating hours (the clinics operating hours is displayed). He can only have one shift per day, but it can be at any clinic. **Employee must select a date on the calenderview first before entering and employee must also enter valid hours in between the time slot** <br>
**ManageShiftsScreen** - **Employee can view his shift hours**, going back to the EmployeeScreen. If he clicks on the View Shifts button, he then is taken to a listview where he can view all his shifts. He can not interact with any of the shifts here as he does not need to (Implementation not required)


## Classes Dev1/Dev2
**User** - abstract class for the user roles<br>
**Employee** - specialization of User<br>
**Patient** - specialization of User<br>
**SignUpScreen** - Sign Up Activity <br>
**LogInScreen/MainActivity** - Log In Activity<br>
**PatientScreen** - After LogIn authentication, if UID belongs to Patient key, send to PatientScreen<br>
**EmployeeScreen** - After LogIn authentication, if UID 
belongs to Employee key, send to EmployeeScreen<br>
**AdministratorScreen** - After LogIn using hardcoded Admin
credentials, opens AdministratorScreen, here admin can just to manage users 
or manage services services<br>
**Service** - This is the service class that is used to instantiate objects of type Service. A service has a name, pay, and role<br>
**ServiceScreen** - If admin clicks on manage services button, he is taken to a screen
where he can add/edit services and view all services<br>
**ManageServices** - If admin clicks on view all services button, he is taken 
to a new activity that has a listview where he can currently see the name of all services. If he clicks on a service, a new activity, ManageServicesPopUp, is pushed onto the stack<br>
**ManageServicesPopUp** - After clicking on a service, admin can see the services name, pay and role here he can choose to delete the service. If he does, the information is sent back and is returned through the intent and is sent back to the ManagaeServices activity, and the listview will be updated<br>
**ManageUsers** - if admin clicks on view all users button, he is taken 
to a new activity that has a listview where he can currently see all the users. If he clicks on a user, a new activity, ManageUsersPopUp, is pushed onto the stack<br>
**ManageUsersPopUp** - After clicking on a user, admin can see the users name and email address, here he can choose to delete the service. If he does, the intent is sent back through the information is sent back and is returned through the intent and is sent back to the ManageUsers activity, and the listview will be updated<br>





## Explanation
>1. Once the employee logs in with an account that he had signed up with
>2. He can then choose if he wants to manage clinics, view his shifts, or add a shift for himself. But he needs to add a clinic first if none exist
>3. If he clicks on manage clinics he is taken to a new activitiy where he can create a clinic, with a name, address, phone number, payment types, predefined operating hours.
>4. He can also Update a clinics info, but must type the name of the Clinic that he wants to update. If he updates a clinic, this will delete all the shifts that employees have for that clinic
>5. He can view all the clinics, and is taken to a list view, here he can select a clinic by simply tapping it, and then add the services he wants to that clinic
>6. The employee can that add shifts, by going back to the EmployeeScreen or employee login page. Here he can click Add Shift, select a clinic and then first click on a date on the calendar view and then enter valid hours
>7. The employee can then view all his respective shifts, by going back to EmployeeScreen and clicking on View Shifts. This will bring him to a listview where he can view all his shifts
