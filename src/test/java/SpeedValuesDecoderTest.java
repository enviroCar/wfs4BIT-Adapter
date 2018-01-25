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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import retrofit2.Call;
import retrofit2.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import org.envirocar.bigiot.wfsadapter.config.Config;
import org.envirocar.bigiot.wfsadapter.config.RemoteConfiguration;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@Import(RemoteConfiguration.class)
//public class SpeedValuesDecoderTest {

//    @Autowired
//    private Config wfsConfig;
    
//    @Autowired
 //   private ObjectMapper objectMapper;
//
//    @Test
//    public void testSpeedValuesDecoding() throws IOException {
//        Call<WFSFeatureCollection> speedvalues = this.measurementService.getAsSpeedValues(1);
//        SpeedValues values = speedvalues.execute().body();
//
//        Assert.assertNotNull(values);
//    }

//}