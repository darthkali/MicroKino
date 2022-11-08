
## Docker Image bauen 
Wird anhand der Dockerfile gebaut. Dieser Schritt kann durch die nutzung von Buildpacks übersprungen werden.

Aktuell pushen wir auf den namespace darthkali. Das sollten wir dann noch anpassen. Ich denke, in MC2 werden wir dann aber auf die GitHub Registry pushen. Da können wir das dann noch umbenennen.

zuerst den Gradle Task 'build' ausführen, damit das aktuelle Programm als JAR vorliegt, dann
```bash
docker build . -t darthkali/movies
```

## Docker image ausführen
```bash
docker run -p 8080:8080 darthkali/movies
```

## Stop Docker Container
container id ausgeben
```bash
docker ps
``` 

```bash
docker stop <container id>
``` 

## Docker Images auf Docker Hub pushen
```bash
docker push docker.io/darthkali/movies
```

