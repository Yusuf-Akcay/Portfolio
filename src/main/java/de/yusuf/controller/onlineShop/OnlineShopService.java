package de.yusuf.controller.onlineShop;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;

@Service
public record OnlineShopService( OnlineShopRepository onlineShopRepository, HashSet<Product> cart) {

  public List<Product> findAll() {
    return onlineShopRepository.findAll();
  }

  public void addToCart( Product product){
    cart.add( product );
    product.setQuantity( product.getQuantity() + 1 );
  }

  public void deleteFromCart( Product product ) {
      cart.remove( product );
      product.setQuantity( 0 );
  }

  public double totalProductPrice( Product product ) {
    return BigDecimal.valueOf( product.getQuantity() )
        .multiply( BigDecimal.valueOf( product.getProductPrice() ) )
        .setScale( 2, RoundingMode.HALF_UP )
        .doubleValue();
  }

  public double totalPrice() {
    return cart().stream().mapToDouble( this::totalProductPrice ).sum();
  }
}
