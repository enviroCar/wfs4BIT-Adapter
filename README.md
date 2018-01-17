# ec4BIT - WFS Adapter

**e**nviro**C**ar - **B**IG **I**o**T** **WFS Adapter** implements an configurable generic adapter, that requests standardized geospatial data from any, freely selectable Web Feature Service, which complies with [OGC Web Feature Service Implementation Specification](http://www.opengeospatial.org/standards/wfs) and takes the WFS data to provide BIG IoT offerings on the [BIG IoT marketplace](https://market.big-iot.org/). The WFS data are mapped into a flattened JSON schema.

BIG IoT is a European project to enable IoT Ecosystems. The BIG IoT API and the BIG IoT Marketplace form an IoT 
ecosystem where European companies can exploit the business potential of the IoT sector.

enviroCar was successfully applied in the First Open Call of the EC funded BIG IoT project. Thus, the enviroCar 
project is one of the first projects to appear on the [BIG IoT marketplace](https://market.big-iot.org/).

## Funding

This project has received funding from the European Union's Horizon 2020 research and innovation programme 
under grant agreement No 688038.

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

#### ec4BIT - WFS Adapter
**_TODO_**

## Installation

  1. Clone the repository: `git clone https://github.com/enviroCar/ec4BIT`.

  2. Build the project: `mvn clean build`.

## Configuration

* Register an organization and a provider:

  1. First, register an organization on the [BIG IoT marketplace](https://market.big-iot.org/).

  2. Second, create a provider.

#### Provide provider ID and SECRET:
* Once you've registered your provider, you can supply it's ID and SECRET:

  Create a file `secret.yml` in `ec4BIT\connector\src\main\resources\` and replace ID and SECRET of your registered 
  provider:

  ```
  bigiot:
    provider:
        id: <YOUR_PROVIDER_ID>
        secret: <YOUR_PROVIDER_SECRET>
  ```
  You find ID and SECRET of your provider and consumer on the marketplace, when being logged in into your organization.

#### Configure application address and port:

* Change address in [`line 12 of application.yml`](https://LINK.com) and port in [`line 13 of application.yml`](https://LINK.com) to the specifications of your desire. The application.yml file is located in `wfsadapter\src\main\resources`.

#### Specify the BIG IoT offering:

The information for the offering registration are located starting from [`line 15 of application.yml`](https://LINK.com). 
The application.yml file is located in `wfsadapter\src\main\resources`.
You must specify the following specifications for your WFS and your desired BIG IoT offering:

| Config Parameter| Description|Example|
|:------|:-------------:|:-----------|
| [**`url`**](https://LINK.com)| The URL of your WFS including URL parameters request=GetFeature, typeName for the feature of interest, and outputFormat. Currently supported outputFormats are gml 2.X and gml 3.1. The URL should be URL-encoded. |**url:** [`http://processing.envirocar.org:9090/geoserver/cite/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=cite:roadsegments&outputFormat=text%2Fxml%3B%20subtype%3Dgml%2F2.1.2`](http://processing.envirocar.org:9090/geoserver/cite/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=cite:roadsegments&outputFormat=text%2Fxml%3B%20subtype%3Dgml%2F2.1.2)  |
| [**`local_id`**](https://LINK.com)| The local_id identifies this offering on the BIG IoT marketplace. It must be unique and distinguishable among all offerings within your organization and provider. | **local_id:** wfsOfferings-roadsegments |
| [**`offeringName`**](https://LINK.com)| The offeringName is the name of your offering. It will be added to the offeringDescription with `.withInformation(new Information(OFFERINGNAME, new RDFType(" .. ")));` | **offeringName:** WFS-OFFERING-ROADSEGMENTS |
| [**`offeringRoute`**](https://LINK.com)| The offeringRoute is the `/bigiot/access/OFFERINGROUTE` access point to your data on the marketplace. It will be added to the offeringDescription with `.withRoute(OFFERINGROUTE);` |**offeringRoute:** roadsegments |
| [**`offeringSchema`**](https://LINK.com)| The offeringSchema is the Category, to which your offered data belong to. It will be added to the offeringDescription with `.withInformation(new Information(" .. ", new RDFType(OFFERINGSCHEMA)));`| **offeringSchema:** http://schema.org/parkingSpaceManagement |
| [**`geometry`**](https://www.LINK.com)| You must specify the geometry property delivered by your WFS, that you want to add as OfferingOutputData to the BIG IoT marketplace offering. You must specify NAME and SCHEMA of the geometry property. | see example [on GitHub](https://www.LINK.com) |
| [**`outputData`**](https://www.LINK.com)| You must specify the properties delivered by your WFS, that you want to add as OfferingOutputData to the BIG IoT marketplace offering. For each property, you must specify NAME and SCHEMA of the property. | see example [on GitHub](https://www.LINK.com) |

## How to deploy
**_TODO_**

## Bugs and Feedback
**_TODO_**