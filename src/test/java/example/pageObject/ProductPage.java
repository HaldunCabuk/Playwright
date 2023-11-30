package example.pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.List;

public class ProductPage{

    Page page;
    Locator sortProds;
    List<String> prodNames;

    public ProductPage(Page page) {
        sortProds = page.locator(".product_sort_container");

        this.page = page;
    }

    public void sortZtoa() {
        sortProds.selectOption("za");
    }

    public void assertZtoA(){
        prodNames = page.locator(".inventory_item_name").allTextContents();
        boolean sirali = true;

        for (int i = 0; i < prodNames.size()-1 ; i++) {
            if (prodNames.get(i).compareTo(prodNames.get(i+1))>0){
                sirali = true;
                break;
            }
        }

        if (sirali){
            System.out.println("Urunler z den a ya sirali");
        }else
            System.out.println("urunler z den a ya sirali degil");

    }

}
