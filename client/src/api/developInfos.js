// TODO: develop VS Prod mode에 따른 프로젝트 환경 셋팅

import axios from '../utils/axios';

// const popularRankingEndpoint = 'posts/popular-ranking';
// const realTimeRankingEndpoint = 'posts/realtime-ranking';
const popularRankingEndpoint = 'popularRanking';
const realTimeRankingEndpoint = 'realTimeRanking';

export const fetchPopularDevelopments = async (something, rejectWithValue) => {
  try {
    const response = await axios.get(popularRankingEndpoint);
    console.log(
      'url with endpoint popular ->',
      `${axios.defaults.baseURL}/${popularRankingEndpoint}`,
    );
    console.log('fetchPopularDevelopments ->', response.data.data);
    if (response.status >= 200 && response.status < 300) {
      return { popularRanking: response.data.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export const fetchRealTimeDevelopments = async (something, rejectWithValue) => {
  console.log(`axios.get.baseurl`, axios.defaults.baseURL);
  try {
    const response = await axios.get(`${realTimeRankingEndpoint}`);
    console.log(
      'url with endpoint realTime ->',
      `${axios.defaults.baseURL}/${realTimeRankingEndpoint}`,
    );
    console.log('fetchRealTimeDevelopments ->', response.data.data);

    if (response.status >= 200 && response.status < 300) {
      return { realTimeRanking: response.data.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export default {};
