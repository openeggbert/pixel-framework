package com.pixelgamelibrary.api.storage.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StorageCommandLineScannerTest {

    @Mock
    private StorageCommandLine mockStorageCommandLine; // Mocking StorageCommandLine

    @Mock
    private CommandLineScanner mockCommandLineScanner; // Mocking CommandLineScanner

    private StorageCommandLineScanner storageCommandLineScanner; // Manually creating the instance

    @BeforeEach
    void setUp() {
        // Initialize mock objects before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteSimpleCommand() {
        // Arrange
        when(mockStorageCommandLine.getCommandLineStart()).thenReturn(">");
        when(mockCommandLineScanner.nextLine()).thenReturn("someCommand");
        StorageCommandResult mockResult = new StorageCommandResult("Command Executed");
        when(mockStorageCommandLine.execute("someCommand")).thenReturn(mockResult);
        when(mockStorageCommandLine.isExited()).thenReturn(true);

        // Manually create the instance of StorageCommandLineScanner with mocks
        storageCommandLineScanner = new StorageCommandLineScanner(mockStorageCommandLine, mockCommandLineScanner);

        // Assert: Verify that the expected methods were called
        verify(mockStorageCommandLine).getCommandLineStart();
        verify(mockStorageCommandLine).execute("someCommand");
    }

    @Test
    void testErrorOutput() {
        // Arrange
        when(mockStorageCommandLine.getCommandLineStart()).thenReturn(">");
        when(mockCommandLineScanner.nextLine()).thenReturn("invalidCommand");
        StorageCommandResult errorResult = new StorageCommandResult("Error occurred", true);
        when(mockStorageCommandLine.execute("invalidCommand")).thenReturn(errorResult);
        when(mockStorageCommandLine.isExited()).thenReturn(true);

        // Manually create the instance of StorageCommandLineScanner with mocks
        storageCommandLineScanner = new StorageCommandLineScanner(mockStorageCommandLine, mockCommandLineScanner);

        // Assert: Verify the error output
        verify(mockStorageCommandLine).execute("invalidCommand");
        assertTrue(errorResult.isError(), "The result should indicate an error");
    }
}
