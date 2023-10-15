//package service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class CrollerService {
//    @Scheduled(fixedRate = 1000) // ms단위(1초=1000)
//    public void run() {
//        System.err.println("돌아감");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//
//        WebDriver driver = new ChromeDriver(options);
//
//        driver.get("https://prod.danawa.com/list/?cate=20322920&15main_20_03");
//        System.out.println("여기까지 옴");
//        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='prod_info'][contains(text(), '상세 스펙')]"));
//        WebElement productNameElement = prodInfo.findElement(By.cssSelector("div.prod_name[name='productName']"));
//
//        String productName = productNameElement.getText();
//        System.out.println("상품 이름: "+ productName);
//
//        WebElement prodSpecSet = prodInfo.findElement(By.xpath(".//div[@class='prod_spec_set']/div[@class='screen_out']"));
//
//        if (prodSpecSet.getText().contains("상세 스펙")) {
//            List<WebElement> specElements = prodSpecSet.findElements(By.xpath(".//*"));
//            for (WebElement element : specElements) {
//                if(element.getText().matches(".*시간.*|.*배터리.*|.*용량.*|.*출력.*"))
//                    System.out.println(element.getText());
//            }
//        }
//        driver.quit();
//    }
//
//}
