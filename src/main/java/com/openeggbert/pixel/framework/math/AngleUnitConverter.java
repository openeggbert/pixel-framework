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
package com.openeggbert.pixel.framework.math;

import com.openeggbert.pixel.framework.PixelException;
import static com.openeggbert.pixel.framework.math.AngleUnit.GRADIAN;
import static com.openeggbert.pixel.framework.math.AngleUnit.NORMALIZED;
import static com.openeggbert.pixel.framework.math.AngleUnit.RADIAN;

/**
 * Utility class for converting between different angle units.
 * Provides methods to convert degrees, radians, gradians, and normalized values.
 * 
 * The class is designed as a utility and should not be instantiated.
 * It offers conversion methods for common angle units to facilitate
 * mathematical calculations in game development.
 * 
 * @author robertvokac
 */
class AngleUnitConverter {

    // Constant values for angle conversions
    public static final float _360_DEGREES = 360f;
    public static final float _180_DEGREES = 180f;
    public static final float _90_DEGREES = 90f;
    public static final float DEGREES_PER_RADIAN = _180_DEGREES / (float) Math.PI;
    public static final float RADIANS_PER_DEGREE = (float) Math.PI / _180_DEGREES;
    public static final float GRADIANS_PER_90_DEGREES = 100f;

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class meant to provide static methods only.
     */
    private AngleUnitConverter() {
        // Not meant to be instantiated.
    }

    /**
     * Converts an angle from degrees to radians.
     *
     * @param degrees the angle in degrees
     * @return the angle converted to radians
     */
    public static float convertDegreesToRadians(float degrees) {
        return degrees * RADIANS_PER_DEGREE;
    }

    /**
     * Converts an angle from degrees to gradians.
     * Gradians are a metric unit of angular measure where 90 degrees equals 100 gradians.
     *
     * @param degrees the angle in degrees
     * @return the angle converted to gradians
     */
    public static float convertDegreesToGradians(float degrees) {
        return degrees / _90_DEGREES * GRADIANS_PER_90_DEGREES;
    }

    /**
     * Converts an angle from degrees to a normalized value between 0 and 1.
     * Normalized angles are useful for representing fractional rotations.
     *
     * @param degrees the angle in degrees
     * @return the normalized angle
     */
    public static float convertDegreesToNormalized(float degrees) {
        return degrees / _360_DEGREES;
    }

    /**
     * Converts an angle from radians to degrees.
     *
     * @param radians the angle in radians
     * @return the angle converted to degrees
     */
    public static float convertRadiansToDegrees(float radians) {
        return radians * DEGREES_PER_RADIAN;
    }

    /**
     * Converts an angle from gradians to degrees.
     *
     * @param gradians the angle in gradians
     * @return the angle converted to degrees
     */
    public static float convertGradiansToDegrees(float gradians) {
        return gradians / GRADIANS_PER_90_DEGREES * _90_DEGREES;
    }

    /**
     * Converts a normalized angle value to degrees.
     * A normalized value is assumed to be between 0 and 1.
     *
     * @param normalized the normalized angle value
     * @return the angle converted to degrees
     */
    public static float convertNormalizedToDegrees(float normalized) {
        return normalized * _360_DEGREES;
    }

    /**
     * Converts the given value from one angle unit to another.
     * Supports conversion between degrees, radians, gradians, and normalized values.
     *
     * If the input and output angle units are the same, the original value is returned.
     *
     * @param value the value to convert
     * @param inputAngleUnit the unit of the input value
     * @param outputAngleUnit the unit of the output value
     * @return the converted value in the target angle unit
     * @throws PixelException if the conversion is not supported
     */
    public static float convert(float value, AngleUnit inputAngleUnit, AngleUnit outputAngleUnit) {
        if (inputAngleUnit == outputAngleUnit) {
            // No conversion needed if the units are the same
            return value;
        }
        if(inputAngleUnit == null) {
            throw new PixelException("inputAngleUnit is null");
        }
        
        if(outputAngleUnit == null) {
            throw new PixelException("outputAngleUnit is null");
        }
        if (inputAngleUnit == AngleUnit.DEGREE) {
            // Convert from degrees to the specified output unit
            switch (outputAngleUnit) {
                case NORMALIZED:
                    return convertDegreesToNormalized(value);
                case RADIAN:
                    return convertDegreesToRadians(value);
                case GRADIAN:
                    return convertDegreesToGradians(value);
                default:
                    throw new PixelException("Unsupported conversion from degrees.");
            }
        }

        if (outputAngleUnit == AngleUnit.DEGREE) {
            // Convert from the specified input unit to degrees
            switch (inputAngleUnit) {
                case NORMALIZED:
                    return convertNormalizedToDegrees(value);
                case RADIAN:
                    return convertRadiansToDegrees(value);
                case GRADIAN:
                    return convertGradiansToDegrees(value);
                default:
                    throw new PixelException("Unsupported conversion to degrees.");
            }
        }

        // If neither the input nor output is in degrees, convert via degrees as an intermediate step
        float degrees = convert(value, inputAngleUnit, AngleUnit.DEGREE);
        return convert(degrees, AngleUnit.DEGREE, outputAngleUnit);
    }
    
    public static float normalizeAnticlockwiseDegrees(float degrees, boolean enabled) {
        return enabled ? _360_DEGREES - degrees : degrees;
    }
    public static float normalizeAnticlockwiseDegrees(float degrees) {
        return normalizeAnticlockwiseDegrees(degrees, true);
    }
}
