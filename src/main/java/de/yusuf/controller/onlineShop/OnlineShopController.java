package de.yusuf.controller.onlineShop;

import de.yusuf.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller ("onlineShopController")
@SessionScope
public class OnlineShopController extends AbstractController  {

  private final OnlineShopService onlineShopService;
  private List<Product> allProducts;
  private List<Product> showProducts;

  public OnlineShopController( OnlineShopService onlineShopService ) {
    this.onlineShopService = onlineShopService;
    allProducts = onlineShopService.findAll();
  }

  @Override public void init() {
    onlineShopService.cart().clear();
    notFilter();
  }

  public void notFilter() { showProducts = allProducts; }

  public void clothingFilter() { showProducts = allProducts.stream().filter( prod -> prod.getCategory() == Product.Category.CLOTHING ).toList(); }

  public void technicFilter() { showProducts = allProducts.stream().filter( prod -> prod.getCategory() == Product.Category.TECHNIC ).toList(); }

  public void bookFilter() { showProducts = allProducts.stream().filter( prod -> prod.getCategory() == Product.Category.BOOK ).toList(); }

  public void addToCart( Product product){ onlineShopService.addToCart( product); }

  public void deleteFromCart( Product product ) { onlineShopService.deleteFromCart( product ); }

  public String productPriceAsString( Product product ) { return String.format("%,.02f", product.getProductPrice() ); }

  public String totalProductPrice( Product product ) { return String.format("%,.02f", onlineShopService.totalProductPrice( product )); }

  public String totalPrice() { return String.format("%,.2f", onlineShopService.totalPrice());}

  public OnlineShopService getOnlineShopService() {
    return onlineShopService;
  }

  public List<Product> getAllProducts() {
    return allProducts;
  }

  public void setAllProducts( List<Product> allProducts ) {
    this.allProducts = allProducts;
  }

  public List<Product> getShowProducts() {
    return showProducts;
  }

  public void setShowProducts( List<Product> showProducts ) {
    this.showProducts = showProducts;
  }
}
