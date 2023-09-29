package com._02server;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Croller {
    public static void main(String[] args) {
        // CSV 파일 경로
        String csvFilePath = "output.csv";

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://prod.danawa.com/list/?cate=20322920&15main_20_03");

        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='main_prodlist main_prodlist_list']"));
        for (WebElement e : prodInfo.findElements(By.cssSelector("div.prod_main_info"))) {
            String name = e.findElement(By.cssSelector("p.prod_name a[name='productName']")).getText();

            String contents = e.findElement(By.cssSelector("div.spec_list")).getText();
            contents = contents.replaceAll("\\n","");

            saveDataToCSV(name,contents,csvFilePath);
            System.out.println("상품 이름: " + name + "\n  " + contents);
            System.out.println();
        }


//        WebElement prodSpecSet = prodInfo.findElement(By.xpath(".//div[@class='prod_spec_set']/div[@class='screen_out']"));
//
//        if (prodSpecSet.getText().contains("상세 스펙")) {
//            List<WebElement> specElements = prodSpecSet.findElements(By.xpath(".//*"));
//            for (WebElement element : specElements) {
//                if(element.getText().matches(".*시간.*|.*배터리.*|.*용량.*|.*출력.*"))
//                    System.out.println(element.getText());
//            }
//        }
        driver.quit();
    }

    private static void saveDataToCSV(String name, String contents, String filePath) {
        try (Writer writer = new FileWriter(filePath, true)) {
            List<String[]> data = new ArrayList<>();


            // 데이터 행 추가
            String[] row = {name, contents};
            data.add(row);

            for (String[] rowData : data) {
                writer.write(String.join(" ※ ", rowData));
            }
            writer.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
