package spring.exam.data.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.exam.data.springdata.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);

    @Query("SELECT p FROM Product p WHERE p.state='USED'")
    List<Product> getUsedProducts();
}
