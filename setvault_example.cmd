set VAULT_TOKEN=<vault root token>
set VAULT_ADDR=http://<vault host>:<vault port>
vault kv put secret/y2api/jt400 as400host=<ibm i host> as400user=<ibm i user id> as400password=<ibm i user password> dburl="jdbc:as400://<ibm i host>/;prompt=false;translate binary=true;" dbuser=<ibm i db user id> dbpassword=<ibm i db user password> authClientId=<keycloak client id> authClientSecret=<keycloak client secret> authurl=https://keycloak host:8443/auth/realms/<keycloak realm>
