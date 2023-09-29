package com._02server;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
public class Croller {
    public static void main(String[] args) {
        // CSV 파일 경로
        String csvFilePath = "output-bluetoothEar.csv";

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        String cate = Integer.toString(CategoryNumber.무선이어폰.cate); //여기를 바꿔서

        driver.get("https://prod.danawa.com/list/?cate="+cate);
        for(int i= 2; i<=100; i++) { //(페이지별 파싱 스크립트 통해)

            Map<String, String> nameConMap = parseThisPage(driver);

            Iterator<String> iter = nameConMap.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                saveDataToCSV(key, nameConMap.get(key), csvFilePath); //한 페이지 파싱할 때마다 파일 저장
            }

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("movePage(%d)", i));
        }

        driver.quit();
    }
    private static Map<String, String> parseThisPage(WebDriver driver) {
        Map<String, String> parsed = new HashMap<>();
        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='main_prodlist main_prodlist_list']"));
        for (WebElement e : prodInfo.findElements(By.cssSelector("div.prod_main_info"))) {
            try {
                String name = e.findElement(By.cssSelector("p.prod_name a[name='productName']")).getText();
                String contents = e.findElement(By.cssSelector("div.spec_list")).getText();
                contents = contents.replaceAll("\\n","");

                parsed.put(name,contents);

                System.out.printf("상품 이름: %s\n내용: %s\n\n",name,contents);

            } catch(NoSuchElementException noSuchElementException) {
                //System.out.println("이 페이지 끝\n");
            }catch (StaleElementReferenceException staleElementReferenceException) {
                //System.out.println("이 페이지 끝\n");
                break;
            }
        }
        return parsed;
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

enum CategoryNumber {
    보조배터리(20322920),
    무선이어폰(12237349),
    무선헤드폰(12237350),
    미러리스카메라(12237505),
    DSLR카메라(12237506),
    하이엔드카메라(12237507),
    액션캠코더(12237508);

    int cate;
    CategoryNumber(int i) {
        this.cate = i;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}