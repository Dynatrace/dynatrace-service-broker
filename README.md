# Dynatrace Service Broker

This project is a self-hostable application that provides a service broker that proxies Dynatrace SaaS/Managed credentials to applications.  This is typically useful for on-premise deployments of Cloud Foundry that wish to easily bind to Dynatrace SaaS or Managed clusters. In order to facilitate self-hosting, the application is designed to work in [Cloud Foundry][p].

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
  Since the application is designed to work in a PaaS environment, all configuration is done with environment variables.  The `environmentid`, `apitoken` and `endpoint` value need to be provided for connecting to Dynatrace SaaS/Managed. The `tenanttoken` is deprecated but supported tough.

| Key | Description
| --- | -----------
| `environmentid` | Your Dynatrace environment ID is the unique identifier of your Dynatrace environment. You can find it easily by looking at the URL in your browser when you are logged into your Dynatrace environment. The subdomain `<environmentid>` in `https://<environmentid>.live.dynatrace.com` represents your environment ID.
| `apitoken` | The token for integrating your Dynatrace environment with Cloud Foundry. You can find it in the deploy Dynatrace section within your environment. The `apitoken` replaces deprecated ~~`tenanttoken`~~ option.
| `endpoint` | The Dynatrace connection endpoint to connect to. For Dynatrace SaaS this is `https://<environmentid>.live.dynatrace.com`. If you are using Dynatrace Managed please specify the endpoint properly, e.g. `https://<your-managed-server-url>/e/<environmentid>`.
| `tenanttoken` | (deprecated) The token for agent communication. The `tenanttoken` was replaced by `apitoken`.
| `SECURITY_USER_NAME` | The username that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value.
| `SECURITY_USER_PASSWORD` | The password that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value

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
