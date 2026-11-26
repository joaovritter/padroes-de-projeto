import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// o proxy manda as chamadas /api para o backend, assim não precisa de CORS
export default defineConfig({
  plugins: [react()],
  server: { proxy: { "/api": "http://localhost:3001" } },
});
