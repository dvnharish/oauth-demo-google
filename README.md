# Spring Security Auth 2 Authenticate with Google 

```
Note: Please create the client id and client secrets from google console  to access the apis
```

Spring Security 5 introduces a new OAuth2LoginConfigurer class that we can use for configuring an external Authorization Server.


## Maven Dependencies

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
    <version>2.3.3.RELEASE</version>
</dependency>
```

##  Clients Setup

### Obtaining Client Credentials
To obtain client credentials for Google OAuth2 authentication, head on over to the Google API Console – section “Credentials”.

Here we'll create credentials of type “OAuth2 Client ID” for our web application. This results in Google setting up a client id and secret for us.

We also have to configure an authorized redirect URI in the Google Console, which is the path that users will be redirected to after they successfully login with Google.

add redirection URL 
``` URL
http://localhost:8081/login/oauth2/code/google
```

### Security Configuration

we need to add the client credentials to the application.properties file. 

```properties 
spring.security.oauth2.client.registration.google.client-id=<your client id>
spring.security.oauth2.client.registration.google.client-secret=<your client secret>
```

### Other Dependencies to create a spring boot project 
```xml
	    <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

```

### Adding Controler with normal access and with restricted access

```java

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping
	public String sayHelloToFreeWorld() {

		return "Hello Free World";

	}

	@GetMapping("/restricted")
	public String sayHelloAferLogginToGoogle() {

		return "Hello Google User";
	}
}

```

### Web Security Configurations 

```java

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter{


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
		.antMatcher("/**") // matches everything 
		.authorizeHttpRequests() // authorizing HTTP requests
		.antMatchers("/")// if the matchers are with only '/'
		.permitAll() // permitting all
		.anyRequest()// any other request apart from the above
		.authenticated()// said to be authenticated with 
		.and()
		.oauth2Login();// auth 2 login 
	
	}
	
}


```


# The End Enjoy The code



