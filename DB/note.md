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