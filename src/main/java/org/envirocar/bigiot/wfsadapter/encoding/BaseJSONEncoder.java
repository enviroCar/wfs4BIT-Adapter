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
import com.fasterxml.jackson.databind.JsonSerializer;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author dewall
 */
public abstract class BaseJSONEncoder<T> extends JsonSerializer<T> {

    protected final void writeArrayOfObjects(JsonGenerator gen, String fieldName, List<?> objects) throws IOException {
        gen.writeArrayFieldStart(fieldName);
        for (Object o : objects) {
            gen.writeObject(o);
        }
        gen.writeEndArray();
    }

    protected final void writeArrayOfObjects(JsonGenerator gen, List<?> objects) throws IOException {
        gen.writeStartArray();
        for (Object o : objects) {
            gen.writeObject(o);
        }
        gen.writeEndArray();
    }
}
