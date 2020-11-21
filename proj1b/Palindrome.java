public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(word.length() - 1 - i);
            deque.addFirst(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        boolean result = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        boolean result = true;
        for (int i = 0; i < word.length() / 2; i++) {
//            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                result = false;
                break;
            }
        }
        return result;
    }
}
