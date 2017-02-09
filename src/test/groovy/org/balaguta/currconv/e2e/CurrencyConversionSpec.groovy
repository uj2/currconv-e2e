package org.balaguta.currconv.e2e

import org.balaguta.currconv.e2e.page.LoginPage
import org.balaguta.currconv.e2e.page.UnauthenticatedIndexPage
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification

import java.time.LocalDate
import java.time.Month

import static java.util.concurrent.TimeUnit.SECONDS

class CurrencyConversionSpec extends Specification {

    WebDriver driver
    String baseUrl

    def setup() {
        driver = new ChromeDriver()
        driver.manage().timeouts().implicitlyWait 10, SECONDS
    }

    def cleanup() {
        driver.quit()
    }

    def "as an unauthenticated user I see a welcome message on main screen"() {
        when: "index page is opened"
        def page = UnauthenticatedIndexPage.open(driver)

        then: "it contains a welcome message"
        page.body.text.contains 'Welcome! Please log in to start converting currencies.'
    }

    def "as a user I can log in and see main screen"() {
        when: "login page is opened"
        def page = LoginPage.open(driver)

        and: "valid credentials are entered"
        page = page.login("joe@example.com", "joe123")

        then: "proper currency conversion screen shows up"
        page.body.text.contains "Welcome, Joe Simple!"
        exists page.amount
        exists page.fromCurrency
        exists page.toCurrency
        exists page.ratesFrom
        exists page.logoutForm
    }

    def "as a user I can convert currency and see the result"() {
        when: "login page is opened"
        def page = LoginPage.open(driver)

        and: "valid credentials are entered"
        page = page.login("joe@example.com", "joe123")

        and: "conversion is invoked"
        page = page.convert(123.4, "PLN", "EUR", LocalDate.of(2016, Month.FEBRUARY, 9))


        then: "conversion results are displayed"
        assert page.jumbotron.text == "123.40 PLN 27.71 EUR"
    }

    def exists(WebElement element) {
        try {
            element.displayed
        } catch (NoSuchElementException e) {
            throw new AssertionError("$element does not exist")
        }
    }

}
