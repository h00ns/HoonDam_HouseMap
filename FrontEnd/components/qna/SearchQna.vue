<template>
    <div id="box">
        <b-container>
            <b-row>
                <b-col cols=3></b-col>
                <b-col>
            <b-input-group class="">
                <template #prepend>
                <b-dropdown :text="searchWord" variant="outline-secondary">
                    <b-dropdown-item @click="changeKey('title')">제목</b-dropdown-item>
                    <b-dropdown-item  @click="changeKey('writer')">작성자</b-dropdown-item>
                    <b-dropdown-item  @click="changeKey('content')">내용</b-dropdown-item>

                </b-dropdown>
                </template>

                <b-form-input type="text" v-model="searchData.word"></b-form-input>

                <template #append>
                    <b-button type="button" @click="search" variant="outline-secondary">검색</b-button>
                </template>
            </b-input-group>
            </b-col>
            <b-col cols=3></b-col>
        </b-row>
        </b-container>

    </div>
</template>

<script>
export default {
    data() {
        return {
            searchData:{
                key: 'title',
                word: '',
                
            },
            searchWord:'제목'
        }
    },
    methods: {
        search() {
            this.$store.dispatch('searchQna',this.searchData)
        },
        changeKey(key) {
            this.searchData.key = key;
            console.log(key)
            if(key == 'title'){
                this.searchWord = '제목'
            }
            if(key == 'writer') {
                this.searchWord = '작성자'
            }
            if(key == 'content') {
                this.searchWord = '내용'
            }
        }
    }
    
}
</script>

<style scoped>
    #box {
        margin-top: 100px;
    }
</style>
