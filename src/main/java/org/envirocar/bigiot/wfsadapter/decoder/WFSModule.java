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
package org.envirocar.bigiot.wfsadapter.decoder;

import com.fasterxml.jackson.databind.module.SimpleModule;

import org.envirocar.bigiot.wfsadapter.encoding.WFSFeatureCollectionEncoder;
import org.envirocar.bigiot.wfsadapter.encoding.WFSFeatureMemberEncoder;
import org.envirocar.bigiot.wfsadapter.model.WFSFeatureCollection;
import org.envirocar.bigiot.wfsadapter.model.WFSFeatureMember;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class WFSModule extends SimpleModule {

    public WFSModule() {
        this.addSerializer(WFSFeatureCollection.class, new WFSFeatureCollectionEncoder());
        this.addSerializer(WFSFeatureMember.class, new WFSFeatureMemberEncoder());
    }

}
