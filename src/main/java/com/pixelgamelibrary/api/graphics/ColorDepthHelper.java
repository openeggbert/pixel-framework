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

import com.pixelgamelibrary.api.Pixel;
import com.pixelgamelibrary.api.utils.BinaryUtils;
import java.util.BitSet;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author robertvokac
 */
@AllArgsConstructor
@Getter
class ColorDepthHelper {

    final int r, g, b;
    final int redBits, greenBits, blueBits;

    ColorDepthHelper(Color color, ColorDepth colorDepth) {
        this(color, colorDepth.getRedBitCount(), colorDepth.getGreenBitCount(), colorDepth.getBlueBitCount());
    }
    ColorDepthHelper(Color color, int redBits, int greenBits, int blueBits) {
        // Create masks based on the number of bits for each color component
        int redMask = (1 << redBits) - 1;   // Mask for red
        int greenMask = (1 << greenBits) - 1; // Mask for green
        int blueMask = (1 << blueBits) - 1;  // Mask for blue

        // Reduce each color component to the specified number of bits and apply the mask
        this.r = (Color.floatElementToInt(color.getRed()) >> (BinaryUtils.BITS_PER_BYTE - redBits)) & redMask;
        this.g = (Color.floatElementToInt(color.getGreen()) >> (BinaryUtils.BITS_PER_BYTE - greenBits)) & greenMask;
        this.b = (Color.floatElementToInt(color.getBlue()) >> (BinaryUtils.BITS_PER_BYTE - blueBits)) & blueMask;
        this.redBits = redBits;
        this.greenBits = greenBits;
        this.blueBits = blueBits;
    }
    BitSet getBitSet() {
        BinaryUtils bu = Pixel.utils().binary();
        return bu.merge3BitSets(
                bu.convertIntToBitSet(r, redBits),
                bu.convertIntToBitSet(g, greenBits),
                bu.convertIntToBitSet(b, blueBits),
                redBits, 
                greenBits, 
                blueBits);
    }
}
