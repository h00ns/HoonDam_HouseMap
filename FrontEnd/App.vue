<template>
  <div id="app">
    <top-header style="z-index:100;"/>
    <transition name="view">
      <router-view />
    </transition>
    <bottom/>
  </div>
</template>

<script>
import TopHeader from '@/layout/Top.vue';
import Bottom from '@/layout/Bottom.vue';

export default {
  name: 'App',
  components: {
    TopHeader,
    Bottom,
  },
};
</script>


<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.view-enter-active, .view-leave-active {
  transition: opacity 1.5s;
}
.view-leave-active {
  position: absolute;
}
.view-enter, .view-leave-to {
  opacity: 0;
}
</style>
