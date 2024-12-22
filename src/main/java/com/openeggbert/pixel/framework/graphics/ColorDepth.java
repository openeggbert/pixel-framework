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
package com.openeggbert.pixel.framework.graphics;

import com.openeggbert.pixel.framework.PixelException;
import lombok.Getter;

/**
 *
 * @author robertvokac
 */
public enum ColorDepth {
    BITS_32(32, 8,8,8),
    BITS_24(24,8,8,8),
    BITS_16(16,5,6,5),
    BITS_8(8,3,3,2),
    BITS_4(4);

    @Getter
    private final int bitCount;
    @Getter
    private final int redBitCount, greenBitCount, blueBitCount;

    ColorDepth(int bitCount, int redBitCount, int greenBitCount, int blueBitCount) {
        int sum = (redBitCount + greenBitCount + blueBitCount);
        if(sum == 24 && bitCount == 32) {
            sum = 32;
        }
        if(sum != 0 && bitCount != sum) {
            throw new PixelException("Fatal internal error: " + "bitCount != (redBitCount + greenBitCount + blueBitCount)");
        }
        this.bitCount = bitCount;
        this.redBitCount = redBitCount;
        this.greenBitCount = greenBitCount;
        this.blueBitCount = blueBitCount;
        
    }
    ColorDepth(int bitCount) {
        this(bitCount, 0, 0, 0);
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
