/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        black1: '#OAOAOA',
        black2: '#111111',
        black3: '#222222',
        gray1: '#ECE9E7',
        gray2: '#DCDAD6',
        gray3: '#B1B1B1',
        gray4: '#555555',
        gray5: '#595959',
        gray6: '#777777',
        gray7: '#787878',
        gray8: '#7A7A7A',
        activeBlue: '#0045F6',
      },
    },
  },
  plugins: [],
};
