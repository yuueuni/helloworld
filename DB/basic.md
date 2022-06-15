# basic

## DDL

- Data Definition Language
  - ex) CREATE, ALTER, DROP, RENAME, TRUNCATE

- add index

```sql
alter table {table name} add index {index name} ({colume name});
create index {index name} on {index name} ({colume name});
```
> [create index](https://dev.mysql.com/doc/refman/8.0/en/create-index.html) -> 인덱스 명이 필수 파라미터
>
> [alter table ~ add index](https://dev.mysql.com/doc/refman/8.0/en/alter-table.html) -> 컬럼명 필수, 인덱스명 선택 파라미터

## DML

- Data Manipulation Language
  - ex) SELECT, INSERT, UPDATE, DELETE

## DCL

- Data Control Language
  - ex) GRANT, REVOKE

## TCL

- Transacton Control Language
  - ex) COMMIT, ROLLBACK, SAVEPOINT

## MYSQL

- ONLINE DDL
  - [v5.6 MYSQL (Online DDL)](https://dev.mysql.com/doc/refman/5.6/en/innodb-online-ddl-operations.html)

