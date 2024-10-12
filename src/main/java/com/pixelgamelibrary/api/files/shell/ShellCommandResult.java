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

package com.pixelgamelibrary.api.files.shell;

/**
 * The ShellCommandResult class encapsulates the result of executing a file system command.
 * It holds the output of the command and a flag indicating whether an error occurred.
 * 
 * @author robertvokac
 */
public class ShellCommandResult {

    /**
     * Default constructor that initializes an empty result.
     */
    public ShellCommandResult() {
        this("");
    }

    /**
     * Constructor that initializes the result with the specified output.
     * 
     * @param output the output of the command
     */
    public ShellCommandResult(String output) {
        this(output, false);
    }

    /**
     * Constructor that initializes the result with the specified output and error flag.
     * 
     * @param output the output of the command
     * @param error true if an error occurred, false otherwise
     */
    public ShellCommandResult(String output, boolean error) {
        this.output = output;
        this.error = error;
    }

    /**
     * Gets the output of the command.
     * 
     * @return the output as a string
     */
    public String getOutput() {
        return output;
    }

    /**
     * Checks if the command result indicates an error.
     * 
     * @return true if an error occurred, false otherwise
     */
    public boolean isError() {
        return error;
    }

    /**
     * Sets the output of the command, given an integer value.
     * 
     * @param output the integer output to set
     */
    public void setOutput(int output) {
        setOutput(String.valueOf(output));
    }

    /**
     * Sets the error output and marks the result as an error.
     * 
     * @param output the error message to set
     */
    public void setErrorOutput(String output) {
        this.output = output;
        setError(true);
    }

    /**
     * Sets the output of the command.
     * 
     * @param output the output to set as a string
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * Sets the error flag.
     * 
     * @param error true to indicate an error, false otherwise
     */
    public void setError(boolean error) {
        this.error = error;
    }

    private String output; // Holds the output of the command
    private boolean error; // Indicates whether an error occurred
}
