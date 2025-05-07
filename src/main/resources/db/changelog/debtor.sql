drop table debtor;

create table debtor(
    id bigint primary key default nextval('debtor_id_seq'),
    user_id bigint references _user(id),
    debtor_full_name varchar(100),
    sum double precision not null default 0,
    expired_date date,
    notes varchar
);