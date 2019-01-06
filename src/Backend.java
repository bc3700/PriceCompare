import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Backend
{
    public static void main(String[] args) {
        double price = getProductPrice("https://www.amazon.com/Crackdown-3-Standard-Xbox-One/dp/B00KVLZ00I/ref=sr_1_1?ie=UTF8&qid=1546790812&sr=8-1&keywords=crackdown%2B3&th=1");
        System.out.println(price);
    }
    public static double getProductPrice(String url) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Brandon Cooper/Python Libraries/ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        sleep(3);

        String dollarPrice = "";
        try
        {
            WebElement dollarPriceElement = driver.findElement(By.className("price-large"));
            dollarPrice = dollarPriceElement.getText();
        }
        catch (NoSuchElementException e) {
            try {
                WebElement dollarPriceElement = driver.findElement(By.className("buyingPrice"));
                dollarPrice = dollarPriceElement.getText();
            }
            catch (NoSuchElementException f) {
                System.out.println("error");
            }
        }

        List<WebElement> superscriptElements = driver.findElements(By.xpath("//*[@class='a-size-small price-info-superscript']"));
        String centPrice = superscriptElements.get(1).getText();

        String priceCombined = dollarPrice + "." + centPrice;


        driver.close();
        return Double.parseDouble(priceCombined);
    }

    public static void sleep(int seconds)
    {
        try
        {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
