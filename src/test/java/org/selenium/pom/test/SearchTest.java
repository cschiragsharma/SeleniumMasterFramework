package org.selenium.pom.test;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void SearchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage= new StorePage(getDriver()).load().
        search(searchFor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");

    }

    @Test
    public void SearchWithExactMatch(){

    }

    @Test
    public void SearchNonExistingProduct(){

    }
}
