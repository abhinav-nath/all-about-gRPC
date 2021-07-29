# All about gRPC

This repository contains details and examples of Google's gRPC (Google Remote Procedure Calls).

Useful documentation:

* [grpc.io](https://grpc.io "gRPC official site")
* [Introduction to gRPC](https://grpc.io/docs/what-is-grpc/introduction/ "Introduction to gRPC")
* [gRPC Motivation and Design Principles](https://grpc.io/blog/principles/ "gRPC Motivation and Design Principles")

## The story behind gRPC
gRPC was initially created by Google, which has used a single general-purpose RPC infrastructure called Stubby to connect the large number of microservices running within and across its data centers for over a decade. In March 2015, Google decided to build the next version of Stubby and make it open source. The result was gRPC, which is now used in many organizations outside of Google to power use cases from microservices to the "last mile" of computing (mobile, web, and Internet of Things).

It uses HTTP/2 for transport, Protocol Buffers as the interface description language, and provides features such as authentication, bidirectional streaming and flow control, blocking or nonblocking bindings, and cancellation and timeouts. It generates cross-platform client and server bindings for many languages. Most common usage scenarios include connecting services in a microservices style architecture, or connecting mobile device clients to backend services.
