package com._02server.com02backendproject.service;

import com._02server.com02backendproject.entities.Category;
import com._02server.com02backendproject.entities.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com._02server.com02backendproject.service.DeviceAddService.saveDataToCSV;
import static com._02server.com02backendproject.service.DeviceAddService.saveDataToDB;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlerService {
    public static void crawlInformation(Category category) throws IOException {
        // CSV 파일 경로
        String csvFilePath = "output-"+category.getEng()+".csv";

        //사파리에서
        //SafariOptions options = new SafariOptions();
        //options.setCapability("headless", true);
        //System.setProperty("webdriver.chrome.driver","/Users/shinyeonggwak/.gradle/caches/modules-2/files-2.1/org.seleniumhq.selenium/selenium-chromium-driver/4.8.3/f0ff2932d0ffdc49388eafa0c379f03d23496a29/selenium-chromium-driver-4.8.3.jar");
        //크롬에서
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        String cate = Integer.toString(category.getCate()); //여기를 바꿔서
        HashMap<String, String> nameConMap = new HashMap<>();
        driver.get("https://prod.danawa.com/list/?cate="+cate);

        List<Device> devices = new ArrayList<>();
        for(int i= 2; i<=100; i++) { //(페이지별 파싱 스크립트 통해)

            parseThisPage(driver,nameConMap);
            //db에 들어갈 데이터로 포팅
            FilterNMapper mapper = new FilterNMapper(nameConMap,category);
            for (String key : nameConMap.keySet()) {
                devices.add(mapper.mapping(key, nameConMap.get(key)));
            }


            //csv로 임시 로컬 저장하는 코드
            Iterator<String> iter = nameConMap.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                saveDataToCSV(key, nameConMap.get(key), csvFilePath); //한 페이지 파싱할 때마다 파일 저장
            }

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("movePage(%d)", i));
        }
        saveDataToDB(devices);

        //BucketService bucketService = new BucketService();
        //bucketService.save_bucket(csvFilePath);



        driver.quit();
    }
    private static void parseThisPage(WebDriver driver,HashMap<String,String> parsed) {
        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='main_prodlist main_prodlist_list']"));
        for (WebElement e : prodInfo.findElements(By.cssSelector("div.prod_main_info"))) {
            try {
                String name = e.findElement(By.cssSelector("p.prod_name a[name='productName']")).getText();
                String contents = e.findElement(By.cssSelector("div.spec_list")).getText();
                contents = contents.replaceAll("\\n", "");
                String imageURI = e.findElement(By.cssSelector("div.thumb_image")).findElement(By.cssSelector("img")).getAttribute("src");
                parsed.put(name, contents + " ☆ " + imageURI);


            } catch (NoSuchElementException noSuchElementException) {
                //System.out.println("이 페이지 끝\n");
            } catch (StaleElementReferenceException staleElementReferenceException) {
                //System.out.println("이 페이지 끝\n");
                break;
            }
        }
    }

}
