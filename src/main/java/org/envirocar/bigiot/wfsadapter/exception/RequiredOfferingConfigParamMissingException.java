/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.envirocar.bigiot.wfsadapter.exception;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class RequiredOfferingConfigParamMissingException extends Exception {

    public RequiredOfferingConfigParamMissingException() {
        super();
    }

    public RequiredOfferingConfigParamMissingException(String message) {
        super(message);
    }

}