# Protobuf (Protocol Buffers)

[Google Developers Page](https://developers.google.com/protocol-buffers "Protocol Buffers")

[Download protoc](https://github.com/protocolbuffers/protobuf/releases "Download protoc")

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

Then this is how its Protobuf (proto2) looks like:

```
message Person {
    required string name = 1;
    required int32 id = 2;
    optional string email = 3;
}
```

proto3 removed `optional` and `required` keywords.

So in proto3 all fields are optional by default.

### person.proto

```
syntax = "proto3";

option java_multiple_files = true;
option java_package = "com.codecafe.grpc.protobuf.model";

message Person {
  string name = 1;
  int32 age = 2;
  string email = 3;
}
```