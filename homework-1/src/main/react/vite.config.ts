import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc';
import cp from "rollup-plugin-copy";
import { join} from "node:path"

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), ],
  server: {
    port: 3000
  },
  build: {
    rollupOptions: {
      output: {
        dir: join(__dirname, "../resources/static"),
        entryFileNames: "[name].[hash].js",
        chunkFileNames: "[name].[hash].js",
        assetFileNames: "[name].[hash].[ext]",
      }
    }
  },
})
