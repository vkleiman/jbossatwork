# jbossatwork
Examples from Jboss At Work Book: Practical Guide\
By Tom Marrs and Scott Davis\
http://shop.oreilly.com/product/9780596007348.do

Implemented with Spring Boot, Postgres, Hibernate\
The completed site running on Google Compute Engine is at http://104.198.189.115:8080

Ch 3 - notes\
Uses in-memory datastore, to run locally checkout ch3 branch\
mvn install; java -jar target/jboss*.jar

Ch 4,5 - notes\
Uses JDBC, or JPA/Hibernate with Postgres\
to run locally checkout ch 5 branch
1. Install Postgres DB
2. Run SQL scripts under src/main/resources to setup tables
3. Set DB Username, Password in application.properties
4. mvn install; java -jar target/jboss*.jar
