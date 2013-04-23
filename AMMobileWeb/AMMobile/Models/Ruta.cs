using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class Ruta
    {
        [Key]
        public int RutaID { get; set; }
        public int RutaNo { get; set; }

        [DataType(DataType.Date)]
        [Required(ErrorMessage = "*")]
        public DateTime FechaRuta { get; set; }

        public String Direccion { get; set; }
        public String Barrio { get; set; }
        public String Departamento { get; set; }
        public String Ciudad { get; set; }
        public String Referente { get; set; }
        public String Sector { get; set; }
        public int RutaConsecutivo { get; set; }
        public String Guia { get; set; }
        public String E { get; set; }
        public String Hora { get; set; }
        public String NotaOperativa { get; set; }
        public int UsuarioID { get; set; }
        public String NombreUsuario { get; set; }
        public int EstadoRutaID { get; set; }
        public DateTime FechaCreacion { get; set; }

        public virtual Usuario Usuario { get; set; }
        public virtual EstadoRuta EstadoRuta { get; set; }

    }
}