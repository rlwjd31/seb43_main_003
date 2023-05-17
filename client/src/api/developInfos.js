// TODO: develop VS Prod mode에 따른 프로젝트 환경 셋팅

import axios from 'axios';

// TODO: 임시적으로 json-server를 이용하여 비동기적으로 data 가져오기!!
// const BASE_URL = import.meta.env.VITE_BASE_URL;

export const fetchPopularDevelopments = async (something, rejectWithValue) => {
  const BASE_URL = import.meta.env.VITE_BASE_URL;
  try {
    const response = await axios.get(`${BASE_URL}/popular`);
    if (response.status >= 200 && response.status < 300) {
      return { popularRanking: response.data };
    }
  } catch (err) {
    return rejectWithValue({ error: err.message }); // rejectWithValue적용
  }

  return null;
};

export default {};
