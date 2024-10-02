///////////////////////////////////////////////////////////////////////////////////////////////
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
package com.pixelgamelibrary.api.net.sockets;

/**
 * @author robertvokac
 */
public class ClientSocketHints {
    public static final ClientSocketHints getDefault() {
        return new ClientSocketHints();
    }

    public int connectionMsTimeout = 5000;
    public SocketPerformancePreferences performancePreferences = new SocketPerformancePreferences();
    public int trafficClass = 0x14;

    public boolean keepAlive = true;
    public boolean tcpNoDelay = true;
    public int sendBufferSize = 4096;
    public int receiveBufferSize = 4096;
    public boolean linger = false;
    public int lingerDuration = 0;
    public int socketTimeout = 0;
}
