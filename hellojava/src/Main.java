import org.testng.Assert;
import org.testng.annotations.Test;

public class Main {

    @Test
    public void aTest(){
        int theAnswer = 42;

        Assert.assertEquals(theAnswer, 42);
    }

    @Test
    public void anotherTest(){
        String s = "some string";

        Assert.assertTrue(s.contains("not"));
    }
}
