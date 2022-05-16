# Array
> 같은 자료형의 변수로 이루어진 구성 요소(component)가 모인 것
- int형, doubld형 등 상관 없음
```java
 // 1) a는 자료형이 int형인 배열
int[] a;
int a[];

// 2) 길이가 5인 배열 선언
b = new int[5];
```
> `1)` 두 방식 모두 사용 가능하나, `int[] a;` 방식이 int형인 배열임을 보다 명시적으로 나타내 더 많이 사용됨
>
> `2)` int형의 배열 본체를 생성 후 변수 b가 "참조"하는것
> - 배열 b의 각 요소의 자료형은 int형이고 배열 b의 자료형은 int[5]형

(+) **new 연산자**가 생성하는 것은 배열 본체에 대한 참조. 참조하는 곳이 b에 대입되고 그 결과 배열 변수 b가 배열 본체를 참조하게 됨

(+) 배열 변수 이름[인덱스] : **인덱스**는 **배열 길이 - 1** 까지 가능

(+) 배열 길이 : 배열 변수 이름.length

```java
int[] a;
a = new int[5];

int[] b = new int[5];
```
> 배열의 구성 요소는 **자동으로 0으로 초기화**되는 규칙이 있음 ex) a[0] = 0, a[1] = 0 ...
>
> 구성 요소를 초기화 하는 값, 곧 배열을 생성할 때 각 구성 요소에 넣어지는 값을 **초기값(default value)**라고 함
- 각 자료형의 초기값 (배열, 인스턴스, 클래스 변수도 해당됨)

형 | 초기값
:---------: | :---------:
byte | (byte)0
short | (short)0
int | 0
long | 0L
float | 0.0f
double | 0.0d
char | '\u0000'
boolean | false
참조형 | 공백 참조 또는 null

\* 배열의 구성 요소와 클래스의 필드는 초기화됨. 메서드 안에서 선언한 지역 변수는 초기화되지 않음. 즉, 변수를 만들어도 초기화 수행되지 않음
```java
int a;
System.out.println("a : " + a); // 컴파일 오류
```

- 배열 생성과 동시에 각 요소의 초기화도 가능
```java
class InArrayInit {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5}; // 배열 초기자에 의한 생성
        int[] b = new int[] {1, 2, 3, 4, 5};

        for (int i = 0; i < a.length; i++)
            System.out.println("a[" + i + "] = " + a[i]);
    }
}
```

```java
class CloneArray() {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = a.clone(); // 배열 복제

        b[3] = 0;

        System.out.print("a =");
        for (int i = 0; i < a.length; i++)
            System.out.print(" " + a[i]);
        System.out.print("\nb =");
        for (int i = 0; i < b.length; i++)
            System.out.print(" " + b[i])
        // a = 1 2 3 4 5
        // b = 1 2 3 0 5
    }
}
```
(+) 배열 복제시 복제한 배열에 대한 참조를 생성함. 배열 b 생성시 배열 a의 복제를 참조하도록 초기화됨

## String
- 자바에서는 char 배열이 아닌 String 클래스를 이용해서 문자열을 처리하는데, String 클래스가 char 배열에 여러 가지 기능을 추가하여 확장한 것이기 때문이다.

### 주요 메서드
메서드 | 설명
:----: | :------:
char charAt(int index) | 문자열에서 해당 위치(index)에 있는 문자를 반환한다.
int length() | 문자열의 길이를 반환한다.
String substring(int from, int to) | 문자열에서 해당 범위(from ~ to)에 있는 문자열을 반환한다. (to는 범위에 포함되지 않음)
boolean equals(String str) | 문자열의 내용이 같은지 확인한다. 같으면 결과는 true, 다르면 false 가 된다.
char[] toCharArray() | 문자열을 문자배열(char[])로 변환해서 반환한다.

## 접근 제한자
- 객체의 멤버에 대한 접근을 제한

### 종류
- public : 모든 접근 허용
- protected : 같은 패키지(폴더)의 객체, 상속 관계의 객체 허용
- default : 같으 패키지(폴더)의 객체 허용
- private : 현재의 객체 안에서만 허용

접근 제한자 사용
- 클래스 : public, default
- 생성자 : public, protected, default, private
- 멤버 변수 : public, protected, default, private
- 멤버 메서드 : public, protected, default, private
- 지역 변수 : 접근 제한자를 사용할 수 없음

```java
// a[idx1] <-> a[idx2]
int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
```

[Up](#)