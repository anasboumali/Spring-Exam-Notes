package spring.exam.data.springdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import spring.exam.data.springdata.config.OnlineStoreConfig;
import spring.exam.data.springdata.entities.Address;
import spring.exam.data.springdata.entities.Product;
import spring.exam.data.springdata.entities.ProductState;
import spring.exam.data.springdata.entities.Seller;
import spring.exam.data.springdata.repositories.AddressRepository;
import spring.exam.data.springdata.repositories.ProductRepository;
import spring.exam.data.springdata.repositories.SellerRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = OnlineStoreConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OnlineStoreTest {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @BeforeAll
    void setup() {
        Seller sellerToSave = new Seller(
                "Anas",
                List.of(new Product("Nike", ProductState.USED), new Product("Adidas", ProductState.NEW), new Product("Puma", ProductState.USED)),
                new Address("Bordeaux"));


        sellerRepository.save(sellerToSave);
    }

    @Test
    void testFindSellerById() {
        Optional<Seller> foundSeller = sellerRepository.findById(1L);
        assertThat(foundSeller).isPresent();
        assertThat(foundSeller.get().getName()).isEqualTo("Anas");
        assertThat(foundSeller.get().getAddress().getCity()).isEqualToIgnoringCase("Bordeaux");
        assertThat(foundSeller.get().getProducts()).hasSize(3);
        assertThat(foundSeller.get().getProducts().get(0).getTitle()).isEqualTo("Nike");
    }

    @Transactional
    @Test
    void testRemoveSeller() {
        //test
        sellerRepository.deleteById(1L);

        //verify
        Optional<Seller> foundSeller = sellerRepository.findById(1L);
        Optional<Address> address = addressRepository.findByCity("Bordeaux");
        Optional<Product> product = productRepository.findByTitle("Nike");

        assertThat(foundSeller).isNotPresent();
        assertThat(address).isNotPresent();//true , thanks to  CascadeType.REMOVE on Seller Entity
        assertThat(product).isNotPresent();//true , thanks to  CascadeType.ALL on Seller Entity
    }

    @Transactional
    @Test
    void testUpdateSeller() {

        Seller newSeller = new Seller();
        Address address = new Address();
        address.setId(1L);
        address.setCity("Paris");

        newSeller.setId(1L);
        newSeller.setName("Hamid");
        newSeller.setAddress(address);

        Seller updatedSeller = sellerRepository.save(newSeller);

        assertThat(updatedSeller).isNotNull();
        assertThat(updatedSeller).isEqualTo(newSeller);
        assertThat(updatedSeller.getAddress().getCity()).isEqualTo("Paris");//true , thanks to  CascadeType.MERGE on Seller Entity
    }

    @Test
    void testProductsPagination() {

        Page<Product> products = productRepository.findAll(PageRequest.of(0, 2, Sort.by("title").descending()));

        assertThat(products.getTotalElements()).isEqualTo(3);
        assertThat(products.getTotalPages()).isEqualTo(2);
        assertThat(products.getNumberOfElements()).isEqualTo(2);
        assertThat(products.getContent().get(0).getTitle()).isEqualTo("Puma");
    }

    @Test
    void testGetUsedProducts() {
        assertTrue(productRepository.getUsedProducts().stream().allMatch(product -> product.getState().equals(ProductState.USED)));
    }

}
