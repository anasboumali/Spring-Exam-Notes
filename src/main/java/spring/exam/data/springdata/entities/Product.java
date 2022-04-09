package spring.exam.data.springdata.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = {"title"})
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;

    @Column
    String title;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    Seller seller;

    @Enumerated(EnumType.STRING)
    ProductState state;

    public Product(String title, ProductState state) {
        this.title = title;
        this.state = state;
    }
}
