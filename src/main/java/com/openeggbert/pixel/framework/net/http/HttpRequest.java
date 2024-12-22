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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an HTTP request, containing methods to configure various parameters:
 * <ul>
 * <li><strong>httpMethod:</strong> Common methods include GET and POST. Utilize {@link HttpMethod} for static references.</li>
 * <li><strong>url:</strong> The endpoint for the request.</li>
 * <li><strong>headers:</strong> A map of headers; the setter can be called multiple times.</li>
 * <li><strong>timeout:</strong> Duration to attempt a connection before timing out.</li>
 * <li><strong>content:</strong> A string that holds the data for processing the HTTP request.</li>
 * </ul>
 * 
 * Example usage:
 * 
 * <pre>
 * Map&lt;String, String&gt; parameters = new HashMap&lt;&gt;();
 * parameters.put("user", "myuser");
 * 
 * HttpRequest httpGet = new HttpRequest(HttpMethod.GET);
 * httpGet.setUrl("http://example.com");
 * httpGet.setContent(HttpParametersUtils.convertHttpParameters(parameters));
 * ...
 * NetworkUtils.sendHttpRequest(httpGet, new HttpResponseListener() {
 *     public void handleHttpResponse(HttpResponse response) {
 *         String status = response.getResultAsString();
 *         // Process response here
 *     }
 * 
 *     public void failed(Throwable t) {
 *         String status = "failed";
 *         // Handle failure here
 *     }
 * });
 * </pre>
 */
public class HttpRequest {

    private String httpMethod;
    private String url;
    private Map<String, String> headers;
    private int timeout;

    private String content;
    private InputStream contentStream;
    private long contentLength;

    private boolean followRedirects = true;
    private boolean includeCredentials = false;

    public HttpRequest() {
        this.headers = new HashMap<>();
        this.timeout = 0; // Default timeout value
    }

    /** 
     * Constructs a new HTTP request with the specified method.
     * @param httpMethod The HTTP method for the request, e.g., GET or POST.
     */
    public HttpRequest(String httpMethod) {
        this();
        this.httpMethod = httpMethod;
    }

    /** 
     * Sets the URL for the HTTP request.
     * @param url The URL to set. 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 
     * Adds a header to the HTTP request.
     * @param name The name of the header.
     * @param value The value of the header. 
     */
    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    /** 
     * Sets the content to be sent with the HTTP request.
     * @param content A string representing the data to be sent.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /** 
     * Sets the content as an input stream for transmission, useful for larger data.
     * @param contentStream The stream containing the content data.
     * @param contentLength The length of the content.
     */
    public void setContent(InputStream contentStream, long contentLength) {
        this.contentStream = contentStream;
        this.contentLength = contentLength;
    }

    /** 
     * Sets the timeout duration for the HTTP request.
     * @param timeout The number of milliseconds to wait before timing out. 
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /** 
     * Specifies whether to follow redirects for HTTP requests.
     * @param followRedirects True to follow redirects, false otherwise.
     */
    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    /** 
     * Indicates if credentials should be included in cross-origin requests.
     * @param includeCredentials True to include credentials, false otherwise.
     */
    public void setIncludeCredentials(boolean includeCredentials) {
        this.includeCredentials = includeCredentials;
    }

    /** 
     * Sets the HTTP method of the request.
     * @param httpMethod The HTTP method to set.
     */
    public void setMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /** 
     * Gets the timeout value for the HTTP request.
     * @return The timeout in milliseconds.
     */
    public int getTimeout() {
        return timeout;
    }

    /** 
     * Gets the HTTP method of the request.
     * @return The HTTP method.
     */
    public String getMethod() {
        return httpMethod;
    }

    /** 
     * Gets the URL of the HTTP request.
     * @return The URL.
     */
    public String getUrl() {
        return url;
    }

    /** 
     * Gets the content string for the HTTP request.
     * @return The content string.
     */
    public String getContent() {
        return content;
    }

    /** 
     * Gets the input stream containing the content.
     * @return The content stream.
     */
    public InputStream getContentStream() {
        return contentStream;
    }

    /** 
     * Gets the length of the content if it is provided as a stream.
     * @return The length of the content.
     */
    public long getContentLength() {
        return contentLength;
    }

    /** 
     * Gets the headers of the HTTP request.
     * @return A map of headers.
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /** 
     * Indicates if redirects should be followed.
     * @return True if redirects are followed, false otherwise.
     */
    public boolean getFollowRedirects() {
        return followRedirects;
    }

    /** 
     * Indicates if credentials are included in cross-origin requests.
     * @return True if credentials are included, false otherwise.
     */
    public boolean getIncludeCredentials() {
        return includeCredentials;
    }

    /** 
     * Resets the request to its initial state.
     */
    public void reset() {
        httpMethod = null;
        url = null;
        headers.clear();
        timeout = 0;

        content = null;
        contentStream = null;
        contentLength = 0;

        followRedirects = true;
        includeCredentials = false;
    }
}
