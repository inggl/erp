import * as process from "process";
import * as passportJwt from "passport-jwt";
import fs from 'fs';
import path from 'path';

const jwtStrategy = new passportJwt.Strategy({
    issuer: process.env.KEYCLOAK_ISSUER || "http://localhost/auth/realms/erp",
    secretOrKey: fs.readFileSync(path.resolve(__dirname, "../../certs/id_rsa.pub")),
    jwtFromRequest: passportJwt.ExtractJwt.fromAuthHeaderAsBearerToken(),
}, (payload, done) => {
    done(null, payload);
});

export {
    jwtStrategy
};