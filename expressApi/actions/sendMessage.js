import { messageApi } from "../apis.js"

export const sendMessage = async (text, student) => {
    const message = {
        text,
        student,
        readed: false
    }

    await fetch(`${messageApi}/post`, {
        method: "POST",
        body: JSON.stringify(message)
    })

}