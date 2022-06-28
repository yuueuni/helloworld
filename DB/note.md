# note

> db tmi

- fk 컬럼도 null 이 가능하다.
- null 가능한 컬럼의 경우, 보통 index 사용하지 않는다. (값이 있는 경우만 index 해야 효율적 ??..)

- (mysql) query 내 변수

```sql
select @no=member_no
from member
where name='test';

select phone_number
from member_phone
where member_no = @no
```

- `@{변수명}` 으로 쿼리내 변수 선언하여 사용 가능

## datetime vs timestamp

- datetime : timezone 변경시 영향X
  - 1000-01-01 00:00:00부터 9999-12-31 23:59:59까지 지원

- timestamp : timezone 변경시 영향O
  - 1970-01-01 00:00:01부터 2038-01-19 03:14:07까지 지원

- timestamp index가 더 빠르게 생성됨

## Replication Lag

- 복제 지연

- 마스터에서는 여러 개의 thread 가 동시다발적으로 write 를 수행하고 있는 반면, 슬레이브에 replay 하는 thread 는 1개 밖에 되지 않기 때문
