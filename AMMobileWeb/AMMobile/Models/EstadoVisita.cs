using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class EstadoVisita
    {

        public static int VISITA_EFECTIVA = 1;
        public static int VISITA_NO_EFECTIVA = 2;
        [Key]
        public int EstadoVisitaID { get; set; }
        public String EstadoNm { get; set; }
    }
}