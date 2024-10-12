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

import com.pixelgamelibrary.api.PixelException;
import static com.pixelgamelibrary.api.math.AngleUnit.GRADIAN;
import static com.pixelgamelibrary.api.math.AngleUnit.NORMALIZED;
import static com.pixelgamelibrary.api.math.AngleUnit.RADIAN;

/**
 * Utility class for converting between different angle units.
 * Provides methods to convert degrees, radians, gradians, and normalized values.
 * 
 * @author robertvokac
 */
class AngleUnitConverter {

    public static final float _360_DEGREES = 360f;
    public static final float _180_DEGREES = 180f;
    public static final float _90_DEGREES = 90f;
    public static final float DEGREES_PER_RADIAN = _180_DEGREES / (float) Math.PI;
    public static final float RADIANS_PER_DEGREE = (float) Math.PI / _180_DEGREES;
    public static final float GRADIANS_PER_90_DEGREES = 100f;

    private AngleUnitConverter() {
        // Not meant to be instantiated.
    }

    public static float convertDegreesToRadians(float degrees) {
        return degrees * RADIANS_PER_DEGREE;
    }

    public static float convertDegreesToGradians(float degrees) {
        return degrees / _90_DEGREES * GRADIANS_PER_90_DEGREES;
    }

    public static float convertDegreesToNormalized(float degrees) {
        return degrees / _360_DEGREES;
    }

    public static float convertRadiansToDegrees(float radians) {
        return radians * DEGREES_PER_RADIAN;
    }

    public static float convertGradiansToDegrees(float gradians) {
        return gradians / GRADIANS_PER_90_DEGREES * 90f;
    }

    public static float convertNormalizedToDegrees(float normalized) {
        return normalized * _360_DEGREES;
    }

    /**
     * Converts the given value from one angle unit to another.
     *
     * @param value the value to convert
     * @param inputAngleUnit the unit of the input value
     * @param outputAngleUnit the unit of the output value
     * @return the converted value in the target angle unit
     * @throws PixelException if the conversion is not supported
     */
    public static float convert(float value, AngleUnit inputAngleUnit, AngleUnit outputAngleUnit) {
        if (inputAngleUnit == outputAngleUnit) {
            // No conversion needed
            return value;
        }
        if (inputAngleUnit == AngleUnit.DEGREE) {
            switch (outputAngleUnit) {
                case NORMALIZED:
                    return convertDegreesToNormalized(value);
                case RADIAN:
                    return convertDegreesToRadians(value);
                case GRADIAN:
                    return convertDegreesToGradians(value);
                default:
                    throw new PixelException("Unsupported operation.");
            }
        }

        if (outputAngleUnit == AngleUnit.DEGREE) {
            switch (inputAngleUnit) {
                case NORMALIZED:
                    return convertNormalizedToDegrees(value);
                case RADIAN:
                    return convertRadiansToDegrees(value);
                case GRADIAN:
                    return convertGradiansToDegrees(value);
                default:
                    throw new PixelException("Unsupported operation.");
            }
        }
        // Convert to degrees as an intermediate step
        float degrees = convert(value, inputAngleUnit, AngleUnit.DEGREE);
        return convert(degrees, AngleUnit.DEGREE, outputAngleUnit);
    }
}
