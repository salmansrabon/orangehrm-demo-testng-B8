package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashboardTestRunner extends Setup {
    DashboardPage dashboardPage;
    @BeforeTest(groups = "smoke")
    public void login(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
        dashboardPage=new DashboardPage(driver);
        dashboardPage.menuItems.get(1).click(); //click PIM
    }
    @Test(priority = 1, groups = "smoke", description ="Check if search button is working" )
    public void clickonSearchButton(){
        driver.findElement(By.cssSelector("[type='submit']")).click();
    }
    @Test(priority = 2, groups = "smoke", description = "Check if reset button is working")
    public void clickonResetButton(){
        driver.findElement(By.cssSelector("[type=reset]")).click();
    }
    @Test(priority = 3, description = "Check if new user is created successfully")
    public void createUser() throws IOException, ParseException, InterruptedException {
        DashboardPage dashboardPage=new DashboardPage(driver);
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password=faker.internet().password();

        EmployeeModel model=new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);

        dashboardPage.createUser(model);
        String textTitleExpected= driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).getText();
        System.out.println(textTitleExpected);
        Thread.sleep(7000);
        if(textTitleExpected.contains("Personal Details")){
            Utils.saveEmployeeInfo(model);
        }
        Allure.description("User created successfully");

    }
}
