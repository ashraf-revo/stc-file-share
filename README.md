## stc-file-share
this project contain 3 tasks as mentioned in the assessment
- see task1 in `com.asrevo.stcfileshare.Task1`
- see task2 in `com.asrevo.stcfileshare.Task2`


#### steps to run task 3
- run `gradle bootBuildImage` to build docker image
- run `docker-compose -f application-docker-compose.yml up` to bootstrap application


#### features in task 3
- using `spring-boot-docker-compose` this is the new way to run docker compose by spring 
- using `spring-boot-starter-webflux & webflux-security` this is using spring-reactive which utilize Projectreactor and Reactor Netty
- build `upload and downalod apis`
- using GitHub action's to build application
