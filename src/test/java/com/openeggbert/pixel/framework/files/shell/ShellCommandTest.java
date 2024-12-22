package com.openeggbert.pixel.framework.files.shell;

import com.openeggbert.pixel.framework.files.shell.ShellCommandResult;
import com.openeggbert.pixel.framework.files.shell.ShellCommandLine;
import com.openeggbert.pixel.framework.files.shell.BaseShellCommand;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.openeggbert.pixel.framework.files.shell.ShellCommand;

class ShellCommandTest {

    private ShellCommand mockCommand;
    private ShellCommandLine mockCommandLine;
    private ShellCommandResult mockCommandResult;
    private Function<String, ShellCommandResult> mockFunction;

    @BeforeEach
    void setUp() {
        mockCommandLine = mock(ShellCommandLine.class);
        mockCommandResult = mock(ShellCommandResult.class);
        mockFunction = mock(Function.class);

        // Mock the behavior of execute method
        when(mockFunction.apply(anyString())).thenReturn(mockCommandResult);

        // Create an instance of BaseShellCommand with mocks
        mockCommand = new BaseShellCommand(mockCommandLine, "testCommand", mockFunction);
    }

    @Test
    void testGetName() {
        assertEquals("testCommand", mockCommand.getName());
    }

    @Test
    void testGetShellCommandLine() {
        assertEquals(mockCommandLine, mockCommand.getShellCommandLine());
    }

    @Test
    void testSetShellCommandLine() {
        ShellCommandLine newCommandLine = mock(ShellCommandLine.class);
        mockCommand.setShellCommandLine(newCommandLine);
        assertEquals(newCommandLine, mockCommand.getShellCommandLine());
    }

    @Test
    void testExecute() {
        ShellCommandResult result = mockCommand.execute("testCommand arg1");
        assertEquals(mockCommandResult, result);
        verify(mockFunction).apply("testCommand arg1");
    }

    @Test
    void testEmptyNewResult() {
        ShellCommandResult result = ShellCommand.emptyNewResult();
        assertNotNull(result);
        assertTrue(result instanceof ShellCommandResult);
    }
}
