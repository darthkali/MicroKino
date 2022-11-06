# MC2_MicroKino
Mobile Computing 2 Projekt WiSe2022 von Danny Steinbrecher und Christian Harders

<img width="572" alt="image" src="https://user-images.githubusercontent.com/46423967/200179175-4360981b-71a7-4493-bb55-2d0febdda00e.png">


## Präsentation zum Thema Microservices und Infrastruktur aus dem Masterprojekt
### PPTX
https://github.com/fh-erfurt/MC2_MicroKino/blob/main/Masterprojekt_Pr%C3%A4sentation.pptx

### PDF
[Masterprojekt_Präsentation.pdf](https://github.com/fh-erfurt/MC2_MicroKino/files/9821293/Masterprojekt_Prasentation.pdf)

## Naming Conventions

### Namespace
- de.fherfurt.xxx

### Service
#### PackageName
- cinemaservice
- movieservice

#### Modelname
- Cinema
- Movie

#### Reponame
- CinemaRepository
- MovieRepository

#### Applicationname
- CinemaServiceApplication
- MovieServiceApplication

#### Controller
- CinemaServiceController





## Pull Package von GitHub Registry
Um das Paackage welches ihr in eure private GitHub Registry deployed habt zu pullen, müsst ihr euch zunächst Authentifizieren. Das erfolgt über den folgenden Befehl

``` bash
  docker login ghcr.io
```

Hierbei werden ihr aufgefordert einen Usernamen und ein Passwort einzugeben. Für das Passswort benötigt ihr einen Personal Access Token. Diesen könnt Ihr euch unter eruem Account anlegen.


<img width="1117" alt="Bildschirmfoto 2022-10-11 um 14 11 22" src="https://user-images.githubusercontent.com/46423967/195088292-d74ce8ed-251d-4513-8f2f-db2f6e3d99b4.png">

Danach könnt Ihr das Package pullen:

``` bash
  docker pull ghcr.io/<namespace>/<package-name>
```

## Semgrep
https://github.com/marketplace/actions/semgrep-action

``` yaml
  semgrep:
    name: Scan
    runs-on: ubuntu-20.04
    container:
      image: returntocorp/semgrep
    steps:
    - uses: actions/checkout@v3
    - run: semgrep ci
 ```
