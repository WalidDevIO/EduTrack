import { messageApi } from "../apis.js"

export const sendMessage = async (text, student) => {
    const message = {
        text,
        student,
        readed: false
    }

    await fetch(`${messageApi}/`, {
        method: "POST",
        body: JSON.stringify(message)
    })

}