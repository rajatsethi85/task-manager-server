# Task-manager
This repo holds the server code for task manager application, Under this i have developed API's to create, edit, view and delete the tasks. Currently only Admin can create and edit tasks and other users can view and update the statua of tasks and also can add comments.

Implementation:
1. Created a springboot application and implemented the spring security and JWT based authentication to secure the app.
2. Connect the app to mysql db to persist the data in DB.
3. When we run the application for 
4. Created interface throughout the app.
5. Create the app image with docker.

Run application on Intellij:
1. Pull the code from https://github.com/rajatsethi85/task-manager-server.git
2. I have made this repo public so that everyone can access.
3. Open the pom.xml file on intellij as a project and let it build.
4. Add the required properties in application.properties file to connect to mysq DB.
5. When this application starts for first time a new Admin account will be created with email: admin@test.com and password: admin
6. After this run the client application https://github.com/rajatsethi85/task-manager-client.git and login using the credentials.
7. Currently there will be only one admin user.
8. You can create new contributor account as well from UI.
9. Run maven command mvn clean install to build the project or you can directly run the springboot app.
10. Application will run on port 8080 by default.up

Run application using docker:
1. Install Docker on your machine.
2. Go to the root file of the app and run below command:
    1. docker build -t task-manager
    2. docker run -d -p 8080:8080 --name task-manager-container task-manager-server
3. This will build the project create an image and run it on a container.
4. Now you can access the endpoints using localhost:8080.

NOTES:
1. To hit the API you should have the valid api key.
2. docker file is the part of the source code.

