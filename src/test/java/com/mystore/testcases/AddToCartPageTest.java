/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

/**
 * @author SuprajaBaru
 *
 */
public class AddToCartPageTest extends BaseClass {
	
	 IndexPage index;
	 SearchResultPage searchResultPage;
	 AddToCartPage addToCartPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity","Regression"})
	public void setup(@Optional("Chrome")String browser) {
		launchApp(browser); 
	}
	
	
	@AfterMethod(groups = {"Smoke", "Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(dataProvider = "getProduct", dataProviderClass = DataProviders.class, groups = {"Regression","Sanity"})
	public void addToCartTest(String productName,String quantitynum, String size) throws Throwable {
		Log.startTestCase("addToCartTest");
		
		index= new IndexPage();
		
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(quantitynum);
		addToCartPage.selectSize(size);
		addToCartPage.clickOnAddToCart();
		
		boolean result=addToCartPage.validateAddtoCart();
		Assert.assertTrue(result);
		Log.endTestCase("addToCartTest");
		
	} 

}
