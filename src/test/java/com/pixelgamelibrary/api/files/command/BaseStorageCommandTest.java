package com.pixelgamelibrary.api.files.command;

import com.pixelgamelibrary.api.files.command.StorageCommandResult;
import com.pixelgamelibrary.api.files.command.StorageCommandLine;
import com.pixelgamelibrary.api.files.command.BaseStorageCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BaseStorageCommandTest {

    private StorageCommandLine mockCommandLine;
    private StorageCommandResult mockCommandResult;
    private Function<String, StorageCommandResult> mockFunction;
    private BaseStorageCommand command;

    @BeforeEach
    void setUp() {
        mockCommandLine = mock(StorageCommandLine.class);
        mockCommandResult = mock(StorageCommandResult.class);
        mockFunction = mock(Function.class);

        // When the function is applied, return the mock result
        when(mockFunction.apply(anyString())).thenReturn(mockCommandResult);

        // Create an instance of BaseStorageCommand with mocks
        command = new BaseStorageCommand(mockCommandLine, "testCommand", mockFunction);
    }

    @Test
    void testGetName() {
        assertEquals("testCommand", command.getName());
    }

    @Test
    void testGetStorageCommandLine() {
        assertEquals(mockCommandLine, command.getStorageCommandLine());
    }

    @Test
    void testExecute() {
        StorageCommandResult result = command.execute("testCommand arg1");
        assertEquals(mockCommandResult, result);
        verify(mockFunction).apply("testCommand arg1");
    }

    @Test
    void testSetStorageCommandLine() {
        StorageCommandLine newCommandLine = mock(StorageCommandLine.class);
        command.setStorageCommandLine(newCommandLine);
        assertEquals(newCommandLine, command.getStorageCommandLine());
    }
}
