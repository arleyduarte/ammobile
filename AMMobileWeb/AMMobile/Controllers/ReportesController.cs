using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AMMobile.Models;

namespace AMMobile.Controllers
{
    [Authorize]
    public class ReportesController : Controller
    {
        //
        private EfDbContext db = new EfDbContext();

        [Authorize(Roles = "Admin")]
        public ActionResult ReporteOE()
        {
            var reportesOD = from p in db.ReporteOE
                           where p.ReporteOEID == 0
                           orderby p.FechaRegistro descending
                           select p;

            FiltroReporteOE filtro = new FiltroReporteOE();
            filtro.FechaFinal = System.DateTime.Now;
            filtro.FechaInicial = System.DateTime.Now;
            var model = new BuscadorReporteOEViewModel
            {
                FiltroReporteOE = filtro,
                ReporteOE = reportesOD
            };

            ViewBag.UsuarioID = new SelectList(db.Usuarios.OrderBy(x => x.NombreUsuario), "UsuarioID", "Nombre");
            return View(model);
        }

        [HttpPost]
        [Authorize(Roles = "Admin")]
        public ActionResult ReporteOE(BuscadorReporteOEViewModel viewModel)
        {
            var reportesOD = from p in db.ReporteOE
                             where p.ReporteOEID == 0
                             orderby p.FechaRegistro descending
                             select p;


            if (viewModel.FiltroReporteOE.UsuarioID == 0)
            {
                reportesOD = from p in db.ReporteOE
                             where p.FechaRegistro >= viewModel.FiltroReporteOE.FechaInicial
                             && p.FechaRegistro <= viewModel.FiltroReporteOE.FechaFinal
                             orderby p.FechaRegistro descending
                             select p;
            }
            else
            {
                reportesOD = from p in db.ReporteOE
                             where p.FechaRegistro >= viewModel.FiltroReporteOE.FechaInicial
                             && p.FechaRegistro <= viewModel.FiltroReporteOE.FechaFinal
                             && p.UsuarioID == viewModel.FiltroReporteOE.UsuarioID
                             orderby p.FechaRegistro descending
                             select p;
            }


            FiltroReporteOE filtro = new FiltroReporteOE();
            filtro.FechaFinal = viewModel.FiltroReporteOE.FechaInicial;
            filtro.FechaInicial = viewModel.FiltroReporteOE.FechaFinal;
            


            var model = new BuscadorReporteOEViewModel
            {
                FiltroReporteOE = filtro,
                ReporteOE = reportesOD
            };

            ViewBag.UsuarioID = new SelectList(db.Usuarios.OrderBy(x => x.NombreUsuario), "UsuarioID", "Nombre", viewModel.FiltroReporteOE.UsuarioID);
            return View(model);
        }
    }
}
