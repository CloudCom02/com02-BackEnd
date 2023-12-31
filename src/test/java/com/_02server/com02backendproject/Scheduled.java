package com._02server.com02backendproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.List;

public class Scheduled {
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "src/main/java/com/_02server/com02backendproject/service/chromedriver";
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://prod.danawa.com/list/?cate=20322920&15main_20_03");

        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='prod_info'][contains(text(), '상세 스펙')]"));
        WebElement productNameElement = prodInfo.findElement(By.cssSelector("div.prod_name[name='productName']"));

        String productName = productNameElement.getText();
        System.out.println("상품 이름: "+ productName);

        WebElement prodSpecSet = prodInfo.findElement(By.xpath(".//div[@class='prod_spec_set']/div[@class='screen_out']"));

        if (prodSpecSet.getText().contains("상세 스펙")) {
            List<WebElement> specElements = prodSpecSet.findElements(By.xpath(".//*"));
            for (WebElement element : specElements) {
                if(element.getText().matches(".*시간.*|.*배터리.*|.*용량.*|.*출력.*"))
                    System.out.println(element.getText());
            }
        }
        driver.quit();
    }
}
