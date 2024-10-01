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
package com.pixelgamelibrary.api.interfaces;

import com.pixelgamelibrary.api.utils.BinaryUtilsImpl;
import com.pixelgamelibrary.api.utils.CollectionUtils;
import com.pixelgamelibrary.api.utils.ReflectionUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author robertvokac
 */
public interface Utils {
    XmlElement parseXml(String xmlString);
            default String decodeBase64AsString(String string) {
        return new String(decodeBase64AsByteArray(string));
    }
    
    //json support - todo

    byte[] decodeBase64AsByteArray(String string);

    default String encodeToBase64(String string) {
        return encodeToBase64(string.getBytes());
    }

    String encodeToBase64(byte[] data);
    List<String> listSupportedCompressions();
    byte[] compress(byte[] data, String compression, Map<String, String> arguments);
    default byte[] compress(byte[] data, String compression) {
        return compress(data, compression, new HashMap<>());
    }
    byte[] decompress(byte[] data, String compression);
    default Stream<String> splitStringToLinesAsStream(String string) {
        return Arrays.asList(string.split("\\r?\\n")).stream();
    }
    ReflectionUtils reflection();
    CollectionUtils collections();
    default BinaryUtilsImpl binary() {
        return BinaryUtilsImpl.INSTANCE;
    }

}
