import { studentApi } from "../apis";
import { loggedRoute } from "./loggedRoute"

export const studentInfo = async (req, res) => {
    const answer = await loggedRoute(req, res)
    if(!answer.ok) return;

    const student = await fetch(`${studentApi}/${answer.studentNumber}`).then(r => r.json())
    
}