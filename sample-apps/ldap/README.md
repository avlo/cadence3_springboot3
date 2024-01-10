#### LDAP mode
&nbsp;&nbsp;&nbsp;&nbsp;_activates LDAP authentication + spring-security authorization_  

Substitute password for work-account (in this case, TU01088) in _**sample-apps/ldap/src/main/resources/application.properties**_ and _**src/test/resources/application-test.properties**_

    spring.ldap.username=CN=TU01088,OU=Normal,OU=WorkAccounts,DC=mfad,DC=mfroot,DC=org
    spring.ldap.password=<TU01088_PASSWORD>
then:

	$ cd sample-apps/ldap/
	/sample-apps/ldap$  mvn spring-boot:run  

Login & Authenticate LDAP user:

    localhost:8080/login

Authenticated and Authorized endpoint displaying existing application users

    localhost:8080/users