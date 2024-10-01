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
package com.pixelgamelibrary.api.graphics;

import com.pixelgamelibrary.api.PixelException;
import lombok.Getter;

/**
 *
 * @author robertvokac
 */
public enum ColorDepth {
    BITS_32(32),
    BITS_24(24),
    BITS_16(16),
    BITS_8(8),
    BITS_4(4);
    
    @Getter
    private final int bitCount;

    ColorDepth(int bitCount) {
        this.bitCount = bitCount;
    }
    public static ColorDepth from(int bitCount) {
        switch (bitCount) {
            case 32:
                return BITS_32;
            case 24:
                return BITS_24;
            case 16:
                return BITS_16;
            case 8:
                return BITS_8;
            case 4:
                return BITS_4;
            default:
                throw new PixelException("Unsupported bit depth");
        }
    }
}
