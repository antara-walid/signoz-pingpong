# signoz-pingpong

The microservices architecture is a powerful design paradigm for breaking down complex monolithic software systems into smaller, more manageable pieces. 
These pieces can be built and deployed independently of each other.
However, The distributed nature of the services means that we need to trace one or more transactions across multiple services, physical machines, and different data stores.
SigNoz is an open-source application performance monitoring tool that helps you monitor your applications and troubleshoot problems. SigNoz uses distributed tracing to gain visibility into your software stack.

As an innovative project, I had the chance to use Signoz in a basic project named ping-pong, the project idea is to create three microservices with a web layer, that sends and receives HTTP requests/responses randomly 
using RestTemplate or Spring WebClient, to create complexity and introduce different errors that can be traced and fixed using __Signoz__.

The project is not finished and is currently under development.
