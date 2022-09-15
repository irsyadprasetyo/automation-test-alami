package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.EleveniaPage;
import webdriverpool.WebdriverInitializer;

public class EleveniaStepDef {

  EleveniaPage elevenia = new EleveniaPage(WebdriverInitializer.driver);

  @And("User {string} quantity {int} on Elevenia product page")
  public void userAddQuantityOnEleveniaProductPage(String type, int qty) {
    elevenia.addQuantity(type, qty);
  }

  @And("User choose item on the first list on Elevenia search result page")
  public void userChooseItemOnTheFirstListOnEleveniaSearchResultPage() throws InterruptedException {
    elevenia.clickItemOnFirstList();
  }

  @And("User click add to cart on Elevenia product page")
  public void userClickAddToCart() {
    elevenia.clickAddToCart();
  }

  @When("User click {string} on cart page")
  public void userClickOnCartPage(String option) {
    elevenia.clickHapus();
  }

  @And("User click {string} on Elevenia search result page")
  public void userClickOnEleveniaSearchResultPage(String sort) {
    elevenia.chooseSortMethod(sort);
  }

  @And("User click {string} on hapus cart on popup page")
  public void userClickOnHapusCartOnPopupPage(String arg0) {
    elevenia.clickOkConfirmHapus();
  }

  @And("User click {string} on tambah cart popup page")
  public void userClickOnTambahCartPopupPage(String opt) {
    elevenia.clickYesOrNo(opt);
  }

  @And("User click Batal on ubah kurir popup page")
  public void userClickOnUbahKurirPopupPage() {
    elevenia.cancelUbahKurir();
  }

  @And("User click Ubah Kurir on cart page")
  public void userClickUbahKurirOnCartPage() {
    elevenia.ubahKurir();
  }

  @Given("User go to Elevenia site")
  public void userGoToEleveniaSite() {
    elevenia.openPage();
  }

  @And("User search with keyword {string} on search bar Elevenia homepage")
  public void userSearchWithKeywordOnSearchBarEleveniaHomepage(String keyword) {
    elevenia.searchGoods(keyword);
  }

  @Then("User verify the Item is removed from cart")
  public void userVerifyTheItemIsRemovedFromCart() {
    Assert.assertTrue("not equals!", elevenia.verifyCartIsEmpty()
        .equalsIgnoreCase("Tidak ada produk di Shopping Cart."));
  }
}
