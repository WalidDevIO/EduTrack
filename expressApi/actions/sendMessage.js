import { messageApi } from "../apis"

export const sendMessage = async (message, student) => {

    const studentNumber = student.id

    const message = {
        text: message,
        student: studentNumber,
        readed: false
    }

    await fetch(`${messageApi}/post`, {
        method: "POST",
        body: JSON.stringify(message)
    })

}