SET @acc_id = (select IFNULL(max(id), 0)+1 from account);

insert into account(id, created_time, email, password, refresh_token_jti, salt) values
(@acc_id, now(),	'admin@mail.com', 	'$2a$10$rtp6QcOQBjsFcPZa9rMH0uuNNPC4nCXJb.Qkr0/pAQZZ1lxd4Vw5u',	'e3cccf97-43d8-4364-be2f-a825be3af467',	'Jx7keFRdis4STUcJQxf4aBD7TlkbRsgtp0DfGnhHZTE=');

SET @u_profile = (select IFNULL(max(id), 0)+1 from user_profile);

insert into user_profile(id, email, account_id) values
(@u_profile, 'admin@mail.com', @acc_id);

insert into account_role(account_id, role_id) values
(@acc_id, 1),
(@acc_id, 2),
(@acc_id, 3),
(@acc_id, 4);
