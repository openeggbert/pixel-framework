package com.pixelgamelibrary.api.storage.command;

import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageCommandTest {

    private StorageCommand mockCommand;
    private StorageCommandLine mockCommandLine;
    private StorageCommandResult mockCommandResult;
    private Function<String, StorageCommandResult> mockFunction;

    @BeforeEach
    void setUp() {
        mockCommandLine = mock(StorageCommandLine.class);
        mockCommandResult = mock(StorageCommandResult.class);
        mockFunction = mock(Function.class);

        // Mock the behavior of execute method
        when(mockFunction.apply(anyString())).thenReturn(mockCommandResult);

        // Create an instance of BaseStorageCommand with mocks
        mockCommand = new BaseStorageCommand(mockCommandLine, "testCommand", mockFunction);
    }

    @Test
    void testGetName() {
        assertEquals("testCommand", mockCommand.getName());
    }

    @Test
    void testGetStorageCommandLine() {
        assertEquals(mockCommandLine, mockCommand.getStorageCommandLine());
    }

    @Test
    void testSetStorageCommandLine() {
        StorageCommandLine newCommandLine = mock(StorageCommandLine.class);
        mockCommand.setStorageCommandLine(newCommandLine);
        assertEquals(newCommandLine, mockCommand.getStorageCommandLine());
    }

    @Test
    void testExecute() {
        StorageCommandResult result = mockCommand.execute("testCommand arg1");
        assertEquals(mockCommandResult, result);
        verify(mockFunction).apply("testCommand arg1");
    }

    @Test
    void testEmptyNewResult() {
        StorageCommandResult result = StorageCommand.emptyNewResult();
        assertNotNull(result);
        assertTrue(result instanceof StorageCommandResult);
    }
}
