package stepDefinitions;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StockbitDefinition {

	WebDriver driver = new ChromeDriver();

	@Given("^User go to website demo testing$")
	public void user_go_to_website_demo_testing() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "E:\\MasterSelenium\\PathWebDriver\\chromedriver.exe");
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/select-menu");
		Thread.sleep(2000);

	}

	@When("^User in “select menu” page$")
	public void user_in_select_menu_page() throws Throwable {
		String validateUrl = driver.getCurrentUrl();
		System.out.println(validateUrl);
		Assert.assertEquals(validateUrl, "https://demoqa.com/select-menu");
		Thread.sleep(2000);

	}

	@Then("^User success input all select menu$")
	public void user_success_input_all_select_menu() throws Throwable {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='withOptGroup']/div")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='selectOne']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='oldSelectMenu']")).isDisplayed());
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//*[text()='Multiselect drop down']/parent::p/following-sibling::div/div/div[2]"))
				.isDisplayed());
	}

	@And("^User choose select value “Another root option”$")
	public void user_choose_select_value_another_root_option() throws Throwable {
		WebElement select = driver.findElement(By.xpath("//div[@id='withOptGroup']/div"));
		Actions move = new Actions(driver);
		move.moveToElement(select).click().keyDown(Keys.ARROW_UP).keyDown(Keys.ENTER).build().perform();
		Thread.sleep(2000);
	}

	@And("^User choose select one “Other”$")
	public void user_choose_select_one_other() throws Throwable {
		WebElement select = driver.findElement(By.xpath("//div[@id='selectOne']"));
		Actions move = new Actions(driver);
		move.moveToElement(select).click().keyDown(Keys.ARROW_UP).keyDown(Keys.ENTER).build().perform();
		Thread.sleep(2000);
	}

	@And("^User choose old style select menu “Aqua”$")
	public void user_choose_old_style_select_menu_aqua() throws Throwable {
		WebElement select = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));
		Select aqua = new Select(select);
		aqua.selectByVisibleText("Aqua");
		JavascriptExecutor command = (JavascriptExecutor) driver;
		command.executeScript("window.scroll(0,200)");
		Thread.sleep(2000);
	}

	@And("^User choose multi select drop down “all color”$")
	public void user_choose_multi_select_drop_down_all_color() throws Throwable {
		WebElement selectAll = driver.findElement(
				By.xpath("//*[text()='Multiselect drop down']/parent::p/following-sibling::div/div/div[2]"));
		selectAll.click();
		Thread.sleep(2000);
		for (int i = 1; i <= 4; i++) {
			Actions enter = new Actions(driver);
			enter.keyDown(Keys.ENTER).build().perform();
			Thread.sleep(1000);
		}
	}

	@Given("^User go to website testing$")
	public void user_go_to_website_testing() throws Throwable {
		driver.manage().window().maximize();
		driver.navigate().to("https://demoqa.com/books");
	}

	@When("^User in “Book Store” page$")
	public void user_in_book_store_page() throws Throwable {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://demoqa.com/books");
	}

	@Then("^User see “No rows found”$")
	public void user_see_no_rows_found() throws Throwable {
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='rt-noData']")).getText(), "No rows found");
	}

	@And("^User search book \"([^\"]*)\"$")
	public void user_search_book_something(String strArg1) throws Throwable {
		driver.findElement(By.xpath("//input[@id='searchBox']")).sendKeys(strArg1);
		JavascriptExecutor command = (JavascriptExecutor) driver;
		command.executeScript("window.scroll(0,200)");
		Thread.sleep(2000);
	}

	@And("^User click book “Git Pocket Guide”$")
	public void user_click_book_git_pocket_guide() throws Throwable {
		driver.findElement(By.xpath("//span[@id='see-book-Git Pocket Guide']/a")).click();
	}

	@Then("^User see “Git Pocket Guide”$")
	public void user_see_git_pocket_guide() throws Throwable {
		JavascriptExecutor command = (JavascriptExecutor) driver;
		command.executeScript("window.scroll(0,300)");
		Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/books?book=9781449325862");
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@id='ISBN-wrapper']/div/label[@id='userName-value']")).getText(),
				"9781449325862");
	}
}
