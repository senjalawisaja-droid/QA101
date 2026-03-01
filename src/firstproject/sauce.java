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

public class sauce extends testdata {

	@BeforeTest

	public void sauce2() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get(MywebSite);
		driver.manage().window().maximize();
		driver.navigate().to(MywebSite2);
		wait.until(ExpectedConditions.urlContains("google"));
		driver.navigate().back();
		wait.until(ExpectedConditions.urlContains("saucedemo"));

	}

	@Test(priority = 1)
	public void login() {
		// -------------------elements------------

		WebElement UserNameInput = driver.findElement(By.id("user-name"));
		WebElement PassWordInput = driver.findElement(By.id("password"));
		WebElement LogInBotten = driver.findElement(By.id("login-button"));
		//----------------------------actions----------------

		UserNameInput.sendKeys("standard_user");
		PassWordInput.sendKeys("secret_sauce");
		LogInBotten.click();
		Assert.assertEquals(driver.getCurrentUrl().contains("inventory"), true);
	}

	@Test(priority = 2, enabled = false, invocationCount = 6)

	public void RandomCart() {
		List<WebElement> AddToCartRand = driver.findElements(By.className("btn_primary"));
		int Randomindex = rand.nextInt(AddToCartRand.size());
		AddToCartRand.get(Randomindex).click();

	}

	@Test(priority = 2, enabled = true)
	public void add() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		List<WebElement> addToCartButtons = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn_primary")));
		// List<WebElement> buttons = driver.findElements(By.className("btn_primary"));

		for (int i = 0; i < addToCartButtons.size(); i++) {
			wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(i))).click();
		}
		System.out.println("تم اضافة العناصرللسلة");
	}

	@Test(priority = 3, enabled = true)
	public void cartbadge() throws InterruptedException {

		// -------------------elements------------

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cartBadge = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
		List<WebElement> AllItemNames = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> AllItemsPrices = driver.findElements(By.className("inventory_item_price"));

		String badgeText = cartBadge.getText();
		int itemCount = Integer.parseInt(badgeText);
		String info = " and the price is ";
//----------------------------actions----------------
		System.out.println("عدد المنتجات في السلة: " + itemCount);

		for (int i = 0; i < AllItemNames.size(); i++) {

			System.out.println(AllItemNames.get(i).getText() + info + AllItemsPrices.get(i).getText());

		}

	}

	@Test(priority = 4, enabled = false)
	public void remove() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		List<WebElement> addToCartButtons = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("btn_secondary")));
		List<WebElement> buttons = driver.findElements(By.className("btn_secondary"));
		
		
		driver.navigate().refresh();
		for (int i = 0; i < addToCartButtons.size(); i++) {
			wait.until(ExpectedConditions.elementToBeClickable(buttons.get(i))).click();
		}
		System.out.println("تم ازالة العناصرمن للسلة");

	}

	@Test(priority = 5, enabled = false)

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
