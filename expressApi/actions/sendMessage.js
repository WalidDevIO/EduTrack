import { messageApi } from "../apis.js"

export const sendMessage = async (text, student) => {

    const studentNumber = student.id

    const message = {
        text,
        student: studentNumber,
        readed: false
    }

    await fetch(`${messageApi}/post`, {
        method: "POST",
        body: JSON.stringify(message)
    })

}