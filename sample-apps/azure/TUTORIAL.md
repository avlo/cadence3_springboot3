## Example Project Structure, with minimal required classes & property file  

```
  $ tree
  |-- pom.xml
  `-- src
      `-- main
          |-- java
          |   `-- edu
          |       `-- mayo
          |           `-- lpea
          |               `-- cad
          |                   `-- cadence3
          |                       |-- Cadence3AzureUserDemo.java
          |                       |-- config
          |                       |   `-- ExampleAzureUserConfig.java
          |                       |-- model
          |                       |   `-- entity
          |                       |       `-- ExampleAzureUser.java
          |                       |-- repository
          |                       |   `-- ExampleAzureUserRepository.java
          `-- resources
              |-- application.yml
```
<hr style="border:2px solid gray">  

## Per above 5 components requiring provision:
----
a custom user class ([example above, ExampleAzureUser.java](https://dev.azure.com/mclm/GBS%20CAD/_git/Cadence3_SpringBoot3?version=GBmaster&path=/sample-apps/azure/src/main/java/edu/mayo/lpea/cad/cadence3/model/entity/ExampleAzureUser.java)), containing:
  - `extends AppUser`  
  - implements required methods:  
    `T getInstantiatedCustomAppUserType();`  
    `T createNewCustomAppUserInstance();`   
  - annotations  
    `@Embeddable`  
    `@Entity`  
  - all fields which you'd like your user object to have  

----

a jpa/hibernate JpaRepository ([example above, ExampleAzureUserRepository.java](https://dev.azure.com/mclm/GBS%20CAD/_git/Cadence3_SpringBoot3?version=GBmaster&path=/sample-apps/azure/src/main/java/edu/mayo/lpea/cad/cadence3/repository/ExampleAzureUserRepository.java)) interface for your custom user class, containing:
  - annotated with  
    `@Repository`  
  - `extends JpaRepository<<YOUR_CUSTOM_USER_CLASS>, Long>`  

----

an `application.yml` file, with values populated for <span style="color:red"><TENANT_ID>, <CLIENT_ID></span> and <span style="color:red"><CLIENT_SECRET_VALUE></span>
  ```java
spring:  
    cloud:
      azure:
        active-directory:
          enabled: true
          profile:
            tenant-id: <TENANT_ID>
#          environment:
#            active-directory-endpoint:
#            microsoft-graph-endpoint:
          credential:
            client-id: <CLIENT_ID>
            client-secret: <CLIENT_SECRET_VALUE, not CLIENT-SECRET>
          authorization-clients:
            aad:
#            authorization-grant-type: client_credentials
              scopes:
                - openid
#              - https://graph.microsoft.com/Analytics.Read
                - email
```  

----

a spring boot `@Configuration` annotated file ([example above, ExampleAzureUserConfig.java](https://dev.azure.com/mclm/GBS%20CAD/_git/Cadence3_SpringBoot3?version=GBmaster&path=/sample-apps/azure/src/main/java/edu/mayo/lpea/cad/cadence3/config/ExampleAzureUserConfig.java)), containing:
  - annotations:  
```java
    @Configuration  
    @EnableJpaRepositories(basePackageClasses = {<YOUR_CUSTOM_USER_CLASS>Repository.class})  
    @EntityScan(basePackageClasses = {<YOUR_CUSTOM_USER_CLASS>.class})  
    @PropertySource("classpath:application.yml")  
    @ComponentScan(basePackages = "edu.mayo.lpea.cad.cadence3.*")
```
  - `@Bean` definition:  
```
    public CustomizableAppUserService customizableAppUserService()  
```
<hr style="border:2px solid gray">  

Additional, Under Construction  

<img src="https://dev.azure.com/mclm/e7c85e5b-8c26-4875-96ef-57032bd1e896/_apis/git/repositories/de4257d9-2244-4390-89b5-90fa19fd0521/items?path=/sample-apps/azure/UnderConstruction.png&versionDescriptor[versionOptions]=0&versionDescriptor[versionType]=0&versionDescriptor[version]=master&resolveLfs=true&%24format=octetStream&api-version=5.0" alt="Under Construction" title="Under Construction">

all above classes can have any additional user-related logic as you'd like

demo extra's:

  CustomController  
  CustomService  
  CustomDTO  
