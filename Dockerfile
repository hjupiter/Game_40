FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/cuarenta_game.jar /cuarenta_game/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/cuarenta_game/app.jar"]
