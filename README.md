# Lab01_TeamA-<br>

# Dev 2
## Admin Manage Services - Add/Delete/Edit services
### Admin login credentials:<br>User: admin <br>Password: 5T5ptQ

### Collaborators

Bryson Devon Kene Keon Kirjan<br>

### Repository


**All changes for Dev 2 are in branch f/Deliverable 2**<br>
https://github.com/professor-forward/lab01_TeamA-/tree/f/Deliverable2  <br>

## Unit Tests

5 Unit Tests were created.

Two Unit Tests were created for the Sign Up page
One Unit Test was created for the Log In Page
Two Unit Tests were created for the ServiceScreen page

Unit Tests can be found at: https://github.com/professor-forward/lab01_TeamA-/tree/f/Deliverable2/app/src/test/java/com/teama/walkinclinic

### CirlceCI Integration
**For our CircleCI integration, it was not possible to create the test using the repository in the professor forward organization, this was due to not having enough credits. Instead I had created the same repository privately outside of the organization and ran the tests (forking is also disabled) **

**The results of the Unit tests**  <br>
[![CircleCI](https://circleci.com/gh/kprime21/Deliverable2CircleCI.svg?style=svg&circle-token=65bbf77c6582ac606f65e1297391b907ac4d7679)](https://circleci.com/gh/kprime21/Deliverable2CircleCI)


### Classes, Dev 2
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
**Service** - This is the service class that is used to instantiate objects of type Service. A service has a name and pay<br>
**ServiceScreen** - If admin clicks on manage services button, he is taken to a screen
where he can add/edit services and view all services<br>
**ManageServices** - If admin clicks on view all services button, he is taken 
to a new activity that has a listview where he can currently see the name of all services. If he clicks on a service, a new activity, ManageServicesPopUp, is pushed onto the stack<br>
**ManageServicesPopUp** - After clicking on a service, admin can see the services name
and pay, here he can choose to delete the service. If he does, the information is sent back and is returned through the intent and is sent back to the ManagaeServices activity, and the listview will be updated<br>
### Classes, Not related to Dev 2
**ManageUsers** - if admin clicks on view all users button, he is taken 
to a new activity that has a listview where he can currently see all the users. If he clicks on a user, a new activity, ManageUsersPopUp, is pushed onto the stack<br>
**ManageUsersPopUp** - After clicking on a user, admin can see the users name and email address, here he can choose to delete the service. If he does, the intent is sent back through the information is sent back and is returned through the intent and is sent back to the ManageUsers activity, and the listview will be updated<br>





### Explanation
>1. Once the admin logs in using his login credentials he is taken to his welcome screen
>2. He can then choose if he wants to manage services or manage users
>3. If he clicks on manage services he is taken to a new activity.
>4. In this new activity he can add or edit a service or view all services
>>4a. if he chooses to add a service he must enter a service name and a number for pay<br>
>>4b. If he chooses to edit a service he must enter a service name that already exists
>5. If he chooses to view all services, he is taken to a new activity, this activity contains a list view that lists all service names
>>5a. If he clicks on a service name he is taken to a new activity<br>
>>5b. In this new activity he can view the service name and pay, and he can choose to delete the service here, which will remove it from the list view and remove it from its key in firebase realtime database
### Explanation, Not related to Dev 2 
>6. If he clicks on manage users he is taken to a new activitiy, this activity contains a list view that lists all user names
>>6a. If he clicks on a users name he is taken to a new activity<br>
>>6b. In this new activity he can view the users email, and he can choose to delete the user here, which will remove it from the list view and remove it from its key in firebase realtime database