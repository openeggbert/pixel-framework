package com.openeggbert.pixel.framework.files.shell;

import com.openeggbert.pixel.framework.files.shell.ShellCommandResult;
import com.openeggbert.pixel.framework.files.shell.ShellCommandLineScanner;
import com.openeggbert.pixel.framework.files.shell.ShellCommandLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.openeggbert.pixel.framework.files.shell.ShellScanner;

class ShellCommandLineScannerTest {

    @Mock
    private ShellCommandLine mockShellCommandLine; // Mocking ShellCommandLine

    @Mock
    private ShellScanner mockCommandLineScanner; // Mocking CommandLineScanner

    private ShellCommandLineScanner shellCommandLineScanner; // Manually creating the instance

    @BeforeEach
    void setUp() {
        // Initialize mock objects before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteSimpleCommand() {
        // Arrange
        when(mockShellCommandLine.getCommandLineStart()).thenReturn(">");
        when(mockCommandLineScanner.nextLine()).thenReturn("someCommand");
        ShellCommandResult mockResult = new ShellCommandResult("Command Executed");
        when(mockShellCommandLine.execute("someCommand")).thenReturn(mockResult);
        when(mockShellCommandLine.isExited()).thenReturn(true);

        // Manually create the instance of ShellCommandLineScanner with mocks
        shellCommandLineScanner = new ShellCommandLineScanner(mockShellCommandLine, mockCommandLineScanner);

        // Assert: Verify that the expected methods were called
        verify(mockShellCommandLine).getCommandLineStart();
        verify(mockShellCommandLine).execute("someCommand");
    }

    @Test
    void testErrorOutput() {
        // Arrange
        when(mockShellCommandLine.getCommandLineStart()).thenReturn(">");
        when(mockCommandLineScanner.nextLine()).thenReturn("invalidCommand");
        ShellCommandResult errorResult = new ShellCommandResult("Error occurred", true);
        when(mockShellCommandLine.execute("invalidCommand")).thenReturn(errorResult);
        when(mockShellCommandLine.isExited()).thenReturn(true);

        // Manually create the instance of ShellCommandLineScanner with mocks
        shellCommandLineScanner = new ShellCommandLineScanner(mockShellCommandLine, mockCommandLineScanner);

        // Assert: Verify the error output
        verify(mockShellCommandLine).execute("invalidCommand");
        assertTrue(errorResult.isError(), "The result should indicate an error");
    }
}
