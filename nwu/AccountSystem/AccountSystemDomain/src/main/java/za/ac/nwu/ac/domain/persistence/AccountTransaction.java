package za.ac.nwu.ac.domain.persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TRANSACTION", schema = "MARCELL")
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = -2794706207079707709L;
    @Id
    @SequenceGenerator(name = "ACCOUNT_TX_SEQ", sequenceName = "MARCELL.ACCOUNT_TX_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_TX_SEQ")

    private Long transactionId;
    private AccountType accountType;
    private Long memberId;
    private Long amount;
    private LocalDate transactionDate;
    private AccountTransactionDetails details;

    public AccountTransaction(){
    }

    public AccountTransaction(Long transactionId, AccountType accountType, Long memberId, Long amount, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
    public AccountTransaction(AccountType accountType, Long memberId, Long amount, LocalDate transactionDate) {
        this.accountType = accountType;
        this.memberId = memberId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    @Column(name = "TX_ID")
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Column(name = "MEMBER_ID")
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "AMOUNT")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Column(name = "TX_DATE")
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    @OneToOne(targetEntity = AccountTransactionDetails.class, fetch = FetchType.LAZY, mappedBy = "accountTransaction", orphanRemoval = true, cascade = CascadeType.PERSIST)
    public AccountTransactionDetails getDetails(){
        return getDetails();
    }
    public void setDetails(AccountTransactionDetails details){ this.details = details; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(accountType, that.accountType) && Objects.equals(memberId, that.memberId) && Objects.equals(amount, that.amount) && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountType, memberId, amount, transactionDate);
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId=" + transactionId +
                ", accountType=" + accountType +
                ", memberId=" + memberId +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
