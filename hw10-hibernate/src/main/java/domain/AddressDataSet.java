package domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@GenericGenerator(name="address_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
public class AddressDataSet extends DataSet {

    @Column
    private String city;

    @Column
    private String region;

    @Column
    private Integer apartment;

}
