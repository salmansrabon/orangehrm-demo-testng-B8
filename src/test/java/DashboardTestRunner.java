import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DashboardTestRunner extends Setup {
    @BeforeTest
    public void login(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
    }
    @Test(priority = 1)
    public void createUser(){
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.createUser("Test","User","testuser100","P@ssword123");

    }
}
