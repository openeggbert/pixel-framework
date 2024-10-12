package com.pixelgamelibrary.api.files.shell;

import com.pixelgamelibrary.api.files.shell.ShellCommandResult;
import com.pixelgamelibrary.api.files.shell.ShellCommandLine;
import com.pixelgamelibrary.api.files.shell.BaseShellCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseShellCommandTest {

    private ShellCommandLine mockCommandLine;
    private ShellCommandResult mockCommandResult;
    private Function<String, ShellCommandResult> mockFunction;
    private BaseShellCommand command;

    @BeforeEach
    void setUp() {
        mockCommandLine = mock(ShellCommandLine.class);
        mockCommandResult = mock(ShellCommandResult.class);
        mockFunction = mock(Function.class);

        // When the function is applied, return the mock result
        when(mockFunction.apply(anyString())).thenReturn(mockCommandResult);

        // Create an instance of BaseShellCommand with mocks
        command = new BaseShellCommand(mockCommandLine, "testCommand", mockFunction);
    }

    @Test
    void testGetName() {
        assertEquals("testCommand", command.getName());
    }

    @Test
    void testGetShellCommandLine() {
        assertEquals(mockCommandLine, command.getShellCommandLine());
    }

    @Test
    void testExecute() {
        ShellCommandResult result = command.execute("testCommand arg1");
        assertEquals(mockCommandResult, result);
        verify(mockFunction).apply("testCommand arg1");
    }

    @Test
    void testSetShellCommandLine() {
        ShellCommandLine newCommandLine = mock(ShellCommandLine.class);
        command.setShellCommandLine(newCommandLine);
        assertEquals(newCommandLine, command.getShellCommandLine());
    }
}
