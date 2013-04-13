

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



        if ($('#CreationDate').val() == "") { $('#CreationDate').attr('value', dToday); }
        $('#CreationDate').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#CumpleanosSecretaria').val() == "") { $('#CumpleanosSecretaria').attr('value', dToday); }
        $('#CumpleanosSecretaria').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#Cumpleanos').val() == "") { $('#Cumpleanos').attr('value', dToday); }
        $('#Cumpleanos').datepicker({ format: 'mm/dd/yyyy' });


        if ($('#FechaApertura').val() == "") { $('#FechaApertura').attr('value', dToday); }
        $('#FechaApertura').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#ConversionDate').val() == "") { $('#ConversionDate').attr('value', dToday); }
        $('#ConversionDate').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#BirthDay').val() == "") { $('#BirthDay').attr('value', dToday); }
        $('#BirthDay').datepicker({ format: 'mm/dd/yyyy' });



        if ($('#FiltroFechaUsuario_FechaFinal').val() == "") { $('#FiltroFechaUsuario_FechaFinal').attr('value', dToday); }
        $('#FiltroFechaUsuario_FechaFinal').datepicker({ format: 'mm/dd/yyyy' });

        if ($('#FiltroFechaUsuario_FechaInicial').val() == "") { $('#FiltroFechaUsuario_FechaInicial').attr('value', dToday); }
        $('#FiltroFechaUsuario_FechaInicial').datepicker({ format: 'mm/dd/yyyy' });


    });


})


