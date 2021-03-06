public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }

    private boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        boolean result = true;
        for (int i = 0; i < word.length() / 2; i++) {
//            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
            if (!equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                result = false;
            }
        }
        return result;
    }
}
