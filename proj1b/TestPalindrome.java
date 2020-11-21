import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome("racecar"));

        OffByN offBy5 = new OffByN(5);
        assertFalse(palindrome.isPalindrome("cat", offBy5));
        assertFalse(palindrome.isPalindrome("racecar", offBy5));
        assertTrue(palindrome.isPalindrome("af", offBy5));

        OffByN offBy2 = new OffByN(2);
        assertFalse(palindrome.isPalindrome("cat", offBy2));
        assertFalse(palindrome.isPalindrome("racecar", offBy2));
        assertTrue(palindrome.isPalindrome("ac", offBy2));
    }
}
