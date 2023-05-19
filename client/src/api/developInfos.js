// TODO: develop VS Prod mode에 따른 프로젝트 환경 셋팅

import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BASE_URL;
const popularRankingEndpoint = 'posts/popular-ranking';
const realTimeRankingEndpoint = 'posts/realtime-ranking';
// const popularRankingEndpoint = 'popularRanking';
// const realTimeRankingEndpoint = 'realTimeRanking';

export const fetchPopularDevelopments = async (something, rejectWithValue) => {
  try {
    const response = await axios.get(`${BASE_URL}/${popularRankingEndpoint}`);
    console.log('url with endpoint popular ->', `${BASE_URL}/${popularRankingEndpoint}`);
    console.log('fetchPopularDevelopments ->', response.data);
    if (response.status >= 200 && response.status < 300) {
      return { popularRanking: response.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export const fetchRealTimeDevelopments = async (something, rejectWithValue) => {
  try {
    const response = await axios.get(`${BASE_URL}/${realTimeRankingEndpoint}`);
    console.log(
      'url with endpoint realTime ->',
      `${BASE_URL}/${realTimeRankingEndpoint}`,
    );
    console.log('fetchRealTimeDevelopments ->', response.data);

    if (response.status >= 200 && response.status < 300) {
      return { realTimeRanking: response.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export default {};
