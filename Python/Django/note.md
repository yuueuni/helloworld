# Note

## MethodMapper

- [MethodMapper](https://github.com/encode/django-rest-framework/blob/19655edbf782aa1fbdd7f8cd56ff9e0b7786ad3c/rest_framework/decorators.py#L177)

```python
class MyViewSet(ViewSet):
    @action(detail=False)
    def example(self, request, **kwargs):
        ...
    @example.mapping.post
    def create_example(self, request, **kwargs):
        ...
```

## Left Join

- 전제 조건 : fk 컬럼 있어야 함

```python
{table name}.objects.filter({column_left}__{column_right}={condition})
```

