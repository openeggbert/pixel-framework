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

package com.pixelgamelibrary.api.files.command;

/**
 * The StorageCommandLineScanner class provides a command-line interface for interacting with
 * the StorageCommandLine instance. It reads user input and executes commands in a loop until
 * the exit command is issued.
 * 
 * @author robertvokac
 */
public class StorageCommandLineScanner {

    /**
     * Constructs a StorageCommandLineScanner instance that continuously reads input from the
     * user and executes commands until the exit command is issued.
     * 
     * @param storageCommandLine the StorageCommandLine instance to interact with
     * @param scanner the Scanner object for reading user input
     */
    public StorageCommandLineScanner(StorageCommandLine storageCommandLine, CommandLineScanner scanner) {

        while (true) {
            // Print the command line prompt
            System.out.print(storageCommandLine.getCommandLineStart());
            // Read user input
            String argument = scanner.nextLine();

            // Execute the command and get the result
            StorageCommandResult result = storageCommandLine.execute(argument);
            // Print error or output based on the result
            if (result.isError()) {
                printError(result.getOutput());
            } else {
                print(result.getOutput());
            }
            // Exit if the command line session is marked as exited
            if (storageCommandLine.isExited()) {
                break;
            }
        }
    }

    /**
     * Prints a message to the standard output.
     * 
     * @param msg the message to print
     */
    private static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints an error message to the standard error output.
     * 
     * @param msg the error message to print
     */
    private static void printError(String msg) {
        System.err.println(msg);
    }

}
