package com.openeggbert.pixel.framework.files.shell;

import com.openeggbert.pixel.framework.files.shell.ShellCommandResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShellCommandResultTest {

    @Test
    void testDefaultConstructor() {
        // Test default constructor which initializes an empty result
        ShellCommandResult result = new ShellCommandResult();

        assertNotNull(result.getOutput(), "Output should not be null");
        assertEquals("", result.getOutput(), "Default output should be empty");
        assertFalse(result.isError(), "Default result should not indicate an error");
    }

    @Test
    void testConstructorWithOutput() {
        // Test constructor that initializes with an output string
        String expectedOutput = "Test Output";
        ShellCommandResult result = new ShellCommandResult(expectedOutput);

        assertEquals(expectedOutput, result.getOutput(), "Output should match the provided string");
        assertFalse(result.isError(), "Result should not indicate an error by default");
    }

    @Test
    void testConstructorWithOutputAndErrorFlag() {
        // Test constructor that initializes with output and error flag
        String expectedOutput = "Error Output";
        ShellCommandResult result = new ShellCommandResult(expectedOutput, true);

        assertEquals(expectedOutput, result.getOutput(), "Output should match the provided string");
        assertTrue(result.isError(), "Error flag should be set to true");
    }

    @Test
    void testSetOutputString() {
        // Test setting the output using a string
        ShellCommandResult result = new ShellCommandResult();
        String expectedOutput = "New Output";
        result.setOutput(expectedOutput);

        assertEquals(expectedOutput, result.getOutput(), "Output should be updated correctly");
    }

    @Test
    void testSetOutputInt() {
        // Test setting the output using an integer
        ShellCommandResult result = new ShellCommandResult();
        int expectedOutput = 12345;
        result.setOutput(expectedOutput);

        assertEquals(String.valueOf(expectedOutput), result.getOutput(), "Output should match the integer converted to string");
    }

    @Test
    void testSetErrorOutput() {
        // Test setting an error output and marking the result as an error
        ShellCommandResult result = new ShellCommandResult();
        String errorOutput = "Error occurred";
        result.setErrorOutput(errorOutput);

        assertEquals(errorOutput, result.getOutput(), "Error output should be set correctly");
        assertTrue(result.isError(), "Error flag should be set to true after setting error output");
    }

    @Test
    void testSetErrorFlag() {
        // Test setting the error flag directly
        ShellCommandResult result = new ShellCommandResult();
        result.setError(true);

        assertTrue(result.isError(), "Error flag should be set to true");

        result.setError(false);
        assertFalse(result.isError(), "Error flag should be set to false");
    }
}
