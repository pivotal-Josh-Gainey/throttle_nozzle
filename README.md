# throttle_nozzle

This is a simple Spring Boot application that can be pushed to a Cloud Foundry deployment and serve as a Nozzle to consume from the firehose and be throttled to simulate slow consumers.

The application gets it's Cloud Foundry access credentials from environment variables passed in via the manifest.yml.

The credentials provided need to be able to access the firehose so lets create a user that has this priveledge. (or you can supply your own that have this access if desired.)
```java
uaac target --skip-ssl-validation <api.FQDN>
uaac token client get admin -s <admin-client-secret>
uaac client add throttle_nozzle \
  --name throttle_nozzle_user \
  --secret throttle_nozzle_password \
  --authorized_grant_types client_credentials,refresh_token \
  --authorities doppler.firehose,cloud_controller.global_auditor
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
