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
package org.envirocar.bigiot.wfsadapter;

import java.io.IOException;

import org.eclipse.bigiot.lib.Provider;
import org.eclipse.bigiot.lib.ProviderSpark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Arne de Wall <a.dewall@52north.org>
 */
@Configuration
public class AdapterConfiguration {

    @Bean
    public Provider provideProviderSpark(
            @Value("${bigiot.provider.id}") String providerId,
            @Value("${bigiot.marketplace.url}") String marketplaceUri,
            @Value("${bigiot.provider.address}") String localDomain,
            @Value("${bigiot.provider.port}") int port,
            @Value("${bigiot.provider.secret}") String secret) throws IOException {
        System.out.println(providerId + " : " + secret);
        Provider provider = new ProviderSpark(providerId, marketplaceUri, localDomain, port);
        provider.authenticate(secret);
        return provider;
    }
}
