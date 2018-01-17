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
package org.envirocar.bigiot.wfsadapter.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.envirocar.bigiot.wfsadapter.model.WFSFeatureCollection;
import org.envirocar.bigiot.wfsadapter.model.WFSFeatureMember;
import org.envirocar.bigiot.wfsadapter.model.WFSProperty;
import org.envirocar.bigiot.wfsadapter.WFSConfiguration;
import org.envirocar.bigiot.wfsadapter.WFSConfiguration.OfferingConfigurations.OutputData;
import org.envirocar.bigiot.wfsadapter.WFSConfiguration.OfferingConfigurations.OfferingGeometry;
import org.envirocar.bigiot.wfsadapter.filter.WFSFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureImpl;

import org.opengis.feature.simple.SimpleFeature;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import org.envirocar.bigiot.wfsadapter.exception.RequiredOfferingConfigParamMissingException;

import org.xml.sax.SAXException;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class DAO {

    @Autowired
    private WFSConfiguration wfsConfig;

    private static final Logger LOG = LoggerFactory.getLogger(DAO.class);

    public WFSFeatureCollection get(WFSFilter filter) throws ParserConfigurationException, SAXException {
        String customWFSFilterParam = null;
        String maxFeaturesFilterParam = null;
        String sortByFilterParam = null;
        String bbFilterParam = null;
        String featureIDParam = null;
        String propertyNameParam = null;
        String urlString = "";
        try {
            urlString = wfsConfig.getOffering().getUrl();
        } catch (RequiredOfferingConfigParamMissingException rocpme) {
            rocpme.printStackTrace();
        }
        if (filter.hasCustomWFSFilter()) {
            customWFSFilterParam = filter.getCustomWFSFilter().string();
            urlString += "&Filter=" + customWFSFilterParam;
        }
        if (filter.hasMaxFeaturesFilter()) {
            maxFeaturesFilterParam = filter.getMaxFeaturesFilter().string();
            urlString += "&maxFeatures=" + maxFeaturesFilterParam;
        }
        if (filter.hasSortByFilter()) {
            sortByFilterParam = filter.getSortByFilter().string();
            urlString += "&sortBy=" + sortByFilterParam;
        }
        if (filter.hasBoundingBoxFilter()) {
            bbFilterParam = filter.getBoundingBoxFilter().string();
            urlString += "&bbox=" + bbFilterParam;
        }
        if (filter.hasFeatureIDFilter()) {
            featureIDParam = filter.getFeatureIDFilter().string();
            urlString += "&featureID=" + featureIDParam;
        }
        if (filter.hasPropertyNameFilter()) {
            propertyNameParam = filter.getPropertyNameFilter().string();
            urlString += "&propertyName=" + propertyNameParam;
        }
        try {
            URL url = new URL(urlString);
            InputStream gmlResponse = url.openStream();
            org.geotools.xml.Configuration configuration = new org.geotools.gml3.GMLConfiguration();
            org.geotools.xml.Parser parser = new org.geotools.xml.Parser(configuration);
            parser.setStrict(false);
            Object o = parser.parse(gmlResponse);
            ArrayList<SimpleFeatureImpl> features = new ArrayList();
            if (o instanceof HashMap) { // gml 3.2
                ArrayList<SimpleFeatureImpl> list = (ArrayList) ((HashMap) o).get("member");
                Iterator<SimpleFeatureImpl> iter = list.iterator();
                while (iter.hasNext()) {
                    SimpleFeatureImpl feature = iter.next();
                    features.add(feature);
                }
            } else if (o instanceof ListFeatureCollection) { // gml 2 & gml 3.1
                ListFeatureCollection lfc = (ListFeatureCollection) o;
                Iterator<SimpleFeature> iter = lfc.iterator();
                while (iter.hasNext()) {
                    SimpleFeature feature = iter.next();
                    features.add((SimpleFeatureImpl) feature);
                }
            }

            List<OutputData> outputDatas = wfsConfig.getOffering()
                    .getOutputData();

            OfferingGeometry og = wfsConfig.getOffering().getGeometry();
            String offeringGeometryName = og.getName();
            String offeringGeometrySchema = og.getSchema();

            // create model:
            WFSFeatureCollection fc = new WFSFeatureCollection();
            Iterator<SimpleFeatureImpl> iter = features.iterator();
            while (iter.hasNext()) {
                SimpleFeatureImpl feature = iter.next();

                WFSFeatureMember fm = new WFSFeatureMember();

                // add default geometry:
                fm.setGeom(new WFSProperty(offeringGeometryName, feature.getDefaultGeometry().toString(), offeringGeometrySchema));

                // add specified properties into featureMember:
                for (OutputData outputData : outputDatas) {
                    fm.addProperty(new WFSProperty(outputData.getName(),
                            feature.getAttribute(outputData.getName()),
                            outputData.getSchema()));
                }

                fc.addFeature(fm);
            }
            return fc;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }
        return null;
    }

}