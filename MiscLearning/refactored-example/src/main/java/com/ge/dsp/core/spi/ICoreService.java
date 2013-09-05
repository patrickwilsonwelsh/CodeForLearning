/*
 * Copyright (c) 2012 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.ge.dsp.core.spi;

/**
 * Defines core services provided by DSP kernel.
 */
public interface ICoreService
{
    // add something so that we track each bundle provided by dsp or core service. Anything plugged in will be tracked, then
    // during shutdown phase, or in maintenance mode we leave all them and keep the core running. Then we can restart them if want.
    // this is useful in upgrade mode.

    /**
     * Get a list of all registered cartridges that are registered.
     * 
     * @return list of CartridgeInfo objects, each represents one cartridge
     */
    // public List<CartridgeInfo> listAllRegisteredCartridges();

    /**
     * Stop and uninstall the cartridge by its id.
     * 
     * @param cartridgeId cartridge id assigned, same as the bundle id
     */
    // public void uninstallCartridge(int cartridgeId);

    /**
     * Get unique id for the kernel or this running instance. This id is not changed unless manually modified in the config file.
     * This id is initialized the first time the instance is started.
     * 
     * @return a unique uuid
     */
    public String getKernelId();
}
