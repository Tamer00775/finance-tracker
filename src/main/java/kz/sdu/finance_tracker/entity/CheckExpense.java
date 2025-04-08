package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "check_expense")
@Getter
@Setter
@ToString
public class CheckExpense {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_id_seq")
    @SequenceGenerator(name = "check_id_seq", sequenceName = "check_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "check_uuid")
    private String checkUuid;

    @Column(name = "created_date")
    private Instant createdDate;
    @Column(name = "created_by")
    private String created_by;

    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

}
