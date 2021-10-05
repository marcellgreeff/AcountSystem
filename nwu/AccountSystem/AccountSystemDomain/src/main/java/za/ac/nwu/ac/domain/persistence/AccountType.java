package za.ac.nwu.ac.domain.persistence;

import com.sun.javafx.beans.IDProperty;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TYPE")
public class AccountType implements Serializable {

    private static final long serialVersionUID = -5523354245483977469L;
    @Id
    @SequenceGenerator(name = "ACCOUNT_TYPE_SEQ", sequenceName = "MARCELL.ACCOUNT_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_TYPE_SEQ")

    @Column(name = "ACCOUNT_TYPE_ID")
    private Long accountTypeId;

    @Column(name = "MNEMONIC")
    private String mnemonic;

    @Column(name = "ACCOUNT_NAME")
    private String accountTypeName;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @Column(name = "MILES")
    private Long miles;

    @OneToMany(targetEntity = AccountTransaction.class, fetch = FetchType.LAZY, mappedBy = "accountType", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<AccountTransaction> accountTransactions;
    public AccountType(){

    }

    public Set<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(Set<AccountTransaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }

    public AccountType(Long accountTypeId, String mnemonic, String accountTypeName, LocalDate creationDate, Long miles) {
        this.accountTypeId = accountTypeId;
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.miles = miles;
    }

    public AccountType(String mnemonic, String accountTypeName, LocalDate creationDate, Long miles)
    {
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.miles = miles;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getMiles(){return miles;}
    public void setMiles(Long miles){this.miles = miles;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return Objects.equals(accountTypeId, that.accountTypeId) && Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(creationDate, that.creationDate) && Objects.equals(miles, that.miles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTypeId, mnemonic, accountTypeName, creationDate, miles);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "accountTypeId=" + accountTypeId +
                ", mnemonic='" + mnemonic + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", creationDate=" + creationDate +
                ", miles=" + miles +
                '}';
    }
}
