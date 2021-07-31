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
    required string name = 1;  // 1 is tag, can be any number
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

<br>

## Proto Scalar Types

| **Java Type** | **Proto Type** |
|---------------|----------------|
| int           | int32          |
| long          | int64          |
| float         | float          |
| double        | double         |
| boolean       | bool           |
| String        | string         |
| byte[]        | bytes          |

### Collections and Map

| **Java Type** | **Proto Type** |
|---------------|----------------|
| List          | repeated       |
| Map           | map            |

### Default Values

| **Proto Type**          | **Default**         |
|-------------------------|---------------------|
| int32 / any number type | 0                   |
| bool                    | false               |
| string                  | empty string        |
| enum                    | first value         |
| repeated                | empty list          |
| map                     | wrapper / empty map |

### Wrapper Types

Like Java, there are [Wrapper types](https://developers.google.com/protocol-buffers/docs/reference/google.protobuf "Wrapper Types") in protobuf as well.

To use them, we just need to import `wrappers.proto` in our proto file.

`import google/protobuf/wrappers.proto`

and we can use them like this:

```
message Person {
    string name = 1;
    google.protobuf.Int32Value age = 2;
}
```

`int32` : `Int32Value`

`float` : `FloatValue`

`bool` : `BoolValue`

and many more ...


## Protobuf allows Composition

### car.proto

```
message Car {
  string make = 1;
  string model = 2;
  int32 year = 3;
}
```

### address.proto

```
message Address {
  int32 zipCode = 1;
  string street = 2;
  string city = 3;
}
```

### person.proto

```
message Person {
  string name = 1;
  int32 age = 2;
  string email = 3;
  Car car = 4;
  Address address = 5;
}
```

## How is Protobuf Serialization/Deserialization better than JSON ?

This is how JSON serialized:
```
"name" : "tom",
"age" : 28
```

So the String keys also get serialized and deserialized along with the values.

But, in Protobuf, we define a `tag` or `field number` for each field:

```
message Person {
  string name = 1;
  int32 age = 2;
```

This `tag` can be any numeric (unique) value. It does not need to be sequential.

Now when the data gets serialized then it is done in the form of `tag:value` mapping:

```
1 = tom
2 = 28
```

So, protobuf simply ignores the String keys and uses tags to map corresponding values.
String keys are just for the readability of Developers.

Under the hood, only tags and values get serialized and de-serialized.

#### This is the main reason why the size of the byte array of JSON after serialization is much larger than that of a protobuf.

This contributes in the significant performance improvement of protobuf.

#### Another optimization is that only those fields get serialized which have some values.

The fields which do not have values do not even get serialized.

At the receiver side while deserializing, protobuf initializes the values for the fields which had not been set.

This is because there is no `null` in protobuf.

### Important points about tags / field numbers

* Although any unique number can be used for the tags, big numbers must be avoided as they'll increase the size of the byte array.

* As per the [community guidelines](https://developers.google.com/protocol-buffers/docs/proto3#assigning_field_numbers "Assigning Field Numbers"):

  - Field numbers in the range of 1 - 15 take 1 byte to encode
  - 16 - 2047 take 2 bytes
  - 1 is smallest
  - 2^29 - 1 is the largest
  - 19000 - 19999 are reserved for protobuf implementation

* So you should reserve the field numbers 1 through 15 for very frequently occurring message elements.

* Remember to leave some room for frequently occurring elements that might be added in the future.

* Do not change the field number once it is in use. As it is a contract between the sender and the receiver.