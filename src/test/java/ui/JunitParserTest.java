package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JunitParserTest {
    //@@author adamzzq
    @Test
    void testAnalyzeInput_validInput() {
        Parser parser = new Parser();
        assertEquals(CommandType.CREATE_ORDER, parser.analyzeInput("create order -menu 1"));
        assertEquals(CommandType.VIEW_ORDER, parser.analyzeInput("view -order 2"));
        assertEquals(CommandType.EDIT_ORDER, parser.analyzeInput("edit -order 3"));
        assertEquals(CommandType.EXIT, parser.analyzeInput("bye"));
        assertEquals(CommandType.HELP, parser.analyzeInput("help"));

        // case-insensitive
        assertEquals(CommandType.CREATE_ORDER, parser.analyzeInput("CrEaTe OrDeR -mEnU 99"));
        assertEquals(CommandType.VIEW_ORDER, parser.analyzeInput("ViEw -OrDeR 2"));
    }

    //@@author adamzzq
    @Test
    void testAnalyzeInput_invalidInput() {
        Parser parser = new Parser();

        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("asfdhih 123"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("create order -menu 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("view -order 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("edit -order 1 2 3"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("create order -menu"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("view -order"));
        assertThrows(IllegalArgumentException.class, () -> parser.analyzeInput("edit -order"));
    }

    //@@author adamzzq
    @Test
    void splitInputTest() {
        Parser parser = new Parser();
        assertArrayEquals(new String[]{"7"}, parser.splitInput(CommandType.CREATE_ORDER, "create order -menu 7"));
        assertArrayEquals(new String[]{"6"}, parser.splitInput(CommandType.VIEW_ORDER, "view -order 6"));
        assertArrayEquals(new String[]{"5"}, parser.splitInput(CommandType.EDIT_ORDER, "edit -order 5"));
    }

    //@@author adamzzq
    @Test
    void testSplitInput_invalidInput() {
        Parser parser = new Parser();
        assertThrows(AssertionError.class,
                () -> parser.splitInput(CommandType.CREATE_ORDER, "create -Order -menu"));
        assertThrows(AssertionError.class, () -> parser.splitInput(CommandType.VIEW_ORDER, "view -order"));
        assertThrows(AssertionError.class, () -> parser.splitInput(CommandType.EDIT_ORDER, "edit -order"));
    }
}
