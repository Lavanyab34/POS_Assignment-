package ratecalculator.objects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

public class RateCalculator {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor to initialize WebDriver and WebDriverWait
    public RateCalculator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Reduced wait time for efficiency
    }

    // Locators
    private By fromPostcodeTxt = By.xpath("//input[@formControlName='postcodeFrom']");
    private By countryDropdown = By.xpath("//input[@name='country']");
    private By optionIndia = By.xpath("//div[@id='cdk-overlay-4']//span[text()='India']");
    private By weightTxt = By.xpath("//input[@formControlName='itemWeight']");
    private By calculateBtn = By.xpath("//button[contains(text(),'Calculate')]");
    private By quoteDetails = By.xpath("//*[contains(@class, 'border-gray-300')]");

    // Methods

    // Enter the 'From' postcode
    public void enterFromPostcode(String postcode) {
        WebElement fromPostcode = wait.until(ExpectedConditions.visibilityOfElementLocated(fromPostcodeTxt));
        fromPostcode.clear();  // Clear any pre-existing input
        fromPostcode.sendKeys(postcode);
    }

    // Select 'To' country (refactored for flexibility)
    public void selectToCountry(String countryName) {
        WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(countryDropdown));
        countryInput.click();
        countryInput.clear();
        countryInput.sendKeys(countryName);
        
        // Dynamic locator for any country option based on provided countryName
        By countryOption = By.xpath("//div[contains(@id,'cdk-overlay')]//span[text()='" + countryName + "']");
        WebElement countryOptionElement = wait.until(ExpectedConditions.elementToBeClickable(countryOption));
        countryOptionElement.click();
    }

    // Enter the item weight
    public void enterWeight(String weight) {
        WebElement inputWeight = wait.until(ExpectedConditions.visibilityOfElementLocated(weightTxt));
        inputWeight.clear();  // Clear any pre-existing input
        inputWeight.sendKeys(weight);
    }

    // Click the 'Calculate' button
    public void calculateRate() {
        WebElement calculate = wait.until(ExpectedConditions.elementToBeClickable(calculateBtn));
        calculate.click();
    }

    // Verify that shipment quotes are populated
    public void verifyQuotes() {
        List<WebElement> quotes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(quoteDetails));
        Assert.assertFalse(quotes.isEmpty(), "Shipment quotes are not populated.");
    }
}
