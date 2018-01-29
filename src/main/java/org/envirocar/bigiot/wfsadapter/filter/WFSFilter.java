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
package org.envirocar.bigiot.wfsadapter.filter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class WFSFilter {

    private final CustomWFSFilter customWFSFilter;
    private final MaxFeaturesFilter maxFeaturesFilter;
    private final SortByFilter sortByFilter;
    private final BoundingBoxFilter bbFilter;
    private final FeatureIDFilter featureIDFilter;
    private final PropertyNameFilter propertyNameFilter;

    /**
     * Constructor.
     *
     * @param customWFSFilter
     */
    public WFSFilter(CustomWFSFilter customWFSFilter) {
        this(customWFSFilter, null, null, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param maxFeaturesFilter
     */
    public WFSFilter(MaxFeaturesFilter maxFeaturesFilter) {
        this(null, maxFeaturesFilter, null, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param sortByFilter
     */
    public WFSFilter(SortByFilter sortByFilter) {
        this(null, null, sortByFilter, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param bbFilter
     */
    public WFSFilter(BoundingBoxFilter bbFilter) {
        this(null, null, null, bbFilter, null, null);
    }

    /**
     * Constructor.
     *
     * @param featureIDFilter
     */
    public WFSFilter(FeatureIDFilter featureIDFilter) {
        this(null, null, null, null, featureIDFilter, null);
    }

    /**
     * Constructor.
     *
     * @param customWFSFilter
     * @param maxFeaturesFilter
     * @param sortByFilter
     * @param bbFilter
     * @param featureIDFilter
     * @param propertyNameFilter
     */
    public WFSFilter(CustomWFSFilter customWFSFilter, MaxFeaturesFilter maxFeaturesFilter, SortByFilter sortByFilter, BoundingBoxFilter bbFilter, FeatureIDFilter featureIDFilter, PropertyNameFilter propertyNameFilter) {
        this.customWFSFilter = customWFSFilter;
        this.maxFeaturesFilter = maxFeaturesFilter;
        this.sortByFilter = sortByFilter;
        this.bbFilter = bbFilter;
        this.featureIDFilter = featureIDFilter;
        this.propertyNameFilter = propertyNameFilter;
    }

    public BoundingBoxFilter getBoundingBoxFilter() {
        return bbFilter;
    }

    public boolean hasBoundingBoxFilter() {
        return this.bbFilter != null;
    }

    public SortByFilter getSortByFilter() {
        return this.sortByFilter;
    }

    public boolean hasSortByFilter() {
        return this.sortByFilter != null;
    }

    public CustomWFSFilter getCustomWFSFilter() {
        return this.customWFSFilter;
    }

    public boolean hasCustomWFSFilter() {
        return this.customWFSFilter != null;
    }

    public MaxFeaturesFilter getMaxFeaturesFilter() {
        return maxFeaturesFilter;
    }

    public boolean hasMaxFeaturesFilter() {
        return this.maxFeaturesFilter != null;
    }

    public FeatureIDFilter getFeatureIDFilter() {
        return featureIDFilter;
    }

    public boolean hasFeatureIDFilter() {
        return this.featureIDFilter != null;
    }

    public PropertyNameFilter getPropertyNameFilter() {
        return propertyNameFilter;
    }

    public boolean hasPropertyNameFilter() {
        return this.propertyNameFilter != null;
    }

}
