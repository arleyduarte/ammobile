using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;

namespace AMMobile.Models
{
    public class TipoRol
    {
        [Key]
        public String RolNm { get; set; }
        public String Descripcion { get; set; }
    }
}