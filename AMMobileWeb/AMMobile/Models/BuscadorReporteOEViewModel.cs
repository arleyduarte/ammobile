using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AMMobile.Models
{
    public class BuscadorReporteOEViewModel
    {
        public FiltroReporteOE FiltroReporteOE { get; set; }
        public IEnumerable<ReporteOE> ReporteOE { get; set; }
    }
}