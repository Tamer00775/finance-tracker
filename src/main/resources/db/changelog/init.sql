create sequence expense_id_seq
    start with 1;

create sequence category_id_seq
    start with 1;

create sequence user_id_seq
    start with 1;

create sequence debtor_id_seq
    start with 1;

create sequence budget_planning_id_seq
    start with 1;

create sequence check_id_seq
    start with 1;

create table _user
(
    id        bigint default nextval('user_id_seq'::regclass) not null
        primary key,
    full_name varchar,
    email     varchar(50)
        unique,
    password  varchar,
    sum       double precision
);

create table category
(
    id   bigint default nextval('category_id_seq'::regclass) not null
        primary key,
    ru   varchar,
    kk   varchar,
    code varchar
);

create table debtor
(
    id           bigint  default nextval('debtor_id_seq'::regclass) not null
        primary key,
    full_name    varchar(100),
    sum          double precision,
    is_payed     boolean default false,
    category_id  bigint                                             not null
        references category,
    expired_date timestamp,
    comment      text,
    user_id      bigint
        references _user
);

create table expense
(
    id               bigint default nextval('expense_id_seq'::regclass) not null
        primary key,
    created_date     timestamp,
    created_by       varchar,
    operation_type   varchar                                            not null,
    sum              double precision                                   not null,
    category_id      bigint
        references category,
    transaction_type varchar,
    user_id          bigint
        references _user
);

create table check_expense
(
    id           bigint    default nextval('check_id_seq'::regclass) not null
        primary key,
    expense_id   bigint
        references expense,
    check_uuid   varchar,
    created_date timestamp default now(),
    created_by   varchar   default 'system'::character varying,
    title        varchar(50),
    user_id      bigint
        references _user
);

create table budget_planning
(
    id              bigint default nextval('budget_planning_id_seq'::regclass) not null
        primary key,
    user_id         bigint                                                     not null
        references _user,
    name_ru         varchar(255),
    plan_sum        bigint,
    expiration_date timestamp,
    source          varchar,
    current_sum     bigint,
    percentage      double precision
);