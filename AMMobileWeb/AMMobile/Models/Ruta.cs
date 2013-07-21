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
        [Required(ErrorMessage = "*")]		
        public int RutaID { get; set; }
		[Required(ErrorMessage = "*")]
        public int RutaNo { get; set; }

        [DataType(DataType.Date)]
        [Required(ErrorMessage = "*")]
        public DateTime FechaRuta { get; set; }
		[Required(ErrorMessage = "*")]
        public String Direccion { get; set; }
		[Required(ErrorMessage = "*")]		
        public String Barrio { get; set; }
		[Required(ErrorMessage = "*")]		
        public String Departamento { get; set; }
		[Required(ErrorMessage = "*")]		
        public String Ciudad { get; set; }


        public String Referente { get; set; }

        public String Sector { get; set; }
		[Required(ErrorMessage = "*")]		
        public int RutaConsecutivo { get; set; }
		[Required(ErrorMessage = "*")]		
        public String Guia { get; set; }
		[Required(ErrorMessage = "*")]		
        public String E { get; set; }
		[Required(ErrorMessage = "*")]		
        public String Hora { get; set; }
			
        public String NotaOperativa { get; set; }
        public int UsuarioID { get; set; }
        public String NombreUsuario { get; set; }
        public int EstadoRutaID { get; set; }
        public DateTime FechaCreacion { get; set; }

        public String INO { get; set; }
        public String EG { get; set; }
        public String Datos { get; set; }
        public String Causal { get; set; }
        public String NombreControlador { get; set; }
        public DateTime FechaRegistroControlador { get; set; }
        public Boolean ModificadaPorControlador { get; set; }

        public virtual Usuario Usuario { get; set; }
        public virtual EstadoRuta EstadoRuta { get; set; }

    }
}