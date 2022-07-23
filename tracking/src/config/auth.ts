import {Issuer} from "openid-client";

// Create issuer
import process from "process";

const keycloakIssuer = await Issuer.discover(process.env.KEYCLOAK_ISSUER ? process.env.KEYCLOAK_ISSUER : "http://localhost/auth/realms/erp");

// Create client
const client = new keycloakIssuer.Client({
    client_id: process.env.KEYCLOAK_CLIENT_ID ? process.env.KEYCLOAK_CLIENT_ID : 'erp-api',
    client_secret: process.env.KEYCLOAK_CLIENT_ID ? process.env.KEYCLOAK_SECRET : '',
    response_types: ['code']
});

export default client;