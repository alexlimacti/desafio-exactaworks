create table list_tags (id int8 not null, tags varchar(255));
create table tb_expenses (id  bigserial not null, amount numeric(19, 2), description varchar(255), hour_time timestamp, people_name varchar(255), primary key (id));
alter table list_tags add constraint FKamf6qptwvrsjep9cmga4oww3g foreign key (id) references tb_expenses;
