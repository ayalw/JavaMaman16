package maman16.trivianet.triviacommon;

/**
 * This class holds methods for general use.
 */
public class Utils {

    /**
     * This method is for detecting blank lines.
     * Blank lines may contain space, tab, etc.
     * @param
     * @return
     */
    public static boolean isNullOrWhitespace(String s) {
        return s == null || s.equals("") || isWhitespace(s);

    }

    /**
     * Is this a whitespace char - e.g. whitespace, tab.
     * @param s
     * @return
     */
    private static boolean isWhitespace(String s) {
        int length = s.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
