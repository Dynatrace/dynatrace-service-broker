# Ruxit Service Broker

This project is a self-hostable application that provides a service broker that proxies Ruxit credentials to applications.  This is typically useful for on-premise deployments of Cloud Foundry that wish to easily bind to an off-premise Ruxit deployment.  In order to facilitate self-hosting, the application is designed to work in [Cloud Foundry][p].

## Requirements

### Java, Maven
The application is written in Java and packaged as a self executable JAR file.  This enables it to run in anywhere that Java is available.  Building the application (required for deployment) requires [Maven][v].

## Deployment
_The following instructions assume that you have [created an account][c] and [installed the `cf` command line tool][l]._

In order to automate the deployment process as much as possible, the project contains a Cloud Foundry [manifest][m].  To deploy run the following commands:

```bash
$ mvn -Dmaven.test.skip=true clean install
$ cf push
```

### Environment Variables
Since the application is designed to work in a PaaS environment, all configuration is done with environment variables.  The `tenant`, `tenanttoken` and `server` value need to be provided by Ruxit.  All others are unique to a deployment.

| Key | Description
| --- | -----------
| `tenant` | Your Ruxit tenant ID is the unique identifier of your Ruxit environment. You can find it easily by looking at the URL in your browser when you are logged into your Ruxit environment. The subdomain {tenant} in https://{tenant}.live.ruxit.com represents your tenant ID.
| `tenanttoken` | The token for your Ruxit environment. You can find it in the deploy Ruxit section within your environment.
| `server` | The Ruxit server connection URL to connect to. Use `host:port` format for a specific port number. 
| `SECURITY_USER_NAME` | The username that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value.
| `SECURITY_USER_PASSWORD` | The password that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value

## Developing
The project is set up as a Maven project and doesn't have any special requirements beyond that.


## License
The project is released under version 2.0 of the [Apache License][a].

[a]: http://www.apache.org/licenses/LICENSE-2.0
[c]: http://docs.cloudfoundry.com/docs/dotcom/getting-started.html#signup
[l]: http://docs.cloudfoundry.com/docs/dotcom/getting-started.html#install-cf
[m]: manifest.yml
[p]: http://run.pivotal.io
[u]: http://www.famkruithof.net/uuid/uuidgen
[v]: http://maven.apache.org
