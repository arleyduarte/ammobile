using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class ReporteOE
    {
        [Key]
        public int ReporteOEID { get; set; }
        public int RutaID { get; set; }
        public DateTime FechaRegistro { get; set; }
        public String INO { get; set; }
        public String EG { get; set; }
        public String Datos { get; set; }
        public String Causal { get; set; }
        public String GestionOE { get; set; }
        public int UsuarioID { get; set; }
        public String NombreUsuario { get; set; }
        public int EstadoVisitaID { get; set; }

        public virtual Usuario Usuario { get; set; }
        public virtual EstadoVisita EstadoVisita { get; set; }

    }
}