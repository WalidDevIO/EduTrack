import express from 'express'
import { registerStudent } from '../routes/students/registerStudent.js';
import { studentInfo } from '../routes/students/studentInfo.js';
import { subscribeFormation } from '../routes/students/subscribeFormation.js';
import { changeGroups } from '../routes/students/changeGroups.js';
import { subscribe, unsubscribe } from '../routes/students/options.js';
import { deleteMessage, getMessages, readMessage } from '../routes/students/messages.js';

const router = express.Router()

router.post('/register', registerStudent);
router.get('/me', studentInfo)
router.post('/subscribe/:id', subscribeFormation)
router.post('/change-groups/:id', changeGroups)
router.post('/ues/subscribe/:id', subscribe)
router.post('/ues/unsubscribe/:id', unsubscribe)
router.get('/messages', getMessages)
router.put('/messages/read', readMessage)
router.delete('/messages/:id', deleteMessage)

export const studentRouter = router;