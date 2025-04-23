# TinyURL
 Tiny-URL System design implementation using spring boot

### System APIs
To expose the functionality of this service, REST APIs are used for the following features:
 - Shortening a URL
 - Redirecting a short URL
 - Deleting a short URL

 ```mermaid
%%{init: {"flowchart": {"htmlLabels": false}} }%%
flowchart LR
    A["Client"]
    B["API servers"]
    C[(Database)]
    A -- Create url, Redirect url, Delete url --> B --Shorten a URL, Redirect a short URL, Delete a short URL --> C
     B --> A
```
### APIs
#### Create (POST): 
```
POST : {{tiny-url-api-host}}/api/v1/tinyurl
```

#### Redirect (GET): 
```
GET : {{Host}}/{tiny-url}
```

#### Delete (Delete): 
```
DELETE : {{Host}}/api/v1/tinyurl/{tiny-url}
```
Response: 204 http status code with no content

### High Level Architecture:

 ```mermaid
flowchart LR
    A["Client"]
    B["Load Balancer"]
    C1["Node 1"]
    C2["Node 2"]
    C3["Node 3"]
    Cn["Node n"]
    zookeeper["`Zookeeper
        1L to 2L
        2L to 3L
        3L to 4L
         ---
         ---
         nL to (n+1)L`"]
    D[(Cache)]
    E[(RDBMS)]
  A --> B --> C1 & C2 & C3 --> E
   C1 & C2 & C3 -.-> D
 C1 & C2 & C3 & Cn --> zookeeper
```
