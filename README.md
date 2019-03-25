# Dynatrace Service Broker

This project is a self-hostable application that provides a service broker that proxies Dynatrace SaaS/Managed credentials to applications. This is typically useful for on-premise deployments of Cloud Foundry that wish to easily bind to Dynatrace SaaS or Managed clusters. In order to facilitate self-hosting, the application is designed to work in [Cloud Foundry][p].

## Requirements

### Java, Maven
The application is written in Java and packaged as a self executable JAR file.  This enables it to run in anywhere that Java is available. Building the application (required for deployment) requires [Maven][v].

## Deployment
_The following instructions assume that you have an account on a Cloud Foundry cluster and [installed the `cf` command line tool][l]._

In order to automate the deployment process as much as possible, the project contains a Cloud Foundry [manifest][m]. To deploy run the following commands:

```bash
$ mvn -Dmaven.test.skip=true clean install
$ cf push
```

### Environment Variables
Since the application is designed to work in a PaaS environment, all configuration is done with environment variables.
The `DYNATRACE_SERVICE_PLANS` value contains the JSON payload to connect to Dynatrace SaaS/Managed.   
This payload consists of `environmentid`, `apitoken`, `apiurl` and `skiperrors` values to connect to Dynatrace SaaS/Managed.

| Key | Description
| --- | -----------
| `environmentid` | Your Dynatrace environment ID is the unique identifier of your Dynatrace environment. You can find it in the deploy Dynatrace section within your environment.
| `apitoken` | The token for integrating your Dynatrace environment with Cloud Foundry. You can find it in the deploy Dynatrace section within your environment.
| `apiurl` | (Optional) The base URL of the Dynatrace API. If you are using Dynatrace Managed you will need to set this property to `https://<your-managed-server-url>/e/<environmentId>/api`. If you are using Dynatrace SaaS you don't need to set this property.
| `skiperrors` | (Optional) Skips errors during download of the OneAgent.

| Environment variable | Description
| -------------------- | -----------
| `DYNATRACE_SERVICE_PLANS` | The JSON payload for connecting to Dynatrace SaaS/Managed.
| `SECURITY_USER_NAME` | The username that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value.
| `SECURITY_USER_PASSWORD` | The password that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value

Sample JSON payload
```
DYNATRACE_SERVICE_PLANS: '{"A":{"name":"A","environmentid":"kwlxxxxx","apitoken":"xxxxxxxxxxx","apiurl":"https://yourmanagedcluster.com/api","skiperrors":"false"},"B":{"name":"B","environmentid":"pdexxxxx","apitoken":"yyyyyyyyyyyyy","apiurl":"https://yourmanagedcluster.com/api","skiperrors":"true"}}'
```

## Developing
The project is set up as a Maven project and doesn't have any special requirements beyond that.


## License
The project is released under version 2.0 of the [Apache License][a].

[a]: http://www.apache.org/licenses/LICENSE-2.0
[l]: https://github.com/cloudfoundry/cli/releases
[m]: manifest.yml
[p]: https://www.cloudfoundry.org/
[u]: http://www.famkruithof.net/uuid/uuidgen
[v]: http://maven.apache.org
