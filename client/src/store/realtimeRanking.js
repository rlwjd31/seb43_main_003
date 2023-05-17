import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  status: 'loading',
  infos: [
    {
      id: 4,
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
      id: 5,
      title: '노션 사용자의 98%가 이 기능을 모르고 있다!?',
      source: 'youtube',
      imageURI: 'https://img.youtube.com/vi/XCAwSBdeejU/0.jpg',
      recommendedAvg: 3.24,
      recommends: 231,
      author: 'yuha',
      sorta: 'video',
      sourceUrI:
        'https://www.youtube.com/watch?v=XCAwSBdeejU&ab_channel=%EB%85%B8%EB%A7%88%EB%93%9C%EC%BD%94%EB%8D%94NomadCoders',
    },
    {
      id: 6,
      title: 'Home v6.11.0',
      source: 'docs',
      imageURI: 'https://reactrouter.com/ogimage.png',
      recommendedAvg: 4.24,
      recommends: 211,
      author: 'yoonsu',
      sorta: 'text',
      sourceUrI: 'https://reactrouter.com/en/main',
    },
  ],
};

const realtimeRankingSlice = createSlice({
  name: 'ranking',
  initialState,
  reducers: {},
});

export default realtimeRankingSlice;
