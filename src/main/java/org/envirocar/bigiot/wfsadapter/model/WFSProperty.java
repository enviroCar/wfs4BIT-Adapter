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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.envirocar.bigiot.wfsadapter.model;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class WFSProperty {
    
    private String propertyName;
    private Object propertyValue;
    private String propertySchema;

    public WFSProperty(String propertyName, Object propertyValue, String propertySchema) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.propertySchema = propertySchema;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertySchema() {
        return propertySchema;
    }

    public void setPropertySchema(String propertySchema) {
        this.propertySchema = propertySchema;
    }
    
}
