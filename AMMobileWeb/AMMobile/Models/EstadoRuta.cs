using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class EstadoRuta
    {

        public static int RUTA_PENDIENTE = 1;
        public static int RUTA_REALIZADA = 2;
        [Key]
        public int EstadoRutaID { get; set; }
        public String EstadoNm { get; set; }
    }
}