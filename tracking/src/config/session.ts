import expressSession from 'express-session';

// Create express session
const memoryStore = new expressSession.MemoryStore();

const session = expressSession({
    secret: process.env.KEYCLOAK_SECRET ? process.env.SECRET as string : 'secret',
    resave: false,
    saveUninitialized: true,
    store: memoryStore
});

export default session;