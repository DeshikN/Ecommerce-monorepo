
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartServiceTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/cart");
    }

    @Test(description = "5029 - Support percentage and fixed amount coupons with eligibility rules and stacking")
    public void verifyCouponStacking() {
        driver.findElement(By.id("coupon")).sendKeys("PERCENT10");
        driver.findElement(By.id("applyCoupon")).click();

        driver.findElement(By.id("coupon")).sendKeys("FLAT50");
        driver.findElement(By.id("applyCoupon")).click();

        String discount = driver.findElement(By.id("discountTotal")).getText();
        Assert.assertTrue(discount.contains("-"));
    }

    @Test(description = "5064 - Apply coupon before tax calculation")
    public void verifyCouponAppliedBeforeTax() {
        String taxBefore = driver.findElement(By.id("taxBefore")).getText();
        String taxAfter = driver.findElement(By.id("taxAfter")).getText();

        Assert.assertNotEquals(taxBefore, taxAfter);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
