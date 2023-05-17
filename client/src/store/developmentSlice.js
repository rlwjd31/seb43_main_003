/* eslint-disable no-param-reassign */
import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';

import { fetchPopularDevelopments, fetchRealTimeDevelopments } from '../api/developInfos';

const initialState = {
  status: 'loading',
  popularRanking: {
    status: 'loading',
    data: [],
  },
  realTimeRanking: {
    status: 'loading',
    data: [],
  },
  allDevelopments: [],
  error: null,
};

export const fetchPopularDevelopmentsAction = createAsyncThunk(
  'fetchPopularDevelopments',
  async (something, rejectWithValue) =>
    fetchPopularDevelopments(something, rejectWithValue),
);

export const fetchRealTimeDevelopmentsAction = createAsyncThunk(
  'fetchRealTimeDevelopments',
  async (something, rejectWithValue) =>
    fetchRealTimeDevelopments(something, rejectWithValue),
);

const developmentsSlice = createSlice({
  name: 'developments',
  initialState,
  extraReducers: builder => {
    builder.addCase(fetchPopularDevelopmentsAction.pending, (state, action) => {
      state.popularRanking.status = 'loading';
    });
    builder.addCase(fetchPopularDevelopmentsAction.fulfilled, (state, action) => {
      state.popularRanking.status = 'success';
      state.popularRanking.data = action.payload.popularRanking;
    });
    builder.addCase(fetchPopularDevelopmentsAction.rejected, (state, action) => {
      state.popularRanking.status = 'failed';
    });
    builder.addCase(fetchRealTimeDevelopmentsAction.pending, (state, action) => {
      state.realTimeRanking.status = 'loading';
    });
    builder.addCase(fetchRealTimeDevelopmentsAction.fulfilled, (state, action) => {
      state.realTimeRanking.status = 'success';
      state.realTimeRanking.data = action.payload.realTimeRanking;
    });
    builder.addCase(fetchRealTimeDevelopmentsAction.rejected, (state, action) => {
      state.realTimeRanking.status = 'failed';
    });
  },
});

export default developmentsSlice;
