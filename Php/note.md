# Note

## Codeigniter (v2)

### Transaction

- trans_start, trans_begin

```php
// 트랜잭션 시작
$this->{model}->{db}->trans_start();
// 트랜잭션 종료
..->trans_complete();
```

- trans_start : 오토커밋X. trans_complete 미선언시 커밋안됨. commit, rollback 사용 지양.

```php
// 트랜잭션 시작
$this->{model}->{db}->trans_begin();
// 트랜잭션 종료
..->trans_rollback();
..->trans_commit();
```

- trans_begin : 오토커밋X. commit, rollback 사용.

