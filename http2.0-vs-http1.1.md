## HTTP/1.1

* HTTP is an Application layer protocol which works on top of TCP
* Released in 1997
* TCP connection was a 3 way handshake process
* Significant amount of time is spent in establishing a connection
* [Check HTTP/1.1 latency here](https://http1.golang.org/gophertiles?latency=200 "HTTP1.1 Latency")
* We see multiple TCP connections are opened by the browser to download all the images (in parallel)
* Data is sent in plain text

## HTTP/2

* Now use **http2** in the URL given above
* The load time is much faster
* Reason - HTTP/2 uses *Multiplexing*
* It uses one single TCP connection to send parallel requests to the same server
* HTTP/2 is a binary protocol, data is sent in binary format
* HTTP/2 has *Header Compression*
* HTTP/2 has *Flow Control*

## gRPC

* Uses HTTP/2 as the default protocol
* Therefore it inherits all the benefits of HTTP/2
* Non-blocking, Streaming bindings
* **Protobuf** (replaces JSON)
  - Strict Typing
  - DTO
  - Service Definitions
  - Language agnostic
  - Auto-generated bindings for multiple languages
* Great for mobile apps
