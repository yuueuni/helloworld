# Note

## Python, Numpy

- Python 3.9, Numpy 1.22.4 : version error
  - pip version X, OS numpy install (venv error) X

## Note

- `.get({key}, {None value})` 예시

```python
test = {0: None, 1: 'first'}
test.get(0) # None
test.get(0, 'Zero') # None
test.get(3, 'Zero') # Zero
```

- `None` 이라도 Value를 가진 Key 조회시 default Value로 조회되지 않는다.
