package com.aniket.ui;

import com.aniket.ui.pages.InventoryPage;
import com.aniket.ui.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseUiTest {

    @Test
    void validUserSeesProducts() {
        InventoryPage inventory = new LoginPage(driver).logInAs("standard_user", "secret_sauce");
        assertEquals("Products", inventory.titleText());
    }

    @Test
    void lockedOutUserSeesError() {
        LoginPage login = new LoginPage(driver);
        login.logInAs("locked_out_user", "secret_sauce");
        assertTrue(login.errorText().contains("locked out"));
    }

    @Test
    void addingItemUpdatesCartCount() {
        InventoryPage inventory = new LoginPage(driver)
            .logInAs("standard_user", "secret_sauce")
            .addFirstItemToCart();
        assertEquals("1", inventory.cartCount());
    }
}