// TODO: develop VS Prod mode에 따른 프로젝트 환경 셋팅

import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BASE_URL;

export const fetchPopularDevelopments = async (something, rejectWithValue) => {
  try {
    const response = await axios.get(`${BASE_URL}/popularRanking`);
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
    const response = await axios.get(`${BASE_URL}/realtimeRanking`);
    if (response.status >= 200 && response.status < 300) {
      return { realTimeRanking: response.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export default {};
