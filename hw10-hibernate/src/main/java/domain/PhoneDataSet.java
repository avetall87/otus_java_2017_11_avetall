package domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@Data
@SequenceGenerator(name = "common_gen", sequenceName = "phone_seq", allocationSize = 1)
public class PhoneDataSet extends DataSet {

    enum PhoneStatus {
        ACTUAL,
        HISTORY
    }

    @Column
    private String phoneNumber;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhoneStatus status;
}
