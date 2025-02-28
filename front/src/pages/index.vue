<script setup>
import { useAuthStore } from '@/stores/auth';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const authStore = useAuthStore()
const router = useRouter()

onMounted(() => {
    if(authStore.isLogged) {
        if(authStore.role === "admin") router.push('/admin/dashboard')
        else router.push('/etudiant/dashboard')
    }

    router.push('/login')
})

</script>