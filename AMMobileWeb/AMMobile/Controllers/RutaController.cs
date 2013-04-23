using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using AMMobile.Models;
using System.Collections;
using System.IO;
using System.Text;

namespace AMMobile.Controllers
{ 
    public class RutaController : Controller
    {
        private EfDbContext db = new EfDbContext();

        //
        // GET: /Ruta/

        public ViewResult Index()
        {
            DateTime fechaR = System.DateTime.Now;
            var rutas = from p in db.Ruta
                         where p.FechaRuta.Year == fechaR.Year
                        && p.FechaRuta.Month == fechaR.Month
                        && p.FechaRuta.Day == fechaR.Day
                        orderby p.FechaCreacion descending
                        select p;

            return View(rutas);
        }

        public ViewResult Upload()
        {

            return View();
        }

        //
        // GET: /Ruta/Details/5

        public ViewResult Details(int id)
        {
            Ruta ruta = db.Ruta.Find(id);
            return View(ruta);
        }

        //
        // GET: /Ruta/Create

        public ActionResult Create()
        {
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "NombreUsuario");
            ViewBag.EstadoRutaID = new SelectList(db.EstadoRuta, "EstadoRutaID", "EstadoNm");
            return View();
        } 

        //
        // POST: /Ruta/Create

        [HttpPost]
        public ActionResult Create(Ruta ruta)
        {
            ruta.FechaCreacion = System.DateTime.Now;

            if (ModelState.IsValid)
            {
                db.Ruta.Add(ruta);
                db.SaveChanges();
                return RedirectToAction("Index");  
            }

            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "NombreUsuario", ruta.UsuarioID);
            ViewBag.EstadoRutaID = new SelectList(db.EstadoRuta, "EstadoRutaID", "EstadoNm", ruta.EstadoRutaID);
            return View(ruta);
        }
        
        //
        // GET: /Ruta/Edit/5
 
        public ActionResult Edit(int id)
        {
            Ruta ruta = db.Ruta.Find(id);
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "NombreUsuario", ruta.UsuarioID);
            ViewBag.EstadoRutaID = new SelectList(db.EstadoRuta, "EstadoRutaID", "EstadoNm", ruta.EstadoRutaID);
            return View(ruta);
        }

        //
        // POST: /Ruta/Edit/5

        [HttpPost]
        public ActionResult Edit(Ruta ruta)
        {
            ruta.FechaCreacion = System.DateTime.Now;

            if (ModelState.IsValid)
            {
                db.Entry(ruta).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.UsuarioID = new SelectList(db.Usuarios, "UsuarioID", "NombreUsuario", ruta.UsuarioID);
            ViewBag.EstadoRutaID = new SelectList(db.EstadoRuta, "EstadoRutaID", "EstadoNm", ruta.EstadoRutaID);
            return View(ruta);
        }

        //
        // GET: /Ruta/Delete/5
 
        public ActionResult Delete(int id)
        {
            Ruta ruta = db.Ruta.Find(id);
            return View(ruta);
        }

        //
        // POST: /Ruta/Delete/5

        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {            
            Ruta ruta = db.Ruta.Find(id);
            db.Ruta.Remove(ruta);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }


        [HttpPost]
        public ActionResult SendFile(HttpPostedFileBase file)
        {


            if (file != null)
            {
                if (file.ContentLength > 0)
                {
                    var fileName = Path.GetFileName(file.FileName);

                    var path = Path.Combine(Server.MapPath("~/ArchivosSubidos/"), fileName);
                    file.SaveAs(path);

                    StreamReader CsvReader = new StreamReader(path, Encoding.GetEncoding("iso-8859-1"));
                    
                    string inputLine = "";

                    int contador = 0;

                    while ((inputLine = CsvReader.ReadLine()) != null)
                    {
                        var splits = inputLine.Split(',');

                        if (contador != 0 && splits[1].Length != 0)
                        {
             
                            Ruta ruta = new Ruta();
                            ruta.RutaNo = Convert.ToInt32(splits[0]);
                            ruta.FechaRuta = System.DateTime.Now;
                            ruta.Hora = splits[1];
                            ruta.RutaConsecutivo =  Convert.ToInt32(splits[2]);
                            ruta.Guia = splits[3];
                            ruta.Direccion = splits[4];
                            ruta.Barrio = splits[5];
                            ruta.Ciudad = splits[6];
                            ruta.Departamento = splits[7];
                            ruta.Referente = splits[8];
                            ruta.Sector = splits[9];
                            ruta.E = splits[10];
                            ruta.NotaOperativa = splits[11];

                            ValidadorUsuario vu = new ValidadorUsuario();

                            Usuario usuario = vu.getUsuarioByMovil(splits[12]);

                            if (usuario != null)
                            {
                                ruta.UsuarioID = usuario.UsuarioID;
                                ruta.NombreUsuario = usuario.NombreUsuario;
                                ruta.EstadoRutaID = EstadoRuta.RUTA_PENDIENTE;
                                ruta.FechaCreacion = System.DateTime.Now;

                                db.Ruta.Add(ruta);
                                db.SaveChanges();
                            }
     

                        }

                        contador++;
                   

                    }
                    CsvReader.Close();
                }
            }


            return RedirectToAction("Index", "Ruta");
        }
        //-------------------------------------------- API -------------
        [AcceptVerbs(HttpVerbs.Get)]
        public JsonResult Find(int userId)
        {
            ArrayList list = new ArrayList();
            db.Configuration.ProxyCreationEnabled = false;
            DateTime fechaR = System.DateTime.Now;

            var items = from p in db.Ruta
                        where
                        p.UsuarioID == userId
                        &&
                        p.EstadoRutaID == EstadoRuta.RUTA_PENDIENTE
        
                        && p.FechaRuta.Year == fechaR.Year
                        && p.FechaRuta.Month == fechaR.Month
                        && p.FechaRuta.Day == fechaR.Day
                        select p;
            foreach (Ruta item in items)
            {
                list.Add(item);
            }
            return Json(list, JsonRequestBehavior.AllowGet);
        }
    }
}