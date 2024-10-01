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
package com.pixelgamelibrary.api.utils;

import java.util.BitSet;

/**
 *
 * @author robertvokac
 */
public class BinaryUtilsImpl implements BinaryUtils {
    
    public static final BinaryUtilsImpl INSTANCE = new BinaryUtilsImpl();
    private BinaryUtilsImpl() {
        
    }
    public BitSet convertIntToBitSet(int value, int bitCount) {
        BitSet bitSet = new BitSet(bitCount);
        
        int index = 0;
        
        while (value != 0) {
            if ((value & 1) != 0) {
                bitSet.set(index);
            }
            value >>= 1;
            index++;
        }
        return bitSet;
    }
    public BitSet merge3BitSets(BitSet bitSet1, BitSet bitSet2, BitSet bitSet3, int bitSet1Size, int bitSet2Size, int bitSet3Size) {
        // Create a new BitSet with a size corresponding to the sum of the sizes of all input BitSets
        int totalSize = bitSet1Size + bitSet2Size + bitSet3Size;
        BitSet result = new BitSet(totalSize);

        int currentIndex = 0; // Current index for the output BitSet

        // Add bits from the first BitSet
        for (int i = 0; i < bitSet1Size; i++) {
            if (bitSet1.get(i)) {
                result.set(currentIndex);
            }
            currentIndex++;
        }

        // Add bits from the second BitSet
        for (int i = 0; i < bitSet2Size; i++) {
            if (bitSet2.get(i)) {
                result.set(currentIndex);
            }
            currentIndex++;
        }

        // Add bits from the third BitSet
        for (int i = 0; i < bitSet3Size; i++) {
            if (bitSet3.get(i)) {
                result.set(currentIndex);
            }
            currentIndex++;
        }

        return result;
    }

}
