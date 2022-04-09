package spring.exam.data.springdata.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@Setter
@Getter
@ToString(of = {"id", "city"})
@EqualsAndHashCode(of = {"city"})
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String city;

    @OneToOne(mappedBy = "address")
    Seller seller;

    public Address(String city) {
        this.city = city;
    }
}
