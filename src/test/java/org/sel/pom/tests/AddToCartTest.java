package org.sel.pom.tests;

import org.sel.pom.base.BaseTest;
import org.sel.pom.dataproviders.MyDataProvider;
import org.sel.pom.objects.Product;
import org.sel.pom.pages.CartPage;
import org.sel.pom.pages.HomePage;
import org.sel.pom.pages.StorePage;
import org.sel.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().
                getProductThumbnail().
                clickAddToCartBtn(product.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName() +"ddwdw");
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeatureProducts(Product product){
        CartPage cartPage = new HomePage(getDriver()).
                load().
                getProductThumbnail().
                clickAddToCartBtn(product.getName()).
    clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

}
