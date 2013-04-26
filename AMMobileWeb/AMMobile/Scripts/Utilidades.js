

$(document).ready(function () {

    var date = new Date();
    date.setDate(date.getDate() + 5);

    $("table tr:nth-child(even)").addClass("striped");



    $(document).ready(function () {
        var d = new Date();
        var curr_date = d.getDate();
        var curr_month = d.getMonth();
        curr_month++;
        var curr_year = d.getFullYear();
        var dToday = curr_month + "/" + curr_date + "/" + curr_year;



        if ($('#FechaRuta').val() == "") { $('#FechaRuta').attr('value', dToday); }
        $('#FechaRuta').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#FiltroReporteOE_FechaFinal').val() == "") { $('#FiltroReporteOE_FechaFinal').attr('value', dToday); }
        $('#FiltroReporteOE_FechaFinal').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#FiltroReporteOE_FechaInicial').val() == "") { $('#FiltroReporteOE_FechaInicial').attr('value', dToday); }
        $('#FiltroReporteOE_FechaInicial').datepicker({ format: 'mm/dd/yyyy' });

    });


})


