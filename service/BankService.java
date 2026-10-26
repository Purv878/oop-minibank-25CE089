package service;

import model.Account;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class BankService {

    public void displayAccount(Account account) {
        System.out.println(account);
        System.out.println("Interest Rate: " + account.interestRate() + "%");
        System.out.println("Yearly Interest: " + account.yearlyInterest());
    }

    public boolean performWithdrawal(Account account, long amount)
            throws InsufficientFundsException, InvalidAmountException {

        return account.withdraw(amount);
    }
}