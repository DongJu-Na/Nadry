/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      fontSize: {
        '2xs': '11px',
      },
      dropShadow: {
        '2xl': '10px 30px 18px rgba(0, 0, 0, 0.25)',
        '3xl': '0 30px 18px rgba(0, 0, 0, 0.4)',
        top: '0 -3px 5px rgba(0, 0, 0, 0.03)',
      },
      transitionDuration: {
        2000: '2000ms',
      },
    },
  },
  plugins: [],
};
