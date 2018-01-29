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

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class BoundingBoxFilter {

    private static final Logger LOG = LoggerFactory.getLogger(BoundingBoxFilter.class);

    private final Double minX;
    private final Double maxX;
    private final Double minY;
    private final Double maxY;

    /**
     * Constructor.
     *
     * @param minX
     * @param minY
     * @param maxX
     * @param maxY
     */
    public BoundingBoxFilter(Double minX, Double minY, Double maxX, Double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Double getMinX() {
        return minX;
    }
    
    public Double getMinY() {
        return minY;
    }

    public Double getMaxX() {
        return maxX;
    }

    public Double getMaxY() {
        return maxY;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.minX) ^ (Double.doubleToLongBits(this.minX) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.minY) ^ (Double.doubleToLongBits(this.minY) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.maxX) ^ (Double.doubleToLongBits(this.maxX) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.maxY) ^ (Double.doubleToLongBits(this.maxY) >>> 32));
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
        final BoundingBoxFilter other = (BoundingBoxFilter) obj;
        if (!Objects.equals(this.minX, other.minX)) {
            return false;
        }
        if (!Objects.equals(this.minY, other.minY)) {
            return false;
        }
        if (!Objects.equals(this.maxX, other.maxX)) {
            return false;
        }
        if (!Objects.equals(this.maxY, other.maxY)) {
            return false;
        }
        return true;
    }

    public String string() {
        return String.join(",", String.valueOf(minY), String.valueOf(minX),
                String.valueOf(maxY), String.valueOf(maxX));
    }

    @Override
    public String toString() {
        return "BoundingBoxFilter{" + "xmin=" + minX + ", ymin=" + minY + ", xmax=" + maxX + ", ymax=" + maxY + '}';
    }

}
