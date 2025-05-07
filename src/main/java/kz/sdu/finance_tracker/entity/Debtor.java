package kz.sdu.finance_tracker.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "debtor")
@Getter
@Setter
public class Debtor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debtor_id_seq")
    @SequenceGenerator(name = "debtor_id_seq", sequenceName = "debtor_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "debtor_full_name", length = 100)
    private String debtorFullName;

    @Column(nullable = false)
    private Double sum = 0.0;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "payed")
    private boolean payed;
}

