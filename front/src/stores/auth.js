import { api } from "@/utils/axios";
import { defineStore } from "pinia";
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";

export const useAuthStore = defineStore('authStore', () => {

    onMounted(() => {
        if(localStorage.getItem('token')) {

        }
    })

    const isLogged = ref(false)
    const studentNumber = ref()
    const role = computed(() => studentNumber.value === "admin" ? "admin" : studentNumber.value ? "student" : undefined)
    const router = useRouter()

    const login = async (credentials) => {
        if(isLogged.value) return
        const result = await api.post('/auth/login', credentials)
        .then(r => r.data)
        .then(data => {
            isLogged.value = true
            localStorage.setItem('token', data.token)
            return true
        })
        .catch(err => {
            console.error(err)
            return false
        })

        if(result) {
            studentNumber.value = await api.get('/students/me')
                .then(r => r.data.student.id)
                .catch(err => err.response?.data.detail === "Vous êtes un administrateur" ? "admin" : undefined)
        }

        return result
    }

    const checkConnection = async () => {
        
    }

    const logout = async () => {
        await api.delete(`/auth/logout`).catch(console.error)
        localStorage.removeItem('token')
        isLogged.value = false
        router.push('/login')
    }

    return {
        isLogged,
        studentNumber,
        role,
        login,
        logout
    }

})