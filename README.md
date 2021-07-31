# All about gRPC

This repository contains details and examples of Google's gRPC (Google Remote Procedure Calls).

Useful documentation:

* [grpc.io](https://grpc.io "gRPC official site")
* [Introduction to gRPC](https://grpc.io/docs/what-is-grpc/introduction/ "Introduction to gRPC")
* [gRPC Motivation and Design Principles](https://grpc.io/blog/principles/ "gRPC Motivation and Design Principles")

## The story behind gRPC
gRPC was initially created by Google, which has used a single general-purpose RPC infrastructure called Stubby to connect the large number of microservices running within and across its data centers for over a decade. In March 2015, Google decided to build the next version of Stubby and make it open source. The result was gRPC, which is now used in many organizations outside of Google to power use cases from microservices to the "last mile" of computing (mobile, web, and Internet of Things).

It uses HTTP/2 for transport, Protocol Buffers as the interface description language, and provides features such as authentication, bidirectional streaming and flow control, blocking or nonblocking bindings, and cancellation and timeouts. It generates cross-platform client and server bindings for many languages. Most common usage scenarios include connecting services in a microservices style architecture, or connecting mobile device clients to backend services.

## Problems with REST based communication

1. **Request and Response Protocol**

   Most of the times we use *JSON* and *HTTP 1.1* to communicate with other services over the the network. The HTTP call itself takes 3 message exchanges between the client and      the server to establish a *TCP connection*, only after that the actual message is sent to the server. We cannot send another message using the same connection until we receive    a response. We have to create another connection to send another message. **This is a big overhead!**


2. **HTTP Headers**

   HTTP is a *stateless protocol*, so every request carries information like headers and cookies. These are in plain text and could be large in size.
   These cannot be compressed as well.


3. **Serialization and Deserialization**

   If a message has to be sent from service1 to service2 then it has to be first serialized by service1 and then deserialized by service2.
   We as humans love text formats but machines love binary format. The cost (time, cpu, memory) of serializing and deserializing large request/response payloads is very high!


4. **No API Contract**

   There is no way to setup a uniform contract between APIs. One possible solution could be to provide client libraries to the Consumer of your API.
   Which leads to another problem.


5. **Client SDK**

   If our service is written in Java and the client application is also written in Java then we can share the client libraries but what if there are different clients written in    different languages (C/C++/JavaScript/GoLang etc) ? We simply cannot create client libraries for each one of them.

<br>
Google identified many such problems in HTTP based inter-service communication, so they developed Stubby to solve all such issues.

## Stubby
* RPC framework from Google
* 15 years
* 10 billion requests/sec
* Cross-platform
* *Tightly coupled with infrastructure*

## gRPC
* Developed at Google
* Inspired by Stubby and addresses its problems
* Released in 2016
* Adopted by software giants like Netflix, Microsoft etc
* Belongs to CNCF ([Cloud Native Computing Foundation](https://www.cncf.io/ "Cloud Native Computing Foundation"))

<br>
More Details here:

* [HTTP/2.0 vs HTTP/1.1 and gRPC](https://github.com/abhinav-nath/all-about-gRPC/blob/master/notes/http2.0-vs-http1.1.md "HTTP/2.0 vs HTTP/1.1 and gRPC")
* [Protobuf](https://github.com/abhinav-nath/all-about-gRPC/blob/master/notes/protobuf.md "Protocol Buffers")
  - [Serialization Deserialization](https://github.com/abhinav-nath/all-about-gRPC/tree/master/code/protobuf-demo/src/main/java/com/codecafe/grpc/protobuf/serialization_deserialization "Proto Serialization Deserialization")
  - [JSON vs Protobuf Serialization/Deserialization Performance Test](https://github.com/abhinav-nath/all-about-gRPC/tree/master/code/protobuf-demo/src/main/java/com/codecafe/grpc/protobuf/json_vs_proto/JsonVsProtoPerformanceTest.java "JSON vs Proto")
  - [Version Compatibility](https://github.com/abhinav-nath/all-about-gRPC/tree/master/code/protobuf-demo/src/main/java/com/codecafe/grpc/protobuf/versioning "Version Compatibility")
* [gRPC](https://github.com/abhinav-nath/all-about-gRPC/blob/master/notes/gRPC.md "gRPC")
