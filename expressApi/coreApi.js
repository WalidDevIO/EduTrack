import express from 'express';
import cors from 'cors'

import { studentRouter } from './students';
import { initDB } from './db/mongo';

await initDB()

const app = express();

app.use(express.json())
app.use(cors())

const apiRouter = express.Router()

apiRouter.use('/students', studentRouter)




app.use('/api', apiRouter)
app.listen(3000, () => {
    console.log("App listening on port 3000")
})