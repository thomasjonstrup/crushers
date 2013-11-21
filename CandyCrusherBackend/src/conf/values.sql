delete from customers;
delete from banktellers;
delete from groups;
delete from transactions;
delete from accounts;

--------



--Customers--

insert into customers (customer_id, first_name, last_name, email, password, customer_role)
values (1,'Pia', 'Holm', 'ph@cph.dk', 'q', 1);

insert into customers (customer_id, first_name, last_name, email, password, customer_role)
values (2,'Mogens', 'Holm', 'mh@cph.dk', 'q', 1);

insert into customers (customer_id, first_name, last_name, email, password, customer_role)
values (3,'Lasse', 'Holm', 'lh@cph.dk','q', 1);

--Banktellers--

insert into banktellers (bankteller_id, first_name, last_name, email, password, bankteller_role)
values (1,'Hans', 'Larsen', 'hl@cph.dk', 'q', 2);

insert into banktellers (bankteller_id, first_name, last_name, email, password, bankteller_role)
values (2,'Mads', 'Larsen', 'mh@cph.dk', 'q', 2);

insert into banktellers (bankteller_id, first_name, last_name, email, password, bankteller_role)
values (3,'Thomas', 'Larsen', 'tl@cph.dk','q', 2);


--Groups----

insert into groups (group_name, group_desc)
values ('Customers', 'Bank customers');

insert into groups (group_name, group_desc)
values ('Banktellers', 'Banktellers');

insert into customer_group(email, group_name)
values ('ph@cph.dk', 'Customers');

--Accounts-----

insert into accounts(account_id, account_type, balance, owner)
values (1, 'Savings', 100, 1);

insert into accounts(account_id, account_type, balance, owner)
values (2, 'Pension', 500, 2);

insert into accounts(account_id, account_type, balance, owner)
values (3, 'MULLE', 700000, 3);

--Transactions----

insert into transactions(transaction_id, transaction_date, ammount, balance, info, message, source_account_id, target_account_id)
values (1, '2013-11-18', 100, 10000, 'money','overførsel', 1, 2);