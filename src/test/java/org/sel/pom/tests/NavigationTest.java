package org.sel.pom.tests;

import org.sel.pom.base.BaseTest;
import org.sel.pom.pages.HomePage;
import org.sel.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateToStoreUsingMainMenu(){
        StorePage storePage = new HomePage(getDriver()).
                load().
                getMyHeader().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(),"Store");

    }
}
