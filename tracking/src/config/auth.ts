import OAuth2Strategy from "passport-oauth2";
import * as process from "process";

const keycloakStrategy = new OAuth2Strategy({
    authorizationURL: process.env.KEYCLOAK_AUTHORIZATION_ENDPOINT ? process.env.KEYCLOAK_AUTHORIZATION_ENDPOINT : 'http://localhost/auth/realms/erp/protocol/openid-connect/auth',
    tokenURL: process.env.KEYCLOAK_TOKEN_ENDPOINT ? process.env.KEYCLOAK_TOKEN_ENDPOINT : 'http://localhost/auth/realms/erp/protocol/openid-connect/token',
    clientID: process.env.KEYCLOAK_CLIENT_ID ? process.env.KEYCLOAK_CLIENT_ID : 'erp-api',
    clientSecret: process.env.KEYCLOAK_CLIENT_SECRET ? process.env.KEYCLOAK_CLIENT_SECRET : '',
}, (accessToken, refreshToken, results, profile, verified) => {
    verified(null, undefined, undefined);
});

export {
    keycloakStrategy
};
