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
The `DYNATRACE_SERVICE_PLANS` value contains the JSON payload to connect to your Dynatrace tenant.
This payload consists of the properties which were configured.

| Key           | Type    | Description                                                                                 | Required | Default         |
| ------------- | ------- | ------------------------------------------------------------------------------------------- | -------- | --------------- |
| name          | string  | Name of the plan. Used by the broker only, it is not passed on to the application.          | Yes      | N/A             |
| environmentid | string  | The ID for the Dynatrace environment.                                                       | Yes      | N/A             |
| apitoken      | string  | The API Token for the Dynatrace environment.                                                | Yes      | N/A             |
| apiurl        | string  | Overrides the default Dynatrace API URL to connect to.                                      | No       | Default API URL |
| customoneagenturl | string | Custom download URL for the OneAgent installer (paas-sh) | No | empty |
| skiperrors    | boolean | If true, the deployment doesn't fail if the Dynatrace agent download fails.                 | No       | false           |
| networkzone   | string  | If set, agent is configured to choose communication endpoints located at the field's value. | No       | empty           |
| enablefips    | boolean | If true, the [FIPS 140-2 mode](https://www.dynatrace.com/news/blog/dynatrace-achieves-fips-140-2-certification/) is enabled | No       | false           |
| removetechnologies| string | Removes OneAgent code-modules via a comma-separated list. This is applied **before** addtechnologies. | No | empty |
| addtechnologies| string | Adds additional OneAgent code-modules via a comma-separated list. See [supported values](https://docs.dynatrace.com/docs/dynatrace-api/environment-api/deployment/oneagent/download-oneagent-version#parameters) in the "included" row | No | empty |
| unconfigured | boolean | Must be set to true if an installer without the connection metadata is being used via customoneagenturl | No | false |
| tenanttoken | string | [Dynatrace Tenant token](https://docs.dynatrace.com/docs/manage/identity-access-management-classic/access-tokens-and-oauth-clients/access-tokens/rotate-tenant-token) | when unconfigured is set to true | empty |
|dtconnectionpoint | string | [Connection endpoints](https://docs.dynatrace.com/docs/dynatrace-api/environment-api/deployment/oneagent/get-connectivity-info) | when unconfigured is set to true | empty |


| Environment variable | Description
| -------------------- | -----------
| `DYNATRACE_SERVICE_PLANS` | The JSON payload for connecting to Dynatrace SaaS/Managed.
| `SECURITY_USER_NAME` | The username that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value.
| `SECURITY_USER_PASSWORD` | The password that the Cloud Foundry Cloud Controller should use to authenticate.  This can be any value

Sample JSON payload
```
DYNATRACE_SERVICE_PLANS: '{"A":{"name":"A","environmentid":"kwlxxxxx","apitoken":"xxxxxxxxxxx","apiurl":"https://yourmanagedcluster.com/api","skiperrors":"false","networkzone":"xxxxx"},"B":{"name":"B","environmentid":"pdexxxxx","apitoken":"yyyyyyyyyyyyy","apiurl":"https://yourmanagedcluster.com/api","skiperrors":"true","networkzone":"yyyyy"}}'
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

Last Update: 30.4.2021
