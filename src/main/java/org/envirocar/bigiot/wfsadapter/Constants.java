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


/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
interface Constants {
    
    // CustomWFSFilter
    static final String SCHEMA_CUSTOM_WFS_FILTER = "SCHEMA_CUSTOM_FILTER";
    static final String CUSTOM_WFS_FILTER = "Filter";
    
    // MaxFeaturesFilter
    static final String SCHEMA_MAX_FEATURES_FILTER = "SCHEMA_MAX_FEATURES_FILTER";
    static final String MAX_FEATURES_FILTER = "maxFeatures";
    
    // SortByFilter
    static final String SCHEMA_SORT_BY_FILTER = "SCHEMA_SORT_BY_FILTER";
    static final String SORT_BY_FILTER = "sortBy";
    
    // BoundingBoxFilter
    static final String SCHEMA_BOUNDING_BOX_FILTER = "SCHEMA_BOUNDING_BOX_FILTER";
    static final String BOUNDING_BOX_FILTER = "bbox";
    
    // FeatureIDFilter
    static final String SCHEMA_FEATURE_ID_FILTER = "http://schema.org/identifier";
    static final String FEATURE_ID_FILTER = "featureID";
    
    // FeatureIDFilter
    static final String SCHEMA_PROPERTY_NAME_FILTER = "SCHEMA_PROPERTY_FILTER";
    static final String PROPERTY_NAME_FILTER = "propertyName";
    
    
}