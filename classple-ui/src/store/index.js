import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import getters from "./getters";

/**
 *
 * @author zhenwen
 * @date   2020/11/28
 */

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    user,
  },
  getters
})

export default store

