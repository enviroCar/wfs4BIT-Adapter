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
package org.envirocar.bigiot.wfsadapter.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.eclipse.bigiot.lib.offering.OfferingDescription;

import org.envirocar.bigiot.wfsadapter.AbstractRequestHandler;
import org.envirocar.bigiot.wfsadapter.exception.RequestProcessingException;
import org.envirocar.bigiot.wfsadapter.filter.BoundingBoxFilter;
import org.envirocar.bigiot.wfsadapter.filter.CustomWFSFilter;
import org.envirocar.bigiot.wfsadapter.filter.MaxFeaturesFilter;
import org.envirocar.bigiot.wfsadapter.filter.SortByFilter;
import org.envirocar.bigiot.wfsadapter.filter.WFSFilter;
import org.envirocar.bigiot.wfsadapter.model.WFSFeatureCollection;
import org.envirocar.bigiot.wfsadapter.remote.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import org.envirocar.bigiot.wfsadapter.filter.FeatureIDFilter;
import org.envirocar.bigiot.wfsadapter.filter.PropertyNameFilter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class WFSRequestHandler extends AbstractRequestHandler<WFSFeatureCollection> {

    private static final Logger LOG = LoggerFactory.getLogger(WFSRequestHandler.class);

    @Autowired
    private DAO wfsDAO;

    /**
     * Constructor.
     */
    public WFSRequestHandler() {
        super(WFSFeatureCollection.class);
    }

    @Override
    public WFSFeatureCollection processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {
            CustomWFSFilter customFilter = null;
            MaxFeaturesFilter maxFeaturesFilter = null;
            SortByFilter sortByFilter = null;
            BoundingBoxFilter bbFilter = null;
            FeatureIDFilter featureIDFilter = null;
            PropertyNameFilter propertyNameFilter = null;

            if (input.containsKey(CUSTOM_WFS_FILTER)) {
                customFilter = getCustomWFSFilter(input);
            }

            if (input.containsKey(MAX_FEATURES_FILTER)) {
                maxFeaturesFilter = getMaxFeaturesFilter(input);
            }

            if (input.containsKey(SORT_BY_FILTER)) {
                sortByFilter = getSortByFilter(input);
            }

            if (input.containsKey(BOUNDING_BOX_FILTER)) {
                bbFilter = getBoundingBoxFilter(input);
            }

            if (input.containsKey(FEATURE_ID_FILTER)) {
                featureIDFilter = getFeatureIDFilter(input);
            }

            if (input.containsKey(PROPERTY_NAME_FILTER)) {
                propertyNameFilter = getPropertyNameFilter(input);
            }

            WFSFilter filter = new WFSFilter(
                    customFilter,
                    maxFeaturesFilter,
                    sortByFilter,
                    bbFilter,
                    featureIDFilter,
                    propertyNameFilter);
            WFSFeatureCollection test = wfsDAO.get(filter);
            return test;

        } catch (Exception e) {

        }
        return new WFSFeatureCollection();
    }
}
