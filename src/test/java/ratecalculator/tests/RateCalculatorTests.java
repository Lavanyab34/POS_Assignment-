package ratecalculator.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ratecalculator.objects.RateCalculator;

public class RateCalculatorTests {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the ChromeDriver and navigate to the rate calculator page
        driver = new ChromeDriver();
        driver.get("https://www.pos.com.my/send/ratecalculator");
        driver.manage().window().maximize();
    }

    @Test
    public void calculateShippingRate() throws InterruptedException {
        // Create an instance of RateCalculator and perform the test steps
        RateCalculator rateCalculator = new RateCalculator(driver);
        
        // Enter the "From" postcode
        rateCalculator.enterFromPostcode("35600");
        
        // Select the "To" country (e.g., India)
        rateCalculator.selectToCountry("India");
        
        // Enter the weight of the shipment
        rateCalculator.enterWeight("1");
        
        // Click the calculate button to get the shipping rate
        rateCalculator.calculateRate();
        
        // Verify that the quotes are displayed
        rateCalculator.verifyQuotes();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test execution
        if (driver != null) {
            driver.quit();
        }
    }
}
