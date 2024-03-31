package ui;

import java.util.logging.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Parser {
    private static final Logger logger = Logger.getLogger(Parser.class.getName());
    private static final String ANALYZE_INPUT = "analyzeInput";
    private static final String SPLIT_INPUT = "splitInput";

    /**
     * Analyzes the given input and returns the corresponding token.
     *
     * @param input The input to be analyzed.
     * @return The corresponding token.
     * @throws IllegalArgumentException If the input is invalid.
     */
    public static CommandType analyzeInput(String input) throws IllegalArgumentException {
        logger.entering(Parser.class.getName(), ANALYZE_INPUT, input);

        return Arrays.stream(CommandType.values())
                .filter(token -> input.matches(token.getCommandRegex()))
                .findFirst()
                .orElseThrow(() -> {
                    logger.throwing(Parser.class.getName(),
                            ANALYZE_INPUT, new IllegalArgumentException("Invalid input"));
                    return new IllegalArgumentException("Invalid input");
                });
    }

    /**
     * Splits the given input based on the given token.
     *
     * @param token The token to be used for splitting the input.
     * @param input The input to be split.
     * @return The split input containing the arguments required for the command.
     */
    public static String[] splitInput(CommandType token, String input) {
        logger.entering(Parser.class.getName(),
                SPLIT_INPUT, new Object[]{token, input});

        assert token != null : "Token cannot be null";  // Ensures command type token is not null

        Pattern matchedPattern = Pattern.compile(token.getCommandRegex());
        Matcher matcher = matchedPattern.matcher(input);
        boolean hasMatched = matcher.matches();
        assert hasMatched : "Input does not match the token";  // Ensures input matches the token

        return IntStream.rangeClosed(1, matcher.groupCount())
                .mapToObj(matcher::group)
                .filter(s -> s != null && !s.isEmpty())
                .map(String::trim)
                .peek(s -> logger.fine("Split input: " + s))
                .toArray(String[]::new);
    }
}
