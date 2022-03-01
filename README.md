**YAAS (Yet Another Authentication Service)**

YAAS aims to provide authentication capabilities, and secure access to the services with minimum effort.
It allows software products to single sign-on with external Identity Providers, and provide secure access to the local resources.

**Features**
- YAAS is Pluggable, Fully Generic, Extensible and Easy to integrate
- Authentication handlers such as OIDC, X509, Username/Password are readily available
- Support for adding new Authentication handler with minimum effort
- Authenticate with given configurations (for multi-auth support or auth chaining or user based auth strategy)

**The Problem Statement**
- Decentralized authentication and management of different set of credentials could lead to security vulnerabilities
- More customizations and local fixes for connecting to external apps -> More lines of code -> More bugs and vulnerabilities

**YAAS as an Authentication Provider**
- With many enterprises moving to the cloud and taking advantage of third-party services, seamless access to multiple applications from anywhere and on any device is essential
- Cloud to On-Prem connectivity without a need of VPN tunneling*
- Central Policy management, authorization control, request auditing reduces the number of attack surfaces
- Reduces the development effort and deployment time

**Deployment Strategies**
<br>
<Not disclosing them yet ;)>

**Easy Three Steps Integration**
<br>
<Hold on please ;)>

**Future Enhancement**
- Add Persistent Storage, JPA (or custom DAO layer)
- Authorization Support, Service Registry
- More authentication handler supports such as Kerberos, SAML
- Callback mechanisms for granular control and customizations
- Auditing, logging
- Code Security - SCA, SAST, Unit testing with 100% code coverage
- Requires 4 more Developers + 1 QA to make it prod ready

**Note:**
<br>
It is currently under development, stay tuned for the updates.
Currently, it supports: OIDC and X509 auths.<br>
Future roadmap: SAML, Kerberos and more OIDC providers.<br><br>
Passionate developers are welcome to contribute.<br>
Reach out: bhushan.karmarkar12@gmail.com
Cheers!