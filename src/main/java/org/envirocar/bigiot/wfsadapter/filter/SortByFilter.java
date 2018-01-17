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
public class SortByFilter {

    private static final Logger LOG = LoggerFactory.getLogger(SortByFilter.class);

    private final String sortByAttribute;
    private final Boolean sortAscending;

    /**
     * Constructor.
     *
     * @param sortByAttribute
     * @param sortAscending
     */
    public SortByFilter(String sortByAttribute, Boolean sortAscending) {
        this.sortByAttribute = sortByAttribute;
        this.sortAscending = sortAscending;
    }

    public String getSortByAttribute() {
        return sortByAttribute;
    }

    public boolean isSortAscending() {
        return sortAscending;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.sortByAttribute);
        hash = 47 * hash + Objects.hashCode(this.sortAscending);
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
        final SortByFilter other = (SortByFilter) obj;
        if (!Objects.equals(this.sortByAttribute, other.sortByAttribute)) {
            return false;
        }
        if (!Objects.equals(this.sortAscending, other.sortAscending)) {
            return false;
        }
        return true;
    }

    public String string() {
        String sortByText = "";
        if (sortByAttribute != null) {
            sortByText = sortByAttribute;
            if (sortAscending != null) {
                if (sortAscending) {
                    sortByText += "+A";
                } else {
                    sortByText += "+D";
                }
            }
        }
        return sortByText;
    }

    @Override
    public String toString() {
        return "SortByFilter{" + "SortAttribute=" + sortByAttribute + ", Ascending=" + sortAscending + "}";
    }

}