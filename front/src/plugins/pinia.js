import { createPinia } from "pinia";

const pinia = createPinia()

window.__PINIA_DEVTOOLS__ = pinia; // Forcer la détection dans Vue DevTools

export default pinia