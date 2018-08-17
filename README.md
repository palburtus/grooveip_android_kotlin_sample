# GrooVe IP Commercial API Kotlin
This projecct contains a limited SDK and Sample Android pp for the GrooVe IP API written in Kotlin.  The primary purpose of this project is to provide working examples of making calls to the GrooVe IP API as well as computing each calls hash value in Kotlin.    

### Dependencies
This app as written using as few 3rd-party libraries as possible, most notably with regards to HTTP requests and handling JSON.  As of this version the only non-Google/Android dependency included is Mockito for testing.

### Getting Started
In order to ues this project you will have to get a Client Id and API Seccret from SNRBLabs.  

Once you have a Client Id and API secret, clone this project and modify the corresponding fields in the ApiClient.kt file as shown below

```kotlin
object ApiClient {

    val secret = "your api secret"
    val clientId = 1; //change this to your integer client Id
    
    ...
    
```

### Endpoint Examples
Three API endpoints are used in this project
1. List - returns a list of phone numbers by area code
2. Reserve - purchases an available number and adds it to your inventory
3. Inventory - lists numbers you have purchased
