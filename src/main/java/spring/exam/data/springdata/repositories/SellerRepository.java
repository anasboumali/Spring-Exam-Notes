package spring.exam.data.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.exam.data.springdata.entities.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
