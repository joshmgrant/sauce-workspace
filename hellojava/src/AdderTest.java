
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class AdderTest {

    @DataProvider
    public Object[][] adderData() {
        return new Object[][]{
                {1, 1, 2},
                {0, 0 ,0},
                {-1, 2, 1}
        };
    }

    @Test
    public void tetSumNoDataProvider(){
        // adder object takes two integers in its constructor and
        // has a sum method that returns their sum
        Adder adder = new Adder(1 , 2);

        Assert.assertEquals(adder.sum(), 3);
    }

    @Test(dataProvider = "adderData")
    public void testSum(int testA, int testB, int expected) {
        // use a dataprovider for "test data" cases
        Adder adder = new Adder(testA,testB);

        Assert.assertEquals(adder.sum(), expected);
    }

}
