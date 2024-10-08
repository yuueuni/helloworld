## 문맥 교환 (Context Switching)

- 하나의 프로세스가 CPU를 사용 중인 상태에서 다른 프로세스가 CPU를 사용하도록 하기 위해 이전의 프로세스의 상태(문맥)을 보관하고 새로운 프로세스의 상태를 적재하는 작업
- 이때 한 프로세스의 문맥은 그 프로세스 제어 블록(PCB)에 기록되어 있음

### PCB (Process Control Block)

- 시스템 내의 프로세스들을 관리하기 위해 프로세스마다 유지하는 정보들을 담는 커널 내 자료구조로 커널 주소 공간의 data 영역에 존재
- https://j-su2.tistory.com/63

## Swapping (스와핑)

- 프로세스 스와핑이란, 현재 메모리에서 잠깐 다른 저장공간(HDD, SSD)로 옮겨졌다가 돌아왔다 하는 식으로 실행에 따라 교체될 수 있다는걸 의미함
    - 프로세스 단위로 스와핑 하는 경우 외부 단편화 문제로 비효율적
        - 내부 단편화: 주기억장치 내 사용자 영역이 실행 프로그램보다 커서 프로그램의 사용 공간을 할당 후 사용되지 않고 남게 되는 현상
        - 외부 단편화: 남아있는 총 메모리 공간이 요청한 메모리 공간보다 크지만 남아있는 공간이 연속적(contiguous)이지 않아 발생하는 현상

## **Paging(페이징)**

- 하나의 프로세스가 사용하는 메모리 공간이 연속적이어야 한다는 제약을 없애는 메모리 관리 방법으로, 외부 단편화 압축 작업을 해소하기 위해 생긴 방법론
    - 물리 메모리는 Frame 이라는 고정 크기로 분리되어 있고 논리 메모리(프로세스가 점유하는)는 페이지라 불리는 고정 크기의 블록으로 분리됨
- 페이징 기법을 사용함으로써 논리 메모리는 물리 메모리에 저장될 때, 연속되어 저장될 필요가 없고 물리 메모리의 남는 프레임에 적절히 배치되므로 외부 단편화를 해결할 수 있는 장점이 있음

## 가상 메모리 (Virtual Memory)

- 프로세스 전체가 메모리 내에 올라오지 않더라도 실행이 가능하도록 하는 기법으로, 프로그램이 물리 메모리보다 커도 된다는 주요 장점이 있음
- 실제의 물리 메모리 개념과 사용자의 논리 메모리 개념을 분리한 것으로 정리할 수 있어, 작은 메모리를 가지고도 얼마든지 큰 가상 주소 공간을 프로그래머에게 제공할 수 있다
    - 가상 주소 공간
        - 한 프로세스가 메모리에 저장되는 논리적인 모습을 가상 메모리에 구현한 공간
        - 프로세스가 요구하는 메모리 공간을 가상 메모리에서 제공함으로써 직접적으로 필요치 않은 메모리 공간을 실제 물리 메모리에 올리지 않는 것으로 물리 메모리를 절약할 수 있다
- 시스템 라이브러리가 여러 프로세스들 사이에 공유될 수 있도록 하여, 각 프로세스들은 공유 라이브러리를 자신의 가상 주소 공간에 두고 사용하는 것처럼 인식하지만 라이브러리가 올라가있는 물리 메모리 페이지들은 모든 프로세스에 공유되고 있다
- 프로세스들이 메모리를 공유하는 것을 가능하게 하고 프로세스들은 공유 메모리를 통해 통신할 수 있다. 각 프로세스들은 각자 자신의 주소 공간처럼 인식하지만 실제 물리 메모리는 공유되고 있다

## 캐시의 지역성

- 캐시 메모리: 속도가 빠른 장치와 느린 장치간의 속도차에 따른 병목 현상을 줄이기 위한 범용 메모리
    - 캐시 역할 수행을 위해서는 CPU가 어떤 데이터를 원할 것인가를 예측할 수 있어야 하며, 캐시의 성능은 작은 용량의 캐시 메모리에 CPU가 이후에 참조할 쓸모 있는 정보가 얼마나 들어있느냐에 따라 좌우됨
    - 캐시(cache)는 프로세서 가까이에 위치하면서 빈번하게 사용되는 데이터를 놔두는 장소로 캐시에 목적 데이터가 저장되어 있다면 바로 접근하여 출력할 수 있어야 캐시가 의미 있어짐
- 적중율(Hit rate)을 극대화 시키기 위해 데이터 지역성(Locality)의 원리를 사용하며, 지역성의 전제 조건은 프로그램은 모든 코드나 데이터를 균등하게 Access 하지 않는다는 특성을 기본으로 한다.
- 지역성 이란 기억 장치 내의 정보를 균일하게 Access 하는 것이 아닌 어느 한 순간에 특정 부분을 집중적으로 참조하는 특성이다.
    - 시간 지역성: 최근에 참조된 주소의 내용은 곧 다음에 다시 참조되는 특성
    - 공간 지역성: 대부분의 실제 프로그램이 참조된 주소와 인접한 주소의 내용이 다시 참조되는 특성
- Caching Line
    - 캐시에 데이터를 저장할 때 특정 자료구조를 사용하여 묶음으로 저장하는데, 이를 캐싱 라인이라 함
    - 프로세스는 다양한 주소에 있는 데이터를 사용하므로 빈번하게 사용하는 데이터의 주소 또한 흩어져 있는데, 데이터의 메모리 주소 등을 기록해 둔 태그들의 묶음을 캐싱 라인이라 하며, 메모리부터 가져올 때도 캐싱 라인을 기준으로 가져온다
        - Full Associative
        - Set Asscociative
        - Direct Map
- https://resilient-923.tistory.com/397
