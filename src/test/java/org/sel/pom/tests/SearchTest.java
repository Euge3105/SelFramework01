package org.sel.pom.tests;

import org.sel.pom.base.BaseTest;
import org.sel.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void SearchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver()).
                load().
        search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+ searchFor + "”");
    }
}
