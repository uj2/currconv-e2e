# currconv-e2e
Acceptance tests for currconv

To execute:

1. Ensure you have Chrome installed
1. Download [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) and unpack somewhere
1. Start currconv:

   ````console
currconv$ ./gradlew bootRun
````
1. In another terminal run:

   ````console
./gradlew  -De2e.currconv.url=<<CURRCONV_URL>> -Dwebdriver.chrome.driver=<<PATH_TO_CHROMEDRIVER>> test
````
