using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class RangosHora
    {
        [Key]
        public int RangosHoraId { get; set; }
        public int Inferior { get; set; }
        public int Superior { get; set; }
    }
}