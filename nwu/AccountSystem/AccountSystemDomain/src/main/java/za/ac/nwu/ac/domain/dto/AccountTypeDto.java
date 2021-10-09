package za.ac.nwu.ac.domain.dto;

import za.ac.nwu.ac.domain.persistence.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@ApiModel(value = "AccountType",
description = "A DTO model that represents the AccountType")
public class AccountTypeDto implements Serializable {

    private static final long serialVersionUID = 2538138663972118712L;
    private String mnemonic;
    private String accountTypeName;
    private LocalDate creationDate;
    private Long miles;
    public AccountTypeDto(){}

    public AccountTypeDto(String mnemonic, String accountTypeName, LocalDate creationDate, Long miles){
        this.mnemonic = mnemonic;
        this.accountTypeName = accountTypeName;
        this.creationDate = creationDate;
        this.miles = miles;
    }

    public AccountTypeDto(AccountType accountType){
        this.setAccountTypeName(accountType.getAccountTypeName());
        this.setCreationDate(accountType.getCreationDate());
        this.setMnemonic(accountType.getMnemonic());
        this.setMiles(accountType.getMiles());
    }

    @ApiModelProperty(position = 1,
            value = "AccountType Mnemonic",
            name = "Mnemonic",
            notes = "Uniquely identifies the account type",
            dataType = "java.lang.String",
            required = true)
    public String getMnemonic() {return mnemonic;}
    public void setMnemonic(String mnemonic) {this.mnemonic = mnemonic;}

    @ApiModelProperty(position = 2,
            value = "AccountType Name",
            name = "Name",
            notes = "The name of the AccountType",
            dataType = "java.lang.String",
            allowEmptyValue = false,
            required = true)
    public String getAccountTypeName() {return accountTypeName;}
    public void setAccountTypeName(String accountTypeName) {this.accountTypeName = accountTypeName;}

    @ApiModelProperty(position = 3,
            value = "AccountType Creation Date",
            name = "CreationDate",
            notes = "This is the date on which the AccountType was created",
            dataType = "java.lang.LocalDate",
            allowEmptyValue = false,
            required = true)
    public LocalDate getCreationDate() {return creationDate;}
    public void setCreationDate(LocalDate creationDate) {this.creationDate = creationDate;}

    @ApiModelProperty(position = 4,
            value = "AccountType Miles",
            name = "Miles",
            notes = "This is the amount of Miles allocated to account",
            dataType = "java.lang.Long",
            allowEmptyValue = false,
            required = true)
    public Long getMiles(){return miles;}
    public void setMiles(Long miles){this.miles = miles;}

    @JsonIgnore
    public AccountType getAccountType(){
        return new AccountType(getMnemonic(), getAccountTypeName(), getCreationDate(), getMiles());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTypeDto that = (AccountTypeDto) o;
        return Objects.equals(mnemonic, that.mnemonic) && Objects.equals(accountTypeName, that.accountTypeName) && Objects.equals(creationDate, that.creationDate) && Objects.equals(miles, that.miles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mnemonic, accountTypeName, creationDate, miles);
    }

    @Override
    public String toString() {
        return "AccountTypeDto{" +
                "mnemonic='" + mnemonic + '\'' +
                ", accountTypeName='" + accountTypeName + '\'' +
                ", creationDate=" + creationDate +
                ", miles=" + miles +
                '}';
    }
}
