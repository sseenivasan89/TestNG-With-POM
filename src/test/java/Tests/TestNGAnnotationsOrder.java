package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotationsOrder {
 
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("1-1");
    }
 
    @AfterSuite
    public void afterSuite() {
        System.out.println("1-2");
    }
 
    @BeforeTest
    public void beforeTest() {
        System.out.println("2-1");
    }
     
    @AfterTest
    public void afterTest() {
        System.out.println("2-2");
    }
    
    @BeforeGroups
    public void beforeGroup() {
        System.out.println("3-1");
    }
     
    @AfterGroups
    public void afterGroup() {
        System.out.println("3-2");
    }
     
    @BeforeClass
    public void beforeClass() {
        System.out.println("4-1");
    }
 
    @AfterClass
    public void afterClass() {
        System.out.println("4-2");
    }
 
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("5-1");
    }
 
    @AfterMethod
    public void afterMethod() {
        System.out.println("5-2");
    }
    
    @Test(groups= {"IT"})
    public void testCase1() {
		 System.out.println("TEST");
    }
    
    @Test(groups= {"CSE"})
    public void testCase2() {
		 System.out.println("TEST");
    }

}
