# 🏥 Hospital Management System (JavaFX + JDBC + MySQL)

A desktop-based **Hospital Management System** built using **Core Java, JavaFX, JDBC, and MySQL**.
This application helps manage patients, doctors, appointments, billing, and prescriptions with **role-based access control**.

---

# 🚀 Features

## 🔐 Authentication & Role-Based Access

* User login system (Admin, Doctor, Patient)
* Role-based UI rendering
* Session management

---

## 👤 Patient Management

* Add, update, delete patients
* View patient records in TableView
* Search & filter functionality

---

## 👨‍⚕️ Doctor Management

* Add/edit/delete doctors (Admin only)
* Search by name or specialization

---

## 📅 Appointment System

* Book appointments
* Prevent duplicate booking (same doctor, date & time)
* View appointments (role-based)

---

## 💰 Billing System

* Generate bills for appointments
* PDF generation using iText(In process)
---

## 💊 Prescription Module

* Doctors can create prescriptions
* Patients can view prescriptions

---

## 🔎 Search & Filter

* Real-time search for patients and doctors
* JavaFX FilteredList & SortedList used

---

## 🎨 UI/UX

* JavaFX-based responsive UI
* Single dashboard layout
* Dynamic content loading

---

# 🛠️ Tech Stack

| Layer        | Technology       |
| ------------ | ---------------- |
| Language     | Java (Core Java) |
| UI           | JavaFX           |
| Database     | MySQL            |
| Connectivity | JDBC             |
| Build Tool   | Maven            |
| PDF          | iText 7          |

---

# 📂 Project Structure

```
com.hms
│
├── controller      # UI Controllers
├── dao             # Database Access Layer
├── model           # Entity Classes
├── service         # Business Logic
├── utils           # DBConnection, Session
├── main            # Main Application
│
resources
└── fxml            # UI Layout Files
```

---

# 🧠 Architecture

```
Controller → Service → DAO → Database
```

* **Controller** → Handles UI
* **Service** → Business logic
* **DAO** → Database operations
* **Model** → Data objects

---

# 🔐 Role-Based Access

| Role    | Permissions                       |
| ------- | --------------------------------- |
| ADMIN   | Full access                       |
| DOCTOR  | Prescriptions, Appointments       |
| PATIENT | View appointments & prescriptions |

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/hospital-management-system.git
cd hospital-management-system
```

---

## 2️⃣ Configure Database

Create MySQL database:

```sql
CREATE DATABASE hospital_db;
USE hospital_db;
```

Import tables (patients, doctors, users, appointments, billing, prescriptions)

---

## 3️⃣ Update DB Connection

Edit:

```
DBConnection.java
```

```java
private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## 4️⃣ Run Project

```bash
mvn clean javafx:run
```

---

# 📸 Screens (Optional)

* Login Screen
* Dashboard
* Patient Module
* Appointment Module
---

# 🧪 Sample Credentials

| Role    | Username | Password |
| ------- | -------- | -------- |
| Admin   | admin    | admin123 |
| Doctor  | doc1     | doc123   |
| Patient | pat1     | pat123   |

---

# 🚀 Future Enhancements

* Password hashing (bcrypt)
* Email notifications
* REST API integration
* Mobile app version
* Advanced analytics dashboard

---

# 🐞 Known Issues

* JavaFX requires proper module-path configuration
* Maven run required (`mvn javafx:run`)

---

# 🙌 Author

**Aareen Anand**

* Fresher Java Developer
* Passionate about backend & system design

---

# ⭐ If you like this project

Give it a ⭐ on GitHub!

---
