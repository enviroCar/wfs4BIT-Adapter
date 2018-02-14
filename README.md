# wfs4BIGIoT-Adapter

**wfs4BIGIoT-Adapter** implements a configurable generic adapter, that requests standardized geospatial data from any, freely selectable Web Feature Service, which complies with [OGC Web Feature Service Implementation Specification](http://www.opengeospatial.org/standards/wfs) and then takes the WFS data to provide a BIG IoT offering mapped on a flattened JSON schema on the [BIG IoT marketplace](https://market.big-iot.org/).

BIG IoT is a European project to enable IoT Ecosystems. The BIG IoT API and the BIG IoT Marketplace form an IoT 
ecosystem where European companies can exploit the business potential of the IoT sector.


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
|Joda-Time|Apache License Version 2.0|[http://joda-time.sourceforge.net/license.html](http://joda-time.sourceforge.net/license.html)|

#### wfs4BIGIoT-Adapter

|Library|License|Link/Source|
|:----:|:----:|:-----:|
|wfs4BIT-Adapter|GNU General Public License v3.0|[https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/LICENSE](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/LICENSE)|

## Installation

  1. Clone the repository: `git clone https://github.com/enviroCar/wfs4BIT-Adapter`.

  2. Build the project: `mvn clean install`.

## Configuration

#### Register an organization and a provider:

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
  You find ID and SECRET of your provider on the marketplace, when being logged in into your organization.

#### Configure application address and port:

* Change address in [line 12 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L12) and port in [line 13 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L13) to the specifications of your desire. The application.yml file is located in `wfs4BIT-Adapter\src\main\resources\`.

#### Specify the WFS:

Specify the parameters for your Web Featire Service in [lines 15-21 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L15-L21).

| Config Parameters|Required|Description|Example|
|:----:|:----:|:----:|:----:|
|**url**|YES|The url-encoded base URL to your WebFeatureService|[**url:** http://processing.envirocar.org:9090/geoserver/cite/ow](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L16)|
|**service**|YES|The service on your URL must be `wfs`. Other services are not supported yet.|[**service:** WFS](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L17)|
|**version**|YES|The version of your WFS. Supported version options are: `1.0.0`,`1.1.0`, and `2.0.0`.|[**version:** 1.0.0](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L18)|
|**request**|YES|The request to your WFS must be `GetFeature`. Other requests are not supported yet.|[**request:** GetFeature](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L19)|
|**typeName**|YES|The typeName is the name of the feature type, that you want to offer in the marketplace.|[**typeName:** cite:roadsegments](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L20)|
|**outputFormat**|YES|The outputFormat of your data. This WFS-Adapter supports outputFormat GML. Supported versions are 2.0 - 3.1. GML 3.2 is currently not supported. The outputFormat **_must not_** be URL-encoded.|[**outputFormat:** text/xml; subtype=gml/2.1.2](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L21)|
|**srsName**|NO|The spatial reference system of your data. If not set, the default reference system provided by the WFS is used.|[**srsName:** text/xml; subtype=gml/2.1.2](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L22)|

#### Specify the BIG IoT offering:

The information for the offering registration are located starting from [line 23 of `application.yml`](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L23). 
The `application.yml` file is located in `wfsadapter\src\main\resources\`.
You must specify the following specifications for your WFS and your desired BIG IoT offering:

|Config Parameter|Required|Description|Example|
|:------|:---:|:-------------:|:-----------|
| **`local_id`**|YES| The local_id identifies this offering on the BIG IoT marketplace. It must be unique and distinguishable among all offerings within your organization and provider. | [**local_id:** wfsOfferings-roadsegments](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L24) |
| **`name`**|NO| The name for your offering. It will be added to the offeringDescription with `.withName(NAME);` If not specified, the offering name on the marketplace will be `null`. | See [example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L26) |
| **`category`**|YES| The category of your offering. It will be added to the offeringDescription with `.withCategory(CATEGORY);` The category must already exist in the marketplace. If you can't find a matching category, then you have to create ("propose") a category on the marketplace web interface first. Once the category is created, you can use it using the prefix `urn:proposed:` | See [example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L27) |
|**`timePeriod`**|NO| The timePeriod must specify `startDate`, and `endDate`. The startDate and endDate decribe a temporal time periode, in which the data of your offering are about. The DateTimes must be specified in `yyyy-MM-ddThh:mm:ss` (ISO8601 format). | See [Example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L28-L30)|
|**`inRegion`**|NO| The inRegion descibes a spatial bounding box, in which the data of your offering are contained. The inRegion must specify `x1`, `y1`, `x2`, and `y2`. `(x1|y1)` specifies the south western coordinate of the bounding box. `(x2|y2)` specifies the north eastern coordinate of the bounding box. The use of inRegion helps customers to find your offering in the marketplace. It will be added with `.inRegion(BoundingBox.create(Location.create(X1,Y1),Location.create(X2,Y2)))`  | See [Example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L31-L35)|
| **`inCity`**|NO| The inCity specifies a name of a city, to which the data of your offering refer to. It will be added to the offeringDescription with `.inCity(INCITY)`|[**inCity:** Hamburg](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L36)|
| **`inCountry`**|NO| The inCountry specifies a name of a country, to which the data of your offering refer to. It will be added to the offeringDescription with `.inCountry(INCITY)`|[**inCountry:** Germany](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L37)|
| **`route`**|NO| The route is the `/bigiot/access/ROUTE` access point to your data on the marketplace. It will be added to the offeringDescription with `.withRoute(ROUTE);` |[**route:** roadsegments](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L38) |
 **`expireDate`**|NO| The expireDate specifies the DateTime until which your offering is marked as activated on the marketplace. Once, the exireDate is reached, the offering will become marked is not activated. The DateTime must be specified in `yyyy-MM-ddThh:mm:ss` (ISO8601 format).|[**expireDate**: 2018-02-30T01:23:45](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L39)|
| **`accessStreamTimeout`**|NO| The accessStreamTimeout specifies **TODO: finish this sentence**. The DateTime must be specified in `yyyy-MM-ddThh:mm:ss` (ISO8601 format).|[**accessStreamTimeout**: 2018-02-30T01:23:45](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L40)|
|**`licenseType`**|NO| The licenseType specifies the license of your offering's data. Possible options are `OPEN_DATA_LICENSE`,`CREATIVE_COMMONS`,`NON_COMMERCIAL_DATA_LICENSE`.| [**licenseType**: OPEN_DATA_LICENSE](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L41) |
|**`price`**|NO| The price must specify `pricingModel`, `price`, and `currency`. The pricingModel can be one of `FREE`, `PER_ACCESS`, `PER_BYTE`, `PER_MESSAGE`, `PER_MONTH`. The price must be a double value. The currency can be one of `Euros`, `USDollars`}. | See [Example on Github](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L42-L45)|
| **`geometry`**|NO| If your WFS data contains features with default geometries, then you should specify the geometry property name and choose a schema. The geometry parameter is not required, but highly recommended, so that marketplace users can define and apply a custom spatial filter to your offering. | see example [on GitHub](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L46-L48) |
| **`featureIdentifiert`**|NO| If your WFS data features contain an default ID, then you should specify the featureID property name as `featureID` and choose a schema. The featureID parameter is not required, but highly recommended, so that marketplace users can define and apply a FeatureIDFilter to your offering. | see example [on GitHub](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L49-L51) |
|**`mapNullValues`**|NO| Some features might not contain all of your specified OutputDatas. In that case, the output for that feature will not contain that OutputData - if `mapNullValues` is set to `false`. If `mapNullValues` is set to `true`, the output for that feature will still contain that OutputData with value `null`. If missing or not set, _mapNullValues_ is by default set to _false_. | [**mapNullValues:** true](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L52)|
| **`outputData`**|NO| You must specify the properties delivered by your WFS, that you want to add as OfferingOutputData to the BIG IoT marketplace offering. For each property, you must specify NAME and SCHEMA of the property. The SCHEMA has to exist in the marketplace already. If it does not, you can create ("propose") it using the prefix `proposed:`. If there are featureProperties, that you do not want to be added as OutputData to your offering, then simply leave them out. | see example [on GitHub](https://github.com/enviroCar/wfs4BIT-Adapter/blob/develop/src/main/resources/application.yml#L52-L162) |

## How to deploy
This project is implemented using the spring-boot framework. Out of the box, Spring Boot provides an executable *.jar file, that can run the entire Spring application with no fuss: no build required, no setup, no web server configuration, etc.

    1. Build the project with Maven: `mvn clean install`
    2. Take the executable file `wfs4BIT-Adapter-0.1.jar` and move it to a application server of your choice (e.g. Apache Tomcat).
    3. Run `java -jar /path/to/file/wfs4BIT-Adapter-0.1.jar`
    
Further reading: [https://spring.io](https://spring.io/blog/2014/03/07/deploying-spring-boot-applications)

#### Command line arguments
You can specify <PROVIDER_ID>, <PROVIDER_SECRET>, and/or </PATH/TO/application.yml> when starting the application from command line.

* `--bigiot.provider.id=<PROVIDER_ID>`
    use this argument to override the <PROVIDER_ID> in your `secret.yml` file
* `--bigiot.provider.secret=<PROVIDER_SECRET>`
    use this argument to override the <PROVIDER_SECRET> in your `secret.yml` file
* `--spring.config.location=</PATH/TO/application.yml>`
    use this argument to override the entire application configuration by providing an absolute path to an alternative `application.yml` file.

## InputData - WFS GetFeature request parameters
GetFeature requests can return potentially a large amount of data. This **wfs4BIGIoT-Adapter** implements all GetFeature request parameters to limit the returned data to those features of interest. The GetFeature request parameters are added as InputData to your offering. The execution of multiple InputData Filters is possible - though not always reasonable. The following filters are available:

* #### maxFeatures
	Limit the accessed features when consuming the offering by adding `.addNameValue("maxFeature","N")` to the AccessParameters - where `N` specifies the maximum amount of returned features. You can also add maxFeatures as an URL parameter to your offering with `https://adress:port/bigiot/access/route?maxFeatures=N`.
    * Example: http://processing.envirocar.org:8081/bigiot/access/roadsegments?maxFeatures=50

* #### featureID
	Obtain only one specific feature when consuming the offering by adding `.addNameValue("featureID","ID")`to the AccessParameters - where `ID` specifies the feature ID of the specific feature. You can also add maxFeatures as an URL parameter to your offering with `https://adress:port/bigiot/access/route?featureID=ID`.
    * Example: http://processing.envirocar.org:8081/bigiot/access/roadsegments?featureID=30fc97d2-02f7-439d-b00b-f50920a20cbd

* #### propertyName
	Obtain the features and only the features, that contain specific attributes. You can specify a single attribute, or multiple attributes separated by commas. Apply the propertyName filter when consuming the offering by adding `.addNameValue("propertyName", "ATTRIBUTE1,ATTRIBUTE2,...,ATTRIBUTEN")` - where `ATTRIBUTE1,ATTRIBUTE2,...,ATTRIBUTEN` is a comma-separated list of feature attributes/OutputDataNames. You can also add maxFeatures as an URL parameter to your offering with `https://adress:port/bigiot/access/route?propertyName=ATTRIBUTE1,ATTRIBUTE2,...,ATTRIBUTEN`.
    * Example: http://processing.envirocar.org:8081/bigiot/access/roadsegments?propertyName=avgCO2,avgSpeed,avgConsumption

* #### sortBy
	You can sort the obtaining features by attribute descending or ascending. Apply the sortBy filter when consuming the offering by adding `.addNameValue("sortBy", "ATTRIBUTE")` - where `ATTRIBUTE` is the feature attribute/OutputDataName and the returned features will be sorted by that attribute in descending (by default) order. You can also specify `ATTRIBUTE+D` to define descending order or `ATTRIBUTE+A` to define ascending order. You can also add sortBy as an URL parameter to your offering with `?sortBy=ATTRIBUT+A`. Make sure to URL-encode the `+` with `%2B` in your URL, i.e. `https://adress:port/bigiot/access/route?sortBy=ATTRIBUTE%2BA`.
    * Example: http://processing.envirocar.org:8081/bigiot/access/roadsegments?sortBy=avgConsumption%2BD

* #### bbox
	Obtain only feature within a spatial bounding box when consuming the offering by adding `.addNameValue("bbox","MIN_X,MIN_Y,MAX_X,MAX_Y")` to the AccessParameters - where `MIN_X,MIN_Y` is the coordinate for the lower western point and `MAX_X,MAX_Y` is the upper eastern point of the bounding box. You can also add bbox as an URL parameter to your offering with `https://adress:port/bigiot/access/route?bbox=MIN_X,MIN_Y,MAX_X,MAX_Y`.
    * Example: http://processing.envirocar.org:8081/bigiot/access/roadsegments?bbox=51.96,7.61,51.97,7.63

* #### Filter
	The features can be filtered customly when consuming the offering by adding `.addNameValue("Filter","FILTEREXPRESSION")` to the AccessParameters - where `FILTEREXPRESSION` is a logical compound of Spatial Capabilities (i.e. `Equals, Disjoint, Touches, Within, Overlaps, Crosses, Intersects, Contains, DWithin, BBOX`), Comparison Operators (i.e. `PropertyIsEqualTo (=), PropertyIsNotEqualTo (<>), PropertyIsLessThan (<), PropertyIsGreaterThan (>), PropertyIsLessThanOrEqualTo (<=), PropertyIsGreaterThanOrEqualTo (>=), PropertyIsLike, PropertyIsBetween (range)`) and Logical Operators (`And, Or, Not`).
    *	Example: PropertyIsBetween can be applied on an attribute `avgSpeed` with range `[30,50]` with the expression: 
    ```
    <PropertyIsBetween>
    	<PropertyName>
        	avgSpeed
        </PropertyName>
    	<LowerBoundary>
        	<Literal>
            	30
          	</Literal>
        </LowerBoundary>
        <UpperBoundary>
            <Literal>
                50
            </Literal>
        </UpperBoundary>
 	</PropertyIsBetween>
    ``` 
    *	which can be added with `.addNameValue("Filter","<PropertyIsBetween><PropertyName>avgSpeed</PropertyName><LowerBoundary><Literal>30</Literal></LowerBoundary><UpperBoundary><Literal>50</Literal></UpperBoundary></PropertyIsBetween>")`. 
    *	You can also add Filter as an URL parameter to your offering with `https://adress:port/bigiot/access/route?Filter=<PropertyIsBetween><PropertyName>avgSpeed</PropertyName><LowerBoundary><Literal>30</Literal></LowerBoundary><UpperBoundary><Literal>50</Literal></UpperBoundary></PropertyIsBetween>`.
    
## Bugs and Feedback
Developer feedback goes a long way towards making this adapter even better. Submit a bug report or request feature enhancements to [via mail to enviroCar@52north.org](mailto:enviroCar@52north.org?Subject=wfs4BIGIoT-Adapter) or open a issue on this github repository.

## Funding
This project has received funding from the European Union's Horizon 2020 research and innovation programme 
under grant agreement No 688038. 

[enviroCar](https://enviroCar.org) was successfully applied in the First Open Call of the EC funded BIG IoT project. Thus, the enviroCar 
project is one of the first projects to appear on the [BIG IoT marketplace](https://market.big-iot.org/).