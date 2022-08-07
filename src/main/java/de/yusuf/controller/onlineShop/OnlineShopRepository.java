package de.yusuf.controller.onlineShop;

import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OnlineShopRepository  {

  private static final String LOCALHOSTPATH = "src/main/webapp/resources/database/products.xml";
  private static String HOSTPATH = System.getProperty("user.dir") + "/webapps/ROOT/resources/database/products.xml";

  private static final File DATABASE_LOCATION = new File( HOSTPATH );

  private Products products = new Products();
  private final Marshaller marshaller;
  private final Unmarshaller unmarshaller;

  public OnlineShopRepository() {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance( Products.class );
      marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
      unmarshaller = jaxbContext.createUnmarshaller();
    }
    catch ( JAXBException e ) {
      throw new IllegalStateException( e );
    }
  }

  public List<Product> findAll() {
    loadProducts();
    return products.product;
  }

  private void persistProducts() {
    try {
      marshaller.marshal( products, DATABASE_LOCATION );
    }
    catch ( JAXBException e ) {
      e.printStackTrace();
    }
  }

  private void loadProducts() {
    try {
      products = (Products) unmarshaller.unmarshal( DATABASE_LOCATION );
    }
    catch ( JAXBException e ) {
      e.printStackTrace();
    }
  }
}

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
class Products {
  List<Product> product = new ArrayList<>();
}
