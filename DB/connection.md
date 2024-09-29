## 데이터베이스 풀

- 클라이언트 요청에 따라 어플리케이션의 스레드에서 데이터베이스 접근하기 위한 connection을 여러개 생성해 두어 저장해 놓은 공간(캐시)으로 필요할때 꺼내 쓰고 반환하는 기법
- connection이 부족한 경우 클라이언트는 대기 상태로 전환시키고 connection이 반환되면 대기 상태에 있는 클라이언트에 순차적으로 제공
- 매 연결마다 connection 객체를 생성하고 소멸시키는 비용 절감 가능하며 미리 생성된 connection 객체 사용으로 접근 시간이 단축됨
- 디비 접근 커넥션 수 제한하여 메모리와 디비에 걸리는 부하를 조정할 수 있음
- [https://velog.io/@xogml951/Scalability-Test-Thread-Pool-DBCP-적정-설정값-찾기](https://velog.io/@xogml951/Scalability-Test-Thread-Pool-DBCP-%EC%A0%81%EC%A0%95-%EC%84%A4%EC%A0%95%EA%B0%92-%EC%B0%BE%EA%B8%B0)

### Thread Pool, **Connection pool**

- WAS에서 Thread pool과 Connection pool내의 Thread와 Connection의 수는 직접적으로 메모리와 관련이 있기 때문에, 많이 사용하면 할 수록 메모리를 많이 점유하게 된다. 그렇다고 반대로 메모리를 위해 적게 지정한다면, 서버에서는 많은 요청을 처리하지 못하고 대기 할 수 밖에 없다.
- 보통 WAS의 Thread의 수가 Conncetion의 수보다 많은 것이 좋은데, 그 이유는 모든 요청이 DB에 접근하는 작업이 아니기 때문이다.
- Little’s Law: 초당 요청 수와 처리 시간을 곱하여 스레드 개수 선정