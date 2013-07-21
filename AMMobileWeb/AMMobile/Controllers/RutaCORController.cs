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
    public class RutaCORController : Controller
    {
        //
        private EfDbContext db = new EfDbContext();

        [Authorize(Roles = "Admin, Controlador")]
        public ActionResult RutaCOR()
        {
            var rutaCOR = from p in db.Ruta
                           where p.RutaID == 0
                           orderby p.FechaCreacion descending
                           select p;

            FiltroReporteOE filtro = new FiltroReporteOE();
            filtro.FechaFinal = System.DateTime.Now;
            filtro.FechaInicial = System.DateTime.Now;
            var model = new BuscadorRutaViewModel
            {
                FiltroReporteOE = filtro,
                Ruta = rutaCOR
            };

            var usuarios = (from p in db.Usuarios
                            where p.TipoRol.RolNm == "Auxiliar"
                            select p).OrderBy(x => x.NombreUsuario);

            ViewBag.UsuarioID = new SelectList(usuarios, "UsuarioID", "Nombre");
            return View(model);
        }

        [HttpPost]
        [Authorize(Roles = "Admin, Controlador")]
        public ActionResult RutaCOR(BuscadorRutaViewModel viewModel)
        {
            var rutaCOR = from p in db.Ruta
                          where p.RutaID == 0
                          orderby p.FechaCreacion descending
                          select p;


            DateTime fechaInicial = new DateTime(viewModel.FiltroReporteOE.FechaInicial.Year, viewModel.FiltroReporteOE.FechaInicial.Month, viewModel.FiltroReporteOE.FechaInicial.Day);
            DateTime fechaFinal = new DateTime(viewModel.FiltroReporteOE.FechaFinal.Year, viewModel.FiltroReporteOE.FechaFinal.Month, viewModel.FiltroReporteOE.FechaFinal.Day);
            fechaFinal = fechaFinal.AddHours(23);
            fechaFinal = fechaFinal.AddMinutes(59);

            if (viewModel.FiltroReporteOE.UsuarioID == 0)
            {
                rutaCOR = from p in db.Ruta
                          where p.FechaCreacion >= fechaInicial
                             && p.FechaCreacion <= fechaFinal
                          orderby p.FechaCreacion descending
                          select p;
            }
            else
            {
                rutaCOR = from p in db.Ruta
                          where p.FechaCreacion >= fechaInicial
                             && p.FechaCreacion <= fechaFinal
                             && p.UsuarioID == viewModel.FiltroReporteOE.UsuarioID
                          orderby p.FechaCreacion descending
                          select p;
            }


            FiltroReporteOE filtro = new FiltroReporteOE();
            filtro.FechaFinal = viewModel.FiltroReporteOE.FechaInicial;
            filtro.FechaInicial = viewModel.FiltroReporteOE.FechaFinal;



            var model = new BuscadorRutaViewModel
            {
                FiltroReporteOE = filtro,
                Ruta = rutaCOR
            };

            var usuarios = (from p in db.Usuarios
                          where p.TipoRol.RolNm == "Auxiliar"
                            select p).OrderBy(x => x.NombreUsuario);

            ViewBag.UsuarioID = new SelectList(usuarios, "UsuarioID", "Nombre", viewModel.FiltroReporteOE.UsuarioID);
            return View(model);
        }




        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UpdateCOR(int myModalRutaId, int myModalUsuarioId,
            String myMINO,
            String myMEG,
            String myDatos,
            String myCausal,
            DateTime myMFechaInicio, DateTime myMFechaFinal)
        {

            if (myModalRutaId != 0)
            {
                Ruta rutaEditada = db.Ruta.Find(myModalRutaId);
                rutaEditada.FechaRegistroControlador = System.DateTime.Now;
                rutaEditada.ModificadaPorControlador = true;
                rutaEditada.INO = myMINO;
                rutaEditada.EG = myMEG;
                rutaEditada.Datos = myDatos;
                rutaEditada.Causal = myCausal;
                Usuario usuario = db.Usuarios.Find(myModalUsuarioId);
                rutaEditada.NombreUsuario = usuario.NombreUsuario;

                db.Entry(rutaEditada).State = EntityState.Modified;
                db.SaveChanges();
            }





            FiltroReporteOE filtro = new FiltroReporteOE();
            filtro.FechaFinal = myMFechaFinal;
            filtro.FechaInicial = myMFechaInicio;

            var rutaCOR = from p in db.Ruta
                          where p.RutaID == 0
                          orderby p.FechaCreacion descending
                          select p;

            DateTime fechaInicial = new DateTime(filtro.FechaInicial.Year, filtro.FechaInicial.Month, filtro.FechaInicial.Day);
            DateTime fechaFinal = new DateTime(filtro.FechaFinal.Year, filtro.FechaFinal.Month, filtro.FechaFinal.Day);
            fechaFinal = fechaFinal.AddHours(23);
            fechaFinal = fechaFinal.AddMinutes(59);

            if (filtro.UsuarioID == 0)
            {
                rutaCOR = from p in db.Ruta
                          where p.FechaCreacion >= fechaInicial
                             && p.FechaCreacion <= fechaFinal
                          orderby p.FechaCreacion descending
                          select p;
            }
            else
            {
                rutaCOR = from p in db.Ruta
                          where p.FechaCreacion >= fechaInicial
                             && p.FechaCreacion <= fechaFinal
                             && p.UsuarioID == filtro.UsuarioID
                          orderby p.FechaCreacion descending
                          select p;
            }


            var model = new BuscadorRutaViewModel
            {
                FiltroReporteOE = filtro,
                Ruta = rutaCOR
            };

            var usuarios = (from p in db.Usuarios
                            where p.TipoRol.RolNm == "Auxiliar"
                            select p).OrderBy(x => x.NombreUsuario);

            ViewBag.UsuarioID = new SelectList(usuarios, "UsuarioID", "Nombre", myModalUsuarioId);
            return View("RutaCOR", model);

        }






    }
}
