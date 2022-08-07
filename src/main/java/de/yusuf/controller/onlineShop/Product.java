package de.yusuf.controller.onlineShop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class Product {
  private String productName;
  private String productDescription;
  private float productPrice;
  private boolean availability;
  private Category category;
  private String picPath;
  private int quantity;

  public enum Category {
    BOOK("Buch"), CLOTHING("Kleidung"), TECHNIC("Technik");
    private final String label;
    Category( String label ) {
      this.label = label;
    }
    public String getLabel() { return label; }
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName( String productName ) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription( String productDescription ) {
    this.productDescription = productDescription;
  }

  public float getProductPrice() {
    return productPrice;
  }

  public void setProductPrice( float productPrice ) {
    this.productPrice = productPrice;
  }

  public boolean isAvailability() {
    return availability;
  }

  public void setAvailability( boolean availability ) {
    this.availability = availability;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory( Category category ) {
    this.category = category;
  }

  public String getPicPath() {
    return picPath;
  }

  public void setPicPath( String picPath ) {
    this.picPath = picPath;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity( int quantity ) {
    this.quantity = quantity;
  }
}
