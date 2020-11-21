import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlik() {
        assertTrue(Flik.isSameNumber(1, 1));
        assertTrue(Flik.isSameNumber(200, 200));
//        Assert.assertTrue(Flik.isSameNumber(300, 200));
//        Assert.assertTrue(Flik.isSameNumber(400, 400));
//        Assert.assertTrue(Flik.isSameNumber(500, 500));
    }
}
