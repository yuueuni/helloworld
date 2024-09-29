## 트랜잭션(Transaction) 이란

- 데이터베이스의 상태를 변환시키는 하나의 논리적인 작업 단위
- 트랜잭션 성질 (ACID)
    - 원자성(Atomicity, All or Nothing)
        - 트랜잭션의 모든 연산들은 정상적으로 수행 완료되거나 어떠한 연산도 수행되지 않은 상태를 보장해야함
    - 일관성(Consistency)
        - 트랜잭션 완료 후에도 데이터베이스가 일관된 상태로 유지되어야 함
    - 독립성(Isolation)
        - 트랜잭션 실행 중 변경된 데이터는 트랜잭션 완료시까지 다른 트랜잭션이 참조하지 못함
    - 지속성(Durability)
        - 처리 완료된 트랜잭션은 영원히 반영되야 함
- 트랜잭션 상태
    - 활동(Active)
        - 트랜잭션 실행 중 상태로 연산이 정상적으로 실행 중인 상태
    - 장애(Failed)
        - 트랜잭션 실행에 오류가 발생하여 중단된 상태
    - 철회(Aborted)
        - 트랜잭션이 비정상적으로 종료되어 Rollback 연산 수행한 상태
    - 부분 완료(Partially Committed)
        - 트랜잭션이 마지막 연산까지 실행했지만 Commit 연산 실행 직전의 상태
    - 완료(Committed)
        - 트랜잭션이 성공적으로 종료되어 Commit 연산 실행 후의 상태

### 트랜잭션 격리 수준(Transaction Isolation Level)

- Isolation Level
    - 트랜잭션에서 일관성이 없는 데이터를 허용하도록 하는 수준
- 필요성
    - 데이터베이스는 트랜잭션이 원자적이면서도 독립적인 수행을 하도록 함 (ACID 성질)
    - 무조건적인 locking 으로 동시에 수행되는 트랜잭션을 순서대로 처리한다면 디비 성능이 떨어지게 되므로 효율적인 Locking 방법이 필요
        - Locking: 트랜잭션이 DB를 다루는 동안 다른 트랜잭션이 관여하지 못하게 막는 것
        - https://ksh-coding.tistory.com/121#google_vignette
- Isolation level 조정은 동시성이 증가되는데 반해 데이터 무결성에 문제가 발생할 수 있고, 데이터의 무결성을 유지하는 데 반해 동시성이 떨어질 수 있다.
- 레벨이 높아질수록 비용이 높아진다.
- Read Uncommitted (레벨 0)
    - SELECT 문장이 수행되는 동안 해당 데이터에 Shared Lock이 걸리지 않는 Level
    - 트랜잭션에 처리중인 혹은 아직 커밋되지 않은 데이터를 다른 트랜잭션이 읽는 것을 허용
    - 데이터베이스의 일관성을 유지할 수 없다.
- Read Committed (레벨 1)
    - SELECT 문장이 수행되는 동안 해당 데이터에 Shared Lock이 걸리는 Level
    - 트랜잭션이 수행되는 동안 다른 트랜잭션이 접근할 수 없어 대기하게 됨
    - Commit이 이루어진 트랜잭션만 조회할 수 있다.
- Repeatable Read (레벨 2)
    - 트랜잭션이 완료될 때까지 SELECT 문장이 사용하는 모든 데이터에 Shared Lock이 걸리는 Level
    - 트랜잭션이 범위 내에서 조회한 데이터의 내용이 항상 동일함을 보장
- Serializable (레벨 3)
    - 트랜잭션이 완료될 때까지 SELECT 문장이 사용하는 모든 데이터에 Shared Lock이 걸리는 Level
    - 완벽한 읽기 일관성 모드를 제공
- DB Default Level
    - Mysql - REPEATABLE READ
    - Oracle은 READ_COMMIT 이 기본 설정이다.
    - SQL Server는 READ_COMMIT 이 기본 설정이다.
    - Postgres는 READ_COMMIT 이 기본 설정이다.
- 낮은 단계의 Isolation Level 이용시 발생하는 현상
    - Dirty Read
        - 커밋되지 않은 수정 중인 데이터를 다른 트랜잭션에서 읽을 수 있도록 허용할 때 발생하는 현상
        - 어떤 트랜잭션에서 아직 실행이 끝난지 않은 다른 트랜잭션에 의한 변경 사항을 보게 되는 되는 경우
    - Non-Repeatable Read
        - 한 트랜잭션에서 같은 쿼리를 두 번 수행할 때 그 사이에 다른 트랜잭션이 값을 수정 또는 삭제함으로써 두 쿼리의 결과가 상이하게 나타나는 비 일관성 현상
    - Phantom Read
        - 한 트랜잭션 안에서 일정 범위의 레코드를 두 번 이상 읽을 때, 첫 번째 쿼리에서 없던 레코드가 두 번째 쿼리에서 나타나는 현상
        - 이는 트랜잭션 도중 새로운 레코드가 삽입되는 것을 허용하기 때문에 나타난다.
- https://devocean.sk.com/blog/techBoardDetail.do?ID=163799

### 교착상태(DeadLock)

- 두 트랜잭션이 각각 Lock을 설정한 다음 서로의 Lock에 접근하여 값을 얻어오려고 할 때 각각이 Lock 이 설정되어 양쪽 트랜잭션 모두 영원히 처리되지 않게 되는 상태
