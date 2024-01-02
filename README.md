# Cadence3 - User Authentication/Authorization and Application Security framework.

 Spring Boot 3.x + Spring Security 6.x User-Session management and Comprehensive Web-Application security, _**built-in and transparent to application-developer concerns**_, with pluggable modes:

  - Stand-alone user creation/registration & <abbr title="both Authentication & Authorization using Spring-Security UserDetails & UserDetailsService">Spring-Security Authentication & Authorization</abbr> (**_Default_** mode)
  - LDAP user Authentication and <abbr title="Authorization using Spring-Security UserDetails & UserDetailsService">Authorization</abbr> (**_LDAP_** mode)
  - AAD (Azure Active Directory) user + Oauth2/JWT client-application <abbr title="Authorization using Spring-Security UserDetails & UserDetailsService atop Azure Active Directory">Authentication</abbr> and Spring Security <abbr title="Authorization using Spring-Security UserDetails & UserDetailsService">Authorization</abbr> (**_Azure_** mode).
  
All above available as spring-boot-starters for rapidly accelerated customizable **_and secure_** projects.

<hr style="border:2px solid gray">  

## Project Layout/Structure
    $ tree
    |-- autoconfigure (module)
    |   |-- azure
    |   |-- core
    |   |-- h2db
    |   |-- jpa
    |   |-- ldap
    |   |-- web
    |-- lib          (module)
    |   |-- azure
    |   |-- core
    |   |-- h2db
    |   |-- jpa
    |   |-- ldap
    |   |-- web
    |-- sample-apps  (module)
    |   |-- azure
    |   |-- jpa
    |   |-- ldap
    |-- starter      (module)
        |-- azure
        |-- core
        |-- h2db
        |-- jpa
        |-- ldap
        |-- web 

### Module descriptions
- `autoconfigure`:  
  spring-boot3-related auto-configurations (used by project developers/maintainers)  
  
  
- `lib`:  
  core libraries (used by project developers/maintainers)  
  
  
- `sample-apps`:  
  clear & simple examples (for client-application developers/users)  
  
  
- `starter`:  
  powerful spring-boot3 rapid-development packaging feature (for client-application developers/users)  

#### Cadence3 is a dynamic and easily extensible OOP project == Additional/new features can be introduced quickly and easily as desired _without compromising or breaking existing security model/functionality_.

<hr style="border:2px solid gray">  

## Requirements

    $ java -version

>     java version "17.0.7" 2023-04-18 LTS (or higher)
>     Java(TM) SE Runtime Environment (build 17.0.7+8-LTS-224)
>     Java HotSpot(TM) 64-Bit Server VM (build 17.0.7+8-LTS-224, mixed mode, sharing)

    $ mvn -version
>     Apache Maven 3.9.2 (c9616018c7a021c1c39be70fb2843d6f5f9b8a1c) (or higher)
>     Maven home: ~/.sdkman/candidates/maven/current
>     Java version: 17.0.7, vendor: Oracle Corporation, runtime: C:\Users\m259408\.sdkman\candidates\java\current
>     Default locale: en_US, platform encoding: UTF-8
>     OS name: "linux", version: "5.15.0-72-generic", arch: "amd64", family: "unix"
  
  
<hr style="border:2px solid gray">  

## Build
From project root directory:

	$ mvn clean install

## Setup, Run

#### <abbr title="Self-contained user authentication and authorization using JPA/Hibernate">Default</abbr> mode
&nbsp;&nbsp;&nbsp;&nbsp; _Activates canonical spring-security user mode + spring-security authentication **and** authorization_   

Works by default.  No configuration necessary.  

	$ cd sample-apps/jpa/
	/sample-apps/jpa$  mvn spring-boot:run
    

#### LDAP mode
&nbsp;&nbsp;&nbsp;&nbsp;_activates LDAP authentication + spring-security authorization_  

Substitute password for work-account (in this case, TU01088) in _**sample-apps/ldap/src/main/resources/application.properties**_ and _**src/test/resources/application-test.properties**_

    spring.ldap.username=CN=TU01088,OU=Normal,OU=WorkAccounts,DC=mfad,DC=mfroot,DC=org
    spring.ldap.password=<TU01088_PASSWORD>
