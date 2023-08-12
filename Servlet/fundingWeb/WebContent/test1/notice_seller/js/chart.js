function f_datachart(p_arg) {
    var v_month = p_arg.month;
    var v_year = p_arg.year;
    var v_data = p_arg.score;

    myChart.data.months = v_month;
    myChart.data.datasets[0].data = v_data;
}