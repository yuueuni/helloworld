# Argument

## *args vs **kwargs

### *args (arguments)

- 가변 인자를 위한 변수 (tuple)
  - 함수 인자 개수를 모를때 사용

```python
def add(*args):
    print(args)

add(1,2,3) # 1,2,3
add(1,2,3,4) # 1,2,3,4
```

### **kwargs (keyword arguments)

- 가변 인자를 위한 변수로 args와 비슷하나, 파라미터 명을 같이 보낼 수 있음 (dictionary)

```python
def num(**kwargs):
    print(kwargs)

num(number=1, eng='one') # {'number': 1, 'eng': 'one'}
```

- *args, **kwargs 동시 사용
  - *args 선언 후 미사용도 가능

```python
def number_eng(*args, **kwargs):
    print(args, kwargs)

number_eng(1,2,3, eng='onetwothree') # (1,2,3){'eng':'onetwothree'}
number_eng(eng='zero') # (){'eng':'zero'}
```
