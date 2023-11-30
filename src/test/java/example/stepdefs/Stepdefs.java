package example.stepdefs;

import example.pageObject.HomePage;
import example.pageObject.ProductPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.Map;

public class Stepdefs {
    HomePage hp = new HomePage();
    ProductPage pp;
    @io.cucumber.java.en.Given("user navigate to {string}")
    public void userNavigateTo(String url) {
        hp.navigateToHp(url);

    }

    @io.cucumber.java.en.When("user logins with the following credentials")
    public void userLoginsWithTheFollowingCredentials(DataTable table) {
    Map<String,String> map = table.asMap();
    String username = map.get("username");
    String password = map.get("password");

    hp.login(username,password);

    }

    @io.cucumber.java.en.Then("login should be successful")
    public void loginShouldBeSuccessful() {

      pp = hp.textIsVisible("Products");

    }

    @And("sort the products Z to A")
    public void sortTheProductsZToA() {

        pp.sortZtoa();
    }

    @And("products should be in descending order")
    public void productsShouldBeInDescendingOrder() {

        pp.assertZtoA();
    }
}
