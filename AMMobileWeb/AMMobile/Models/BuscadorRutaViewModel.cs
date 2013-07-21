using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AMMobile.Models
{
    public class BuscadorRutaViewModel
    {
        public FiltroReporteOE FiltroReporteOE { get; set; }
        public IEnumerable<Ruta> Ruta { get; set; }
    }
}