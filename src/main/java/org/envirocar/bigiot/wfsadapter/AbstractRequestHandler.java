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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import org.eclipse.bigiot.lib.handlers.AccessRequestHandler;
import org.eclipse.bigiot.lib.offering.OfferingDescription;
import org.eclipse.bigiot.lib.serverwrapper.BigIotHttpResponse;

import org.envirocar.bigiot.wfsadapter.exception.KeyNotFoundException;
import org.envirocar.bigiot.wfsadapter.exception.RequestProcessingException;
import org.envirocar.bigiot.wfsadapter.filter.BoundingBoxFilter;
import org.envirocar.bigiot.wfsadapter.filter.CustomWFSFilter;
import org.envirocar.bigiot.wfsadapter.filter.FeatureIDFilter;
import org.envirocar.bigiot.wfsadapter.filter.MaxFeaturesFilter;
import org.envirocar.bigiot.wfsadapter.filter.PropertyNameFilter;
import org.envirocar.bigiot.wfsadapter.filter.SortByFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public abstract class AbstractRequestHandler<E> implements AccessRequestHandler, Constants {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractRequestHandler.class);

    @Autowired
    private ObjectMapper mapper;

    private Class<? extends E> clazz;

    /**
     * Constructor.
     *
     * @param clazz
     */
    public AbstractRequestHandler(Class<? extends E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public BigIotHttpResponse processRequestHandler(OfferingDescription od, Map<String, Object> map, String subscriberId, String consumerInfo) {
        try {
            E responseEntity = processRequest(od, map);
            String body = mapper.writeValueAsString(responseEntity);

            return BigIotHttpResponse.okay()
                    .withBody(body)
                    .asJsonType();
        } catch (RequestProcessingException e) {
            LOG.error(e.getMessage(), e);
            return BigIotHttpResponse.error()
                    .withBody("{\"status\":\"error\"}")
                    .withStatus(e.getErrorCode())
                    .asJsonType();
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
            return BigIotHttpResponse.error()
                    .withBody("{\"status\":\"error\"}")
                    .withStatus(422)
                    .asJsonType();
        }
    }

    protected <T> T checkAndGetValue(String key, Map<String, Object> input) throws KeyNotFoundException {
        if (!input.containsKey(key)) {
            throw new KeyNotFoundException(key);
        }
        return (T) input.get(key);
    }

    protected CustomWFSFilter getCustomWFSFilter(Map<String, Object> input) throws KeyNotFoundException {
        String customFilter = checkAndGetValue(CUSTOM_WFS_FILTER, input);
        if (customFilter != null) {
            return new CustomWFSFilter(customFilter);
        }
        return new CustomWFSFilter(null);
    }

    protected MaxFeaturesFilter getMaxFeaturesFilter(Map<String, Object> input) throws KeyNotFoundException {
        String maxFeatures = checkAndGetValue(MAX_FEATURES_FILTER, input);
        if (maxFeatures != null) {
            return new MaxFeaturesFilter(Integer.parseInt(maxFeatures));
        }
        return new MaxFeaturesFilter(null);
    }

    protected SortByFilter getSortByFilter(Map<String, Object> input) throws KeyNotFoundException {
        String sortByParam = checkAndGetValue(SORT_BY_FILTER, input);
        if (sortByParam != null) {
            if (sortByParam.contains("+")) {
                String[] paramParts;
                paramParts = sortByParam.split("\\+");
                String sortAttribute = paramParts[0];
                boolean sortAscending = paramParts[1].equals("A");
                return new SortByFilter(sortAttribute, sortAscending);
            } else {
                return new SortByFilter(sortByParam, true);
            }
        }
        return new SortByFilter(null, null);
    }

    protected BoundingBoxFilter getBoundingBoxFilter(Map<String, Object> input) throws KeyNotFoundException {
        String bbFilterParam = checkAndGetValue(BOUNDING_BOX_FILTER, input);
        if (bbFilterParam != null) {
            String[] paramParts = bbFilterParam.split(",");
            Double minA = Double.parseDouble(paramParts[0]);
            Double minB = Double.parseDouble(paramParts[1]);
            Double maxA = Double.parseDouble(paramParts[2]);
            Double maxB = Double.parseDouble(paramParts[3]);
            return new BoundingBoxFilter(minA,minB,maxA,maxB);
        }
        return new BoundingBoxFilter(null,null,null,null);
    }
    
    protected FeatureIDFilter getFeatureIDFilter(Map<String, Object> input) throws KeyNotFoundException {
        String featureID = checkAndGetValue(FEATURE_ID_FILTER, input);
        if (featureID != null) {
            return new FeatureIDFilter(featureID);
        }
        return new FeatureIDFilter(null);
    }
    
    protected PropertyNameFilter getPropertyNameFilter(Map<String, Object> input) throws KeyNotFoundException {
        String properties = checkAndGetValue(PROPERTY_NAME_FILTER, input);
        if (properties != null) {
            return new PropertyNameFilter(properties);
        }
        return new PropertyNameFilter(null);
    }

    public abstract E processRequest(OfferingDescription od, Map<String, Object> map) throws RequestProcessingException;
}
