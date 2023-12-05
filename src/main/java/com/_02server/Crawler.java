package com._02server;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import com._02server.BucketService.*;

import static com._02server.SaveService.saveDataToCSV;
import static com._02server.SaveService.saveDataToDB;

//initialize &
public class Crawler {
    public static void crawlInformation(Category category) throws IOException {
        // CSV 파일 경로
        String csvFilePath = "output-"+category.eng+".csv";

        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(service,options);

        String cate = Integer.toString(category.cate); //여기를 바꿔서
        HashMap<String, String> nameConMap = new HashMap<>();
        driver.get("https://prod.danawa.com/list/?cate="+cate);

        List<Device> devices = new ArrayList<>();
        for(int i= 2; i<=100; i++) { //(페이지별 파싱 스크립트 통해)

            parseThisPage(driver,nameConMap);

            //csv로 임시 로컬 저장하는 코드
            Iterator<String> iter = nameConMap.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                saveDataToCSV(key, nameConMap.get(key), csvFilePath); //한 페이지 파싱할 때마다 파일 저장
            }

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("movePage(%d)", i));
        }
        //db에 들어갈 데이터로 포팅
        FilterNMapper mapper = new FilterNMapper(nameConMap,category);
        for (String key : nameConMap.keySet()) {
            devices.add(mapper.mapping(key, nameConMap.get(key)));
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
                contents = contents.replaceAll("\\n","");
                String imageURI = e.findElement(By.cssSelector("div.thumb_image")).findElement(By.cssSelector("img")).getAttribute("src");
                parsed.put(name,contents+" ☆ "+imageURI);


            } catch(NoSuchElementException noSuchElementException) {
                //System.out.println("이 페이지 끝\n");
            }catch (StaleElementReferenceException staleElementReferenceException) {
                //System.out.println("이 페이지 끝\n");
                break;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 0) {
            for(String s : args) {
                for(Category c : Category.values()) {
                    try {
                        if(c.eng.equals(s)) {
                            crawlInformation(c);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            System.out.println("what category do you want to update?????");
        }
    }
}