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
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.envirocar.bigiot.wfsadapter;

import java.util.List;
import org.eclipse.bigiot.lib.exceptions.IncompleteOfferingDescriptionException;
import org.envirocar.bigiot.wfsadapter.exception.RequiredOfferingConfigParamMissingException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class WFSConfiguration {

    private OfferingConfigurations offering;

    public OfferingConfigurations getOffering() {
        return offering;
    }

    public void setOffering(OfferingConfigurations offering) {
        this.offering = offering;
    }

    public static class OfferingConfigurations {

        private String url;
        private String local_id;
        private OfferingInformation withInformation;
        private String offeringOutputs;
        private String route;
        private String inCity;
        private String expireDate;
        private String licenseType;

        private Price price;

        private OfferingGeometry geometry;

        private List<OutputData> outputData;

        public String getUrl() throws RequiredOfferingConfigParamMissingException {
            if (url == null) {
                throw new RequiredOfferingConfigParamMissingException("Missing required offering parameter in application.yml: url");
            }
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLocal_id() throws RequiredOfferingConfigParamMissingException {
            if (local_id == null) {
                throw new RequiredOfferingConfigParamMissingException("Missing required offering parameter in application.yml: local_id");
            }
            return local_id;
        }

        public void setLocal_id(String local_id) {
            this.local_id = local_id;
        }

        public String getInCity() {
            return inCity;
        }

        public void setInCity(String inCity) {
            this.inCity = inCity;
        }

        public String getRoute() {
            return route;
        }

        public void setRoute(String route) {
            this.route = route;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getLicenseType() {
            return licenseType;
        }

        public void setLicenseType(String licenseType) {
            this.licenseType = licenseType;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public OfferingInformation getWithInformation() {
            return withInformation;
        }

        public void setWithInformation(OfferingInformation withInformation) {
            this.withInformation = withInformation;
        }

        public String getOfferingOutputs() {
            return offeringOutputs;
        }

        public void setOfferingOutputs(String offeringOutputs) {
            this.offeringOutputs = offeringOutputs;
        }

        public List<OutputData> getOutputData() {
            return outputData;
        }

        public void setOutputData(List<OutputData> outputData) {
            this.outputData = outputData;
        }

        public OfferingGeometry getGeometry() {
            return geometry;
        }

        public void setGeometry(OfferingGeometry geometry) {
            this.geometry = geometry;
        }

        public static class Price {

            private String currency;
            private Double price;
            private String pricingModel;

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public Double getPrice() {
                return price;
            }

            public void setPrice(Double price) {
                this.price = price;
            }

            public String getPricingModel() {
                return pricingModel;
            }

            public void setPricingModel(String pricingModel) {
                this.pricingModel = pricingModel;
            }

        }

        public static class OfferingInformation {

            private String name;
            private String schema;

            public String getName() throws RequiredOfferingConfigParamMissingException {
                if (name == null) {
                    throw new RequiredOfferingConfigParamMissingException("Missing required offering parameter in application.yml: withInformation.name");
                }
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSchema() throws RequiredOfferingConfigParamMissingException {
                if (schema == null) {
                    throw new RequiredOfferingConfigParamMissingException("Missing required offering parameter in application.yml: withInformation.schema");
                }
                return schema;
            }

            public void setSchema(String schema) {
                this.schema = schema;
            }
        }

        public static class OutputData {

            private String name;
            private String schema;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSchema() {
                return schema;
            }

            public void setSchema(String schema) {
                this.schema = schema;
            }
        }

        public static class OfferingGeometry {

            private String name;
            private String schema;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSchema() {
                return schema;
            }

            public void setSchema(String schema) {
                this.schema = schema;
            }

        }

    }

}
