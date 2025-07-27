title: PlanMate - Your Smart plan Companion
description: |
  PlanMate is a responsive and user-friendly  planner app designed to help people to manage tasks and deadlines efficiently.
  Built using Java Spring Boot, React.js, and MySQL, the app provides countdowns, updates, deletions, and user-specific  plans.
  It’s accessible on both mobile and desktop browsers.


features:
  -  Add, update, delete study tasks
  -  Start and End date support with calendar picker
  -  Real-time overdue marking
  -  Tasks filtered by user login
  -  Fully responsive on mobile and desktop

tech_stack:
  frontend:
    name: React.js
    features:
      - Functional components with hooks
      - Axios for HTTP requests
      - Countdown logic in real-time
      - Responsive CSS (media queries, flexbox/grid)
  backend:
    name: Spring Boot
    features:
      - RESTful API with CRUD
      - Java with Spring MVC + JPA
      - User and StudyPlan entities
      - Service-layer separation
      - Deployed via Render or other Java host
  database:
    name: MySQL
    features:
      - Tables: `user`, `study_plan`
      - Foreign key between user and study_plan
      - Date/time columns supported (startDate, endDate)
      - Connection via `application.properties` in Spring Boot

project_structure:
  backend:
    - model/
    - controller/
    - service/
    - repository/
    - StudyPlannerApplication.java
    - application.properties
  frontend:
    - src/
      - components/
      - pages/
      - App.js
    - public/
    - package.json

installation:
  backend:
    - cd backend
    - ./mvnw clean install
    - ./mvnw spring-boot:run
    - Ensure MySQL is running and configured in application.properties
  frontend:
    - cd frontend
    - npm install
    - npm start
    - Opens at: http://localhost:3000
