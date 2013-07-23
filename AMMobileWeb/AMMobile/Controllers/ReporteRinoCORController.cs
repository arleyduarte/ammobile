using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AMMobile.Models;
using System.Web.UI.WebControls;
using System.Data;

namespace AMMobile.Controllers
{
    [Authorize]
    public class ReporteRinoCORController : Controller
    {
        //
        private EfDbContext db = new EfDbContext();

        [Authorize(Roles = "Admin, Controlador")]
        public ActionResult ReporteRino()
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

            var usuarios = (from p in db.Usuarios
                            where p.TipoRol.RolNm == "Auxiliar"
                            select p).OrderBy(x => x.NombreUsuario);

            ViewBag.UsuarioID = new SelectList(usuarios, "UsuarioID", "Nombre");

           
            return View(model);
        }

        [HttpPost]
        [Authorize(Roles = "Admin, Controlador")]
        public ActionResult ReporteRino(BuscadorReporteOEViewModel viewModel)
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

            prepareReport(reportesOD);

            var usuarios = (from p in db.Usuarios
                            where p.TipoRol.RolNm == "Auxiliar"
                            select p).OrderBy(x => x.NombreUsuario);

            ViewBag.UsuarioID = new SelectList(usuarios, "UsuarioID", "Nombre", viewModel.FiltroReporteOE.UsuarioID);


            return View(model);
        }




        private void prepareReport(IOrderedQueryable<Models.ReporteOE> reportesOD)
        {
            DataTable dt = new DataTable();
            DataColumn noDataColumn = new DataColumn("No", typeof(string));
            DataColumn guiaDataColumn = new DataColumn("Guia", typeof(string));
            DataColumn horaAsignacionDataColumn = new DataColumn("Hora_Asignacion", typeof(string));
            DataColumn horaReporteDataColumn = new DataColumn("Hora_Reporte", typeof(string));
            DataColumn movilDataColumn = new DataColumn("Movil", typeof(string));

            DataColumn inoCORDataColumn = new DataColumn("INO COR", typeof(string));
            DataColumn egCORDataColumn = new DataColumn("EG COR", typeof(string));
            DataColumn datosCORDataColumn = new DataColumn("Datos COR", typeof(string));
            DataColumn causalCORDataColumn = new DataColumn("Causal COR", typeof(string));

            DataColumn inoDataColumn = new DataColumn("INO", typeof(string));
            DataColumn egDataColumn = new DataColumn("EG", typeof(string));
            DataColumn datosDataColumn = new DataColumn("Datos", typeof(string));
            DataColumn causalDataColumn = new DataColumn("Causal", typeof(string));


            dt.Columns.Add(noDataColumn);
            dt.Columns.Add(guiaDataColumn);
            dt.Columns.Add(horaAsignacionDataColumn);
            dt.Columns.Add(horaReporteDataColumn);
            dt.Columns.Add(movilDataColumn);

            dt.Columns.Add(inoCORDataColumn);
            dt.Columns.Add(egCORDataColumn);
            dt.Columns.Add(datosCORDataColumn);
            dt.Columns.Add(causalCORDataColumn);

            dt.Columns.Add(inoDataColumn);
            dt.Columns.Add(egDataColumn);
            dt.Columns.Add(datosDataColumn);
            dt.Columns.Add(causalDataColumn);



            DataRow dr;
            foreach (ReporteOE report in reportesOD)
            {
                dr = dt.NewRow();
                dr["No"] = report.Ruta.RutaNo.ToString();
                dr["Guia"] = report.Ruta.Guia;
                dr["Hora_Asignacion"] = report.Ruta.Hora;
                dr["Hora_Reporte"] = report.FechaRegistro.ToShortTimeString();
                dr["Movil"] = report.Ruta.Usuario.PIN;

                dr["INO COR"] = report.Ruta.INO;
                dr["EG COR"] = report.Ruta.EG;
                dr["Datos COR"] = report.Ruta.Datos;
                dr["Causal COR"] = report.Ruta.Causal;
                
                dr["INO"] = report.INO;
                dr["EG"] = report.EG;
                dr["Datos"] = report.Datos;
                dr["Causal"] = report.Causal;

                dt.Rows.Add(dr);
            }

            GridView gv = new GridView();

            gv.DataSource = dt;
            gv.DataBind();
            Session["ReporteRINO"] = gv;
        }


        public ActionResult Download()
        {
            return new DownloadFileActionResult((GridView)Session["ReporteRINO"], "ReporteRINO.xls");
        }
    }
}
