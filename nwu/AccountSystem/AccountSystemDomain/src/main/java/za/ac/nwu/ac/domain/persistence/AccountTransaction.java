package za.ac.nwu.ac.domain.persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TRANSACTION")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = -2794706207079707709L;
    @Id
    @SequenceGenerator(name = "ACCOUNT_TRANSACTION_SEQ", sequenceName = "MARCELL.ACCOUNT_TRANSACTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_TRANSACTION_SEQ")

    @Column(name = "TX_ID")
    private Long transactionId;

   // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private Long accountTypeId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "TX_DATE")
    private LocalDate transactionDate;

    public AccountTransaction(){
    }

    public AccountTransaction(Long transactionId, AccountType accountType, Long amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountTypeId = accountType.getAccountTypeId();
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    public AccountTransaction(AccountType accountType, Long amount, LocalDate transactionDate) {
        this.accountTypeId = accountType.getAccountTypeId();
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(AccountType accountTypeId) {
        this.accountTypeId = accountTypeId.getAccountTypeId();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountTypeId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountType=" + accountTypeId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
