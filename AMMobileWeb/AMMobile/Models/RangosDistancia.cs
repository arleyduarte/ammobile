using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class RangosDistancia 
    {

        [Key]
        public int RangosDistanciaId { get; set; }
        public int Inferior { get; set; }
        public int Superior { get; set; }
    }
}
