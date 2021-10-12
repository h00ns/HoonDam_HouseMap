<script>
import { Bar } from "vue-chartjs";

export default {
   extends: Bar,
  props: ["data", "options"],
  created () {
console.log('cres')
      console.log(this.chartData)
  },
  mounted() {
    this.renderLineChart();
    console.log('mou')
      console.log(this.chartData)
  },
  computed: {
    chartData: function() {
      return this.data;
    }
  },
  methods: {
    renderLineChart() {
    this.renderChart(
      {
        labels: [
            '', '서울시 평균','',
          '선택 매물', '' 
         
        ],
        datasets: [
          {
            label: "평당가",
            backgroundColor: "#f87979",
            data: this.chartData
          }
        ]
      },
      { responsive: true, maintainAspectRatio: false }
    );      
    }
  },
  watch: {
    data() {
        this._data._chart.destroy();
        this.renderLineChart();
    }
  }
};
</script>
