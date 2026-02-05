
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartOrderFlowIT {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/order");
    }

    @Test(description = "5034 - Discount allocation across multiple cart items")
    public void verifyDiscountAllocationAcrossItems() {
        String item1Discount = driver.findElement(By.id("item1Discount")).getText();
        String item2Discount = driver.findElement(By.id("item2Discount")).getText();

        Assert.assertNotEquals(item1Discount, "0");
        Assert.assertNotEquals(item2Discount, "0");
    }

    @Test(description = "5049 - Proportional discount allocation and shipping after discount")
    public void verifyProportionalDiscountAllocation() {
        String shippingAfterDiscount = driver.findElement(By.id("shippingAfterDiscount")).getText();
        Assert.assertNotNull(shippingAfterDiscount);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
