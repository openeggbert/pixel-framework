////////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: Game Library - Extension Module
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or modify it under the terms of
// the GNU General Public License as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
// without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with this program. 
// If not, see <https://www.gnu.org/licenses/> or write to the Free Software Foundation, Inc., 
// 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
////////////////////////////////////////////////////////////////////////////////////////////////
package com.pixelgamelibrary.api.interfaces;

import java.util.List;

/**
 * The Extension interface allows the registration and retrieval of various 
 * implementations for specific interfaces. It acts as a flexible extension 
 * module, enabling dynamic modularization and adding extensibility to the 
 * Pixel Game Library.
 *
 * This interface provides generic methods to register, retrieve, and check 
 * the availability of registered implementations, offering a loosely coupled 
 * and highly flexible way to extend the game library.
 *
 * @author robertvokac
 */
public interface Extension {
    
    /**
     * Registers an implementation for a given interface class.
     * 
     * This method allows you to register an implementation for a specific 
     * interface, enabling the extension mechanism to return the appropriate 
     * implementation when requested.
     *
     * @param <T> the type of the interface to be registered
     * @param interfaceClass the Class object of the interface to be implemented
     * @param implementation the instance of the implementation that corresponds to the interface
     */
    <T> void register(Class<T> interfaceClass, T implementation);

    /**
     * Retrieves the registered implementation for a given interface.
     * 
     * This method returns the implementation instance that was previously 
     * registered for the provided interface class.
     *
     * @param <T> the type of the interface
     * @param clazz the Class object of the interface for which the implementation is requested
     * @return the registered implementation of the interface, or null if no implementation is registered
     */
    <T> T get(Class<T> clazz);
    
    default <T> T getOrDefault(Class<T> clazz, T defaultInstance) {
        if(has(clazz)) {
            return get(clazz);
        } else {
            return defaultInstance;
        }
    }
    
    /**
     * Checks if an implementation is registered for a specific interface class.
     * 
     * This method verifies whether there is an implementation registered for 
     * the given interface class.
     *
     * @param <T> the type of the interface
     * @param clazz the Class object of the interface to be checked
     * @return true if an implementation is registered, false otherwise
     */
    <T> boolean has(Class<T> clazz);
    
    /**
     * Lists all the registered interface classes.
     * 
     * This method provides a list of all the interface classes for which 
     * implementations have been registered. It can be useful for debugging or 
     * introspection purposes.
     *
     * @return a List of Class objects representing the registered interfaces
     */
    List<Class<?>> list();
}
