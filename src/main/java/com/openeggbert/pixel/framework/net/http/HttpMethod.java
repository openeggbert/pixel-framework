// Pixel: Game library.
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see 
// <https://www.gnu.org/licenses/> or write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////
package com.openeggbert.pixel.framework.net.http;

/**
 * Enum representing the various HTTP methods used for making requests. 
 * Each method is described below:
 * <ul>
 * <li><b>HEAD:</b> Requests a response identical to a GET request, but without the response body.</li>
 * <li><b>GET:</b> Requests a representation of the specified resource, intended only for data retrieval.</li>
 * <li><b>POST:</b> Submits data to the specified resource, potentially causing changes or side effects on the server.</li>
 * <li><b>PUT:</b> Replaces all current representations of the target resource with the provided payload.</li>
 * <li><b>PATCH:</b> Applies partial modifications to a resource.</li>
 * <li><b>DELETE:</b> Removes the specified resource.</li>
 * </ul>
 */
public enum HttpMethod {

    /** The HEAD method requests a response identical to a GET request without the response body. */
    HEAD("HEAD"),
    
    /** The GET method requests a representation of the specified resource, intended solely for data retrieval. */
    GET("GET"),
    
    /** The POST method submits an entity to the specified resource, potentially causing changes on the server. */
    POST("POST"),
    
    /** The PUT method replaces all current representations of the target resource with the given payload. */
    PUT("PUT"),
    
    /** The PATCH method is used to apply partial modifications to a resource. */
    PATCH("PATCH"),
    
    /** The DELETE method removes the specified resource. */
    DELETE("DELETE");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
