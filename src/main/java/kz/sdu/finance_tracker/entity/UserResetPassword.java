package kz.sdu.finance_tracker.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_reset_password")
@Getter
@Setter
public class UserResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_id_seq")
    @SequenceGenerator(name = "user_reset_password_id_seq", sequenceName = "user_reset_password_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "temporary_password")
    private String temporaryPassword;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "resetted")
    private boolean resetted = false;
}
