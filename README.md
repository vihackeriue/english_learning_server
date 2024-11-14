# API English Learning Server

## Overview
This is a server-side API for an English learning application, built using Java and the Spring Framework.

### Key Technologies
- **Java**: The primary language used for building the API.
- **Spring Framework**:
  - **Spring Boot**: For easy configuration and setup of the application.
  - **Spring Security**: To handle authentication and authorization.
  - **Spring MVC**: For building RESTful APIs.

### Additional Features
- **Cloudinary**: Used to store and manage images.
- **MySQL**: Relational database for data persistence.

## API Endpoints

### Auth APIs
- **Login**
  - `POST` [http://localhost:8080/api/v1/auth/authenticate](http://localhost:8080/api/v1/auth/authenticate)

- **Sign Up**
  - `POST` [http://localhost:8080/api/v1/auth/register](http://localhost:8080/api/v1/auth/register)

- **Logout**
  - `POST` [http://localhost:8080/api/v1/auth/logout](http://localhost:8080/api/v1/auth/logout)

- **Refresh Token**
  - `POST` [http://localhost:8080/api/v1/auth/refresh-token](http://localhost:8080/api/v1/auth/refresh-token)

### User APIs
- **Get List of User Information**
  - `GET` [http://localhost:8080/api/v1/auth/users](http://localhost:8080/api/v1/auth/users)

- **Get User Information by ID**
  - `GET` [http://localhost:8080/api/v1/auth/users/{id}](http://localhost:8080/api/v1/auth/users/{id})

### Course APIs
- **Add Course**
  - `POST` [http://localhost:8080/api/v1/auth/courses](http://localhost:8080/api/v1/auth/courses)

- **Update Course**
  - `PUT` [http://localhost:8080/api/v1/auth/courses/{id}](http://localhost:8080/api/v1/auth/courses/{id})

- **Delete Course**
  - `DELETE` [http://localhost:8080/api/v1/auth/courses/{id}](http://localhost:8080/api/v1/auth/courses/{id})

- **Show Course List**
  - `GET` [http://localhost:8080/api/v1/auth/courses](http://localhost:8080/api/v1/auth/courses)

- **Show Course by ID**
  - `GET` [http://localhost:8080/api/v1/auth/courses/{id}](http://localhost:8080/api/v1/auth/courses/{id})

### Lesson APIs
- **Add Lesson**
  - `POST` [http://localhost:8080/api/v1/auth/lessons](http://localhost:8080/api/v1/auth/lessons)

- **Update Lesson**
  - `PUT` [http://localhost:8080/api/v1/auth/lessons/{id}](http://localhost:8080/api/v1/auth/lessons/{id})

- **Delete Lesson**
  - `DELETE` [http://localhost:8080/api/v1/auth/lessons/{id}](http://localhost:8080/api/v1/auth/lessons/{id})

- **Show Lesson List**
  - `GET` [http://localhost:8080/api/v1/auth/lessons](http://localhost:8080/api/v1/auth/lessons)

- **Show Lesson by ID**
  - `GET` [http://localhost:8080/api/v1/auth/lessons/{id}](http://localhost:8080/api/v1/auth/lessons/{id})

- **Show Lesson by courses id**
  - `GET` [http://localhost:8080/api/v1/auth/lessons/course/{id}](http://localhost:8080/api/v1/auth/lessons/course/{id})

- **Show Lesson by level**
  - `GET` [http://localhost:8080/api/v1/auth/lessons/level/{level}](http://localhost:8080/api/v1/auth/lessons/level/{level})

### UserCourse APIs
- **Add UserCourse**
  - `POST` [http://localhost:8080/api/v1/auth/userCourses/enroll?userId&courseId&studentCode&role&status](http://localhost:8080/api/v1/auth/userCourses/enroll?userId&courseId&studentCode&role&status)

- **Delete UserCourse**
  - `DELETE` [http://localhost:8080/api/v1/auth/lessons/{id}](http://localhost:8080/api/v1/auth/lessons/{id})

- **Show Lesson UserCourse**
  - `GET` [http://localhost:8080/api/v1/auth/userCourses](http://localhost:8080/api/v1/auth/userCourses)

- **Show UserCourse by ID**
  - `GET` [http://localhost:8080/api/v1/auth/userCourses/{id}](http://localhost:8080/api/v1/auth/userCourses/{id})

- **Show UserCourse by user id**
  - `GET` [http://localhost:8080/api/v1/auth/userCourses/user/{id}](http://localhost:8080/api/v1/auth/userCourses/user/{id})

### UserLesson APIs
- **Add User Lesson**
  - `POST` [http://localhost:8080/api/v1/auth/userLesson/startLesson?userId&courseId&lessonId](http://localhost:8080/api/v1/auth/userLesson/startLesson?userId&courseId&lessonId)

- **Delete User Lesson**
  - `DELETE` [http://localhost:8080/api/v1/auth/userLesson/{id}](http://localhost:8080/api/v1/auth/userLesson/{id})

- **Show User Lesson**
  - `GET` [http://localhost:8080/api/v1/auth/userLesson](http://localhost:8080/api/v1/auth/userLesson)

- **Show User Lesson by user id**
  - `GET` [http://localhost:8080/api/v1/auth/userLesson/{id}](http://localhost:8080/api/v1/auth/userLesson/{id})


---

## Installation and Setup
1. Clone the repository.
2. Set up a MySQL database and configure the database credentials in `application.properties`.
3. Configure Cloudinary settings in `application.properties` for image storage.
4. Run the application using your preferred IDE or via command line:
   ```bash
   ./mvnw spring-boot:run

![Alt text](b.png)

