///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel Game Library.
// Copyright (C) 2024 Your Name or Company.
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
package com.pixelgamelibrary.api;

import com.pixelgamelibrary.api.PixelException;

/** Represents the version of the game library.
 * 
 * Author: Your Name */
public class Version {
    /** The current version of the game library in major.minor.revision format **/
    public static final String VERSION_STRING = "1.0.0";

    /** The current major version **/
    public static final int MAJOR_VERSION;

    /** The current minor version **/
    public static final int MINOR_VERSION;

    /** The current revision version **/
    public static final int REVISION_VERSION;

    static {
        try {
            String[] versionParts = VERSION_STRING.split("\\.");
            MAJOR_VERSION = versionParts.length < 1 ? 0 : Integer.parseInt(versionParts[0]);
            MINOR_VERSION = versionParts.length < 2 ? 0 : Integer.parseInt(versionParts[1]);
            REVISION_VERSION = versionParts.length < 3 ? 0 : Integer.parseInt(versionParts[2]);
        } catch (Exception e) {
            // This should not happen
            throw new PixelException("Invalid version format: " + VERSION_STRING, e);
        }
    }

    public static boolean isVersionHigher(int major, int minor, int revision) {
        return isVersionHigherOrEqual(major, minor, revision + 1);
    }

    public static boolean isVersionHigherOrEqual(int major, int minor, int revision) {
        if (MAJOR_VERSION != major) return MAJOR_VERSION > major;
        if (MINOR_VERSION != minor) return MINOR_VERSION > minor;
        return REVISION_VERSION >= revision;
    }

    public static boolean isVersionLower(int major, int minor, int revision) {
        return isVersionLowerOrEqual(major, minor, revision - 1);
    }

    public static boolean isVersionLowerOrEqual(int major, int minor, int revision) {
        if (MAJOR_VERSION != major) return MAJOR_VERSION < major;
        if (MINOR_VERSION != minor) return MINOR_VERSION < minor;
        return REVISION_VERSION <= revision;
    }
}
