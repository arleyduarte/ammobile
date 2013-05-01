using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using com.cgg.movi2.cripto;

namespace AMMobile.Models
{
    public class ValidadorUsuario
    {
        private EfDbContext db = new EfDbContext();
       
        private Encode encode = new Encode();

        public bool validarUsuario(String login, String clave)
        {


            String claveEncriptada = encode.EncriptarPassword(clave);
            Usuario usuario = getUsuarioByLoginClave(login, claveEncriptada);

            if (usuario != null)
            {
                return true;
            }
            else
            {
                //La clave no esta encriptada
                usuario = getUsuarioByLoginClave(login, clave);
                if (usuario != null)
                {
                    encriptarClave(usuario, claveEncriptada);
                    return true;
                }

                else
                {
                    return false;
                }

            }

        }

        private void encriptarClave(Usuario usuario, String claveEncriptada)
        {
            usuario.Clave = claveEncriptada;

            db.SaveChanges();
            
        }


        public Usuario getUsuarioByLoginClave(String login, String clave)
        {

            String claveEncriptada = encode.EncriptarPassword(clave);

            Usuario item = (from p in db.Usuarios
                        where p.NombreUsuario == login
                        && p.Clave == claveEncriptada
                        && p.Estado == Usuario.ACTIVO
                            select p).SingleOrDefault();

            if (item == null)
            {
                item = (from p in db.Usuarios
                                where p.NombreUsuario == login
                                && p.Clave == clave
                                && p.Estado == Usuario.ACTIVO
                                select p).SingleOrDefault();

            }

            return item;
        }

        public Usuario getUsuarioByLogin(String login)
        {
            Usuario item = (from p in db.Usuarios
                            where p.NombreUsuario == login
                            && p.Estado == Usuario.ACTIVO
                            select p).SingleOrDefault();


            return item;
        }


        public Usuario getUsuarioById(int usuarioId)
        {
            Usuario item = (from p in db.Usuarios
                            where p.UsuarioID == usuarioId
                            
                            select p).SingleOrDefault();


            return item;
        }

        public Usuario getUsuarioByCedula(String cedula)
        {
            Usuario item = (from p in db.Usuarios
                            where p.Cedula == cedula

                            select p).SingleOrDefault();


            return item;
        }

        public Usuario getUsuarioByMovil(String movil)
        {
            try
            {
                Usuario item = (from p in db.Usuarios
                            where p.PIN == movil
                            && p.Estado == Usuario.ACTIVO

                            select p).SingleOrDefault();


            return item;
     
            }catch(Exception e){
                return null;

            }

          }
    }
}