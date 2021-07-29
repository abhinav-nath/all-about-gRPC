# Protobuf (Protocol Buffers)

[Google Developers Page](https://developers.google.com/protocol-buffers "Protocol Buffers")

* IDL (Interface Description Language) for APIs
* Platform neutral
* Language neutral
* Extensible mechanism for serializing/deserilizing structured data
* Very fast / optimized for inter-service commnunication
* Provides client libraries automatically for many languages

If this is the DTO object:

```
public class Person {
    private String name;
    private int age;
    private String email;
  
    // getters
    // setters
}
```

Then this is how its Protobuf looks like:

```
message Person {
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
}
```
