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
package org.envirocar.bigiot.wfsadapter.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class WFSFeatureMember implements BaseEntity {
    
    private List<WFSProperty> properties;
    private WFSProperty geom;
    
    public WFSFeatureMember() {
        this.properties = new ArrayList<>();
        this.geom = null;
    }
    
    public WFSFeatureMember(List<WFSProperty> properties) {
        this.properties = properties;
        this.geom = null;
    }

    public List<WFSProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WFSProperty> properties) {
        this.properties = properties;
    }
    
    public void addProperty(WFSProperty property) {
        this.properties.add(property);
    }

    public WFSProperty getGeom() {
        return geom;
    }

    public void setGeom(WFSProperty geom) {
        this.geom = geom;
    }
    
}