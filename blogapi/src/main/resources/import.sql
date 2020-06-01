--this script initiates db for h2 db (used in test profile)
insert into user (id, account_status, email, first_name, last_name) values (1, 'CONFIRMED', 'john@domain.com', 'John', 'Steward')
insert into user (id, account_status, email, first_name) values (2, 'NEW', 'brian@domain.com', 'Brian')
insert into user (id, account_status, email, first_name) values (3, 'REMOVED', 'ala@domain.com', 'Ala')
insert into user (id, account_status, email, first_name) values (4, 'CONFIRMED', 'ken@domain.com', 'Ken')
insert into user (id, account_status, email, first_name) values (5, 'CONFIRMED', 'abi@domain.com', 'Abi')

insert into blog_post (id, entry, user_id) values (1, 'blog post test', 1)
insert into blog_post (id, entry, user_id) values (2, 'blog post test', 1)

insert into like_post (id, post_id, user_id) values (1, 2, 5)
