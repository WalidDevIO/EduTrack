import express from 'express';
import cors from 'cors'

import { initDB } from './db/mongo.js';
import { studentRouter } from './subapis/students.js';
import { formationsRouter } from './subapis/formations.js';
import { uesRouter } from './subapis/ues.js';
import { authRouter } from './subapis/auth.js';

await initDB()

const app = express();

app.use(express.json())
app.use(cors())

const apiRouter = express.Router()
apiRouter.use('/students', studentRouter)
apiRouter.use('/formations', formationsRouter)
apiRouter.use('/ues', uesRouter)
apiRouter.use('/auth', authRouter)

app.use('/api', apiRouter)
app.listen(3000, () => {
    console.log("App listening on port 3000")
})