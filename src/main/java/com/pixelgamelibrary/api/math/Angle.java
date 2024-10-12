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

/**
 *
 * @author robertvokac
 */
public class Angle {
    
    public static final float DEFAULT_ROTATION_IN_ANGLES = 0f;
    
    private float degrees;
    public static Angle ofDegrees(float degrees) {
        return new Angle(degrees);
    }

    public static Angle ofRadians(float radians) {
        return new Angle(radians, AngleUnit.RADIAN);
    }

    public static Angle ofGradians(float gradians) {
        return new Angle(gradians, AngleUnit.GRADIAN);
    }

    public static Angle ofNormalized(float normalized) {
        return new Angle(normalized, AngleUnit.NORMALIZED);

    }
    
    public Angle(float value, AngleUnit angleUnit) {
        this(value, angleUnit, false);
    }
    public Angle(float rotatioInAnglesIn) {
        this(rotatioInAnglesIn, false);
    }
    
    public Angle(float value, AngleUnit angleUnit, boolean anticlockwise) {
        this.degrees = AngleUnitConverter.normalizeAnticlockwiseDegrees(AngleUnit.convert(value, angleUnit, AngleUnit.DEGREE), anticlockwise);
    }
    public Angle(float rotatioInAnglesIn, boolean anticlockwise) {
        this.degrees = AngleUnitConverter.normalizeAnticlockwiseDegrees(rotatioInAnglesIn, anticlockwise);
    }
    
    public float asDegrees() {
        return degrees;
    }
    public float asRadians() {
        return as(AngleUnit.RADIAN);
    }
    public float asGradians() {
        return as(AngleUnit.GRADIAN);
    }
    public float asNormalized() {
        return as(AngleUnit.NORMALIZED);
    }
    public float as(AngleUnit angleUnit) {
        if(angleUnit == AngleUnit.DEGREE) {
            return degrees;
        }
        return AngleUnit.convert(degrees, AngleUnit.DEGREE, angleUnit);
    }
    public void set(float value, AngleUnit angleUnit) {
        this.degrees = AngleUnit.convert(value, angleUnit, AngleUnit.DEGREE);
    }
}
