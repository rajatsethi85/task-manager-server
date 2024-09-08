# Task-manager
This repo holds the server code to fetch the weather forecast report from the Open weather API.

Implementation:
1. Created a springboot application and implemented the spring security and JWT based authentication to secure the app.
3. Created interface throughout the app.
5. Create the app image with docker.

Run application on Intellij:
1. Pull the code from https://github.com/rajatsethi85/task-manager-server.git
2. I have made this repo public so that everyone can access.
3. Open the pom.xml file on intellij as a project and let it build.
4. Run maven command mvn clean install.
5. Application will run on port 8080 by default.

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

