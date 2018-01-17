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

    /**
     * Constructor.
     *
     * @param customWFSFilter
     */
    public WFSFilter(CustomWFSFilter customWFSFilter) {
        this(customWFSFilter, null, null, null);
    }
    
    /**
     * Constructor.
     *
     * @param maxFeaturesFilter
     */
    public WFSFilter(MaxFeaturesFilter maxFeaturesFilter) {
        this(null, maxFeaturesFilter, null, null);
    }

    /**
     * Constructor.
     *
     * @param sortByFilter
     */
    public WFSFilter(SortByFilter sortByFilter) {
        this(null, null, sortByFilter, null);
    }
    
    /**
     * Constructor.
     *
     * @param bbFilter
     */
    public WFSFilter(BoundingBoxFilter bbFilter) {
        this(null, null, null, bbFilter);
    }


    /**
     * Constructor.
     *
     * @param customWFSFilter
     * @param maxFeaturesFilter
     * @param sortByFilter
     * @param bbFilter
     */
    public WFSFilter(CustomWFSFilter customWFSFilter, MaxFeaturesFilter maxFeaturesFilter, SortByFilter sortByFilter, BoundingBoxFilter bbFilter) {
        this.customWFSFilter = customWFSFilter;
        this.maxFeaturesFilter = maxFeaturesFilter;
        this.sortByFilter = sortByFilter;
        this.bbFilter = bbFilter;
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

}