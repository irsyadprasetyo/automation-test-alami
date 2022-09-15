package pages;

import groovy.util.logging.Slf4j;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class EleveniaPage {

  ChromeDriver driver;


  private static WebElement element;

  public EleveniaPage(ChromeDriver driver) {
    this.driver = driver;
  }

  public void openPage() {
    driver.get("https://www.elevenia.co.id/");
    // avoid the popup
    try {
      element = driver.findElementByCssSelector(".btn-close.closeBtnMainPromo");
      element.click();
      System.out.println("pop up closed!");
    } catch (Exception e) {
      System.out.println("popup not showed");
    }
  }

  public void searchGoods(String keyword) {
    element = driver.findElementByXPath("//*[@id='AKCKwd']");
    element.sendKeys(keyword);
    element.sendKeys(Keys.ENTER);
  }

  public void chooseSortMethod(String sort) {
    By ele = By.cssSelector("#searchCondition_wrap > div.sort_wrap > ul > li > a");
    driver.findElements(ele).forEach(dt -> {
      if (dt.getText().equalsIgnoreCase(sort)) {
        dt.click();
        dt.getAttribute("class").equalsIgnoreCase("active");
      }
    });
  }

  public void clickItemOnFirstList() throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,250)", "");
    Thread.sleep(2000);
    String url = driver.findElementByCssSelector(
        "ul:nth-child(1) > li.itemList:nth-child(1) > div:nth-child(1) > a:nth-child(1)")
        .getAttribute("href");
    driver.get(url);
  }

  public void addQuantity(String type, int qty) {
    By ele = By.cssSelector("div.productOrder.optionArea > div > ul > li > div > div > button");
    element = driver.findElements(ele).stream().filter(dt -> dt.getText()
        .contains(type)).findFirst().get();
    IntStream.range(1, qty).forEach(click ->{
      element.click();
    });
    System.out.println("DONE");
  }

  public void clickAddToCart() {
    element = driver.findElementByCssSelector("div.btnArea > a.btnStyle.btnFlat.btnL.btnOrangeW");
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$('.btnStyle.btnFlat.btnL.btnOrangeW')[0].click()", "");
  }

  public void clickYesOrNo(String option) {
    driver.findElementByCssSelector("div.layPopWrap.popCart1> p.headingTitle").isDisplayed();
    element = driver.findElementsByCssSelector("#mo_lay144 > div.btnC > a").stream()
        .filter(opt -> opt.getText().contains(option)).findFirst().get();
    try {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.elementToBeClickable(element));
      element.click();
    } catch (Exception e) {
      element.click();
    }
  }

  public void ubahKurir() {
    element = driver.findElementByXPath("//*[@name='deliveryChangePopup']");
    element.click();
  }

  public void cancelUbahKurir() {
    driver.switchTo().frame("ifrLayer");
    WebDriverWait wait = new WebDriverWait(driver, 10);
    element = driver.findElementByXPath("//*[@id=\"frm\"]/article/p[3]/a[2]");
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
    driver.switchTo().parentFrame();
  }

  public void clickHapus() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("$('#frmTmall > table > tbody > tr:nth-child(1) > "
        + "td.btn.cartfont > a.btnStyle.btnS.btnWGray')[0].click()", "");
  }

  public void clickOkConfirmHapus() {
//    driver.switchTo().frame("ifrLayer");
    WebDriverWait wait = new WebDriverWait(driver, 10);
    element = driver.findElementById("chkDelPopY");
    wait.until(ExpectedConditions.elementToBeClickable(element));
    element.click();
//    driver.switchTo().parentFrame();
  }

  public String verifyCartIsEmpty() {
    element = driver.findElementByCssSelector("#frmTmall > table > tbody > tr > td > strong");
    return element.getText();
  }

  public void avoidAlert() {
    By ele = By.xpath("//*[@id='__next']/div[2]/div[1]/div/div/div/div");
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));
  }
}
