package domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_gen")
    private Long id;
}
