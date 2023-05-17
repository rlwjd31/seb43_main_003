import { configureStore } from '@reduxjs/toolkit';

import popularSlice from './popular';
import realtimeRankingSlice from './realtimeRanking';
import developmentsSlice from './developmentSlice';

const store = configureStore({
  reducer: {
    popular: popularSlice.reducer,
    ranking: realtimeRankingSlice.reducer,
    development: developmentsSlice.reducer,
  },
});

export default store;
