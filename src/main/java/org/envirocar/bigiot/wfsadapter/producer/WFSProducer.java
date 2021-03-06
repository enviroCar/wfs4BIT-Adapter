/*
 * Copyright (C) 2013 - 2018 the enviroCar community
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
package org.envirocar.bigiot.wfsadapter.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.Information;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;

import org.envirocar.bigiot.wfsadapter.AbstractRequestHandler;
import org.envirocar.bigiot.wfsadapter.OfferingProducer;
import org.envirocar.bigiot.wfsadapter.config.Config;
import org.envirocar.bigiot.wfsadapter.config.Config.OfferingConfigurations.OutputData;
import org.envirocar.bigiot.wfsadapter.config.Config.OfferingConfigurations.OfferingGeometry;

import java.util.List;
import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.BoundingBox;
import org.eclipse.bigiot.lib.model.Location;
import org.eclipse.bigiot.lib.model.Price;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescriptionChain;
import org.envirocar.bigiot.wfsadapter.config.Config.OfferingConfigurations.InRegion;
import org.envirocar.bigiot.wfsadapter.exception.OfferingConfigParamMissingException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class WFSProducer extends OfferingProducer {

    @Autowired
    private WFSRequestHandler requestHandler;

    @Autowired
    private Config wfsConfig;

    /**
     *
     * @return
     */
    @Override
    protected RegistrableOfferingDescription getOfferingDescription() {
        RegistrableOfferingDescriptionChain odc;
        RegistrableOfferingDescription od;

        // add required offering parameters as specified in application.yml:
        try {
            String local_id = wfsConfig.getOffering().getLocal_id();
            String name = wfsConfig.getOffering().getName();
            String category = wfsConfig.getOffering().getCategory();

            // register offeringDescription:
            odc = provider.createOfferingDescription(local_id)
                    .withName(name)
                    .withCategory(category)
                    .addInputData(MAX_FEATURES_FILTER, new RDFType(SCHEMA_MAX_FEATURES_FILTER), ValueType.TEXT)
                    .addInputData(CUSTOM_WFS_FILTER, new RDFType(SCHEMA_CUSTOM_WFS_FILTER), ValueType.TEXT)
                    .addInputData(SORT_BY_FILTER, new RDFType(SCHEMA_SORT_BY_FILTER), ValueType.TEXT)
                    .addInputData(BOUNDING_BOX_FILTER, new RDFType(SCHEMA_BOUNDING_BOX_FILTER), ValueType.TEXT)
                    .addInputData(FEATURE_ID_FILTER, new RDFType(SCHEMA_FEATURE_ID_FILTER), ValueType.TEXT)
                    .addInputData(PROPERTY_NAME_FILTER, new RDFType(SCHEMA_PROPERTY_NAME_FILTER), ValueType.TEXT)
                    .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                    .withAccessRequestHandler(getRequestHandler());

            // add optional offering parameters as specified in application.yml:
            if (wfsConfig.getOffering().getRoute() != null
                    && !wfsConfig.getOffering().getRoute().isEmpty()) {
                odc.withRoute(wfsConfig.getOffering().getRoute());
            }
            if (wfsConfig.getOffering().getInCity() != null
                    && !wfsConfig.getOffering().getInCity().isEmpty()) {
                odc.inCity(wfsConfig.getOffering().getInCity());
            }
            if (wfsConfig.getOffering().getInCountry()!= null
                    && !wfsConfig.getOffering().getInCountry().isEmpty()) {
                odc.inRegion(wfsConfig.getOffering().getInCountry());
            }
            if (wfsConfig.getOffering().getExpireDate() != null
                    && !wfsConfig.getOffering().getExpireDate().isEmpty()) {
                DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
                DateTime now = new DateTime();
                DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getExpireDate());
                long millis = expireDate.getMillis() - now.getMillis();
                if (millis > 1000) {
                    odc.withExpirationInterval(millis);
                }
            }
            if (wfsConfig.getOffering().getAccessStreamTimeout() != null
                    && !wfsConfig.getOffering().getAccessStreamTimeout().isEmpty()) {
                DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
                DateTime now = new DateTime();
                DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getAccessStreamTimeout());
                long millis = expireDate.getMillis() - now.getMillis();
                if (millis > 1000) {
                    odc.withAccessStreamSessionTimeout(millis);
                }
            }
            if (wfsConfig.getOffering().getTimePeriod() != null) {
                DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
                DateTime startDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getTimePeriod().getStartDate());
                DateTime endDate = TEMPORAL_TIME_PATTERN.parseDateTime(wfsConfig.getOffering().getTimePeriod().getEndDate());
                odc.withTimePeriod(startDate, endDate);
            }
            if (wfsConfig.getOffering().getInRegion() != null) {
                InRegion ir = wfsConfig.getOffering().getInRegion();
                odc.inRegion(BoundingBox.create(
                        Location.create(ir.getX1(), ir.getY1()), Location.create(ir.getX2(), ir.getY2())
                ));
            }
            if (wfsConfig.getOffering().getLicenseType() != null
                    && !wfsConfig.getOffering().getLicenseType().isEmpty()) {
                switch (wfsConfig.getOffering().getLicenseType()) {
                    case "OPEN_DATA_LICENSE":
                        odc.withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE);
                        break;
                    case "CREATIVE_COMMONS":
                        odc.withLicenseType(BigIotTypes.LicenseType.CREATIVE_COMMONS);
                        break;
                    case "NON_COMMERCIAL_DATA_LICENSE":
                        odc.withLicenseType(BigIotTypes.LicenseType.NON_COMMERCIAL_DATA_LICENSE);
                        break;
                    default:
                        odc.withLicenseType(BigIotTypes.LicenseType.valueOf(
                                wfsConfig.getOffering().getLicenseType()));
                        break;
                }
            }
            odc.withPricingModel(BigIotTypes.PricingModel.FREE);
            odc.withPrice(Price.Euros.amount(0.000));
            if (wfsConfig.getOffering().getPrice() != null) {
                if (wfsConfig.getOffering().getPrice().getCurrency() != null
                        && wfsConfig.getOffering().getPrice().getCurrency().length() > 0) {
                    if (wfsConfig.getOffering().getPrice().getPrice() != null) {
                        switch (wfsConfig.getOffering().getPrice().getCurrency()) {
                            case "Euros":
                                odc.withPrice(Price.Euros.amount(wfsConfig.getOffering().getPrice().getPrice()));
                                break;
                            case "USDollars":
                                odc.withPrice(Price.USDollars.amount(wfsConfig.getOffering().getPrice().getPrice()));
                                break;
                            default:
                                odc.withPrice(Price.Euros.amount(0.00));
                                break;
                        }
                        if (wfsConfig.getOffering().getPrice().getPricingModel() != null) {
                            switch (wfsConfig.getOffering().getPrice().getPricingModel()) {
                                case "PER_ACCESS":
                                    odc.withPricingModel(BigIotTypes.PricingModel.PER_ACCESS);
                                    break;
                                case "PER_BYTE":
                                    odc.withPricingModel(BigIotTypes.PricingModel.PER_BYTE);
                                    break;
                                case "PER_MESSAGE":
                                    odc.withPricingModel(BigIotTypes.PricingModel.PER_MESSAGE);
                                    break;
                                case "PER_MONTH":
                                    odc.withPricingModel(BigIotTypes.PricingModel.PER_MONTH);
                                    break;
                                default:
                                    odc.withPricingModel(BigIotTypes.PricingModel.FREE);
                                    break;
                            }
                        }
                    }
                }
            }

            od = odc;

            // add Geometry OutputData to offeringDescription as specified in application.yml:
            OfferingGeometry og = wfsConfig.getOffering().getGeometry();
            String offeringGeometryName = og.getName();
            String offeringGeometrySchema = og.getSchema();

            od.addOutputData(
                    new IOData(
                            offeringGeometryName,
                            new RDFType(offeringGeometrySchema),
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

    @Override
    protected AbstractRequestHandler getRequestHandler() {
        return requestHandler;
    }

}