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

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class CustomWFSFilter {

    private static final Logger LOG = LoggerFactory.getLogger(CustomWFSFilter.class);

    private final String customFilter;
    
    /**
     * Constructor.
     *
     * @param customFilter
     */
    public CustomWFSFilter(String customFilter) {
        this.customFilter = customFilter;
    }

    public String getCustomWFSFitler() {
        return customFilter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.customFilter);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomWFSFilter other = (CustomWFSFilter) obj;
        if (!Objects.equals(this.customFilter, other.customFilter)) {
            return false;
        }
        return true;
    }

    public String string() {
        String customFilterText = "";
        if (customFilter != null) {
            customFilterText = customFilter;
        } 
        return customFilterText;
    }

    @Override
    public String toString() {
        return "CustomWFSFilter{"+"Filter=" + customFilter + "}";
    }
    
}