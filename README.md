# Lab01_TeamA-<br>

# Dev 4 - Patient Functionality
#### -Patient Functionality<br> 
### Patient Test Log In<br> username: patient<span></span>@gmail.com<br>password: patient


### Collaborators

Kirjan 300025125<br>
Devon 300056174<br>
Bryson 300078043<br>
Kene 300027974<br>
Keon 8607224<br>

### Repository

**All changes for Dev 4 are in branch f/Deliverable 4 (Default Branch)**<br>
 <br>

## Unit Tests
**Unit Tests can be found here**<br> 
https://github.com/professor-forward/lab01_TeamA-/tree/f/Deliverable4/app/src/test/java/com/teama/walkinclinic

**The 10 unit tests we had chosen to do, are related to:**<br>
-Creating a User that is an Employee or Patient<br>
-Creating a Service that the Patient will want to book an appointment for for when finding a Clinic<br>
-The Employee creating his Shift at a Clinic that he will be working at, in which the Patient will be booking an appointment for or writing a review<br>
-Creating a Clinic that the Patient will book an Appointment or Write a Review for<br>
-The Patient writing a Review for a Clinic, in which he can choose to show that he did not like the Clinic, A service, or an Employee




### CirlceCI Integration
For our CircleCI integration, just like Deliverable 2/3, it was not possible for me to create the test using the repository in the professor forward organization.

Instead I had copied the repository privately outside of the organization and ran the tests using that repository. 

Here is a link to the repository for the Deliverable 4 unit tests: https://github.com/kprime21/Deliverable4CircleCI

It contains all the code of our latest running model

Since the project is private, I had created a token in the API permissions on CircleCI to be able to pass over the **Passed** result as you can see below. 

**The results of the Unit tests**  <br>
[![CircleCI](https://circleci.com/gh/kprime21/Deliverable4CircleCI/tree/master.svg?style=svg&circle-token=90b62aeb960330eafb495a58693276ecca329dd6)](https://circleci.com/gh/kprime21/Deliverable4CircleCI/tree/master)

## Classes, Dev 4
**SelectServiceScreen** - After Patient logs in, he can search for a clinic by first selecting the active services that are offered by all available Clinics<br>
**SelectClinicScreen** - After he selects one of the available services, he then has a list view of all the available clinics that are offering that specific service<br>
**ClinicOptionsScreen** - After he has selected the Clinic, he can either book an appointment or write a review/rating for that Clinic<br>
**AppointmentScreen** - This screen is where the Patient will book an appointment, in the time slot for the Clinic he has chosen. Instead of setting a wait timer, I had<br>
decided to make it so that appointments are available in 15 minute intervals and that no two appointments can overlap (Unless it is for an emergency clinic, as it would be pointless
to have any sort of timer as the patient could be in critical condition)<br>
**ReviewScreen** - This screen is where the Patient will review the clinic by typing a Review message and Review rating (out of 5 stars), the patient can choose to 
ommit his message and only leave a star rating<br>
**Review** - This class is what is used to keep track of every Patients review<br>


## Classes Dev1/Dev2/Dev3
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
**Shift** - This is the Shift class that is used to instantiate objects of type Shift. A Shift has the hours and Clinic the employee works at<br>
**Clinic** - This is the Clinic class that is used to instantiate Clinics that are created by the Employee<br>
**ClinicInfoScreen** - **Create Clinics/Add Services to Clinicss** After Employee logs in, if he clicks on ManageClinics button he is taken to a page where he can create Clinic. Or **Update (BONUS)** a clinic, that is change it's working hours along with other info. If the info of a clinic is changed, it will have all its employees shifts deleted. **Employee can not leave Clinic info blank when creating a clinic, when updating a clinic employee must enter an existing clinic name and must have at least one payment type checked**<br>
**ManageClinics** - At the ManageClinics screen, the employee can choose to click on View Clinics, to view all the current made Clinics. If he selects a clinic on the list he is then taken to a page where he can add the predefined services which were made by the admin to that specific clinic<br>
**ClinicServiceScreen** - **Add/Remove services created by admin** At the ClinicServiceScreen, the employee can add services and remove services from that specific clinic. The Top listview represents services that are active in that specific clinic and the bottom listview represents services that are not active in the clinic. All you need to do is simply tap a service in either listview to make it move from one to the other<br>
**PickAClinicScreen** - going back to the EmployeeScreen when he first logs in. If he clicks on Add Shifts button, he then is taken to another screen to pick a clinic. This screen holds a single listview for the employee to select, to which he will add shift hours to that clinic he selected<br>
**WorkDaysScreen** - **Employee setting his Shift hours**, this screen is where the employee will choose his shift hours. He needs to first click on the calendar day, and then select a time that is in the time slot of the clinics operating hours (the clinics operating hours is displayed). He can only have one shift per day, but it can be at any clinic. **Employee must select a date on the calenderview first before entering and employee must also enter valid hours in between the time slot** <br>
**ManageShiftsScreen** - **Employee can view his shift hours**, going back to the EmployeeScreen. If he clicks on the View Shifts button, he then is taken to a listview where he can view all his shifts. He can not interact with any of the shifts here as he does not need to (Implementation not required)





## Explanation
>1. Once the patient logs in with an account that he had signed up with
>2. He then searches for his Clinic, by first selecting the service he is looking for and then he sees a list of Clinics that will offer that Service and selects that Clinic
>3. He can then either book an appointment or review a Clinic
>4. If he selects to book an appointment, he is taken to a screen where there will be two spinners. One spinner has the Clinics hours, and the hour is generated depending on that Clinic's operating hours. There is also a 15 minute interval for each appointment. If the time is already booked, the patient will get a toast message telling him to pick another time. Rather than making it so that there is a wait time, each patient has their own appointment time
>5. If he selects to review a Clinic, he is taken to a screen where there is a Rating widget and a text box. The patient can give the Clinic a rating out of 5 stars and he can, if he chooses to include a rating message
