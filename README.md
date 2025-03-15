# Runnerz
A spring boot api application.

## # To run the project
```bash
./mvnw spring-boot:run
```

## # To test API endpoints - Using curl

1. Get: Getting a Run Info
```bash
curl -x GET localhost:8080/runs 

// or
curl -s localhost:8080/runs

// for pretty printing.. using json_pp or we can use jp
curl -s localhost:8080/runs | json_pp

```

2. Create: Creating A Run
```bash
curl -X POST localhost:8080/run \
        -H 'Content-Type: application/json' \
        -d '{ "id": 3, "title": "Third Run", "startOn" : "2025-03-15T21:33:05.104952507", "stopOn":"2025-03-15T21:34:05.104957073", "miles":20, "location":"INDOOR" }'
```