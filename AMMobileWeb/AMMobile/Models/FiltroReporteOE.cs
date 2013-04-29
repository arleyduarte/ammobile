using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class FiltroReporteOE
    {

        [Required(ErrorMessage = "*")]
        [DataType(DataType.Date)]
        public DateTime FechaInicial { get; set; }
        [Required(ErrorMessage = "*")]
        [DataType(DataType.Date)]
        public DateTime FechaFinal { get; set; }
        public int UsuarioID { get; set; }
    }
}
