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


            DateTime fechaInicial = new DateTime(viewModel.FiltroReporteOE.FechaInicial.Year, viewModel.FiltroReporteOE.FechaInicial.Month, viewModel.FiltroReporteOE.FechaInicial.Day);
            DateTime fechaFinal = new DateTime(viewModel.FiltroReporteOE.FechaFinal.Year, viewModel.FiltroReporteOE.FechaFinal.Month, viewModel.FiltroReporteOE.FechaFinal.Day);
            fechaFinal = fechaFinal.AddHours(23);
            fechaFinal = fechaFinal.AddMinutes(59);

            if (viewModel.FiltroReporteOE.UsuarioID == 0)
            {
                reportesOD = from p in db.ReporteOE
                             where p.FechaRegistro >= fechaInicial
                             && p.FechaRegistro <= fechaFinal
                             orderby p.FechaRegistro descending
                             select p;
            }
            else
            {
                reportesOD = from p in db.ReporteOE
                             where p.FechaRegistro >= fechaInicial
                             && p.FechaRegistro <= fechaFinal
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
