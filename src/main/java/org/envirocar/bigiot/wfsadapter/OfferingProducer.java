/*
 * Copyright (C) 2013 - 2017 the enviroCar community
 *
 * This file is part of the enviroCar BIGIOT WFS Adapter.
 *
 * The BIGIOT WFS Adapter is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The BIGIOT WFS Adapter is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.bigiot.wfsadapter;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.eclipse.bigiot.lib.Provider;
import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.Information;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescriptionChain;

import org.envirocar.bigiot.wfsadapter.Config.OfferingConfigurations.OutputData;

import java.util.List;
import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.Price.Euros;
import org.eclipse.bigiot.lib.model.Price.USDollars;
import org.envirocar.bigiot.wfsadapter.Config.OfferingConfigurations.OfferingGeometry;
import org.envirocar.bigiot.wfsadapter.exception.OfferingConfigParamMissingException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public abstract class OfferingProducer implements InitializingBean, DisposableBean, Constants {

    @Value("${ec4bit.contact}")
    private String contact;

    @Autowired
    private Config wfsConfig;

    @Autowired
    protected Provider provider;
    protected RegistrableOfferingDescription offeringDescription;

    protected abstract RegistrableOfferingDescription getOfferingDescription();

    protected abstract AbstractRequestHandler getRequestHandler();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.offeringDescription = getOfferingDescription();
        this.provider.register(offeringDescription);
    }

    @Override
    public void destroy() throws Exception {
        this.provider.deregister(offeringDescription.getOfferingId());
        this.offeringDescription.deregister();
        this.provider.terminate();
    }

    // generic WFS offerings
    protected RegistrableOfferingDescriptionChain addWFSOfferingDescription(RegistrableOfferingDescriptionChain offering) {

        try {
            // add required offering parameters as specified in application.yml:
            String local_id = wfsConfig.getOffering().getLocal_id();
            String informationName = wfsConfig.getOffering().getWithInformation().getName();
            String informationSchema = wfsConfig.getOffering().getWithInformation().getSchema();

            // register offeringDescription:
            RegistrableOfferingDescriptionChain od = provider.createOfferingDescription(local_id)
                    .addInputData(MAX_FEATURES_FILTER, new RDFType(SCHEMA_MAX_FEATURES_FILTER), ValueType.TEXT)
                    .addInputData(CUSTOM_WFS_FILTER, new RDFType(SCHEMA_CUSTOM_WFS_FILTER), ValueType.TEXT)
                    .addInputData(SORT_BY_FILTER, new RDFType(SCHEMA_SORT_BY_FILTER), ValueType.TEXT)
                    .addInputData(BOUNDING_BOX_FILTER, new RDFType(SCHEMA_BOUNDING_BOX_FILTER), ValueType.TEXT)
                    .withInformation(new Information(informationName, new RDFType(informationSchema)))
                    .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                    .withAccessRequestHandler(getRequestHandler());

            // add optional offering parameters as specified in application.yml:
            if (wfsConfig.getOffering().getRoute() != null
                    && !wfsConfig.getOffering().getRoute().isEmpty()) {
                od.withRoute(wfsConfig.getOffering().getRoute());
            }
            if (wfsConfig.getOffering().getInCity() != null
                    && !wfsConfig.getOffering().getInCity().isEmpty()) {
                od.inCity(wfsConfig.getOffering().getInCity());
            }
            if (wfsConfig.getOffering().getExpireDate() != null
                    && !wfsConfig.getOffering().getExpireDate().isEmpty()) {
                DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
                DateTime now = new DateTime();
                DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getExpireDate());
                long millis = expireDate.getMillis() - now.getMillis();
                if (millis > 1000) {
                    od.withExpirationInterval(millis);
                }
            }
            if (wfsConfig.getOffering().getAccessStreamTimeout() != null
                    && !wfsConfig.getOffering().getAccessStreamTimeout().isEmpty()) {
                DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
                DateTime now = new DateTime();
                DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getAccessStreamTimeout());
                long millis = expireDate.getMillis() - now.getMillis();
                if (millis > 1000) {
                    od.withAccessStreamSessionTimeout(millis);
                }
            }
            if (wfsConfig.getOffering().getLicenseType() != null
                    && !wfsConfig.getOffering().getLicenseType().isEmpty()) {
                switch (wfsConfig.getOffering().getLicenseType()) {
                    case "OPEN_DATA_LICENSE":
                        od.withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE);
                        break;
                    case "CREATIVE_COMMONS":
                        od.withLicenseType(BigIotTypes.LicenseType.CREATIVE_COMMONS);
                        break;
                    case "NON_COMMERCIAL_DATA_LICENSE":
                        od.withLicenseType(BigIotTypes.LicenseType.NON_COMMERCIAL_DATA_LICENSE);
                        break;
                    default:
                        od.withLicenseType(BigIotTypes.LicenseType.valueOf(
                                wfsConfig.getOffering().getLicenseType()));
                        break;
                }
            }
            od.withPricingModel(BigIotTypes.PricingModel.FREE);
            od.withPrice(Euros.amount(0.000));
            if (wfsConfig.getOffering().getPrice() != null) {
                if (wfsConfig.getOffering().getPrice().getCurrency() != null
                        && wfsConfig.getOffering().getPrice().getCurrency().length() > 0) {
                    if (wfsConfig.getOffering().getPrice().getPrice() != null) {
                        switch (wfsConfig.getOffering().getPrice().getCurrency()) {
                            case "Euros":
                                od.withPrice(Euros.amount(wfsConfig.getOffering().getPrice().getPrice()));
                                break;
                            case "USDollars":
                                od.withPrice(USDollars.amount(wfsConfig.getOffering().getPrice().getPrice()));
                                break;
                            default:
                                od.withPrice(Euros.amount(0.00));
                                break;
                        }
                        if (wfsConfig.getOffering().getPrice().getPricingModel() != null) {
                            switch (wfsConfig.getOffering().getPrice().getPricingModel()) {
                                case "PER_ACCESS":
                                    od.withPricingModel(BigIotTypes.PricingModel.PER_ACCESS);
                                    break;
                                case "PER_BYTE":
                                    od.withPricingModel(BigIotTypes.PricingModel.PER_BYTE);
                                    break;
                                case "PER_MESSAGE":
                                    od.withPricingModel(BigIotTypes.PricingModel.PER_MESSAGE);
                                    break;
                                case "PER_MONTH":
                                    od.withPricingModel(BigIotTypes.PricingModel.PER_MONTH);
                                    break;
                                default:
                                    od.withPricingModel(BigIotTypes.PricingModel.FREE);
                                    break;
                            }
                        }
                    }
                }
            }
            
            // add Geometry OutputData to offeringDescription as specified in application.yml:
            OfferingGeometry og = wfsConfig.getOffering().getGeometry();
            String offeringGeometryName = og.getName();
            String offeringGeometrySchema = og.getSchema();
            
            od.addOutputData(
                    new IOData(
                            offeringGeometryName,
                            new RDFType(offeringGeometrySchema),
                            ValueType.TEXT));

            // add NominatimRefLink to osmid as OutputData
            od.addOutputData(
                    new IOData(
                            "OSM-Ref",
                            new RDFType("SCHEMA_OSM_LOOKUP_NOMINATIM"),
                            ValueType.TEXT));
            
            // add OutputData to offeringDescription as specified in application.yml:
            List<OutputData> offeringOutputData = wfsConfig.getOffering()
                    .getOutputData();
            
            for (OutputData outputData : offeringOutputData) {
                String outputDataName = outputData.getName();
                String outputDataSchema = outputData.getSchema();
                od.addOutputData(
                        new IOData(
                                outputDataName,
                                new RDFType(outputDataSchema),
                                ValueType.TEXT));
            }

            return od;
        } catch (OfferingConfigParamMissingException rocpme) {
            rocpme.printStackTrace();
        }
        return null;
    }

}