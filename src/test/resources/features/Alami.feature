Feature: Alami Test

  Scenario: Verify user can remove buy list on the cart page
    Given User go to Elevenia site
    And User search with keyword "Komputer" on search bar Elevenia homepage
    And User click "Produk Terlaris" on Elevenia search result page
    And User choose item on the first list on Elevenia search result page
    And User "Increase" quantity 3 on Elevenia product page
    And User click add to cart on Elevenia product page
    And User click "Ya" on tambah cart popup page
    And User click Ubah Kurir on cart page
    And User click Batal on ubah kurir popup page
    When User click "Hapus" on cart page
    And User click "OK" on hapus cart on popup page
    Then User verify the Item is removed from cart
