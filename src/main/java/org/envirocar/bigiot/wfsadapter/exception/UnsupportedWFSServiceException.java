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
package org.envirocar.bigiot.wfsadapter.exception;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class UnsupportedWFSServiceException extends Exception {

    private int errorCode;

    public UnsupportedWFSServiceException(String msg) {
        this(msg, 500);
    }

    public UnsupportedWFSServiceException(String msg, int errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public UnsupportedWFSServiceException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

}
