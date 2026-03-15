# Travel Insurance Purchase Application

A simple web application that allows users to purchase travel insurance policies through a multi-step form.

The application is built using:

- Spring Boot
- Thymeleaf
- SQLite
- Maven

---

# Prerequisites

Before running the application, ensure the following are installed.

## 1. Java Development Kit

Install **Java 21 or later**.

Verify installation:

```
java -version
```

You should see something similar to:

```
openjdk version "21"
```

---

## 2. Maven

Verify installation:

```
mvn -version
```

If Maven is not installed, follow the guide:

https://maven.apache.org/install.html

---

## 3. Git

Verify installation:

```
git --version
```

Download Git if needed:

https://git-scm.com/downloads

---

## 4. IDE (Optional)

You may run the project in any Java IDE such as:

- IntelliJ IDEA
- Eclipse IDE
- Visual Studio Code

---

# Clone the Repository

Clone the project from GitHub:

```
git clone https://github.com/Lilmissshar/TravelInsurance.git
```

Navigate into the project directory:

```
cd travel-insurance
```

---

# Pull the Latest Code from Master

If the repository already exists locally, update it with the latest changes:

```
git pull origin master
```

---

# Build the Application

Run the following command to build the project:

```
mvn clean install
```

This will:

- Download dependencies
- Compile the project
- Package the application

---

# Run the Application

You can run the application using Maven:

```
mvn spring-boot:run
```

Alternatively, run the main class:

```
TravelInsuranceApplication.java
```

from your IDE.

---

# Access the Application

Once the application starts, open a browser and navigate to:

```
http://localhost:8080
```

You should see the travel insurance purchase form.

---

# Database

The application uses **SQLite**.

The database file will be automatically created when the application runs:

```
insurance.db
```

It will appear in the **project root directory**.

To inspect the database, you can use:

**DB Browser for SQLite**

Download here:

https://sqlitebrowser.org/

---

# Application Flow

The purchase process follows a multi-step workflow:

1. **Step 1 – Select Coverage and Area**
2. **Step 2 – Select Plan**
3. **Step 3 – Enter Customer Details**
4. **Step 4 – Review Summary**
5. **Confirm – Policy saved to database**

---

# Project Structure

```
src/main/java/com/example/travel_insurance
```

Folders:

- `controller` – Handles HTTP requests
- `service` – Business logic
- `repository` – Database access
- `entity` – JPA entities
- `dto` – Data transfer objects
- `util` – Utility classes
- `enums` – Application enums

---

# Troubleshooting

## Port Already in Use

If port **8080** is already used, update `application.properties`:

```
server.port=8081
```

---

## Clean and Rebuild

If dependencies fail:

```
mvn clean install
```

---

# Author

Sharon Ong
