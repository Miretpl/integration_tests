--this script initiates db for h2 db (used in test profile)
insert into user (id, account_status, email, first_name, last_name) values (1, 'CONFIRMED', 'john@domain.com', 'John', 'Steward')
insert into user (id, account_status, email, first_name) values (2, 'NEW', 'brian@domain.com', 'Brian')
insert into user (id, account_status, email, first_name) values (3, 'REMOVED', 'ala@domain.com', 'Ala')
