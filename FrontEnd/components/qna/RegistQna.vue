<template>
  <div id="box"> 
    <b-button @click="$bvModal.show('registForm')">글작성</b-button>

  <b-modal id="registForm" ref="registForm">

    <template #modal-header="">     
      <h5>QnA</h5>
    </template>

    <template #default="">
      <regist-form-qna v-on:isSuccess='process'></regist-form-qna>
    </template>

    <template #modal-footer="{cancel}">      
      <b-button size="sm" variant="outline-danger" @click="cancel()">
        취소
      </b-button>
    </template>

  </b-modal>
  </div>
</template>

<script>
import RegistFormQna from './RegistFormQna.vue';

  export default {
    data() {
      return {
        name: '',
      }
    },
    components: {
      RegistFormQna
    },
    methods: {
      process() {
        this.$refs['registForm'].hide()
        this.$emit('mvFirstPage')
      }
    }
  }
</script>
<style scoped>
  #box {
    margin: 20px 0;
  }
</style>