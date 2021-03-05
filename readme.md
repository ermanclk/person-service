Person-service


A basic service that provides crud operations for given person data.

-- contains unit tests only for service
-- docker build plugin and docker file added in pom.xml to provide docker container and easy containerize process
-- uses embedded h2 database so no need to installation of database


To run the service :

use maven plugin to build:
 
1.import project using git version control,
2.run > maven install
3.run > docker build
4.run > docker run

run all commands by plugin. You can also just run application by using directly idea without using docker container.
There is a "person-service.postman_collection.json" in source, it can be imported into Postman for testing endpoints.