import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification
import static java.util.concurrent.TimeUnit.SECONDS

class CurrencyConversionSpec extends Specification {

    WebDriver driver
    String baseUrl

    def setup() {
        driver = new ChromeDriver()
        driver.manage().timeouts().implicitlyWait 10, SECONDS
        baseUrl = System.getProperty("e2e.currconv.url")
        println baseUrl
    }

    def cleanup() {
        driver.quit()
    }

    def "as a user I can convert currencies for current date"() {
        when:
        driver.get baseUrl + "/login"

        then:
        driver.title == "Currency Converter"
    }

}
