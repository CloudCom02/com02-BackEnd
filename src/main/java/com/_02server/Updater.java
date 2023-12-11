package com._02server;
/*
로직 ------
카테고리 넘버 주입 -> 해당 넘버를 이용하여 크롤링 하여 임시 변수에 저장,
List<String>타입 -> 현재 가지고 있는 데이터와 대조하여 같다면? -> 쭉 삭제 -> 결국 한쪽에는 없는게 나오겠지
그럼 result List<String>이 널이 아니라면 DataUpdate함수를 호출하는거지 호출 완료된 개수를 int로 return해
널이라면 0을 리턴해
_________________________
Key	           | 타입     |
_________________________|
DeviceNames	   | Array   |
categoryNumber | Int(8)  |
--------------------------
 */
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.*;

import static com._02server.SaveService.*;

public class Updater {
    public Updater() {}
    public static void updateThisCategory(Category category) {
        HashSet<String> crolledSet = new HashSet<>();

        String tmpCsvPath = "output-subBattery.csv";
        HashSet<String> prevSavedSet = retrieveDeviceNames();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        String cate = Integer.toString(category.cate); //여기를 바꿔서

        driver.get("https://prod.danawa.com/list/?cate="+cate);
        for(int i= 2; i<=100; i++) { //(페이지별 파싱 스크립트 통해)
            crolledSet.addAll(parseName(driver));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("movePage(%d)", i));
        }

        int success = dataCheck(category,crolledSet,prevSavedSet);
        System.out.printf("성공한 업데이트 %d건, 프로그램 종료합니다.\n",success);

    }

    //파일로 버퍼링하는 방식.... 일단 안해!!! -> filter로 배터리케이스같은 자잘한거 떨어트릴 때 필요한 방식임!
    private static HashSet<String> loadNameListFile(String tmpCsvPath) {
        //접근하기
        File dataFile = new File(tmpCsvPath);
        HashSet<String> nameSet = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String str;
            while((str = reader.readLine()) != null) {
                int lastOfName = str.indexOf((int)'※');
                str = str.substring(0,lastOfName-1);
                nameSet.add(str);
            }

        }catch(FileNotFoundException fileNotFoundException) {
            System.out.println("파일 없음???");
        } catch (IOException e) {
            System.out.println("내용이없네???");
        }
        return nameSet;
    }

    //0일 시 업데이트 필요 없음
    //1 이상일 시 업데이트 함수 호출
    //-1일 시
    public static int dataCheck(Category category, Set<String> crolledSet, Set<String> prevSavedSet) {
        //여기부터 신상 판별기
        //별 문제 없이 둘이 똑같다면
        crolledSet.removeAll(prevSavedSet);
            //차집합이 0이라면
        if (crolledSet.isEmpty()) {
            System.out.println("업데이트가 없습니다. 프로그램을 종료합니다.");
            return 0;
            //차집합이 존재, 여러개라면 일단 몇개인지 뽑기
        } else {
            //{보배 케이스 등 연관 없는 데이터의 이름 셋 = noRelevantList}
            //이것도 removeAll prev-norel 연산을 통해서 밑return 0 혹은 return 자연수 하는 코드 필요.
            //if (crolledSet.removeAll(noRelevantSet))했는데 crolledSet.isEmpty()이면 return 0
            System.out.printf("%d 개의 업데이트가 필요합니다. 업데이트를 진행합니다.\n",crolledSet.size());
            return dataUpdate(crolledSet,category);
        }
    }
    //살짝 아리까리 한부분은 리스트로 반환, 교환해도 되나...
    private static Set<String> parseName(WebDriver driver) {
        HashSet<String> parsed = new HashSet<>();
        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='main_prodlist main_prodlist_list']"));
        try {
            for (WebElement e : prodInfo.findElements(By.cssSelector("div.prod_main_info"))) {
                String name = e.findElement(By.cssSelector("p.prod_name a[name='productName']")).getText();
                parsed.add(name);
            }

        } catch(NoSuchElementException | StaleElementReferenceException noSuchElementException) {
        }

        return parsed;
    }
    //성공한 개수 반환
    protected static int dataUpdate(Set<String> needUpdateSet, Category category) {
        Map<String,String> newDataMap = new HashMap<>();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);//너무 많이 생성됨
        System.out.println("< "+category+" 업데이트 항목 >");
        List<Device> devices = new ArrayList<>();
        for(String searchName : needUpdateSet) {
            newDataMap.putAll(parseOneProduct(searchName,driver));
        }
        FilterNMapper mapper = new FilterNMapper(newDataMap,category);
        for (String key : newDataMap.keySet()) {
            devices.add(mapper.mapping(key, newDataMap.get(key)));
        }
        newDataMap.forEach((k,v) -> System.out.println("name : "+k+" contents : "+v) );
        saveDataToDB(devices);

        //newDataMap을 이용해서 데이터베이스에 업데이트하는 코드 입력(추상클래스로 상위에 넣을지... 고민중!)
        return devices.size();
    }

    private static Map<String,String> parseOneProduct(String searchName,WebDriver driver) throws NoSuchElementException, StaleElementReferenceException {
        driver.get("https://search.danawa.com/dsearch.php?query=" + searchName );
        Map<String, String> parsed = new HashMap<>();
        WebElement prodInfo = driver.findElement(By.xpath("//div[@class='main_prodlist main_prodlist_list']"));
        for(WebElement e :prodInfo.findElements(By.cssSelector("div.prod_main_info")) ) {
            String contents = e.findElement(By.cssSelector("div.spec_list")).getText();
            contents = contents.replaceAll("\\n", "");

            parsed.put(searchName, contents);
            break;
        }

        return parsed;
        //try catch 넣어야하는데 어떤 exception을 뱉는지 모름 -> 알게되면 catch해서 return 0 해주길
    }
    //tester
    public static void main(String[] args) {
        Category target = null;
        if(args.length == 0 ) {
            System.out.println("어떤 카테고리를 업데이트할 지 입력하세요");
        } else {
            for(String s : args) {
                for(Category c : Category.values()) {
                    if(s.equals(c.eng)) {
                        target = c;
                        updateThisCategory(target);
                    }
                }
            }
        }
        if(target == null) {
            System.out.println("해당 카테고리가 없어서 프로그램을 종료합니다");
        }

    }
}
