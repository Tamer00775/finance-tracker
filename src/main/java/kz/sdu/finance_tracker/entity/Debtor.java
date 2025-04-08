package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "debtor")
@Getter
@Setter
@ToString
public class Debtor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debtor_id_seq")
    @SequenceGenerator(name = "debtor_id_seq", sequenceName = "debtor_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "sum", nullable = false)
    private Double sum;

    @Column(name = "is_payed", nullable = false)
    private Boolean isPayed = false;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Column(name = "comment")
    private String comment;

}

