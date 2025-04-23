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
