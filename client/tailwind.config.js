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
        gray2: '#DADADA',
        gray3: '#DCDAD6',
        gray4: '#B1B1B1',
        gray5: '#555555',
        gray6: '#595959',
        gray7: '#777777',
        gray8: '#787878',
        gray9: '#7A7A7A',
        gray10: '#858585',
        gray11: '#717171',
        activeBlue: '#0045F6',
        kakaoYellow: '#FDDC3F',
      },
      fontFamily: {
        'noto-kr': ['Noto Sans KR', 'sans-serif'],
        play: ['Play', 'sans-serif'],
      },
      maxWidth: {
        limit: '1080px',
      },
      boxShadow: {
        around: 'rgba(0, 0, 0, 0.05) 0px 3px 50px',
      },
    },
  },
  plugins: [],
};
