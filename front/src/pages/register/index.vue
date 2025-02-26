<template>
    <LoginForm :auth="register" :registerMode="true"/>
</template>

<script setup>
    const register = (data) => {
        fetch("http://localhost:3000/registerStudent", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(r => {
            if(r.ok) return r.json()
            throw new Error()
        })
        .then(data => {
            alert(JSON.stringify(data))
        })
        .catch(console.error)
    }
</script>