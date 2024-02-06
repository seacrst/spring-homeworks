package seacrest.bank_app.entities;
import seacrest.bank_app.common.Currency;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@RequiredArgsConstructor
@Entity
public class AccountEntity extends AbstractEntity {
    @Column
    public final String number;
    @Column
    public final Currency currency;
    @Column
    public Double balance;
    @Column
    public final Long ownerId;
}
