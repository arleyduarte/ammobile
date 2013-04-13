using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AMMobile.Models;
using Models.BaseEntities;

namespace AMMobile.Controllers
{ 
    public class ReporteOEController : Controller
    {
        private EfDbContext db = new EfDbContext();

        //
        // GET: /ReporteOE/

        public ViewResult Index()
        {
            var reporteoe = db.ReporteOE.Include(r => r.Usuario).Include(r => r.EstadoVisita);
            return View(reporteoe.ToList());
        }

        //
        // GET: /ReporteOE/Details/5

        public ViewResult Details(int id)
        {
            ReporteOE reporteoe = db.ReporteOE.Find(id);
            return View(reporteoe);
        }

        //
        // GET: /ReporteOE/Create

        public ActionResult Create()
        {
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "Cedula");
            ViewBag.EstadoVisitaID = new SelectList(db.EstadoVisita, "EstadoVisitaID", "EstadoNm");
            return View();
        } 

        //
        // POST: /ReporteOE/Create

        [HttpPost]
        public ActionResult Create(ReporteOE reporteoe)
        {
            if (ModelState.IsValid)
            {
                db.ReporteOE.Add(reporteoe);
                db.SaveChanges();
                return RedirectToAction("Index");  
            }

            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "Cedula", reporteoe.UsuarioID);
            ViewBag.EstadoVisitaID = new SelectList(db.EstadoVisita, "EstadoVisitaID", "EstadoNm", reporteoe.EstadoVisitaID);
            return View(reporteoe);
        }
        
        //
        // GET: /ReporteOE/Edit/5
 
        public ActionResult Edit(int id)
        {
            ReporteOE reporteoe = db.ReporteOE.Find(id);
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "Cedula", reporteoe.UsuarioID);
            ViewBag.EstadoVisitaID = new SelectList(db.EstadoVisita, "EstadoVisitaID", "EstadoNm", reporteoe.EstadoVisitaID);
            return View(reporteoe);
        }

        //
        // POST: /ReporteOE/Edit/5

        [HttpPost]
        public ActionResult Edit(ReporteOE reporteoe)
        {
            if (ModelState.IsValid)
            {
                db.Entry(reporteoe).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "Cedula", reporteoe.UsuarioID);
            ViewBag.EstadoVisitaID = new SelectList(db.EstadoVisita, "EstadoVisitaID", "EstadoNm", reporteoe.EstadoVisitaID);
            return View(reporteoe);
        }

        //
        // GET: /ReporteOE/Delete/5
 
        public ActionResult Delete(int id)
        {
            ReporteOE reporteoe = db.ReporteOE.Find(id);
            return View(reporteoe);
        }

        //
        // POST: /ReporteOE/Delete/5

        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {            
            ReporteOE reporteoe = db.ReporteOE.Find(id);
            db.ReporteOE.Remove(reporteoe);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }

        //----------------------------------------- API
        [HttpPost]
        public JsonResult Add(ReporteOE reporte)
        {
            Status status = new Status();
            reporte.FechaRegistro = System.DateTime.Now;
            
            ValidadorUsuario vu = new ValidadorUsuario();
            Usuario usuario = vu.getUsuarioById(reporte.UsuarioID);
            reporte.NombreUsuario = usuario.NombreUsuario;

            try
            {
                db.ReporteOE.Add(reporte);
                db.SaveChanges();
                status.Success = true;
                status.Code = 1;
                status.Description = "" + reporte.ReporteOEID;

                Ruta ruta = db.Ruta.Find(reporte.RutaID);
                ruta.EstadoRutaID = EstadoRuta.RUTA_REALIZADA;
                db.Entry(ruta).State = EntityState.Modified;
                db.SaveChanges();
            }
            catch (Exception e)
            {
                status.Success = false;
                status.Code = 2;
                status.Description = e.ToString();
            }

            return Json(status, JsonRequestBehavior.AllowGet);
        }
    }
}