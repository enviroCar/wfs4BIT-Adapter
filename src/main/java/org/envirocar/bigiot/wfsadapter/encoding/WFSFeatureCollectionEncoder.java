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
package org.envirocar.bigiot.wfsadapter.encoding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import org.envirocar.bigiot.wfsadapter.model.WFSFeatureCollection;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class WFSFeatureCollectionEncoder extends BaseJSONEncoder<WFSFeatureCollection> {

    @Override
    public void serialize(WFSFeatureCollection wfsfc, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        
        writeArrayOfObjects(gen, wfsfc.getFeatures());
        
    }

}
