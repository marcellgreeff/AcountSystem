package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.persistence.AccountTypeRepository;
import za.ac.nwu.ac.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImpl implements AccountTypeTranslator {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeTranslatorImpl(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes(){
        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try {
            for(AccountType accountType : accountTypeRepository.findAll()){
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        }catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return accountTypeDtos;
    }


    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto){
        try{
            AccountType accountType = accountTypeRepository.save(accountTypeDto.getAccountType());
            return new AccountTypeDto(accountType);
        }
        catch (RuntimeException e){
            throw new RuntimeException("Unable to save to the DB");
        }
    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonicNativeQuery(String mnemonic) {
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonicNativeQuery(mnemonic);
            return new AccountTypeDto(accountType);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }

    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic) {
        try{
            AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch (Exception e){
            throw new RuntimeException("Unable to read from DB", e);
        }
    }

    @Override
    public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic)
    {
        try{
            return accountTypeRepository.getAccountTypeDtoByMnemonic(mnemonic);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }

    }

    @Override
    public AccountTypeDto save(AccountType accountType) {
        try {
            return new AccountTypeDto(accountTypeRepository.save(accountType));
        } catch (Exception e){
            throw new RuntimeException("Unable to save to the DB", e);
        }
    }

    @Override
    public AccountType getAccountTypeDbByMnemonic(String mnemonic) {
        try {
            AccountType accountType = accountTypeRepository.getAccountTypeDbByMnemonic(mnemonic);
            return accountType;
        } catch (Exception e) {
            throw new RuntimeException("Unable to read from the database",e);
        }
    }

    @Override
    public Integer update(String mnemonic, Long miles) {
        AccountType accountType = accountTypeRepository.getAccountTypeByMnemonic(mnemonic);
        Long addedMiles = accountType.getMiles() + miles;
        accountTypeRepository.update(mnemonic, addedMiles);
        return null;
    }

    @Override
    public void someMethod() {

    }

    @Override
    public AccountTypeDto delete(String mnemonic) {
        return null;
    }

    @Override
    public Integer deleteAccountTypeByMnemonic(String mnemonic) {
        accountTypeRepository.deleteAccountTypeByMnemonic(mnemonic);
       // accountTypeRepository.delete(accountType);
        return 1;

    }

}
