package ecommerce;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCaseRunner {

	/******************************
	 * Declare variable
	 *******************************/
	public WebDriver driver;
	public String baseUrl, webTableUrl, windowHandle, alertHandle;
	/*************************
	 * Create Account Xpath
	 ********************************/
	String createAnAccountbtn = "//a[@title='Create an Account']";
	String xfirstName = "firstname";
	String xlastName = "lastname";
	String email_Address = "email_address";
	String createpassword = "password";
	String confirmPassword = "confirmation";
	String isSubscribed = "is_subscribed";

	/***********************
	 * Home Page Xpath
	 ***************************************/
	String homePageHeader = "//h2";
	String navHeaderlist = "//nav[@id='nav']//li/a";
	String sortByDropdown = "(//select[@title='Sort By'])[1]";
	String footerLinks = "//div[@class='footer']//li/a";

	/************************ Constructor **************************************/
	TestCaseRunner() {
		baseUrl = "http://live.guru99.com/";
		webTableUrl = "http://demo.guru99.com/test/web-table-element.php";
		alertHandle = "http://demo.guru99.com/test/delete_customer.php";
		windowHandle = "http://demo.guru99.com/popup.php";
	}

	/************************ Browser Driver instantiation **********************/
	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "chromedriver.exe");
		driver = new ChromeDriver();
		/******************************Implicit Wait ***************************************/
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		return driver;
	}

	public static void main(String[] s) throws InterruptedException {

		TestCaseRunner testRunner = new TestCaseRunner();

		System.out.println("**********************TEST CASE 1***************************************");
		testRunner.Verify_Page_title();
		System.out.println("**********************TEST CASE 2****************************************");
		testRunner.verifyProductsSortedByNames();
		System.out.println("**********************TEST CASE 3****************************************");
		testRunner.verifyProductsSortedByNames_ignorecase();
		System.out.println("**********************TEST CASE 4****************************************");
		testRunner.verifyTotallinksInHomePage();
		System.out.println("**********************TEST CASE 5****************************************");
		testRunner.registerUser_verifyDefaultSignupCheck();
		System.out.println("**********************TEST CASE 6****************************************");
		testRunner.playwithWebTables();
		System.out.println("**********************TEST CASE 7****************************************");
		testRunner.handleAlert();
		System.out.println("**********************TEST CASE 8****************************************");
		testRunner.handleMultipleWindows();
		System.out.println("**********************TEST CASE 9****************************************");

	}

	/***************************
	 * TestCases
	 ************************************************************/
	public void Verify_Page_title() {

		getDriver();
		String expectedHeadervalue = "THIS IS DEMO SITE FOR";
		driver.get(baseUrl);

		/*****************
		 * Explicit Wait
		 ****************************************************/
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(homePageHeader)));
		/***********************************************************************************/

		/**********************Fluent Wait **************************************************/
		// Waiting 30 seconds for an element to be present on the page, checking
		// for its presence once every 500 milliseconds.
		Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);

		fluentwait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(homePageHeader));
			}
		});
		
		/**************************************************************************************/

		String homePageHead = driver.findElement(By.xpath(homePageHeader)).getText();
		System.out.println("Home Page header : " + homePageHead);

		try {
			Assert.assertTrue(expectedHeadervalue.equalsIgnoreCase(homePageHead.trim()));
		} catch (AssertionError e) {
			System.out.println("Test Failed as Strings does not match");
		} catch (Exception e) {
			System.out.println("Test Case Failed");
		} finally {
			driver.close();
		}
	}

	public void verifyProductsSortedByNames() {

		getDriver();
		System.out.println(driver);
		driver.get(baseUrl);
		try {
			List<WebElement> navHeaderElements = driver.findElements(By.xpath(navHeaderlist));

			for (WebElement e : navHeaderElements) {

				if (e.getText().trim().equalsIgnoreCase("moBile")) {
					if (e.isDisplayed() && e.isEnabled()) {
						e.click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}

		try {
			Select selectElement = new Select(driver.findElement(By.xpath(sortByDropdown)));
			List<WebElement> selectElements = selectElement.getOptions();

			for (WebElement e : selectElements) {
				if (e.getText().trim().equalsIgnoreCase("Name")) {
					selectElement.selectByVisibleText(e.getText().trim());
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		driver.quit();
	}

	public void verifyProductsSortedByNames_ignorecase() {

		getDriver();
		driver.get(baseUrl);
		try {
			List<WebElement> navHeaderElements = driver.findElements(By.xpath(navHeaderlist));
			for (WebElement e : navHeaderElements) {

				if (e.getText().trim().equalsIgnoreCase("moBile")) {
					if (e.isDisplayed() && e.isEnabled()) {
						e.click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}

		try {
			List<WebElement> navHeaderElements = driver.findElements(By.xpath(navHeaderlist));

			for (WebElement e : navHeaderElements) {

				if (e.getText().trim().equalsIgnoreCase("moBile")) {
					if (e.isDisplayed() && e.isEnabled()) {
						e.click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}

		try {
			Select selectElement = new Select(driver.findElement(By.xpath(sortByDropdown)));
			List<WebElement> selectElements = selectElement.getOptions();

			for (WebElement e : selectElements) {
				if (e.getText().trim().equalsIgnoreCase("naMe")) {
					selectElement.selectByVisibleText(e.getText().trim());
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		driver.quit();
	}

	public void verifyTotallinksInHomePage() {

		getDriver();
		driver.get(baseUrl);
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		int linkcount = allLinks.size();
		System.out.println("********LINKS***********");
		for (WebElement e : allLinks) {

			System.out.println(e.getAttribute("href"));
		}
		System.out.println(linkcount);

		try {
			Assert.assertTrue("Links Count not as expected", linkcount == 24);
		} catch (Exception e) {
			System.out.println("Link count not as expected");
		} finally {
			driver.close();
		}

		driver.quit();
	}

	public void registerUser_verifyDefaultSignupCheck() {

		getDriver();

		driver.get(baseUrl);

		try {
			List<WebElement> footernavlinks = driver.findElements(By.xpath(footerLinks));

			for (WebElement e : footernavlinks) {
				if (e.getText().trim().equalsIgnoreCase("My account")) {
					if (e.isDisplayed() && e.isEnabled()) {
						e.click();
						break;
					}
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.xpath(createAnAccountbtn)).click();
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.id(xfirstName)).sendKeys("Test");
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.id(xlastName)).sendKeys("User");
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.id(email_Address)).sendKeys("testUser@yopmail.com");
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.id(createpassword)).sendKeys("123456");
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			driver.findElement(By.id(confirmPassword)).sendKeys("123456");
		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		try {
			WebElement e = driver.findElement(By.id(isSubscribed));
			if (!e.isSelected()) {
				e.click();
			}

		} catch (Exception e) {
			System.out.println("Element Not found");
		}

		driver.quit();
	}

	public void handleAlert() throws InterruptedException {
		getDriver();

		driver.get(alertHandle);

		driver.findElement(By.name("cusid")).sendKeys("53920");

		driver.findElement(By.name("submit")).submit();

		// Switching to Alert
		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		Thread.sleep(5000);

		// Accepting alert
		// alert.accept();
		alert.dismiss();

		driver.close();
	}

	public void handleMultipleWindows() {
		getDriver();

		driver.get(windowHandle);

		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.

		Set<String> s1 = driver.getWindowHandles();

		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {

			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");
				driver.findElement(By.name("btnLogin")).click();

				// Closing the Child Window.
				// driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
		driver.quit();
	}

	public void playwithWebTables() {

		getDriver();
		driver.get(webTableUrl);

		// To locate table.
		WebElement mytable = driver.findElement(By.xpath("//body//table//tbody"));

		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));

		// To calculate no of rows In table.
		int rows_count = rows_table.size();

		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {

			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

			// To calculate no of columns (cells). In that specific row.
			int columns_count = Columns_row.size();

			System.out.println("Number of cells In Row " + row + " are " + columns_count);

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();

				System.out
						.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
			}
			System.out.println("-------------------------------------------------- ");
		}

		driver.close();
	}

//	/***************************
//	 * Create Account Page Functions
//	 ****************************************/
//	public boolean clickCreateAnAccountButton() {
//		boolean clicked = false;
//		try {
//			driver.findElement(By.xpath(createAnAccountbtn)).click();
//			clicked = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//
//		return clicked;
//
//	}
//
//	public boolean enterfirstName(String firstName) {
//		boolean enteredText = false;
//		try {
//			driver.findElement(By.id(xfirstName)).sendKeys(firstName);
//			enteredText = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return enteredText;
//	}
//
//	public boolean enterlastName(String lastName) {
//		boolean enteredText = false;
//		try {
//			driver.findElement(By.id(xlastName)).sendKeys(lastName);
//			enteredText = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return enteredText;
//	}
//
//	public boolean enterEmail(String email) {
//
//		boolean enteredText = false;
//		try {
//			driver.findElement(By.id(email_Address)).sendKeys(email);
//			enteredText = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return enteredText;
//	}
//
//	public boolean createpassword(String password) {
//		boolean enteredText = false;
//		try {
//			driver.findElement(By.id(createpassword)).sendKeys(password);
//			enteredText = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return enteredText;
//	}
//
//	public boolean confirmpassword(String password) {
//		boolean enteredText = false;
//		try {
//			driver.findElement(By.id(confirmPassword)).sendKeys(password);
//			enteredText = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return enteredText;
//	}
//
//	public boolean clickSignUpNewsletterChekbox() {
//		boolean selected = false;
//		try {
//			WebElement e = driver.findElement(By.id(isSubscribed));
//			if (!e.isSelected()) {
//				e.click();
//				selected = true;
//			}
//
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return selected;
//	}
//
//	/**********************************
//	 * Home Page Functions
//	 ********************************************/
//	public String getHeaderText() {
//		String headerText = null;
//		try {
//			headerText = driver.findElement(By.xpath(homePageHeader)).getText();
//		} catch (Exception e) {
//			System.out.println("Incorrect Header");
//		}
//		return headerText;
//
//	}
//
//	public boolean clickOnNavHeaderLink(String navHeaderName) {
//		boolean clicked = false;
//		try {
//			List<WebElement> navHeaderElements = driver.findElements(By.xpath(navHeaderlist));
//
//			for (WebElement e : navHeaderElements) {
//
//				if (e.getText().trim().equalsIgnoreCase(navHeaderName)) {
//					if (e.isDisplayed() && e.isEnabled()) {
//						e.click();
//						clicked = true;
//						break;
//					}
//				}
//			}
//		} catch (NoSuchElementException e) {
//			System.out.println("Element Not found");
//		}
//		return clicked;
//	}
//
//	public boolean selectValuefromSortBy(String sortByValue) {
//		boolean selected = false;
//
//		try {
//			Select selectElement = new Select(driver.findElement(By.xpath(sortByDropdown)));
//			selectElement.selectByVisibleText(sortByValue);
//			selected = true;
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return selected;
//
//	}
//
//	public boolean selectValuefromSortByUsingOptions(String sortByValue) {
//		boolean selected = false;
//
//		try {
//			Select selectElement = new Select(driver.findElement(By.xpath(sortByDropdown)));
//			List<WebElement> selectElements = selectElement.getOptions();
//
//			for (WebElement e : selectElements) {
//				if (e.getText().trim().equalsIgnoreCase(sortByValue)) {
//					selectElement.selectByVisibleText(e.getText().trim());
//					selected = true;
//					break;
//				}
//			}
//
//		} catch (Exception e) {
//			System.out.println("Element Not found");
//		}
//		return selected;
//
//	}
//
//	public List<WebElement> getAllLinks() {
//		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
//		System.out.println("********LINKS***********");
//		for (WebElement e : allLinks) {
//
//			System.out.println(e.getAttribute("href"));
//		}
//
//		return allLinks;
//
//	}
//
//	public boolean clickonFooterNavLink(String footerlink) {
//		boolean clicked = false;
//		try {
//			List<WebElement> footernavlinks = driver.findElements(By.xpath(footerLinks));
//
//			for (WebElement e : footernavlinks) {
//				if (e.getText().trim().equalsIgnoreCase(footerlink)) {
//					if (e.isDisplayed() && e.isEnabled()) {
//						e.click();
//						clicked = true;
//						break;
//					}
//				}
//			}
//		} catch (NoSuchElementException e) {
//			System.out.println("Element Not found");
//		}
//		return clicked;
//	}

}
