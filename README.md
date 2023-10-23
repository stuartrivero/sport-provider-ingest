# Sports Ingester API

## Local Development

To run it locally use:

```
  ./gradlew bootRun
```

NOTES:

* API KEYs are hardcoded - but ideally these should be easily changeable/removable
* Naive exception handling - using exceptions to fail when transforming or saving rather than return results
* More thorough testing of JSON Parsing needed
* More clarification is needed on the start and end dates. The zoneId when reading is parsed as UTC. Why are times not used?
* Only two countries are supported GB and US. 
* Validation is required for the inputs e.g. countryCodes, round count >= 1
* Data needs to be sanitized to avoid SQL injection
* What happens to unused fields? 
* Does the incoming json need to be saved?
* Incoming json needs to be sanitized for insecurities
* Would be better to have separate tables for courses and use foreign keys for them
* The db is currently in-memory which is obviously not production ready and would be deleted on restart
* It would be good to spin up the application and database using docker to be able to run test containers.
* The data could be put on a queue to avoid having the caller have to deal with downstream dependency problems
