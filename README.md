## Delivery time estimator

Instructions:
 * Ensure machine has JDK 21
 * Testing `./gradlew test`
 * Add api key to configuration file `src/main/resources/application.properties`
 * Running the application `./gradlew bootRun`
 * Api can be tested from [Swagger Ui](http://localhost:8080/swagger-ui/index.html) or with the following curl command


      $ curl -X POST \
          -H "Accept: application/json" \
          -H "Content-Type: application/json" \
          -d '{"limit":10,"length":1}' \
          http://localhost:8080/estimate
