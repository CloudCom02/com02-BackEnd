package com._02server.com02backendproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Scheduled {
    public static void main(String[] args) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        WebDriver driver = new EdgeDriver(options);

        driver.get("https://prod.danawa.com/list/?cate=20322920&15main_20_03");

        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='prod_info'][contains(text(), '상세 스펙')]"));
        WebElement productNameElement = prodInfo.findElement(By.cssSelector("div.prod_name[name='productName']"));
    }
}
