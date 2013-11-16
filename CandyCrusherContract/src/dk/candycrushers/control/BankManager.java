/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.candycrushers.control;

import dk.candycrushers.dto.AccountDetail;
import dk.candycrushers.dto.CustomerDetail;
import dk.candycrushers.dto.CustomerSummary;
import java.util.Collection;
import javax.ejb.Remote;

/**
 *
 * @author Thomas
 */
@Remote
public interface BankManager {

    String sayHello(String name);
    Collection<CustomerSummary> getCustomers();
    CustomerDetail getCustomer(long id);
    CustomerDetail addCustomer(String firstName, String lastName, String email, String password, int role);
    CustomerDetail updateCustomer(long customerID, String firstName, String lastName, String email);

    AccountDetail addAccount(String accountType, double balance, long customerID);
    AccountDetail getAccount(long id);
    AccountDetail transactionToAnOtherAccount(int fromAccountId, int toAccountId, double amount);
    AccountDetail getAccountTransactionToEachOther(long accountId);
    
    

}
