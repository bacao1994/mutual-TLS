## References
- https://medium.com/@salarai.de/how-to-enable-mutual-tls-in-a-sprint-boot-application-77144047940f 
- https://bohutskyi.com/security-mtls-in-spring-boot-aef44316dd4b
- https://aws.amazon.com/vi/blogs/compute/introducing-mutual-tls-authentication-for-amazon-api-gateway/


## CA
* `openssl genrsa -out ca.key 2048` Generating Root Certificates (Create the private certificate authority (CA) private)
* `openssl req -x509 -new -nodes -key ca.key -sha256 -days 365 -out ca.pem` Request a certificate from openssl using the key generated in the previous step (Create the public keys:)

    + CountryCode: US
    + State: Washington
    + Locality: Seattle
    + Organization Name: AWS
    + Organizational Unit:DBC
    + Common Name: localhost
    + Email Address: bacao1994@gmail.com
    + Challenge pw: 123456

### Signing Server Certificate
* `openssl genrsa -out server.key 2048`  Create a server private key
* `openssl req -new -key server.key -out server.csr` Create a CSR for our server certificate (It will prompt for the same kind of information that was required for the root CA. CN field needs to be fully qualified hostname on which the server will be accessible, in this case localhost)

* `openssl x509 -req -in server.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out server.pem -days 365 -sha256` This should create the server certificate with name **server.pem** which together with **server.key** will be used to configure Tomcat to enable SSL

### Signing Client Certificate
* `openssl genrsa -out client.key 2048` Create a client private key
* `openssl req -new -key client.key -out client.csr` create a CSR for the client in the same way 
* `openssl x509 -req -in client.csr -CA rootCA.pem -CAkey rootCA.key -CAcreateserial -out client.pem -days 365 -sha256` Then we sign the client certificate also in the same way

### 
* `openssl pkcs12 -export -in server.pem -out keystore.p12 -name server -nodes -inkey server.key` export the certificate and the private key to a PKCS format key store which we can use to configure the Spring application with