package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "expense_scheduler")
@Getter
@Setter
@NoArgsConstructor
public class ExpenseScheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_scheduler_id_seq")
    @SequenceGenerator(name = "expense_scheduler_id_seq", sequenceName = "expense_scheduler_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id", referencedColumnName = "id")
    private Expense expense;

    @Column(name = "type")
    private String type;
}
