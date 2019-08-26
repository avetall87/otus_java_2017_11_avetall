package domain;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@SequenceGenerator(name = "common_gen", sequenceName = "user_seq", allocationSize = 1)
public class UserDataSet extends DataSet {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String patronymic;

    @Column(nullable = false)
    private Integer age;

//    @OneToOne(mappedBy = "id")
//    private AddressDataSet addressDataSet;
//
//    @OneToMany(mappedBy = "id")
//    private List<PhoneDataSet> phones;
}
