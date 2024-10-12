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

class AngleTest {

    private static final float TOLERANCE = 0.0001f;

    @Test
    void testOfDegrees() {
        Angle angle = Angle.ofDegrees(90f);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), TOLERANCE);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testOfRadians() {
        Angle angle = Angle.ofRadians((float) Math.PI);
        assertEquals(180f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI, angle.asRadians(), TOLERANCE);
        assertEquals(200f, angle.asGradians(), TOLERANCE);
        assertEquals(0.5f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testOfGradians() {
        Angle angle = Angle.ofGradians(100f);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), TOLERANCE);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testOfNormalized() {
        Angle angle = Angle.ofNormalized(0.5f);
        assertEquals(180f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI, angle.asRadians(), TOLERANCE);
        assertEquals(200f, angle.asGradians(), TOLERANCE);
        assertEquals(0.5f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testConstructorWithDegrees() {
        Angle angle = new Angle(270f);
        assertEquals(270f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) (3 * Math.PI / 2), angle.asRadians(), TOLERANCE);
        assertEquals(300f, angle.asGradians(), TOLERANCE);
        assertEquals(0.75f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testConstructorWithDegreesAndAnticlockwise() {
        Angle angle = new Angle(270f, true);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), TOLERANCE);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testSetAngle() {
        Angle angle = Angle.ofDegrees(45f);
        angle.set(90f, AngleUnit.DEGREE);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), TOLERANCE);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testSetAngleFromRadians() {
        Angle angle = Angle.ofRadians((float) Math.PI / 4);
        angle.set((float) Math.PI / 2, AngleUnit.RADIAN);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), TOLERANCE);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }

    @Test
    void testSetAngleFromGradians() {
        Angle angle = Angle.ofGradians(50f);
        angle.set(100f, AngleUnit.GRADIAN);
        assertEquals(90f, angle.asDegrees(), TOLERANCE);
        assertEquals((float) Math.PI / 2, angle.asRadians(), TOLERANCE);
        assertEquals(100f, angle.asGradians(), 0.001f);
        assertEquals(0.25f, angle.asNormalized(), TOLERANCE);
    }
}
