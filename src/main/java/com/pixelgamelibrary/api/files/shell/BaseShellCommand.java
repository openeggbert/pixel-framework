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

import java.util.function.Function;

/**
 * The {@code BaseShellCommand} class provides a basic implementation of the {@link ShellCommand} interface.
 * It defines a command that can be executed within a file system command-line context using a function
 * that processes the command and its arguments.
 * 
 * @author robertvokac
 */
public class BaseShellCommand implements ShellCommand {

    /**
     * The command-line interface this command is associated with.
     */
    private ShellCommandLine shellCommandLine = null;

    /**
     * The name of the command.
     */
    private String name;

    /**
     * The function that will be applied to execute the command with its arguments.
     */
    private final Function<String, ShellCommandResult> function;

    /**
     * Constructs a new {@code BaseShellCommand} with the specified command-line interface, name, and execution function.
     * 
     * @param shellCommandLineIn the command-line interface associated with this command.
     * @param nameIn the name of the command.
     * @param functionIn the function that defines the command's behavior when executed.
     */
    public BaseShellCommand(
            ShellCommandLine shellCommandLineIn, String nameIn, Function<String, ShellCommandResult> functionIn
    ) {
        setShellCommandLine(shellCommandLineIn);
        this.name = nameIn;
        this.function = functionIn;
    }

    /**
     * Sets the {@link ShellCommandLine} for this command.
     * 
     * @param shellCommandLineIn the command-line interface to set.
     */
    @Override
    public final void setShellCommandLine(ShellCommandLine shellCommandLineIn) {
        shellCommandLine = shellCommandLineIn;
    }

    /**
     * Returns the {@link ShellCommandLine} associated with this command.
     * 
     * @return the command-line interface.
     */
    @Override
    public final ShellCommandLine getShellCommandLine() {
        return shellCommandLine;
    }

    /**
     * Returns the name of this command.
     * 
     * @return the command name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Executes the command with the provided arguments.
     * 
     * @param commandWithArguments the command string including its arguments.
     * @return the result of executing the command.
     */
    @Override
    public ShellCommandResult execute(String commandWithArguments) {
        return function.apply(commandWithArguments);
    }

}
