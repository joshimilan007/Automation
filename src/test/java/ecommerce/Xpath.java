package ecommerce;

public class Xpath {

	//Type of locators in Selenium
//	Id locator
//	Name locator
//	linkText & Partial linkText
	//driver.findElement(By.linkText("Trouble Signing in?")).click();
	//driver.findElement(By.partiallinkText("Trouble")).click();
//	CSS Selector
//	XPath
	
	
	// ******************************XPATH
	// SELECTOR***********************************
	// There are 13 different axis, it tells the xpath processor , which direction
	// to head.

	// AbsoluteXpath_logo = //html/body/div/div/header/div/a;
	// relativePath_logo=//header[@id='header']/div/a;

	// 1* child axes
	// header[@id='header']/div/a

	// 2* Parent axes
	// header[@id='header']/child::div/a/parent::div/a

	// 3* SearchInput field : using following-sibling
	// form[@id='search_mini_form']//label/following-sibling::input

	// 4* Logo : preceding-sibling
	// header[@id='header']/child::div/a/img[@class='small']/preceding-sibling::img

	// 5*
	// using /.. ( parent )

	// Most widely used Xpath Functions
	//1*	contains()
	//input[contains(@id,'sear')]
	
    //2* starts-with()
	//input[starts-with(@id,'sear')]
    
	//3* text()
	//h2[contains(text(),'demo')]


	// *****************************CSS
	// SELECTOR******************************************
	// Type of CSS Selectors in Selenium tests-
//	There are there important special characters in css selectors:
//		1. '^' symbol, represents the starting text in a string.
//		2. '$' symbol represents the ending text in a string.
//		3. '*' symbol represents contains text in a string.

	// 1* ID
	// css=input#Email
	// 2* Class
	// css=label.remember
	// 3* Attribute
	// css=input[type=’submit’]
	// 4* Sub-String
	// css=input#Passwd[name^='Pass']
	// ^ : the symbol used to match a string using a prefix

	// css=input[id^='ema']
	// css=input[id$='mail']
	// css=input[id*='mai']

}
