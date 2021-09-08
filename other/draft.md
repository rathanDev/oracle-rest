GetStarted
https://www.dropwizard.io/en/latest/getting-started.html

Continue Reading
https://www.dropwizard.io/en/latest/manual/core.html

# build image
docker build -t dropwizard/dropwizard-docker .

# run
docker run -p 8080:8080 dropwizard/dropwizard-docker





9/4/2021, 11:52:31 AM
-------------------------------------------------------------------------------------------
"CREATE TABLE ADMIN.sale_tb
(
prod_id NUMBER ,
cust_id VARCHAR2 (20) ,
"desc"  VARCHAR2 (20)
)
LOGGING"
Table ADMIN.SALE_TB created.


9/4/2021, 11:52:31 AM
-------------------------------------------------------------------------------------------
"ALTER TABLE ADMIN.sale_tb
ADD CONSTRAINT sale_tb_PK PRIMARY KEY ( prod_id )
USING INDEX LOGGING"
Table ADMIN.SALE_TB altered.

# ----------------------------------------------------------------------------------------------------------------------

MySQL db err
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'onePassword@123';

create table task_tb (
task_id int not null,
task_desc varchar(100) ,
task_date varchar(20) ,
primary key(task_id)
);

insert into task_tb values (1, 'Become React Guru', '2021-01-01'), (2, 'Learn Dropwizard', '2021-02-01');

select * from task_tb;

! java.sql.SQLException: Field 'task_id' doesn't have a default value

! Causing: javax.persistence.OptimisticLockException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [org.jana.dropwizard.entity.TaskEntity#8]

org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [org.jana.dropwizard.entity.TaskEntity#8]

ALTER TABLE task_tb RENAME COLUMN task_id TO id;

WARN  [2021-09-05 10:22:39,589] org.hibernate.engine.jdbc.spi.SqlExceptionHelper: SQL Error: 1064, SQLState: 42000
ERROR [2021-09-05 10:22:39,590] org.hibernate.engine.jdbc.spi.SqlExceptionHelper: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'desc='task4', status='COMPLETED' where id=4' at line 1


ERROR [2021-09-05 23:49:06,737] io.dropwizard.jersey.errors.LoggingExceptionMapper: Error handling a request: 8a9b9826e7a7cd82
! java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'desc='task8', status='PENDING' where id=8' at line 1

ERROR [2021-09-05 23:55:28,245] io.dropwizard.jersey.errors.LoggingExceptionMapper: Error handling a request: 415a2cd4dc2a0811
! org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [org.jana.dropwizard.entity.TaskEntity#8]

Dropwizard save not working
DEBUG [2021-09-06 14:36:36,106] org.eclipse.jetty.io.ManagedSelector: Selector sun.nio.ch.WindowsSelectorImpl@55a54d83 woken with none selected
DEBUG [2021-09-06 14:36:36,106] org.eclipse.jetty.io.WriteFlusher: ignored: WriteFlusher@7f342c6a{IDLE}->null
! java.util.concurrent.TimeoutException: Idle timeout expired: 30011/30000 ms
! at org.eclipse.jetty.io.IdleTimeout.checkIdleTimeout(IdleTimeout.java:171)
! at org.eclipse.jetty.io.IdleTimeout.idleCheck(IdleTimeout.java:113)
! at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
! at java.util.concurrent.FutureTask.run(FutureTask.java:266)
! at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$201(ScheduledThreadPoolExecutor.java:180)
! at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:293)
! at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
! at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
! at java.lang.Thread.run(Thread.java:748)

ERROR [2021-09-07 13:17:46,622] io.dropwizard.jersey.errors.LoggingExceptionMapper: Error handling a request: 1dbf8362bd76c20f
! org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) : [org.jana.dropwizard.entity.TaskEntity#8]

# ----------------------------------------------------------------------------------------------------------------------

mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=0.1.0



# ----------------------------------------------------------------------------------------------------------------------
# TODO 

* Clone from a git repo in Dockerfile
git clone https://github.com/spring-projects/spring-petclinic.git
cd spring-petclinic

* Add workdir 
FROM openjdk:16-alpine3.13
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]

* Run app as a non-root user
Refer  https://spring.io/guides/gs/spring-boot-docker/
  FROM openjdk:8-jdk-alpine
  RUN addgroup -S spring && adduser -S spring -G spring
  USER spring:spring
  ARG JAR_FILE=target/*.jar
  COPY ${JAR_FILE} app.jar
  ENTRYPOINT ["java","-jar","/app.jar"]



# ----------------------------------------------------------------------------------------------------------------------