import { configureStore } from '@reduxjs/toolkit';

import developInfos from './developInfos';

const store = configureStore({
  reducer: {
    developInfos: developInfos.reducer,
  },
});

export default store;
