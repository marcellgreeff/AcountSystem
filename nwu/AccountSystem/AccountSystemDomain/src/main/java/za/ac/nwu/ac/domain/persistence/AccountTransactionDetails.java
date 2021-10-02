package za.ac.nwu.ac.domain.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT_TX_DETAILS", schema = "MARCELL")
public class AccountTransactionDetails implements Serializable {

    private static final long serialVersionUID = -31119343363775125L;
    Long accountTransactionDetailsId;
    AccountTransaction accountTransaction;
    String partnerName;
    Long numberOfItems;

    @Id
    @SequenceGenerator(name = "ACCOUNT_DETAILS_SEQ", sequenceName = "MARCELL.ACCOUNT_DETAILS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_DETAILS_SEQ")
    @Column(name = "ACCOUNT_TX_DETAILS_ID")

    public Long getAccountTransactionDetailsId(){
        return accountTransactionDetailsId;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TX_ID")
    public AccountTransaction getAccountTransaction(){
        return accountTransaction;
    }

    @Column(name = "PARTNER_NAME")
    public String getPartnerName(){
        return partnerName;
    }

    @Column(name = "NUMBER_OF_ITEMS")
    public Long getNumberOfItems(){
        return numberOfItems;
    }

    public AccountTransactionDetails(){

    }

    public AccountTransactionDetails(AccountTransaction accountTransaction, String partnerName, Long numberOfItems){
        this.accountTransaction = accountTransaction;
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;
    }

    public AccountTransactionDetails(String partnerName, Long numberOfItems){
        this.partnerName = partnerName;
        this.numberOfItems = numberOfItems;
    }

    public void setAccountTransaction(AccountTransaction accountTransaction){
        this.accountTransaction = accountTransaction;
    }

    public void setAccountTransactionDetailsId(Long accountTransactionDetailsId)
    {
        this.accountTransactionDetailsId = accountTransactionDetailsId;
    }

    public void setNumberOfItems(Long numberOfItems){
        this.numberOfItems = numberOfItems;
    }


    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransactionDetails that = (AccountTransactionDetails) o;
        return Objects.equals(accountTransactionDetailsId, that.accountTransactionDetailsId) && Objects.equals(accountTransaction, that.accountTransaction) && Objects.equals(partnerName, that.partnerName) && Objects.equals(numberOfItems, that.numberOfItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountTransactionDetailsId, accountTransaction, partnerName, numberOfItems);
    }

    @Override
    public String toString() {
        return "AccountTransactionDetails{" +
                "accountTransactionDetailsId=" + accountTransactionDetailsId +
                ", accountTransaction=" + accountTransaction +
                ", partnerName='" + partnerName + '\'' +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
