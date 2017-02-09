package org.balaguta.currconv.e2e.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

abstract class AbstractCurrencyConverterPage {

    WebDriver driver

    AbstractCurrencyConverterPage(WebDriver driver) {
        this.driver = driver
    }

    def <T extends AbstractCurrencyConverterPage> T init(Class<T> pageClass) {
        PageFactory.initElements driver, pageClass
    }

    static createUrl(String relativeUrl) {
        def baseUrl = new URL(System.getProperty("e2e.currconv.url"))
        return new URL(baseUrl, relativeUrl).toString()
    }

    static <T extends AbstractCurrencyConverterPage> T goTo(String url, WebDriver driver, Class<T> pageClass) {
        driver.get createUrl(url)
        PageFactory.initElements driver, pageClass
    }
}