then:

	$ cd sample-apps/ldap/
	/sample-apps/ldap$  mvn spring-boot:run

#### <abbr title="Active Directory OAuth2">ADOauth2</abbr> mode

&nbsp;&nbsp;&nbsp;&nbsp; _Activates ADOauth2 (Active Directory Oauth2 + JWT) user authentication mode + spring-security authorization_,
&nbsp;&nbsp;&nbsp;&nbsp; _aka, Single-Sign-On (SSO)_

Substitute below attributes in _**sample-apps/azure/src/main/resources/application.yml**_ and _**src/test/resources/application-test.properties**_

    spring:
      thymeleaf:
        cache: false
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
                  - email~/git/Cadence3_SpringBoot3$

then:

	$ cd sample-apps/azure/
	/sample-apps/azure$  mvn spring-boot:run
  

<hr style="border:2px solid gray">  

## Application Use
### Default mode.  Stand-alone JPA authentication & authorization)

Register new application user:

    localhost:8080/register
    
Login & Authenticate user:

    localhost:8080/login

Authenticated and Authorized endpoint displaying existing application users

    localhost:8080/users
  
  
----

### LDAP mode
    
Login & Authenticate LDAP user:

    localhost:8080/login

Authenticated and Authorized endpoint displaying existing application users

    localhost:8080/users

----
  
### ADOauth2 mode
    
Login & Authenticate ADOauth2 user:

    localhost:8080/login

Authenticated and Authorized endpoint displaying existing application users

    localhost:8080/users

<hr style="border:2px solid gray">  

## Viewing DB contents

DB console:

    localhost:8080/h2-console/

*user: sa*  
*password: // blank* 

Display all framework table contents:

    SELECT * FROM USERS;
    SELECT * FROM APPUSER_AUTHUSER;
    SELECT * FROM APPUSER;
    SELECT * FROM AUTHORITIES;
  
  
<hr style="border:2px solid gray">  

## Automated testing
### Build and run unit-tests

    $ mvn test

unit-test code-coverage reports can now be displayed by opening browser file:

    target/site/jacoco/index.html
    
_note: for complete code-coverage results, use **mvn verify** command below_

----

### Build and run both integration-tests and unit-tests

    $ mvn verify -P [jpa|ldap|adoauth2]

complete (integration-test and unit-test) code-coverage reports can now be displayed by opening browser file:

    target/site/jacoco/index.html
  
  
<hr style="border:2px solid gray">  

## Auto imported dependencies reference (significanct items)

    $ mvn dependency:tree
    +- org.springframework.boot:spring-boot-starter:jar:3.2.1:compile
    |  +- org.springframework.boot:spring-boot:jar:3.2.1:compile
    |  |  \- org.springframework:spring-context:jar:6.1.2:compile
    |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.2.1:compile
    |  +- org.springframework:spring-core:jar:6.1.2:compile
    +- org.springframework.boot:spring-boot-devtools:jar:3.2.1:compile
    +- org.springframework.boot:spring-boot-starter-security:jar:3.2.1:compile
    |  +- org.springframework:spring-aop:jar:6.1.2:compile
    |  |  \- org.springframework:spring-beans:jar:6.1.2:compile
    |  +- org.springframework.security:spring-security-config:jar:6.2.1:compile
    |  |  \- org.springframework.security:spring-security-core:jar:6.2.1:compile
    |  |     \- org.springframework.security:spring-security-crypto:jar:6.2.1:compile
    |  \- org.springframework.security:spring-security-web:jar:6.2.1:compile
    |     \- org.springframework:spring-web:jar:6.1.2:compile
    +- org.springframework.boot:spring-boot-starter-data-jpa:jar:3.2.1:compile
    |  +- org.springframework.boot:spring-boot-starter-jdbc:jar:3.2.1:compile
    |  |  \- org.springframework:spring-jdbc:jar:6.1.2:compile
    |  +- org.hibernate.orm:hibernate-core:jar:6.4.1.Final:compile
    |  +- org.springframework.data:spring-data-jpa:jar:3.2.1:compile
    |  \- org.springframework:spring-aspects:jar:6.1.2:compile
