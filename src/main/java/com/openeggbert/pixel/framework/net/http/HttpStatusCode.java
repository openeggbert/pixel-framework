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
package com.openeggbert.pixel.framework.net.http;

import com.openeggbert.pixel.framework.PixelException;
import java.util.Arrays;
import java.util.function.Supplier;
import lombok.Getter;

/**
 * Enum representing various HTTP status codes utilized in responses for
 * {@link HttpRequest}. Each constant is associated with its respective integer
 * value, and they are categorized based on the type of response they represent.
 */
public enum HttpStatusCode {

    // --- 1xx Informational Responses ---
    /**
     * Indicates that the initial part of a request has been received and the
     * client should continue with the request.
     */
    CONTINUE(100),
    /**
     * Indicates that the server is switching protocols as requested by the
     * client, as per the client's request.
     */
    SWITCHING_PROTOCOLS(101),
    /**
     * Indicates that the server has received and is processing the request, but
     * no response is available yet.
     */
    PROCESSING(102),
    // --- 2xx Successful Responses ---
    /**
     * Indicates that the request was successful and the server has returned the
     * requested resource.
     */
    OK(200),
    /**
     * Indicates that a new resource has been created in response to a POST
     * request.
     */
    CREATED(201),
    /**
     * Indicates that the request has been accepted for processing, but the
     * processing is not complete.
     */
    ACCEPTED(202),
    /**
     * Indicates that the server successfully processed the request, but is
     * returning information that may differ from the original request.
     */
    NON_AUTHORITATIVE_INFORMATION(203),
    /**
     * Indicates that the server has successfully processed the request, but is
     * not returning any content.
     */
    NO_CONTENT(204),
    /**
     * Indicates that the server has successfully processed the request and
     * wants the client to reset the view.
     */
    RESET_CONTENT(205),
    /**
     * Indicates that the server is delivering only part of the resource due to
     * a range header sent by the client.
     */
    PARTIAL_CONTENT(206),
    /**
     * Indicates multiple status responses for WebDAV.
     */
    MULTI_STATUS(207),
    // --- 3xx Redirection Responses ---
    /**
     * Indicates multiple options for the resource that the client can choose.
     */
    MULTIPLE_CHOICES(300),
    /**
     * Indicates that the requested resource has been permanently moved to a new
     * URI.
     */
    MOVED_PERMANENTLY(301),
    /**
     * Indicates that the requested resource has been temporarily moved to a
     * different URI.
     */
    MOVED_TEMPORARILY(302),
    /**
     * Indicates that the client should perform a GET request to a different URI
     * to retrieve the resource.
     */
    SEE_OTHER(303),
    /**
     * Indicates that the resource has not been modified since the last request,
     * so the client can use the cached version.
     */
    NOT_MODIFIED(304),
    /**
     * Indicates that the requested resource must be accessed through a proxy.
     */
    USE_PROXY(305),
    /**
     * Indicates that the client must perform a temporary redirect to a
     * different URI.
     */
    TEMPORARY_REDIRECT(307),
    // --- 4xx Client Error Responses ---
    /**
     * Indicates that the server cannot process the request due to a client
     * error (e.g., malformed request).
     */
    BAD_REQUEST(400),
    /**
     * Indicates that the request requires user authentication and the client
     * has not provided it.
     */
    UNAUTHORIZED(401),
    /**
     * Indicates that the request was valid, but the server is refusing to
     * process it.
     */
    PAYMENT_REQUIRED(402),
    /**
     * Indicates that the server understands the request but refuses to
     * authorize it.
     */
    FORBIDDEN(403),
    /**
     * Indicates that the requested resource could not be found on the server.
     */
    NOT_FOUND(404),
    /**
     * Indicates that the method specified in the request is not allowed for the
     * resource.
     */
    METHOD_NOT_ALLOWED(405),
    /**
     * Indicates that the requested resource is not acceptable for the client
     * according to the Accept headers.
     */
    NOT_ACCEPTABLE(406),
    /**
     * Indicates that the client must authenticate with the proxy before the
     * requested resource can be retrieved.
     */
    PROXY_AUTHENTICATION_REQUIRED(407),
    /**
     * Indicates that the server timed out waiting for the request.
     */
    REQUEST_TIMEOUT(408),
    /**
     * Indicates that the request could not be completed due to a conflict with
     * the current state of the resource.
     */
    CONFLICT(409),
    /**
     * Indicates that the requested resource is no longer available.
     */
    GONE(410),
    /**
     * Indicates that the request requires a valid Content-Length header.
     */
    LENGTH_REQUIRED(411),
    /**
     * Indicates that one or more conditions in the request header fields
     * evaluated to false.
     */
    PRECONDITION_FAILED(412),
    /**
     * Indicates that the server is refusing to process the request because the
     * request payload is too large.
     */
    REQUEST_TOO_LONG(413),
    /**
     * Indicates that the URI provided was too long for the server to process.
     */
    REQUEST_URI_TOO_LONG(414),
    /**
     * Indicates that the server refuses to accept the request because the
     * payload format is not supported.
     */
    UNSUPPORTED_MEDIA_TYPE(415),
    /**
     * Indicates that the server cannot serve the requested range for the
     * resource.
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416),
    /**
     * Indicates that the server cannot meet the requirements of the Expect
     * request-header field.
     */
    EXPECTATION_FAILED(417),
    // --- 5xx Server Error Responses ---
    /**
     * Indicates that the server encountered an unexpected condition that
     * prevented it from fulfilling the request.
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * Indicates that the server does not support the functionality required to
     * fulfill the request.
     */
    NOT_IMPLEMENTED(501),
    /**
     * Indicates that the server, while acting as a gateway, received an invalid
     * response from the upstream server.
     */
    BAD_GATEWAY(502),
    /**
     * Indicates that the server is currently unable to handle the request due
     * to temporary overloading or maintenance of the server.
     */
    SERVICE_UNAVAILABLE(503),
    /**
     * Indicates that the server, while acting as a gateway, did not receive a
     * timely response from the upstream server.
     */
    GATEWAY_TIMEOUT(504),
    /**
     * Indicates that the server does not support the HTTP protocol version that
     * was used in the request.
     */
    HTTP_VERSION_NOT_SUPPORTED(505),
    /**
     * Indicates insufficient storage space on the server to complete the
     * request.
     */
    INSUFFICIENT_STORAGE(507),
    // --- Special HTTP Codes ---
    /**
     * Indicates that the server has a variant configuration that is not
     * appropriate for the request.
     */
    VARIANT_ALSO_NEGOTIATES(506),
    /**
     * Indicates that the client needs to authenticate to gain network access.
     */
    NETWORK_AUTHENTICATION_REQUIRED(511);

    @Getter
    private final int statusCode;

    /**
     * Private constructor to initialize the HTTP status code.
     *
     * @param statusCode the integer value representing the HTTP status code.
     */
    HttpStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Retrieves the integer value associated with this HTTP status code.
     *
     * @return the HTTP status code as an integer.
     */
    public int getStatusCode() {
        return statusCode;
    }

    public static HttpStatusCode ofCode(int statusCode) {
        return Arrays
                .asList(HttpStatusCode.values())
                .stream()
                .filter(e -> e.getStatusCode() == statusCode)
                .findFirst()
                .orElseThrow(() -> new PixelException("There is no such status code: " + statusCode));
    }
    public int getFirstDigit() {
    return Integer.parseInt(String.valueOf(statusCode).substring(0, 1));
}

}
