/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.candycrushers.control;

import dk.candycrushers.dto.CustomerDetail;
import dk.candycrushers.dto.CustomerSummary;
import dk.candycrushers.model.Customer;
import static dk.candycrushers.control.BankAssembler.*;
import dk.candycrushers.dto.AccountDetail;
import dk.candycrushers.model.Account;
import dk.candycrushers.model.Groups;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Thomas
 */
@Stateless
public class BankManagerBean implements BankManager {

    @PersistenceContext(unitName = "CandyCrusherBackendPU")
    private EntityManager em;

    @Override
    public String sayHello(String name) {
        return "Hello " + name + " from Remote Bank Manager Bean";
    }

    @Override
    public Collection<CustomerSummary> getCustomers() {
        Collection<Customer> customers = em.createNamedQuery("Customer.findAll").getResultList();
        System.err.println("#CUST = " + customers.size());
        return createCustomerSummaries(customers);
    }

    @Override
    public CustomerDetail getCustomer(long id) {
//        Query q = em.createNamedQuery("Customer.findByCustomerId");
//
//        q.setParameter("id", id);
//
//        Customer cus = (Customer) q.getSingleResult();
//
        Customer cus = em.find(Customer.class, (int)id);
        CustomerDetail cDetail = new CustomerDetail(cus.getCustomerId(), cus.getFirstName(), cus.getLastName(), cus.getEmail());

        return createCustomerDetail(cus);

    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public CustomerDetail addCustomer(String firstName, String lastName, String email, String password, int role) {
        Customer customer;
        customer = new Customer(firstName, lastName, email, password, role);
        Query query = em.createNamedQuery("Groups.findByGroupName");
        query.setParameter("groupName", "Customers");
        Groups group = (Groups) query.getSingleResult();
        em.persist(customer);
        return createCustomerDetail(customer);
    }

    @Override
    public AccountDetail addAccount(String accountType, double balance, long customerID) {
        Account acc;
        Integer i = (int) customerID;
        acc = new Account(accountType, balance);
        
        Customer cus = em.find(Customer.class, i);
        
        acc.setOwner(cus);
        
        Query query = em.createNamedQuery("Account.findByAccountId");
        em.persist(acc);
        
        return createAccountDetail(acc);
    }

    @Override
    public CustomerDetail updateCustomer(long customerID, String firstName, String lastName, String email, String password) {
        long id = customerID;
        Customer cust;
        if (id == 0) {
            cust = new Customer(0, firstName, lastName, email, password);
            em.persist(cust);
        } else {
            cust = em.find(Customer.class, (int) id);
            cust.setFirstName(firstName);
            cust.setLastName(lastName);
            cust.setEmail(email);
            cust.setPassword(password);
        }
        return createCustomerDetail(cust);
    }

    @Override
    public AccountDetail getAccount(long id) {
        Account acc = em.find(Account.class, (int)id);
        AccountDetail aDetail = new AccountDetail(acc.getAccountId(), acc.getAccountType(), acc.getBalance(), acc.getOwner().getFirstName());

        return createAccountDetail(acc);

    }

    @Override
    public AccountDetail transactionToAnOtherAccount(int fromAccountId, int toAccountId, double amount) {
        Account fromAcc = em.find(Account.class, fromAccountId);
        
        Account toAcc = em.find(Account.class, toAccountId);
        
        toAcc.setBalance(toAcc.getBalance() + amount);
        
        fromAcc.setBalance(fromAcc.getBalance() - amount);
        
        return createAccountDetail(fromAcc);
    }

    @Override
    public AccountDetail getAccountTransactionToEachOther(long accountId) {
//        DENNE METODE BRUGES IKKE I DET ORIGINALE PROJEKT
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
