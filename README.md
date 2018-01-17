# wfs4BIGIoT-Adapter

**wfs4BIGIoT-Adapter** implements a configurable generic adapter, that requests standardized geospatial data from any, freely selectable Web Feature Service, which complies with [OGC Web Feature Service Implementation Specification](http://www.opengeospatial.org/standards/wfs) and then takes the WFS data to provide a BIG IoT offering mapped on a flattened JSON schema on the [BIG IoT marketplace](https://market.big-iot.org/).

BIG IoT is a European project to enable IoT Ecosystems. The BIG IoT API and the BIG IoT Marketplace form an IoT 
ecosystem where European companies can exploit the business potential of the IoT sector.

enviroCar was successfully applied in the First Open Call of the EC funded BIG IoT project. Thus, the enviroCar 
project is one of the first projects to appear on the [BIG IoT marketplace](https://market.big-iot.org/).


## Libraries and Licenses

#### Third party libraries and licenses

|Library|License|Link/Source|
|:----:|:----:|:----:|
|Eclipse Bridge.IoT|Eclipse Public License 2.0|[https://projects.eclipse.org/proposals/eclipse-bridge.iot](https://projects.eclipse.org/proposals/eclipse-bridge.iot)|
|Spring-framework|Apache License Version 2.0|[https://github.com/spring-projects/spring-framework/blob/master/src/docs/dist/license.txt](https://github.com/spring-projects/spring-framework/blob/master/src/docs/dist/license.txt)|
|Simple Logging Facade for Java|MIT License|[https://www.slf4j.org/license.html](https://www.slf4j.org/license.html)|
|commons-logging, commons-io|Apache License Version 2.0|[https://commons.apache.org/proper/commons-bsf/license.html](https://commons.apache.org/proper/commons-bsf/license.html)|
|geotools|GNU Lesser Public License Version 2.1|[http://docs.geotools.org/latest/userguide/welcome/license.html](http://docs.geotools.org/latest/userguide/welcome/license.html)|
|squreup retrofit|Apache License Version 2.0|[https://github.com/square/retrofit/blob/master/LICENSE.txt](https://github.com/square/retrofit/blob/master/LICENSE.txt)|

#### wfs4BIGIoT-Adapter

|Library|License|Link/Source|
|:----:|:----:|:-----:|
|wfs4BIT-Adapter|GNU General Public License v3.0|[https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/LICENSE](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/LICENSE)|

## Installation

  1. Clone the repository: `git clone https://github.com/enviroCar/wfs4BIT-Adapter`.

  2. Build the project: `mvn clean build`.

## Configuration

* Register an organization and a provider:

  1. First, register an organization on the [BIG IoT marketplace](https://market.big-iot.org/).

  2. Second, create a provider.

#### Provide provider ID and SECRET:
* Once you've registered your provider, you can supply it's ID and SECRET:

  Create a file `secret.yml` in `wfs4BIT-Adapter\src\main\resources\` and replace ID and SECRET of your registered 
  provider:

  ```
  bigiot:
    provider:
        id: <YOUR_PROVIDER_ID>
        secret: <YOUR_PROVIDER_SECRET>
  ```
  You find ID and SECRET of your provider and consumer on the marketplace, when being logged in into your organization.

#### Configure application address and port:

* Change address in [line 12 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L12) and port in [line 13 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L13) to the specifications of your desire. The application.yml file is located in `wfs4BIT-Adapter\src\main\resources\`.

#### Specify the BIG IoT offering:

The information for the offering registration are located starting from [line 15 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L15). 
The `application.yml` file is located in `wfsadapter\src\main\resources\`.
You must specify the following specifications for your WFS and your desired BIG IoT offering:

| Config Parameter|Required|Description|Example|
|:------|:---:|:-------------:|:-----------|
| **`url`**| YES|The URL of your WFS including URL parameters request=GetFeature, typeName for the feature of interest, and outputFormat. Currently supported outputFormats are gml 2.X and gml 3.1. The URL should be URL-encoded. |[**url:** `http://processing.envirocar.org:9090/geoserver/cite/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=cite:roadsegments&outputFormat=text%2Fxml%3B%20subtype%3Dgml%2F2.1.2`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L17)  |
| **`local_id`**|YES| The local_id identifies this offering on the BIG IoT marketplace. It must be unique and distinguishable among all offerings within your organization and provider. | [**local_id:** wfsOfferings-roadsegments](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L18) |
| **`withInformation`**|YES| The withInformation consists of the name and a schema for your offering. It will be added to the offeringDescription with `.withInformation(new Information(NAME, new RDFType(SCHEMA)));` | See [example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L19-L21) |
| **`route`**|NO| The route is the `/bigiot/access/ROUTE` access point to your data on the marketplace. It will be added to the offeringDescription with `.withRoute(ROUTE);` |[**route:** roadsegments](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L23) |
| **`inCity`**|NO| The inCity specifies a name of a city, to which the data of your offering refer to. It will be added to the offeringDescription with `.inCity(INCITY)`|[**inCity:** Barcelona](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L24)|
| **`expireDate`**|NO| The expireDate specifies the DateTime until which your offering is marked as activated on the marketplace. Once, the exireDate is reached, the offering will become marked is not activated. The DateTime must be specified in `yyyy-MM-ddThh:mm:ss` (ISO8601 format).|[**expireDate**: 2018-02-30T01:23:45](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L25)|
| **`accessStreamTimeout`**|NO| The accessStreamTimeout specifies **TODO: finish this sentence**. The DateTime must be specified in `yyyy-MM-ddThh:mm:ss` (ISO8601 format).|[**accessStreamTimeout**: 2018-02-30T01:23:45](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L26)|
|**`licenseType`**|NO| The licenseType specifies the license of your offering's data. Possible options are `OPEN_DATA_LICENSE`,`CREATIVE_COMMONS`,`NON_COMMERCIAL_DATA_LICENSE`.| [**licenseType**: OPEN_DATA_LICENSE](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L27) |
|**`price`**|NO| The price must specify `pricingModel`, `price`, and `currency`. The pricingModel can be one of {FREE, PER_ACCESS, PER_BYTE, PER_MESSAGE, PER_MONTH }. The price must be a double value. The currency can be one of {Euros, USDollars}. | See [Example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L28-L33)|
| **`geometry`**|NO| If your WFS data contains features with default geometries, then you should specify the geometry property name and choose a schema. The geometry parameter is not required, but highly recommended, so that marketplace users can define and apply a custom spatial filter to your offering (see more at [section WFS Filter](#WFSFilter-Filter)). | see example [on GitHub](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L34-L36) |
| **`outputData`**|NO| You must specify the properties delivered by your WFS, that you want to add as OfferingOutputData to the BIG IoT marketplace offering. For each property, you must specify NAME and SCHEMA of the property. | see example [on GitHub](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L37-L147) |

## How to deploy
**_TODO_**

## WFS filter support

* #### maxFeatures
	**_TODO_**

* #### sortBy
	**_TODO_**

* #### bbox
	**_TODO_**

* #### Filter (#WFSFilter-Filter)
	**_TODO_**

## Bugs and Feedback
**_TODO_**


## Funding

This project has received funding from the European Union's Horizon 2020 research and innovation programme 
under grant agreement No 688038. 