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
package com.openeggbert.pixel.framework.files.shell;

/**
 * The {@code ShellCommand} interface defines the contract for commands that can be executed within
 * a file system command-line environment. It provides methods for getting the command's name, executing
 * the command with arguments, and managing the command-line context.
 * 
 * @author robertvokac
 */
public interface ShellCommand {

    /**
     * Returns the name of the command.
     * 
     * @return the name of the command as a {@code String}.
     */
    public String getName();

    /**
     * Executes the command with the specified arguments and returns the result.
     * 
     * @param arguments the arguments to be passed to the command.
     * @return the result of executing the command as a {@link ShellCommandResult}.
     */
    ShellCommandResult execute(String arguments);

    /**
     * Returns the {@link ShellCommandLine} associated with this command.
     * 
     * @return the command-line interface associated with this command.
     */
    ShellCommandLine getShellCommandLine();

    /**
     * Sets the {@link ShellCommandLine} for this command.
     * 
     * @param shellCommandLine the command-line interface to set.
     */
    void setShellCommandLine(ShellCommandLine shellCommandLine);

    /**
     * Creates and returns a new, empty {@link ShellCommandResult}.
     * 
     * @return a new {@link ShellCommandResult} instance.
     */
    static ShellCommandResult emptyNewResult() {
        return new ShellCommandResult();
    }
}
