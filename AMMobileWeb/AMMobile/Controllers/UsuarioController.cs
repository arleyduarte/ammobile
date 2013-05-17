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
    public class UsuarioController : Controller
    {
        private EfDbContext db = new EfDbContext();

        //
        // GET: /Usuario/

        public ViewResult Index()
        {
            var usuarios = from p in db.Usuarios
				         orderby p.PIN
                        select p;
            return View(usuarios.ToList());
        }

        //
        // GET: /Usuario/Details/5

        public ViewResult Details(int id)
        {
            Usuario usuario = db.Usuarios.Find(id);
            return View(usuario);
        }

        //
        // GET: /Usuario/Create
        [Authorize(Roles = "Admin")]
        public ActionResult Create()
        {
            ViewBag.RolNm = new SelectList(db.TipoRoles, "RolNm", "Descripcion");
            return View();
        }

        //
        // POST: /Usuario/Create
        [Authorize(Roles = "Admin")]
        [HttpPost]
        public ActionResult Create(Usuario usuario)
        {

            usuario.FechaCreacion = System.DateTime.Now;
            usuario.Estado = Usuario.ACTIVO;



            if (ModelState.IsValid)
            {
                db.Usuarios.Add(usuario);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.RolNm = new SelectList(db.TipoRoles, "RolNm", "Descripcion", usuario.RolNm);
            return View(usuario);
        }

        //
        // GET: /Usuario/Edit/5
        [Authorize(Roles = "Admin")]
        public ActionResult Edit(int id)
        {
		//reviar edicion usuario
            Usuario usuario = db.Usuarios.Find(id);
            ViewBag.RolNm = new SelectList(db.TipoRoles, "RolNm", "Descripcion", usuario.RolNm);
            return View(usuario);
        }

        //
        // POST: /Usuario/Edit/5
        [Authorize(Roles = "Admin")]
        [HttpPost]
        public ActionResult Edit(Usuario usuario)
        {
            usuario.FechaCreacion = System.DateTime.Now;



            if (ModelState.IsValid)
            {
                db.Entry(usuario).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.RolNm = new SelectList(db.TipoRoles, "RolNm", "Descripcion", usuario.RolNm);
            return View(usuario);
        }

        //
        // GET: /Usuario/Delete/5
        [Authorize(Roles = "Admin")]
        public ActionResult Delete(int id)
        {
            Usuario usuario = db.Usuarios.Find(id);
            return View(usuario);
        }

        //
        // POST: /Usuario/Delete/5
        [Authorize(Roles = "Admin")]
        [HttpPost, ActionName("Delete")]
        public ActionResult DeleteConfirmed(int id)
        {
            Usuario usuario = db.Usuarios.Find(id);
            usuario.Estado = Usuario.INACTIVO;
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }



       //-------------------------------------------- API -------------
        [HttpPost]
        public JsonResult ValidateUser(String login, String password)
        {

            ValidadorUsuario vu = new ValidadorUsuario();
            Status status = new Status();
            Usuario usuario = vu.getUsuarioByLoginClave(login, password);

            if (usuario != null)
            {
                status.Success = true;
                status.Code = usuario.UsuarioID;
                status.Description = usuario.UsuarioID.ToString();
            }

            else
            {
                status.Success = false;
                status.Code = 0;
                status.Description = "User invalid";
            }

            return Json(status, JsonRequestBehavior.AllowGet);
        }
    }
}