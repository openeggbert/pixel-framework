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

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.openeggbert.pixel.framework.files.FileSystem;

/**
 * The ShellCommandLine class represents a command-line interface for interacting with file system.
 * It provides methods to execute various commands that manipulate or retrieve information from the file system.
 * 
 * @author robertvokac
 */
public class ShellCommandLine {

    private String user; // User for the command line
    private String hostname; // Hostname for the command line
    private FileSystem fs; // File system object for interacting with files
    private boolean exited = false; // Indicates if the command line session has been exited
    long startNanoTime = System.nanoTime(); // Start time of the session in nanoseconds

    /**
     * Returns the command line prompt string.
     * 
     * @return the command line prompt string
     */
    public String getCommandLineStart() {
        return user + "@" + hostname + ":" + fs.printWorkingDirectory() + "$ ";
    }

    /**
     * Extracts an argument from the command line arguments based on its index.
     * 
     * @param arguments the command line arguments
     * @param argumentIndex the index of the argument to extract
     * @return the extracted argument
     */
    String extractArgument(String arguments, int argumentIndex) {
        if(arguments.isEmpty()) {
            return arguments;
        }
        String[] array = arguments.split(" ");
        if (argumentIndex >= array.length) {
            return "";
        }
        return array[argumentIndex + 1];
    }

    /**
     * Constructs a ShellCommandLine instance with the specified user, hostname, and file system.
     * Initializes commands for the command line interface.
     * 
     * @param userIn the user for the command line
     * @param hostnameIn the hostname for the command line
     * @param fsIn the file system object for interacting with files
     */
    public ShellCommandLine(String userIn, String hostnameIn, FileSystem fsIn) {
        this.user = userIn;
        this.hostname = hostnameIn;
        this.fs = fsIn;

        // Initialize commands
        addCommand("date", arguments -> provideOutput(result -> result.setOutput(new Date().toString())));
        addCommand("whoami", arguments -> provideOutput(result -> result.setOutput(user)));
        addCommand("uptime", arguments -> provideOutput(result
                -> result.setOutput(
                        new Date().toString().substring(11, 19) + " up "
                        + (System.nanoTime() - startNanoTime) / 1000000000L / 60L
                        + " minutes"
                        + ", 1 user"
                )));

        addCommand("hostname", arguments -> provideOutput(result -> result.setOutput(hostname)));
        addCommand("test", arguments-> provideOutput(result-> result.setOutput((extractArgument(arguments, 0)))));
        addCommand("uname", arguments -> provideOutput(result -> result.setOutput(
                "LinuxBashCommandLinePartialEmulation "
                + ((extractArgument(arguments, 0).equals("-a"))
                ? (hostname + " 0.0.0 ("
                + new Date().toString() + ")")
                : "")
        )));

        addCommand("ls", arguments -> provideOutput(result -> result.setOutput(fsIn
                .list()
                .stream()
                .map(l -> {
                    String[] a = l.split("/");
                    return a[a.length - 1];
                })
                .collect(Collectors.joining("\n")))));

        addCommand("pwd", arguments -> provideOutput(result -> result.setOutput(fsIn.printWorkingDirectory())));
        addCommand("depth", arguments -> provideOutput(result -> result.setOutput(fsIn.depth())));

        addCommand("mkdir", arguments -> provideOutput(result
                -> {
            String string = fsIn.createDirectories(extractArguments(arguments));
            if (string.isEmpty()) {
                result.setOutput("New directory was successfully created");
            } else {
                result.setErrorOutput("Creating new directory failed: " + string);
            }
        }));

        // Set the ShellCommandLine instance for each command
        commands.keySet().stream().map(k -> commands.get(k)).forEach(c -> c.setShellCommandLine(this));
    }

    /**
     * Extracts arguments from the command line arguments string.
     * 
     * @param arguments the command line arguments
     * @return an array of extracted arguments
     */
    private String[] extractArguments(String arguments) {
        return Arrays.asList(arguments.split(" ")).stream()
                .filter(a->!a.isEmpty())
                .toArray(String[]::new);
    }

    /**
     * Provides output based on a consumer function that modifies the result.
     * 
     * @param consumer the function to modify the result
     * @return the modified result
     */
    private ShellCommandResult provideOutput(Consumer<ShellCommandResult> consumer) {
        ShellCommandResult result = ShellCommand.emptyNewResult();
        consumer.accept(result);
        return result;
    }

    /**
     * Adds a command to the command line interface.
     * 
     * @param nameIn the name of the command
     * @param functionIn the function to execute for the command
     */
    private void addCommand(String nameIn, Function<String, ShellCommandResult> functionIn) {
        ShellCommand shellCommand = new BaseShellCommand(this, nameIn, functionIn);
        commands.put(shellCommand.getName(), shellCommand);
    }

    private final Map<String, ShellCommand> commands = new HashMap<>();

    public String getUser() {
        return user;
    }

    public String getHostname() {
        return hostname;
    }

    public FileSystem getFileSystem() {
        return fs;
    }

    public boolean isExited() {
        return exited;
    }

    /**
     * Executes a command with the specified arguments.
     * 
     * @param commandWithArguments the command and its arguments
     * @return the result of the command execution
     */
    public ShellCommandResult execute(String commandWithArguments) {
        String[] arguments = commandWithArguments.split(" ");
        String command = arguments.length == 0 ? "" : arguments[0];

        ShellCommand shellCommand = commands.get(command);
        if (shellCommand != null) {
            return shellCommand.execute(commandWithArguments.substring(command.length()));
        }

        int argumentCount = arguments.length - 1;
        Optional<String> argument1 = Optional.ofNullable(argumentCount >= 1 ? arguments[1] : null);
        Optional<String> argument2 = Optional.ofNullable(argumentCount >= 2 ? arguments[2] : null);

        ShellCommandResult finalResult = new ShellCommandResult();
        switch (command) {
            case "touch":
                String r = fs.touch(argument1.get());
                if (r.isEmpty()) {
                    finalResult.setOutput("New file was successfully created");
                } else {
                    finalResult.setErrorOutput("Creating new directory failed: " + r);
                }
                break;
            case "readtext":
                String rr = fs.readString(argument1.get());
                if (rr != null) {
                    finalResult.setOutput("Text file was successfully loaded" + "\n\n" + rr);
                } else {
                    finalResult.setErrorOutput("Loading text file failed:");
                }
                break;
            case "savetext":
                String result = fs.writeString(argument1.get(), argument2.get());
                if (result.isEmpty()) {
                    finalResult.setOutput("Text file was successfully saved");
                } else {
                    finalResult.setErrorOutput("Saving text file failed: " + result);
                }
                break;
            case "cd":
                String rrr = argument1.isEmpty() ? fs.changeDirectory() : fs.changeDirectory(argument1.get());
                if (rrr.isEmpty()) {
                    finalResult.setOutput("Changing working directory was successfully created");
                } else {
                    finalResult.setErrorOutput("Changing working directory failed: " + rrr);
                }
                break;
            case "debug":
                finalResult.setOutput(fs.debug());
                break;
            case "exit":
                exited = true;
                finalResult.setOutput("Exited");
                break;
            default:
                finalResult.setErrorOutput("Unsupported command: " + command);
        }
        return finalResult;
    }
}
