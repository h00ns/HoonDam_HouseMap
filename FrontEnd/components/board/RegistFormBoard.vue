<template>
    <b-container>
        <b-form >
      <b-form-group class="m-5"
        id="input-group-1"
        label="제목"
        label-for="input-1"
      >
        <b-form-input
          id="input-1"
          v-model="form.title"
          placeholder="제목을 입력해주세요."
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group class="m-5" id="input-group-2" label="내용" label-for="input-2">
        <b-form-textarea
        id="input-2"
        v-model="form.content"
        placeholder="내용을 입력해주세요."
        rows="8"
        max-rows="6"
        ></b-form-textarea>
      </b-form-group>
      <div id="formBtn">
      <b-button pill @click="registBoard" variant="success">등록</b-button>
      <b-button pill @click="reset" variant="secondary">초기화</b-button>
      </div>
    </b-form>
  </b-container>
</template>

<script>
  export default {
    data() {
      return {
       form: {
          title: '',
          content: '',
        },
      }
    },
    methods: {
     registBoard() {
         const board = {
             title: this.form.title,
             writer: localStorage.getItem("userId"),
             content: this.form.content,
             key: this.$store.state.searchCondition.key,
             word: this.$store.state.searchCondition.word
         }
        console.log(board);
        this.$store.dispatch('registBoard',board);
        this.form.title = '';
        this.form.name = '';
        this.form.content = '';
        this.$emit('isSuccess')
      },
      reset() {
        this.form.title = '';
        this.form.name = '';
        this.form.content = '';
      },
    }
  }
</script>

<style scoped>
#formBtn{
  text-align: center;
}

#formBtn > button{
  margin: 0 5px;
}
</style>