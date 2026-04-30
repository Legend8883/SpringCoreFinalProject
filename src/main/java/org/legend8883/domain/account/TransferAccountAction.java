package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.legend8883.model.AccountStorage;
import org.legend8883.properties.AccountProperties;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class TransferAccountAction implements CommandAction {
    private final Scanner scanner;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;
    private final AccountStorage accountStorage;
    private final AccountProperties accountProperties;

    public TransferAccountAction(
            GeneralUtil generalUtil,
            AccountUtil accountUtil,
            AccountStorage accountStorage,
            AccountProperties accountProperties
    ) {
        this.accountStorage = accountStorage;
        this.accountProperties = accountProperties;
        scanner = new Scanner(System.in);
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
    }

    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        System.out.println("Enter sender account id: ");
        String accountSenderIdStr = scanner.nextLine();
        Account accountSender = accountUtil.getAccountByIdWithValidation(accountSenderIdStr);

        System.out.println("Enter recipient account id: ");
        String accountRecipientIdStr = scanner.nextLine();
        Account accountRecipient = accountUtil.getAccountByIdWithValidation(accountRecipientIdStr);

        checkIsSameAccount(accountSender, accountRecipient);

        System.out.println("Enter transfer amount: ");
        int transferAmount = accountUtil.getValidMoneyAmount(scanner.nextLine());

        accountUtil.checkIsInAccountEnoughMoney(accountSender.getMoneyAmount(), transferAmount);

        double commission = getCommission(accountSender, accountRecipient);
        double transferAmountAfterCommission = transferAmount * (1 - commission);
        double commissionAmount = transferAmount - transferAmountAfterCommission;

        double senderMoneyAfterTransfer = accountSender.getMoneyAmount() - transferAmount;
        double recipientMoneyAfterTransfer = accountRecipient.getMoneyAmount() + transferAmountAfterCommission;

        accountSender.setMoneyAmount(senderMoneyAfterTransfer);
        accountRecipient.setMoneyAmount(recipientMoneyAfterTransfer);

        System.out.printf(
                "Transfer completed from account %s to account %s.%n" +
                        "Transfer amount: %s, commission: %s, recipient received: %s",
                accountSender.getId(), accountRecipient.getId(),
                transferAmount, commissionAmount, recipientMoneyAfterTransfer
        );
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_TRANSFER;
    }

    private void checkIsSameAccount(Account accountSender, Account accountRecipient) {
        if (accountSender.getId().equals(accountRecipient.getId())) {
            throw new IllegalArgumentException("You can't transfer to same account.");
        }
    }

    private double getCommission(Account accountSender, Account accountRecipient) {
        if (!accountSender.getUserId().equals(accountRecipient.getUserId())) {
            return accountProperties.getTransferCommission();
        }

        return 0;
    }
}
