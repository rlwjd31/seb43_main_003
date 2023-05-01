import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  status: 'loading',
  infos: [
    {
      id: 1,
      title: 'Http의 특성과 쿠키, 세션, 토큰',
      source: 'velog',
      imageURI:
        'https://velog.velcdn.com/images/bumsu0211/post/5f856261-83f3-456b-8c76-c0c3bf1bc61c/Untitled%205.png',
      recommendedAvg: 4.49,
      recommends: 100,
      author: 'gijung',
      sorta: 'text',
      sourceUrI:
        'https://velog.io/@bumsu0211/HTTP%EC%9D%98-%ED%8A%B9%EC%84%B1%EA%B3%BC-%EC%BF%A0%ED%82%A4-%EC%84%B8%EC%85%98-%ED%86%A0%ED%81%B0',
    },
    {
      id: 2,
      title: '100초 안에 세션 대 토큰 인증',
      source: 'youtube',
      imageURI: 'https://img.youtube.com/vi/tosLBcAX1vk/0.jpg',
      recommendedAvg: 4.32,
      recommends: 54,
      autho: 'yu ha',
      sorta: 'video',
      sourceUrI:
        'https://www.youtube.com/watch?v=tosLBcAX1vk&t=114s&ab_channel=%EB%85%B8%EB%A7%88%EB%93%9C%EC%BD%94%EB%8D%94NomadCoders',
    },
    {
      id: 3,
      title: 'state of 2022 js',
      source: 'docs',
      imageURI:
        'https://devographics.github.io/surveys/state_of_js/js2022/images/js2022-og.png',
      recommendedAvg: 4.82,
      recommends: 231,
      autho: 'yoon su',
      sorta: 'text',
      sourceUrI: 'https://2022.stateofjs.com/en-US/libraries/',
    },
  ],
};

const developInfos = createSlice({
  name: 'developInfos',
  initialState,
  reducers: {},
});

export default developInfos;
