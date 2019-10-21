# Lab01_TeamA-<br>

## Dev 1

### Collaborators
Bryson Devon Kene Keon Kirjan<br>

### Repository

**All changes are in master branch**<br>
https://github.com/professor-forward/lab01_TeamA-  <br>

### Firebase

**Link to firebase project:** https://console.firebase.google.com/project/fireapp-b8642/overview<br><br>

**If need to request accesss, send email to:** <br>
kohuladaskirjan@gmail.com<br>
kkohu030@uottawa.ca<br>

**Who has been invited to the firebase so far:**
>Dr.Andrew Forward: aforward@uottawa.ca <br>
>Aminur Ratul: mratu076@uottawa.ca <br>
>Junzheng Wu: jwu220@uottawa.ca <br>
>Dharama Shukla: dshuk066@uottawa.ca <br>
>Faezeh Halabian: fhala079@uottawa.ca <br>

**To use firebase without using my project, work around:**<br>
>1. Create new firebase project
>2. Create Database (select Realtime Database)
>3. Create Authentication
>4. downloade google-services.json file and place file in:
>>4a. Github Repository > app 
>5. In Android studio click:
>>5a. Tools > Firebase <br>
>>5b. Set up Realtime Databse and Authentication for project with the one you had created (Link your Firebase/Google account to your project)
>6. Everything should now be set up,<br> SignUp should send information to Authentication and Realtime Databse if non-existent and legal<br> LogIn should check Authenticator to LogIn and then database to decide EmployeeScreen or PatientScreen



### Classes, Dev 1

**User** - abstract class for the user roles<br>
**Employee** - specialization of User<br>
**Patient** - specialization of User<br>
**SignUpScreen** - Sign Up Activity <br>
**LogInScreen/MainActivity** - Log In Activity<br>
**PatientScreen** - After LogIn authentication, if UID belongs to Patient key, send to PatientScreen<br>
**EmployeeScreen** - After LogIn authentication, if UID 
belongs to Employee key, send to EmployeeScreen<br>
**AdministratorScreen** - After LogIn using hardcoded Admin
credentials, opens AdministratorSCreen<br>


### Explanation

>1. User opens WalkInClinic Application
>2. If User has LogIn credentials , User can Log In
>3. If User does not have account, click on Sign Up button in top right
>4. User will be taken to a Sign In screen, where they can enter their First Name, Last Name, Email Address, Password and select if they want an Employee or Patient account
>5. Once SignUp button is clicked, FireBase authenticator is used to check if account exists
>>5a. if account does NOT exist, the account is created and user gets a Toast message notifying them the account is created<br>
>>5b. If account does exist, the account is not created and user gets a Toast message notifying them the account cannot be created
>6. User account will be created referencing either a Patient or Employee object depending on what was chosen using Spinner/Drop Down at SignUp Activity
>7. Account email and UID is stored in FireBase authenticator
>8. User can then go back to LogIn page, by clicking button in top right
>9. User can sign in with his newly created Account
>10. Admin can sign in using hardcoded Admin LogIn credentials and be redirected to AdministratorScreen
>11. Once LogIn button is clicked, FireBase authenticator is used to LogIn User depending if credentials are correct
>12. User will then be redericted to a WelcomeScreen depending on what his UID was
>>12a. This is done using Firebase Database, where the UID from the authenticator is being used as a Key under Users/Patients or Users/Employees<br>
>>12b. If UID is a child of patient key then patient screen<br>
>>12c. if UID is a child of employee key then employee screen
>13. User will be displayed with a Welcome message





