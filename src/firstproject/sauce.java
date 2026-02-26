package firstproject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class sauce {

	String MywebSite = "https://www.saucedemo.com/";
	String MywebSite2 = "https://www.google.com/";
	String UserName = "standard_user";
	String PassWord = "secret_sauce";
	WebDriver driver = new EdgeDriver();
	Random rand = new Random();

	@BeforeTest

	public void sauce2() throws InterruptedException {

		driver.get(MywebSite);
		driver.manage().window().maximize();

		
		driver.navigate().to(MywebSite2);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.urlContains("google"));

		driver.navigate().back();
		wait.until(ExpectedConditions.urlContains("saucedemo"));


	}

	@Test(priority = 1)
	public void login() {

		WebElement UserNameInput = driver.findElement(By.id("user-name"));
		WebElement PassWordInput = driver.findElement(By.id("password"));
		WebElement LogInBotten = driver.findElement(By.id("login-button"));

		UserNameInput.sendKeys("standard_user");
		PassWordInput.sendKeys("secret_sauce");
		LogInBotten.click();
		// driver.navigate().refresh();
		Assert.assertEquals(driver.getCurrentUrl().contains("inventory"), true);
	}

	@Test(priority = 2, enabled = false, invocationCount = 6)

	public void RandomCart() {
		List<WebElement> AddToCartRand = driver.findElements(By.className("btn_primary"));
		int Randomindex = rand.nextInt(AddToCartRand.size());

		AddToCartRand.get(Randomindex).click();

		// System.out.println(Randomindex);

	}

	@Test(priority = 2, enabled = false)
	public void add() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		List<WebElement> addToCartButtons = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn_primary")));

		List<WebElement> buttons = driver.findElements(By.className("btn_primary"));

		for (int i = 0; i < addToCartButtons.size(); i++) {

			wait.until(ExpectedConditions.elementToBeClickable(buttons.get(i))).click();

		}

		System.out.println("تم اضافة العناصرللسلة");
		Assert.assertEquals(driver., null);
	}

	@Test(priority = 3, enabled = false)
	public void cartbadge() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cartBadge = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
		String badgeText = cartBadge.getText();
		int itemCount = Integer.parseInt(badgeText);

		System.out.println("عدد المنتجات في السلة: " + itemCount);

		List<WebElement> AllItemNames = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> AllItemsPrices = driver.findElements(By.className("inventory_item_price"));

		String info = " and the price is ";

		for (int i = 0; i < AllItemNames.size(); i++) {

			System.out.println(AllItemNames.get(i).getText() + info + AllItemsPrices.get(i).getText());
		}

	}

	@Test(priority = 4, enabled = false)
	public void remove() {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		List<WebElement> addToCartButtons = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn_secondary")));

		List<WebElement> buttons = driver.findElements(By.className("btn_secondary"));

		for (int i = 0; i < addToCartButtons.size(); i++) {

			wait.until(ExpectedConditions.elementToBeClickable(buttons.get(i))).click();
		}
		System.out.println("تم ازالة العناصرمن للسلة");

	}

	@Test(priority = 5,enabled = false)

	public void cartbadge2() {

		List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));

		if (badges.isEmpty()) {
			System.out.println("Cart is empty - badge is not displayed\nالسلة فارغه و لا يوجد ايقونة");
		} else {
			System.out.println("Items in cart: " + badges.get(0).getText());
		}

	}

	@AfterTest
	public void finish() {

		driver.quit();

	}

}
