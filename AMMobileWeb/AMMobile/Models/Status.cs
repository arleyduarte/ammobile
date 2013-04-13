using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Models.BaseEntities
{
    public class Status
    {
        public bool Success { get; set; }
        public int Code { get; set; }
        public String Description { get; set; }
    }
}