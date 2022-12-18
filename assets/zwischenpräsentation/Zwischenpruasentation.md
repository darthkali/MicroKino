---
theme: css/cctheme/cctheme.css
---

# µKino
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/fh-erfurt/MicroKino/bookingservice.yml?branch=main&label=Booking&style=for-the-badge)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/fh-erfurt/MicroKino/cinemaservice.yml?branch=main&label=Cinema&style=for-the-badge)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/fh-erfurt/MicroKino/movieservice.yml?branch=main&label=Movie&style=for-the-badge)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/fh-erfurt/MicroKino/showservice.yml?branch=main&label=Show&style=for-the-badge)

**Mobile Computing 2 - WiSe 2022**

Danny Steinbrecher und Christian Harders

---
# Projekt Struktur
--
## Architektur
![image](https://user-images.githubusercontent.com/46423967/205976256-8cf2b721-6b22-4d1a-8734-2d0e780b96ac.png)


--
## Pipeline
![image](https://user-images.githubusercontent.com/46423967/205896206-3bc56bc8-43f6-4e49-ac21-29e577fb59ab.png)

--
## Services in IntelliJ laden
Service als Modul in den Projekteinstellungen laden:

![new-module-project-structure](https://user-images.githubusercontent.com/46423967/208294512-6b5f1ea4-7fc1-46af-b05b-c1a7116d39a0.png)


---

# Kafka

--

Wir haben Kafka exemplarisch zwischen Movie und Show implementiert.
Der Endpunkt 

`/show/details/{showId}`

nutzt ein Kafka-Template und den Request-Response-Mechanismus, um asynchron Filmdetails aus dem Movieservice zu erfragen, die anschließend gemeinsam mit den Informationen zur Filmvorstellung zurückgegeben werden.

- [Template](https://github.com/fh-erfurt/MicroKino/blob/main/showservice/src/main/kotlin/de/fherfurt/showservice/messaging/KafkaConfig.kt#L21-L41 "Templates"),
- [Request-Implementierung](https://github.com/fh-erfurt/MicroKino/blob/main/showservice/src/main/kotlin/de/fherfurt/showservice/ShowServiceController.kt#L46-L60 "Request"),
- [Response-Implementierung](https://github.com/fh-erfurt/MicroKino/blob/main/movieservice/src/main/kotlin/de/fherfurt/movieservice/messaging/MovieResult.kt#L10-L22 "Response")

--
## Template
[Showservice]
```kotlin
@Bean
fun replyingKafkaTemplate(
	producerFactory: ProducerFactory<String?, Long>?,
	factory: ConcurrentKafkaListenerContainerFactory<String?, Movie?>
): ReplyingKafkaTemplate<String?, Long, Movie?> {
	val replyContainer: ConcurrentMessageListenerContainer<String?, Movie?> = factory.createContainer(replyTopic)
	replyContainer.containerProperties.isMissingTopicsFatal = false
	replyContainer.containerProperties.setGroupId(groupId!!)
	return ReplyingKafkaTemplate(producerFactory, replyContainer)
}
```


  <aside class="notes">    
  
- String = ShowId
- Long = MovieId


- Rückgabe:
	- String = ShowId
   - Long = MovieId
   - Movie - Movie als JSON
  </aside>
  
--

## Request-Implementierung
[Showservice]

```kotlin
@GetMapping("/show/details/{showId}")
fun getObject(@PathVariable(value = "showId") showId: Long): ResponseEntity<String?>? {
	val show = showRepository?.findShowById(showId)
	val movieId = show?.movieId

	val record: ProducerRecord<String?, Long> =
		ProducerRecord(requestTopic, 0, show?.id.toString(), movieId)
	val future: RequestReplyFuture<String?, Long, Movie?> = 
							replyingKafkaTemplate!!.sendAndReceive(record)
	val response: ConsumerRecord<String?, Movie?>? = future.get()

	val mapper = ObjectMapper().registerKotlinModule()
	val movie = mapper.readValue<Movie?>(response!!.value().toString())

	return ResponseEntity<String?>(mapper.writeValueAsString(movie) + show, HttpStatus.OK)
}
```

--
## Response-Implementierung
[Movieservice]

```kotlin
@Component
class MovieResult {
    @Autowired
    val movieRepository: MovieRepository? = null

    @KafkaListener(topics = ["\${kafka.reuest.topic}"], groupId = "\${kafka.group.id}")
    @SendTo
    fun handle(movieId: Long): String? {
        val movie = movieRepository?.findMovieById(movieId)

        val mapper = ObjectMapper()
        return mapper.writeValueAsString(movie)
    }
}
```

--

## Anmerkung zu Spring REST-Mappings

```
    @GetMapping("/show/list")
    fun getAllMovies(): List<Show>? {
        return showRepository?.findAll()?.toList()
    }

    @GetMapping("/show/{showId}")
    fun getShowById(@PathVariable(value = "showId") showId: Long): Show? {
        return showRepository?.findShowById(showId)
    }

    @PostMapping("/show/remove")
    fun removeShow(@RequestBody show: Show) {
        showRepository?.delete(show);
    }
```

<aside class="notes">    
  
- /show/remove
- NumberFormatException
- /show/{showId}

</aside>

---


# Traefik

--

.. wird über die [docker-compose.yml](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/common_infrastructure.yml#L4-L38) konfiguriert.
Wir haben für jeden Service einen eigenen Router erstellt.
Da Traefik direkt an spezifische Container routen kann, kann jeder Service den selben Port nutzen (in unserem Fall in den jeweiligen application.properties konfiguriert, wir nutzen 8090). Weil wir die Ports allerdings nicht exposen - somit keine "ports"-Definition angeben, müssen wir dem jeweiligen Router noch den Port mitteilen.
Bei Spring muss zusätzlich beachtet werden, dass der jeweilige Webserver standardmäßig auf 'localhost' gebunden wird. Das funktioniert wiederum mit Docker nicht - die Adresse muss (ebenfalls in application.properties) auf 0.0.0.0 geändert werden.

```yaml
# Auszug aus docker-compose.yml
myservice:
    image: repo/myImage
    labels:
      - "traefik.enable=true"
      - "traefik.http.routers.myservice.rule=PathPrefix(`/myservice_prefix`)"   # [1]
      - "traefik.http.services.myservice.loadbalancer.server.port=8090"         # [3]
```

```properties
# Auszug Spring Modul application.properties
server.port=8090        # [2]
server.address=0.0.0.0  # [4]
```

---

# Build Prozess - Continuous Integration

--

Jeder Service besitzt eine eigene Dockerfile, in der wir ein Multi-Stage-Docker-Image bauen. Hier wird im ersten Schritt der Service mittels Gradle gebaut und anschließend daraus das Docker Image erzeugt.

```dockerfile
# Multi-stage Docker Image Build
FROM gradle:jdk17 as build
WORKDIR /workspace/app

# Copy Gradle Config
COPY build.gradle.kts .
COPY settings.gradle.kts .

# First gradle run without src to pull all dependencies
# Just ignoring errors
RUN gradle build 2>/dev/null || true

# copy source and build again
COPY src src
RUN gradle build

# Final Image that will contain the application created by above build image
FROM openjdk:17

ENV LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --from=build --chown=185 /workspace/app/build/libs/bookingservice-0.0.1-SNAPSHOT.jar bookingservice-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/bookingservice-0.0.1-SNAPSHOT.jar"]
```

--

## GitHub Actions Workflow

```yml
name: "movieservice"

on:
  ...

env:
  ...

defaults:
  ...

jobs:
  build-and-push-image:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3
        ...

      - name: Log in to the Container registry
        ...

      - name: Extract metadata (tags, labels) for Docker
        ...

      - name: Build and push Docker image
        uses: docker/build-push-action@ad44023a93711e3deb337508980b4b5e9bcdc5dc
        with:
          context: ./movieservice/
          file: ./movieservice/Dockerfile
          ...
```

<aside class="notes">    
  
- kein eigener Gradle Build implementiert
- lediglich Checkout, das Login in die Container-Registry und im Anschluss das Bauen sowie das Pushen des Docker Images
- separater Test Step   nicht nötig
-  dieser  beim Build des Services ausgeführt
- Schlägt dieser fehl, läuft der Workflow nicht durch und gibt die passende Fehlermeldung aus

</aside>
---

# Local Deployment

--

Hier können wir die gesamte Infrastruktur lokal hochfahren, und müssen nicht jede Änderung der Services hochladen und warten, bis die Packages gebaut werden.

```yml

version: "3.8"

services:
  gateway:
    image: "traefik:v2.9"
    ...
    
  movieservice:
    build:
      context: ../movieservice
      dockerfile: ../movieservice/Dockerfile
    image: "fh-erfurt/microkino:movieservice"
    ...
    
  cinemaservice:
    build:
      context: ../cinemaservice
      dockerfile: ../cinemaservice/Dockerfile
    image: "fh-erfurt/microkino:cinemaservice"
    ...
    
  ...
```

--

Bei uns ist beim Bauen der Anwendung folgender Fehler aufgetreten:
```shell
Gradle build daemon disappeared unexpectedly (it may have been killed or may have crashed)
```
 Das liegt daran, dass der Docker Deamon nicht genügend Arbeitsspeicher zur verfügung hat. Unsere initiale Lösung ist es dem Deamon in den Settings im Docker Desktop mehr Speicher zuzuweisen:
 

<img width="500" alt="Bildschirmfoto 2022-10-11 um 14 11 22" src="https://user-images.githubusercontent.com/46423967/208297991-4474935e-23ee-4592-8af4-030b23f7acab.png">
--

## Produktions- und Testbetrieb
Diese Compose Datei nutzt die Packages, welche automatisiert in den GitHub Action Workflows erzeugt werden. Diese wird dann für den Produktions- und Testbetrieb (Deploymentprozess) genutzt.


```yml
version: "3.8"

services:
  gateway:
    image: "traefik:v2.9"
    ...

  movieservice:
    image: ghcr.io/fh-erfurt/microkino:movieservice
    ...
    
  cinemaservice:
    image: ghcr.io/fh-erfurt/microkino:cinemaservice
    ...
 
  ...
```

--

Wenn mehrere Compose-Files im selben Verzeichnis liegen, kann man sie mit dem '-f' Flag spezifizieren:
```bash
docker compose -f compose-local.yml up -d --build --force-recreate
```
>  **Anmerkung**: beim wiederholten Erzeugen von Container Images mit identischen Tags werden die bestehenden Images nicht überschrieben, sondern nur die Tags gelöscht.
>  Alte Images bleiben dann einfach ungenutzt liegen - in unserem Fall sind diese jeweils > 500mb. Mit dem Befehl
>  ```bash
>  docker image prune -f
>  ```
>  können diese entfernt werden.
>  In unserem Projekt übernimmt das die ausführbare Datei [recreate-local.bat](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/recreate-local.bat) bzw. [recreate-local.sh](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/recreate-local.sh).

--

### Pull Package von GitHub Registry
Um das Package welches ihr in eure private GitHub Registry deployed habt zu pullen, müsst ihr euch zunächst Authentifizieren. Das erfolgt über den folgenden Befehl

```shell
  docker login ghcr.io
```

Hierbei werdet ihr aufgefordert einen **Usernamen** und ein **Passwort** einzugeben. Für das Passwort benötigt ihr einen **Personal Access Token**. Diesen könnt Ihr euch unter eurem Account anlegen.

Danach könnt Ihr das Package pullen:

```bash
  docker pull ghcr.io/<namespace>/<package-name>
```

--

## Recycling

Beispiel aus [compose-local.yml](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/compose-local.yml)
```yml
version: "2.4"

services:
  gateway:
    extends:
      file: common_infrastructure.yml
      service: gateway

  zookeeper:
    extends:
      file: common_infrastructure.yml
      service: zookeeper

  kafka:
    extends:
      file: common_infrastructure.yml
      service: kafka

  movieservice_db:
    extends:
      file: common_infrastructure.yml
      service: db

  movieservice:
    extends:
      file: microservices.yml
      service: movieservice
    build:
      context: ../movieservice
      dockerfile: ../movieservice/Dockerfile
    image: "fh-erfurt/microkino:movieservice"
    depends_on:
      - kafka
      - movieservice_db
```

--

Und ein Part aus [common_infrastructure.yml](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/common_infrastructure)
```yml
  zookeeper:
    image: wurstmeister/zookeeper
    command: [
      "sh", "-c",
      "start-zk.sh config/zookeeper.properties"
    ]
    ports:
      - "2181:2181"
    environment:
      LOG_DIR: /tmp/logs

  kafka:
    image: wurstmeister/kafka
    command: [
      "sh", "-c",
      "start-kafka.sh config/server.properties --override listeners=$${KAFKA_LISTENERS} --override advertised.listeners=$${KAFKA_ADVERTISED_LISTENERS} --override zookeeper.connect=$${KAFKA_ZOOKEEPER_CONNECT}"
    ]
    ports:
      - "9092:9092"
    environment:
      LOG_DIR: "/tmp/logs"
      #KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      #KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'false'
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
    volumes:
      - ${PATH_PREFIX}/var/run/docker.sock:/var/run/docker.sock:ro
```

--

## Windows vs. Unix
Im obenstehenden Beispiel taucht die Umgebungsvariable **${PATH_PREFIX}** auf. Dateipfadangaben für Docker Volumes erfordern unter Windows zu Beginn einen zusätzlichen Slash **/**,
der über die Datei */infrastructure/.env* definiert wird. Standardmäßig heißt die Datei [.env.removeThisExtensionOnWindows](https://github.com/fh-erfurt/MicroKino/blob/main/infrastructure/.env.removeThisExtensionOnWindows) - bei Bedarf löscht man einfach die Endung. Andernfalls ersetzt Compose die unbekannte Variable durch einen leeren String und macht sie somit wie gewünscht unwirksam.

---

### Kubernetes
Damit gehts weiter:

<img width="300" src="https://user-images.githubusercontent.com/46423967/208298160-868304d4-7e04-4db6-bb72-6e5cf2901bec.png">
