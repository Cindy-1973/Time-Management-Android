# Time Management App

A Kotlin-based Android application that helps users manage their tasks by categorizing them into four priority quadrants based on urgency and importance. 
The app is built with Jetpack Compose and follows Material Design principles to provide a clean, responsive, and user-friendly interface.

---

## Features

- **Task Management**
  - Add, edit, delete, and view tasks
  - Mark tasks as finished
  - Unique task titles enforced by Room database

- **Task Categorization**
  - Organizes tasks into:
    1. Urgent & Important
    2. Urgent & Not Important
    3. Not Urgent but Important
    4. Not Urgent & Not Important

- **Pomodoro Technique**
- A 25-minute countdown with an inspiring phrase added in.
   
- **Profile Management**
  - Me screen to display user information
 
## Navigation

- Implemented with **Jetpack Navigation Compose**
- Screens:
  - Home
  - Add Task
  - Task List
  - Task Detail
  - Me 

---

## Room APIs

- Uses **Room APIs** for local storage of tasks

---

## Networking

- Uses **Retrofit** to connect to an external API (e.g., quotes API)  
- Displays a random motivational quote in the timer screen  
- Handles connectivity errors gracefully  

---

## Background Tasks

- Uses **WorkManager** to schedule background work for:  
  - Fetching and updating the motivational quote every 24 hours  
  - Scheduling task reminder notifications based on the task's `dueDate`  
- Work continues even if the app is closed or device restarts  

---

## Runtime Permissions

- Requests permission to **access the device calendar** at runtime  
- Once permission is granted, tasks can be **added to the user's calendar automatically**  
- This permission request is triggered when the user adds a new task with a due date  
- If permission is denied, the app will notify the user and disable calendar syncing for tasks  

## Tech Stack

- **Language**: Kotlin  
- **UI**: Jetpack Compose + Material 3  
- **Architecture**: MVVM (Model-View-ViewModel) + Repository pattern  
- **Dependency Injection**: Koin  
- **Local Database**: Room ORM  
- **Asynchronous**: Kotlin Coroutines + Flow  
- **Navigation**: Jetpack Navigation for Compose

---
