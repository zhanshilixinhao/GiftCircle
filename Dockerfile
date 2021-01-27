FROM java:8

ADD app.jar apptest.jar

ENTRYPOINT ["java","-jar","apptest.jar"]


