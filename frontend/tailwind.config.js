/** @type {import('tailwindcss').Config} */
export default {
  darkMode: 'class',
  content: ['./index.html', './src/**/*.{js,jsx}'],
  theme: {
    extend: {
      fontFamily: {
        display: ['Poppins', 'sans-serif'],
        body: ['Manrope', 'sans-serif'],
      },
      colors: {
        ink: '#0f172a',
        mist: '#e2e8f0',
        signal: '#f97316',
        accent: '#10b981',
      },
      boxShadow: {
        soft: '0 20px 45px -24px rgba(15, 23, 42, 0.35)',
      },
      backgroundImage: {
        'hero-mesh': 'radial-gradient(circle at top left, rgba(249,115,22,0.35), transparent 35%), radial-gradient(circle at top right, rgba(16,185,129,0.28), transparent 28%), linear-gradient(135deg, rgba(15,23,42,0.98), rgba(30,41,59,0.92))',
      },
    },
  },
  plugins: [],
}
