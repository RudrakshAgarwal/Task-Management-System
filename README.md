# Task Management System Application:
Welcome to the Task Management System application repository! This application is a simple task management system that allows users to manage their tasks, update task statuses, add new tasks, edit existing tasks, and delete tasks.

### Technologies Used
1. Java 17
2. Spring Boot
3. MS SQL Server for the database
4. JUnit for testing

### Setup and Run Instructions
To set up and run the Task Management System application on your local machine, please follow the steps below:

### Prerequisites
1. Java Development Kit (JDK) 17 or higher installed on your system.
2. MS SQL Server installed and running.
3. An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse (optional but recommended).

### Step 1: Clone the Repository
1. Open Git Bash.
2. Change the current working directory to where you want to clone the repository.
3. Clone the repository using the following command

git clone https://github.com/yourusername/task-management-system.git

### Step 2: Database Configuration
Before running the application, make sure you have set up the database:

1. Open the application.properties file located in the src/main/resources directory.
2. Modify the following properties according to your MS SQL Server setup:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=Task-Management-System-V3;encrypt=true;trustServerCertificate=true;
spring.datasource.username= `ms sql username`
spring.datasource.password= `ms sql password` 
```



### Step 3: Import the Project into Eclipse
1. Open Eclipse IDE.
2. Go to File > Import.
3. Select Maven > Existing Maven Projects and click Next.
4. Browse to the location where you cloned the repository and click Finish.

### Step 4: Build and Run the Application
1. In Eclipse's Package Explorer, right-click on the project and select Run As > Maven Build.
2. In the Goals field, enter spring-boot:run and click Run.


Alternatively, you can run the application using the command line:

```
cd path/to/task-management-system
mvn spring-boot:run
```


This will build and run the application. You can access it at http://localhost:8080 in your web browser.

### Step 5: Access the Application
Open your web browser and go to http://localhost:8080 to access the Task Management System application.

### Application Features
- View all tasks
- Add a new task
- Edit an existing task
- Update task status
- Delete a task


### Testing
The application includes JUnit tests to ensure its functionality is working correctly. To run the tests, right-click on the project, then select `Run As` > `JUnit Test`.


### Contribution
If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

Thank you for using the Task Management System application!



# Screenshots:
ViewTaskList

![Screenshot (17)](https://github.com/RudrakshAgarwal/Task-Management-System/assets/67146250/94d40eb7-e3ca-4bbd-8a67-4ab32f3eb749)

Edit Task

![Screenshot (18)](https://github.com/RudrakshAgarwal/Task-Management-System/assets/67146250/ce397497-bde3-4e0c-b743-c9b18fcd57fe)

Item Added Successfully:

![Screenshot (19)](https://github.com/RudrakshAgarwal/Task-Management-System/assets/67146250/ce05b6bc-df7c-408c-b1af-59fca22f5247)

Item Deleted Successfully:

![Screenshot (21)](https://github.com/RudrakshAgarwal/Task-Management-System/assets/67146250/58ec75ac-5ea1-4d78-88a1-efb42af44c3c)


