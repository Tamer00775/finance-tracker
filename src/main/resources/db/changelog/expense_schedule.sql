create table expense_scheduler(
                                  id bigint primary key default nextval('expense_scheduler_id_seq'),
                                  expense_id bigint references expense(id),
                                  type varchar
);
