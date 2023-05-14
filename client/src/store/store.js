import { configureStore } from '@reduxjs/toolkit';

import popularSlice from './popular';
import realtimeRankingSlice from './realtimeRanking';

const store = configureStore({
  reducer: {
    popular: popularSlice.reducer,
    ranking: realtimeRankingSlice.reducer,
  },
});

export default store;
