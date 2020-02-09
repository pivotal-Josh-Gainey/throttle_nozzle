# throttle_nozzle

This is a simple Spring Boot application that can be pushed to a Cloud Foundry deployment and serve as a Nozzle to consume from the firehose and be throttled to simulate slow consumers.

Clone the repo
```java
git clone https://github.com/pivotal-Josh-Gainey/throttle_nozzle.git && cd throttle_nozzle
```

The application gets it's Cloud Foundry access credentials from environment variables passed in via the manifest.yml.

The credentials provided need to be able to access the firehose so lets create a user that has this priveledge. (or you can supply your own that has this access.)
```java
uaac target --skip-ssl-validation <api.FQDN>
uaac token client get admin -s <admin-client-secret>
uaac user add throttlenozzle \
    --password throttlenozzle \
    --emails throttle@nozzle.com \
    && uaac member add doppler.firehose throttlenozzle
  ```
Once the user has been created - edit the manifest.yml to include the proper values:

Changing these values:
```java
  env:
    CFUSER: throttle_nozzle_user
    CFPASSWORD: throttle_nozzle_password
    CFAPI: <api.FQDN>
    SUBCRIPTIONID: ThrottleNozzle
```
Once the manifest looks ok, build the jar.
```java
./mvnw install
```

Then you can push to a Cloud Foundry deployment:
```java
cf push
```

Once the application is running, Navigate to the URL and the throttle controller is there.

