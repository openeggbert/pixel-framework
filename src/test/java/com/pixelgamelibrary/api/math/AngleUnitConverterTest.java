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
package com.pixelgamelibrary.api.math;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.pixelgamelibrary.api.PixelException;

class AngleUnitConverterTest {

    private static final float TOLERANCE = 0.0001f;

    @Test
    void testConvertDegreesToRadians() {
        assertEquals((float) Math.PI, AngleUnitConverter.convertDegreesToRadians(180f), TOLERANCE);
        assertEquals((float) Math.PI / 2, AngleUnitConverter.convertDegreesToRadians(90f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertDegreesToRadians(0f), TOLERANCE);
    }

    @Test
    void testConvertDegreesToGradians() {
        assertEquals(100f, AngleUnitConverter.convertDegreesToGradians(90f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertDegreesToGradians(0f), TOLERANCE);
        assertEquals(200f, AngleUnitConverter.convertDegreesToGradians(180f), TOLERANCE);
    }

    @Test
    void testConvertDegreesToNormalized() {
        assertEquals(0.5f, AngleUnitConverter.convertDegreesToNormalized(180f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertDegreesToNormalized(0f), TOLERANCE);
        assertEquals(1f, AngleUnitConverter.convertDegreesToNormalized(360f), TOLERANCE);
    }

    @Test
    void testConvertRadiansToDegrees() {
        assertEquals(180f, AngleUnitConverter.convertRadiansToDegrees((float) Math.PI), TOLERANCE);
        assertEquals(90f, AngleUnitConverter.convertRadiansToDegrees((float) Math.PI / 2), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertRadiansToDegrees(0f), TOLERANCE);
    }

    @Test
    void testConvertGradiansToDegrees() {
        assertEquals(90f, AngleUnitConverter.convertGradiansToDegrees(100f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertGradiansToDegrees(0f), TOLERANCE);
        assertEquals(180f, AngleUnitConverter.convertGradiansToDegrees(200f), TOLERANCE);
    }

    @Test
    void testConvertNormalizedToDegrees() {
        assertEquals(180f, AngleUnitConverter.convertNormalizedToDegrees(0.5f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.convertNormalizedToDegrees(0f), TOLERANCE);
        assertEquals(360f, AngleUnitConverter.convertNormalizedToDegrees(1f), TOLERANCE);
    }

    @Test
    void testConvertBetweenUnits() {
        // Degrees to Radians
        assertEquals((float) Math.PI, AngleUnitConverter.convert(180f, AngleUnit.DEGREE, AngleUnit.RADIAN), TOLERANCE);
        // Radians to Degrees
        assertEquals(180f, AngleUnitConverter.convert((float) Math.PI, AngleUnit.RADIAN, AngleUnit.DEGREE), TOLERANCE);
        // Degrees to Gradians
        assertEquals(200f, AngleUnitConverter.convert(180f, AngleUnit.DEGREE, AngleUnit.GRADIAN), TOLERANCE);
        // Gradians to Degrees
        assertEquals(180f, AngleUnitConverter.convert(200f, AngleUnit.GRADIAN, AngleUnit.DEGREE), TOLERANCE);
        // Degrees to Normalized
        assertEquals(0.5f, AngleUnitConverter.convert(180f, AngleUnit.DEGREE, AngleUnit.NORMALIZED), TOLERANCE);
        // Normalized to Degrees
        assertEquals(180f, AngleUnitConverter.convert(0.5f, AngleUnit.NORMALIZED, AngleUnit.DEGREE), TOLERANCE);
    }

    @Test
    void testNormalizeAnticlockwiseDegrees() {
        assertEquals(270f, AngleUnitConverter.normalizeAnticlockwiseDegrees(90f), TOLERANCE);
        assertEquals(360f, AngleUnitConverter.normalizeAnticlockwiseDegrees(0f), TOLERANCE);
        assertEquals(0f, AngleUnitConverter.normalizeAnticlockwiseDegrees(360f), TOLERANCE);
    }

    @Test
    void testNormalizeAnticlockwiseDegreesIfNeeded() {
        // Enabled
        assertEquals(270f, AngleUnitConverter.normalizeAnticlockwiseDegrees(90f, true), TOLERANCE);
        // Disabled
        assertEquals(90f, AngleUnitConverter.normalizeAnticlockwiseDegrees(90f, false), TOLERANCE);
    }

    @Test
    void testUnsupportedConversion() {
        assertThrows(PixelException.class, () -> AngleUnitConverter.convert(1f, AngleUnit.DEGREE, null));
        assertThrows(PixelException.class, () -> AngleUnitConverter.convert(1f, null, AngleUnit.RADIAN));
    }
}
