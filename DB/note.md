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

## master-slave vs readonly-write

- DB 를 master 와 slave 로 구성하는 경우, slave 를 read-only 로 사용한

```text
## 현재 MySQL 서버의 read_only 설정 확인
 > show global variables like "%read_only%";

## read_only 설정하기
1. my.cnf 수정
 - "read_only" 추가 후 mysqld 재시작

2. 서버 설정 변경
 > SET GLOBAL read_only = ON;
 > FLUSH privileges;
 > SHOW global variables like "%read_only%";

## root를 제외한 계정의 SUPER 유저 권한 삭제
 > UPDATE mysql.user SET super_priv='N' WHERE user <> 'root';
 > FLUSH privileges;
```

- data replication 은 데이터를 물리적으로 다른 서버 공간에 복제하는 일로 이용시 부하 분산 및 고가용성 등의 효과가 있음
  - 기본적으로 비동기 (Asynchronous)로 이루어짐
  - Writable 한 Master Node 에서 Readonly 인 slave로 이루어짐
- Scale out solution : Replication을 통해 Read 요청에 대한 분산을 여러 Slave로 나눔으로써 병목 현상을 해결할 수 있음
- Data security : 데이터가 Slave로 복제됨에 따라서 마스터 서비스에 대한 영향없이 데이터에 대한 백업이 가능
- Long-distance data distribution : Data를 여러 지리적 Location 혹은 Local에 Copy를 만들 수 있다. 이는 장애에 대한 대응책이 될 수 있음

- MySQL의 Replication은 로그 기반으로 비동기적으로 데이터를 복제함
  - Master에서 데이터가 변경되면 Binary Log라는 곳에 이력을 기록하는데, 실행된 SQL을 그대로 기록하거나(Statement-Based), 변경된 행을 Base64로 인코딩하여 기록하거나(Row-Based) 적절히 혼합한 형태(Mixed Type)로 동기화를 수행
    - (1) Master에서 데이터 변경이 일어나면 자신의 데이터베이스에 반영한다.
    - (2) Master에서 변경된 이력을 Binary Log에 기록한 뒤 관련 이벤트를 날린다.
    - (3) Slave IO_THREAD에서 Master 이벤트를 감지하고 Master Binary Log의 Relay Log에 기록한다.
    - (4) Slave SQL_THREAD는 Relay Log를 읽고 자신의 DB에 기록한다.

- Master에서는 여러 세션에서 데이터 변경처리가 가능하지만 Slave에서는 오직 SQL Thread에서만 데이터 변경처리가 이루어질 수 있기 때문에 Master의 데이터 변경 트래픽이 과도할 경우 동기화 시간 차이가 크게 날 수도 있다.

- WAS(Web Application Server)레벨에서 어떤 DB를 바라볼지 Web Framework 레벨에서 지원해주는 경우가 많음
  - Spring의 경우 `@Transactional(readOnly=true)` 어노테이션에서 readOnly 옵션을 이용해서 트랜잭션 설정하나로 서로 다른 데이터 소스로 연결 시킬 수 있음
  - Spring의 AbstracRoutingDataSource 클래스는 여러 개의 DataSource를 하나로 묶고 자동으로 분기처리해주는 Spring 기본 클래스로 @Transactional과 연계시 쉽게 분기처리가 가능
  - 참고로 Spring의 @Transactional 처리는 TransactionManager 선별 -> DataSource에서 Connection 획득 -> Transaction 동기화 순으로 일어나기 때문에 커넥션을 동기화 이후에 얻는 Lazy 한 방식을 사용해야 함을 명심해야 함

- Master-Master replication 구조를 고려할 때에는 Master 간 Conflict 및 데이터베이스에서의 ACID 성질의 유지가 힘들기 때문에 신경쓸 부분이 조금 더 많음
  - Active Directory / LDAP 과 같은 Directory Service Server 나 Data 의 Conflict 우려가 적고 Accessibility 가 중요한 서비스의 경우 고려됨

- 클라우드 환경이 도입되면서 Multi A-Z 에서의 Replication 전략도 눈여겨 봐야하는데, 보통 하나의 Master 를 한 Availability Zone 에 두고 Read-only Slave 들을 같은 Zone 및 다른 AZ 에 배치시켜두는 형태를 취한다. AWS 에서는 이를 Read-Replica 라는 서비스로 제공함

```text
WAS(Web Application Server)
ex) BEA-Web Logic, IBM-Web Spere, TMAX-Jeus, Apache project-Tomcat
- web application을 수행할 수 있는 환경을 제공해주는 서버

 

```

## prosedure

## trigger
