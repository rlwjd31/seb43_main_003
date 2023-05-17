import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  status: 'loading',
  infos: [
    {
      id: 1,
      title: 'Webpack 배우기',
      source: 'velog',
      imageURI:
        'https://velog.velcdn.com/images/rlwjd31/post/b1e71178-a5e1-418a-a3e5-c4bdd0cebaf1/image.png',
      recommendedAvg: 4.59,
      recommends: 120,
      author: 'gijung',
      sorta: 'text',
      sourceUrI: 'https://velog.io/@rlwjd31/webpack-1',
    },
    {
      id: 2,
      title: 'The Ultimate Redux Course 2023 - [LATEST Redux-toolkit]',
      source: 'udemy',
      imageURI: 'https://img-c.udemycdn.com/course/480x270/4965902_1592_5.jpg',
      recommendedAvg: 4.11,
      recommends: 354,
      author: 'yuha',
      sorta: 'video',
      sourceUrI:
        'https://www.udemy.com/course/the-ultimate-redux-course-state-management-library/',
    },
    {
      id: 3,
      title: 'state of 2022 js',
      source: 'docs',
      imageURI:
        'https://devographics.github.io/surveys/state_of_js/js2022/images/js2022-og.png',
      recommendedAvg: 4.82,
      recommends: 231,
      author: 'yoon su',
      sorta: 'text',
      sourceUrI: 'https://2022.stateofjs.com/en-US/libraries/',
    },
  ],
};

const popularSlice = createSlice({
  name: 'popular',
  initialState,
  reducers: {},
});

export default popularSlice;
