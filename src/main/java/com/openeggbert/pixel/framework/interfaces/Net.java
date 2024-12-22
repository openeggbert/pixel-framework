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
package com.openeggbert.pixel.framework.interfaces;

import com.openeggbert.pixel.framework.net.sockets.ClientSocket;
import com.openeggbert.pixel.framework.net.sockets.ServerSocket;
import com.openeggbert.pixel.framework.net.sockets.ClientSocketHints;
import com.openeggbert.pixel.framework.net.http.HttpRequest;
import com.openeggbert.pixel.framework.net.http.HttpResponseListener;
import com.openeggbert.pixel.framework.net.sockets.ServerSocketHints;

/**
 *
 * @author robertvokac
 */
public interface Net {

    public void sendHttpRequest(HttpRequest httpRequest, HttpResponseListener httpResponseListener);

    public void cancelHttpRequest(HttpRequest httpRequest);

    public boolean isHttpRequestPending(HttpRequest httpRequest);

    public ServerSocket newServerSocket(String hostNameOrIpAddress, int port, ServerSocketHints hints);

    ServerSocket newServerSocket(int port, ServerSocketHints hints);

    default ServerSocket newServerSocket(String hostNameOrIpAddress, int port) {
        return newServerSocket(hostNameOrIpAddress, port, ServerSocketHints.getDefault());
    }

    default ServerSocket newServerSocket(int port) {
        return newServerSocket(port, ServerSocketHints.getDefault());
    }

    ClientSocket newClientSocket(String hostNameOrIpAddress, int port, ClientSocketHints hints);

    default ClientSocket newClientSocket(String hostNameOrIpAddress, int port) {
        return newClientSocket(hostNameOrIpAddress, port, ClientSocketHints.getDefault());
    }

}
